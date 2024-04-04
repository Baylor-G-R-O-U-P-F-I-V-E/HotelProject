package edu.baylor.GroupFive.util.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.StringLayout;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.apache.logging.log4j.core.layout.PatternLayout;

public class G5Logger {
    public static final Logger logger = LogManager.getLogger(G5Logger.class);
    public static void initLogging(){
//        ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();
//
//        PatternLayout layout = PatternLayout.newBuilder().withPattern("%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n").build();
//
//        AppenderComponentBuilder console = builder.newAppender("stdout", "Console");
//        builder.add(console);
//
//        AppenderComponentBuilder rootFile = builder.newAppender("fileLog", "MainFile");
//        rootFile.addAttribute("fileName", "logs/application.log");
//        rootFile.addAttribute("filePattern", "rolling-%d{MM-dd-yy}.log");
//        builder.add(rootFile);
    }

}
