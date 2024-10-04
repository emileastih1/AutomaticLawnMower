package com.mowitnow.automaticmower.core.domain;

public record Lawn(int xBoundary, int yBoundary) {
    public boolean isWithinBounds(Position position) {
        int x = position.x();
        int y = position.y();
        return x >= 0 && x <= xBoundary && y >= 0 && y <= yBoundary;
    }
}

