package com.mowitnow.automaticmower.core.domain;

public class Mower {
    private final Lawn lawn;
    private Position position;
    private Orientation orientation;

    public Mower(Position position, Orientation orientation, Lawn lawn) {
        this.position = position;
        this.orientation = orientation;
        this.lawn = lawn;
    }

    void moveForward() {
        Position newPosition = calculateNewPosition();
        if (lawn.isWithinBounds(newPosition)) {
            this.position = newPosition;
        }
    }

    void turnLeft() {
        orientation = switch (orientation) {
            case NORTH -> Orientation.WEST;
            case EAST -> Orientation.NORTH;
            case SOUTH -> Orientation.EAST;
            case WEST -> Orientation.SOUTH;
        };
    }

    void turnRight() {
        orientation = switch (orientation) {
            case NORTH -> Orientation.EAST;
            case EAST -> Orientation.SOUTH;
            case SOUTH -> Orientation.WEST;
            case WEST -> Orientation.NORTH;
        };
    }

    private Position calculateNewPosition() {
        return switch (orientation) {
            case NORTH -> new Position(position.x(), position.y() + 1);
            case EAST -> new Position(position.x() + 1, position.y());
            case SOUTH -> new Position(position.x(), position.y() - 1);
            case WEST -> new Position(position.x() - 1, position.y());
        };
    }

    public Position getPosition() {
        return position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return position + " " + orientation;
    }

}



