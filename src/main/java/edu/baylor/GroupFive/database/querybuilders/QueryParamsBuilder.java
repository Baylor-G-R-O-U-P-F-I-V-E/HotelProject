package edu.baylor.GroupFive.database.querybuilders;

public abstract class QueryParamsBuilder {
    /**
     * Base string for our SQL query
     *
     * i.e. SELECT * FROM table
     * i.e. UPDATE FROM WHERE
     * */
    protected String baseQuery;

    protected void append (StringBuilder builder, String key, Object value) {
    }

    public QueryParamsBuilder(Integer limit_) {
    }

    public String build() {
        return null;
    }
}
