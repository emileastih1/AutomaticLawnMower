package com.mowitnow.automaticmower.core.domain;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Mower Unit Tests")
class MowerTest {

    private Lawn lawn;
    private Mower mower;

    @BeforeEach
    void setUp() {
        lawn = new Lawn(5, 5);
    }

    @Nested
    @DisplayName("moveForward Method Tests")
    class MoveForwardTests {

        @Test
        @DisplayName("Given mower facing NORTH within bounds, when moveForward is called, then position y increases by 1")
        void testMoveForwardNorthWithinBounds() {
            mower = new Mower(new Position(2, 2), Orientation.NORTH, lawn);
            mower.moveForward();
            assertEquals(new Position(2, 3), mower.getPosition());
            assertEquals(Orientation.NORTH, mower.getOrientation());
        }

        @Test
        @DisplayName("Given mower at northern edge facing NORTH, when moveForward is called, then position remains the same")
        void testMoveForwardNorthAtEdge() {
            mower = new Mower(new Position(2, 5), Orientation.NORTH, lawn);
            mower.moveForward();
            assertEquals(new Position(2, 5), mower.getPosition());
            assertEquals(Orientation.NORTH, mower.getOrientation());
        }

        @Test
        @DisplayName("Given mower facing EAST within bounds, when moveForward is called, then position x increases by 1")
        void testMoveForwardEastWithinBounds() {
            mower = new Mower(new Position(2, 2), Orientation.EAST, lawn);
            mower.moveForward();
            assertEquals(new Position(3, 2), mower.getPosition());
            assertEquals(Orientation.EAST, mower.getOrientation());
        }

        @Test
        @DisplayName("Given mower at eastern edge facing EAST, when moveForward is called, then position remains the same")
        void testMoveForwardEastAtEdge() {
            mower = new Mower(new Position(5, 2), Orientation.EAST, lawn);
            mower.moveForward();
            assertEquals(new Position(5, 2), mower.getPosition());
            assertEquals(Orientation.EAST, mower.getOrientation());
        }

        @Test
        @DisplayName("Given mower facing SOUTH within bounds, when moveForward is called, then position y decreases by 1")
        void testMoveForwardSouthWithinBounds() {
            mower = new Mower(new Position(2, 2), Orientation.SOUTH, lawn);
            mower.moveForward();
            assertEquals(new Position(2, 1), mower.getPosition());
            assertEquals(Orientation.SOUTH, mower.getOrientation());
        }

        @Test
        @DisplayName("Given mower at southern edge facing SOUTH, when moveForward is called, then position remains the same")
        void testMoveForwardSouthAtEdge() {
            mower = new Mower(new Position(2, 0), Orientation.SOUTH, lawn);
            mower.moveForward();
            assertEquals(new Position(2, 0), mower.getPosition());
            assertEquals(Orientation.SOUTH, mower.getOrientation());
        }

        @Test
        @DisplayName("Given mower facing WEST within bounds, when moveForward is called, then position x decreases by 1")
        void testMoveForwardWestWithinBounds() {
            mower = new Mower(new Position(2, 2), Orientation.WEST, lawn);
            mower.moveForward();
            assertEquals(new Position(1, 2), mower.getPosition());
            assertEquals(Orientation.WEST, mower.getOrientation());
        }

        @Test
        @DisplayName("Given mower at western edge facing WEST, when moveForward is called, then position remains the same")
        void testMoveForwardWestAtEdge() {
            mower = new Mower(new Position(0, 2), Orientation.WEST, lawn);
            mower.moveForward();
            assertEquals(new Position(0, 2), mower.getPosition());
            assertEquals(Orientation.WEST, mower.getOrientation());
        }
    }

    @Nested
    @DisplayName("turnLeft Method Tests")
    class TurnLeftTests {

        @Test
        @DisplayName("Given mower facing NORTH, when turnLeft is called, then orientation changes to WEST")
        void testTurnLeftFromNorth() {
            mower = new Mower(new Position(2, 2), Orientation.NORTH, lawn);
            mower.turnLeft();
            assertEquals(Orientation.WEST, mower.getOrientation());
            assertEquals(new Position(2, 2), mower.getPosition());
        }

        @Test
        @DisplayName("Given mower facing WEST, when turnLeft is called, then orientation changes to SOUTH")
        void testTurnLeftFromWest() {
            mower = new Mower(new Position(2, 2), Orientation.WEST, lawn);
            mower.turnLeft();
            assertEquals(Orientation.SOUTH, mower.getOrientation());
            assertEquals(new Position(2, 2), mower.getPosition());
        }

        @Test
        @DisplayName("Given mower facing SOUTH, when turnLeft is called, then orientation changes to EAST")
        void testTurnLeftFromSouth() {
            mower = new Mower(new Position(2, 2), Orientation.SOUTH, lawn);
            mower.turnLeft();
            assertEquals(Orientation.EAST, mower.getOrientation());
            assertEquals(new Position(2, 2), mower.getPosition());
        }

        @Test
        @DisplayName("Given mower facing EAST, when turnLeft is called, then orientation changes to NORTH")
        void testTurnLeftFromEast() {
            mower = new Mower(new Position(2, 2), Orientation.EAST, lawn);
            mower.turnLeft();
            assertEquals(Orientation.NORTH, mower.getOrientation());
            assertEquals(new Position(2, 2), mower.getPosition());
        }
    }

    @Nested
    @DisplayName("turnRight Method Tests")
    class TurnRightTests {

        @Test
        @DisplayName("Given mower facing NORTH, when turnRight is called, then orientation changes to EAST")
        void testTurnRightFromNorth() {
            mower = new Mower(new Position(2, 2), Orientation.NORTH, lawn);
            mower.turnRight();
            assertEquals(Orientation.EAST, mower.getOrientation());
            assertEquals(new Position(2, 2), mower.getPosition());
        }

        @Test
        @DisplayName("Given mower facing EAST, when turnRight is called, then orientation changes to SOUTH")
        void testTurnRightFromEast() {
            mower = new Mower(new Position(2, 2), Orientation.EAST, lawn);
            mower.turnRight();
            assertEquals(Orientation.SOUTH, mower.getOrientation());
            assertEquals(new Position(2, 2), mower.getPosition());
        }

        @Test
        @DisplayName("Given mower facing SOUTH, when turnRight is called, then orientation changes to WEST")
        void testTurnRightFromSouth() {
            mower = new Mower(new Position(2, 2), Orientation.SOUTH, lawn);
            mower.turnRight();
            assertEquals(Orientation.WEST, mower.getOrientation());
            assertEquals(new Position(2, 2), mower.getPosition());
        }

        @Test
        @DisplayName("Given mower facing WEST, when turnRight is called, then orientation changes to NORTH")
        void testTurnRightFromWest() {
            mower = new Mower(new Position(2, 2), Orientation.WEST, lawn);
            mower.turnRight();
            assertEquals(Orientation.NORTH, mower.getOrientation());
            assertEquals(new Position(2, 2), mower.getPosition());
        }
    }

    @Nested
    @DisplayName("toString Method Tests")
    class ToStringTests {

        @Test
        @DisplayName("Given mower at position (2, 3) facing NORTH, when toString is called, then it returns '2 3 N'")
        void testToString() {
            mower = new Mower(new Position(2, 3), Orientation.NORTH, lawn);
            String result = mower.toString();
            assertEquals("2 3 N", result);
        }
    }

    @Nested
    @DisplayName("Combined Actions Tests")
    class CombinedActionsTests {

        @Test
        @DisplayName("Given mower facing NORTH, when sequence of actions is performed, then final state is correct")
        void testSequenceOfActions() {
            mower = new Mower(new Position(1, 2), Orientation.NORTH, lawn);
            // When
            mower.turnLeft(); // WEST
            mower.moveForward(); // (0,2)
            mower.turnLeft(); // SOUTH
            mower.moveForward(); // (0,1)
            mower.turnLeft(); // EAST
            mower.moveForward(); // (1,1)
            mower.turnLeft(); // NORTH
            mower.moveForward(); // (1,2)

            assertEquals(new Position(1, 2), mower.getPosition());
            assertEquals(Orientation.NORTH, mower.getOrientation());
        }
    }
}
