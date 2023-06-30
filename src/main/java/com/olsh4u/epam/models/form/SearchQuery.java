package com.olsh4u.epam.models.form;

import java.util.List;

/**
 * Class is used to build a dynamic search query.
 */
public class SearchQuery {
    private StringBuilder sql;
    private final List<Object> params;

    public SearchQuery(StringBuilder sql, List<Object> params) {
        this.sql = sql;
        this.params = params;
    }

    public StringBuilder getSql() {
        return sql;
    }

    public void setSql(StringBuilder sql) {
        this.sql = sql;
    }

    public List<Object> getParams() {
        return params;
    }

}
