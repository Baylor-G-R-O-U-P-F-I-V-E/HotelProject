package edu.baylor.GroupFive.database.querybuilders;

/**
 * Planned class for building queries with an arbitrary amount of search conditions.
 *
 * @author Icko
 */
public abstract class QueryParamsBuilder {
    /**
     * Base string for our SQL query
     *
     * i.e. SELECT * FROM table
     * i.e. UPDATE FROM WHERE
     * */
    protected String baseQuery;

    /**
     * Append a key value pair to a StringBuilder object
     *
     * @param builder StringBuilder used to make query
     * @param key Query argument
     * @param value Value to match
     * @author Icko
     */
    protected void append (StringBuilder builder, String key, Object value) {
    }

    /**
     * Constructor for our QueryParamsBuilder object.
     *
     * @param limit_ Max number of results to return
     * @author Icko
     */
    public QueryParamsBuilder(Integer limit_) {
    }

    /**
     * Builds our query.
     *
     * @return String output of an SQL query
     * @author Icko
     */
    public String build() {
        return null;
    }
}
