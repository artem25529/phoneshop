package com.es.core.model.search;

import java.util.Arrays;
import java.util.Objects;

public enum SortField {
    BRAND, MODEL, DISPLAYSIZEINCHES, PRICE;

    public static SortField saveValueOf(String field) {
        return Arrays.stream(values())
                .filter(value -> value.toString().equalsIgnoreCase(field))
                .findAny()
                .orElse(null);
    }
}
