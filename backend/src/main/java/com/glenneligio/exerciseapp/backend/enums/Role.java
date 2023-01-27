package com.glenneligio.exerciseapp.backend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum Role {
    USER("User"),
    ADMIN("Admin");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Role fromValue(String value) {
        for (Role r : Role.values()) {
            if (r.value.equalsIgnoreCase(value)) {
                return r;
            }
        }
        throw new IllegalArgumentException("No corresponding enum for value " + value);
    }

    @Override
    public String toString() {
        return this.value;
    }
}
