package edu.baylor.GroupFive.database.querybuilders;

/**
 *
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
     *
     * @param builder
     * @param key
     * @param value
     */
    protected void append (StringBuilder builder, String key, Object value) {
    }

    /**
     *
     * @param limit_
     */
    public QueryParamsBuilder(Integer limit_) {
    }

    /**
     *
     * @return
     */
    public String build() {
        return null;
    }
}
