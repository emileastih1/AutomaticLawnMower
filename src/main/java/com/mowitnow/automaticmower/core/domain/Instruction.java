package com.mowitnow.automaticmower.core.domain;

import com.mowitnow.automaticmower.infrastructure.exception.MowerException;

import java.util.Arrays;

public enum Instruction {
    FORWARD('A'),
    LEFT('G'),
    RIGHT('D');

    private final char code;

    Instruction(char code) {
        this.code = code;
    }

    public char getCode() {
        return this.code;
    }

    public static Instruction getByCode(char code) {
        return Arrays.stream(Instruction.values())
                .filter(instruction -> instruction.getCode() == Character.toUpperCase(code))
                .findAny()
                .orElseThrow(() -> new MowerException("Cannot determine instruction for code: " + code));

    }
}
