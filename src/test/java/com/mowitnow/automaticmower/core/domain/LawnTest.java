package com.mowitnow.automaticmower.core.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LawnTest {

    @Test
    public void testIsWithinBounds_PositionInsideBounds_ShouldReturnTrue() {

        Lawn lawn = new Lawn(5, 5);
        Position position = new Position(3, 3);

        boolean result = lawn.isWithinBounds(position);
        assertTrue(result, "Position inside bounds should return true.");
    }

    @Test
    public void testIsWithinBounds_PositionOnBoundary_ShouldReturnTrue() {

        Lawn lawn = new Lawn(5, 5);
        Position position = new Position(5, 5);

        boolean result = lawn.isWithinBounds(position);
        assertTrue(result, "Position on boundary should return true.");
    }

    @Test
    public void testIsWithinBounds_PositionOutsideBounds_ShouldReturnFalse() {

        Lawn lawn = new Lawn(5, 5);
        Position position = new Position(6, 5);

        boolean result = lawn.isWithinBounds(position);
        assertFalse(result, "Position outside bounds should return false.");
    }

    @Test
    public void testIsWithinBounds_NegativeCoordinates_ShouldReturnFalse() {

        Lawn lawn = new Lawn(5, 5);
        Position position = new Position(-1, 2);

        boolean result = lawn.isWithinBounds(position);
        assertFalse(result, "Negative coordinates should return false.");
    }

    @Test
    public void testIsWithinBounds_ZeroCoordinates_ShouldReturnTrue() {

        Lawn lawn = new Lawn(5, 5);
        Position position = new Position(0, 0);

        boolean result = lawn.isWithinBounds(position);
        assertTrue(result, "Zero coordinates should return true.");
    }
}
