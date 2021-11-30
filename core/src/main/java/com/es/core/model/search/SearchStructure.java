package com.es.core.model.search;

public class SearchStructure {
    private String query;
    private SortField sortField;
    private SortOrder sortOrder;

    public SearchStructure() {

    }

    public SearchStructure(String query, SortField sortField, SortOrder sortOrder) {
        this.query = query;
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public SortField getSortField() {
        return sortField;
    }

    public void setSortField(SortField sortField) {
        this.sortField = sortField;
    }

    public SortOrder getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }
}
