package com.mowitnow.automaticmower.core.domain;

import com.mowitnow.automaticmower.infrastructure.exception.MowerException;

import java.util.Arrays;

public enum Orientation {
    NORTH('N'),
    SOUTH('S'),
    EAST('E'),
    WEST('W');

    private final char code;

    Orientation(char code) {
        this.code = code;
    }

    public char getCode() {
        return this.code;
    }

    public static Orientation getByCode(char code) {
        return Arrays.stream(Orientation.values())
                .filter(orientation -> orientation.getCode() == Character.toUpperCase(code))
                .findAny()
                .orElseThrow(() -> new MowerException("Cannot determine orientation for code: " + code));
    }

    @Override
    public String toString() {
        return String.valueOf(this.getCode());
    }
}