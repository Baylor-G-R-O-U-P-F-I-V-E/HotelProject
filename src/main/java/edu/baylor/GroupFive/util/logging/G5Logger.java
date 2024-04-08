package edu.baylor.GroupFive.util.logging;

import edu.baylor.GroupFive.database.controllers.ReservationController;
import org.apache.logging.log4j.*;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.appender.rolling.*;
import org.apache.logging.log4j.core.config.*;
import org.apache.logging.log4j.core.config.builder.api.*;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.apache.logging.log4j.core.filter.*;
import org.apache.logging.log4j.core.layout.PatternLayout;

/**
 * Our logging configuration follows the following philosophy:
 * - In Production:
 *   - Users will need to view and send Error logs (warn/error/fatal)
 *   - User access to info logs for high-level event monitoring is necessary.
 * - In Development:
 *   - Debug statements would be nice to follow
 *   - Segregation of
 *
 * Therefore, depending on which version is built (dev/release), different logfiles
 * will be generated.
 * - Prod:
 *   - application.log      > aggregate (from all markers) info logs
 *   - reservations.log     > Reservation Marker info logs
 *   - storefront.log       > Storefront marker info logs
 *   - authentication.log   > Authentication marker info logs
 *   - error.log            > aggregate error logs
 * - Dev:
 *   - All logfiles listed above, plus:
 *   - [marker].debug.log   > debug+ messages from the marker
 *   - *[marker].trace.log  > trace+ messages from the marker
 *
 *   *Tracelogs are disabled by default, even on dev builds. This is because it's
 *    utterly fucking ridiculous how insanely dense the logs would be lmfao.
 *    logger.trace() statements should be placed everywhere throughout the program,
 *    and when a developer requires their use, he should enable that marker's tracelog.
 *    - enabled tracelogs should never make it into a dev-branch commit.
 *
 */
public class G5Logger {
    public static final Logger logger = LogManager.getLogger(G5Logger.class);
    private static Configuration newConfig;
    private static LoggerConfig newRoot;
    private static Level minLogLevel;
    public static final Marker[] markers = {
        MarkerManager.getMarker("RESERVATIONS"),
        MarkerManager.getMarker("DATABASE")
    };
    private static Level getReleaseLogLevel(String[] args){
        if(!(args.length == 0) && args[0].toLowerCase().equals("-debug"))
            return Level.DEBUG;
        else
            return Level.INFO;
    }
    public static void initLogging(String[] args){
        minLogLevel = getReleaseLogLevel(args);

        // Setup new config skeleton
        LoggerContext context = (LoggerContext) LogManager.getContext();
        ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();
        builder.setStatusLevel(Level.WARN);
        builder.setConfigurationName("G5LoggerConfig");
        builder.setLoggerContext(context);
        builder.add(builder.newLogger("edu.baylor.GroupFive.database"));
        builder.add(builder.newRootLogger());
        newConfig = builder.build();
        newRoot = newConfig.getRootLogger();

        // Appender Definitions
        Appender consoleAppender = ConsoleAppender.newBuilder()
            .setLayout(textLayout)
            .setName("_console.console")
            .setTarget(ConsoleAppender.Target.SYSTEM_OUT)
            .build();
        consoleAppender.start();

        // Configure Root Logger - Processes console & aggregate logs
        Appender appLogAppender = createRollingLog(newRoot, "latest", "latest");
        Appender errLogAppender = createRollingLog(newRoot, "latest.error", "error", getLevelFilter(Level.WARN));
        newRoot.addAppender(consoleAppender, minLogLevel, null);

        // Configure FileAppenders for each marker that we track
        for(Marker marker : markers){
            String markerName = marker.getName().toLowerCase();
            createFilteredLog(newRoot, markerName, markerName, Level.INFO); //marker ascertained from filename
            if(minLogLevel == Level.DEBUG){
                createFilteredLog(newRoot, "debug."+markerName, markerName, Level.DEBUG); // explicit marker.
            }
        }
        newRoot.setLevel(minLogLevel);

        LoggerConfig dbLogger = newConfig.getLoggerConfig("edu.baylor.GroupFive.database");
        createRollingLog(dbLogger, "database", "database", getLevelFilter(minLogLevel));
        dbLogger.setLevel(minLogLevel);
        dbLogger.setAdditive(true);
        newConfig.addLogger(dbLogger.getName(), dbLogger);
        // Fire away
        Configurator.reconfigure(newConfig);
    }

    /**
     * Common components
     */
    private static final PatternLayout textLayout = PatternLayout.newBuilder()
        .withPattern("[%d{yyyy-MM-dd}_%d{HH:mm:ss.SSS}][%t]%maxLen{[%level]==}{7}=> %logger{-3}: %msg%n") //[%-5level]
        .build();
    private static final String logDir = "logs";
    private static final String rotLogPrefix = "%d{yyyy-MM-dd}_%i";

    /**
     * Filter Utilities
     */
    private static Filter getLevelFilter(Level level){
        return ThresholdFilter.createFilter(level, Filter.Result.ACCEPT, Filter.Result.DENY);
    }
    private static Filter getMarkerFilter(String marker){
        return MarkerFilter.createFilter(marker, Filter.Result.ACCEPT, Filter.Result.DENY);
    }

    /**
     * FilteredLog generators.
     * markername = rollingName
     */
    private static void createFilteredLog(LoggerConfig logger, String fileName, String markerName, Level level){
        Filter multiFilter = CompositeFilter.createFilters(new Filter[] {
            ThresholdFilter.createFilter(level, Filter.Result.NEUTRAL, Filter.Result.DENY),
            getMarkerFilter(markerName.toUpperCase())
        });
        createRollingLog(logger, fileName, "debug." + markerName, multiFilter);
    }

    /**
     * RollingLog generators
     */
    private static Appender createRollingLog(LoggerConfig logger, String fileName, String rollingName){return createRollingLog(logger, fileName, rollingName, null);}
    private static Appender createRollingLog(LoggerConfig logger, String fileName, String rollingName, Filter filter){
        Appender appender = RollingFileAppender.newBuilder()
            .setLayout(textLayout)
            .setName("_rfile."+fileName)
            .withFileName(logDir+"/"+fileName+".log")
            .withFilePattern(logDir+"/"+rotLogPrefix+"."+rollingName+".log")
            .withStrategy(DefaultRolloverStrategy.newBuilder().withMax("20").build())
            .withPolicy(OnStartupTriggeringPolicy.createPolicy(1))
            .setFilter(filter)
            .build();
        logger.addAppender(appender, null, filter);
        appender.start();
        return appender;
    }
}
