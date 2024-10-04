package com.mowitnow.automaticmower.core.domain;

public record Position(int x, int y) {
    @Override
    public String toString() {
        return x + " " + y;
    }
}
