package com.zair.data.enums;

public enum Role {
    LIBRARIAN, ADMIN;

    public static final String PREFIX = "ROLE_";

    public static Role of(String withPrefix) {
        return valueOf(withPrefix.replace(PREFIX, ""));
    }
}
