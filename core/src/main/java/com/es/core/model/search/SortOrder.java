package com.es.core.model.search;

import java.util.Arrays;

public enum SortOrder {
    ASC, DESC;
    public static SortOrder safeValueOf(String field) {
        return Arrays.stream(values())
                .filter(value -> value.toString().equalsIgnoreCase(field))
                .findAny()
                .orElse(null);
    }
}
