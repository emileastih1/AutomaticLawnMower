package com.mowitnow.automaticmower.core.domain;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MowerCommandTest {

    @Test
    public void testExecuteInstructions_ShouldMoveMowerCorrectly() {
        Lawn lawn = new Lawn(5, 5);
        Mower mower = new Mower(new Position(1, 2), Orientation.NORTH, lawn);
        List<Instruction> instructions = List.of(
                Instruction.LEFT,
                Instruction.FORWARD,
                Instruction.LEFT,
                Instruction.FORWARD,
                Instruction.LEFT,
                Instruction.FORWARD,
                Instruction.LEFT,
                Instruction.FORWARD,
                Instruction.FORWARD
        );
        MowerCommand mowerCommand = new MowerCommand(mower, instructions);

        mowerCommand.executeInstructions();

        assertEquals(new Position(1, 3), mower.getPosition(), "Mower should end at position (1,3).");
        assertEquals(Orientation.NORTH, mower.getOrientation(), "Mower should be facing NORTH.");
    }

    @Test
    public void testExecuteInstructions_WithBoundaryConditions() {
        Lawn lawn = new Lawn(5, 5);
        Mower mower = new Mower(new Position(0, 0), Orientation.SOUTH, lawn);
        List<Instruction> instructions = List.of(
                Instruction.FORWARD, // Should not move (out of bounds)
                Instruction.LEFT,
                Instruction.FORWARD // Moves east to (1,0)
        );
        MowerCommand mowerCommand = new MowerCommand(mower, instructions);

        mowerCommand.executeInstructions();

        assertEquals(new Position(1, 0), mower.getPosition(), "Mower should end at position (1,0).");
        assertEquals(Orientation.EAST, mower.getOrientation(), "Mower should be facing EAST after turning LEFT from SOUTH.");
    }

    @Test
    public void testExecuteInstructions_EmptyInstructions_ShouldDoNothing() {
        Lawn lawn = new Lawn(5, 5);
        Mower mower = new Mower(new Position(2, 2), Orientation.NORTH, lawn);
        List<Instruction> instructions = List.of();
        MowerCommand mowerCommand = new MowerCommand(mower, instructions);

        mowerCommand.executeInstructions();

        assertEquals(new Position(2, 2), mower.getPosition(), "Mower should remain at the initial position.");
        assertEquals(Orientation.NORTH, mower.getOrientation(), "Mower should maintain initial orientation.");
    }

    @Test
    public void testExecuteInstructions_ComplexSequence() {
        Lawn lawn = new Lawn(5, 5);
        Mower mower = new Mower(new Position(3, 3), Orientation.EAST, lawn);
        List<Instruction> instructions = List.of(
                Instruction.FORWARD,
                Instruction.FORWARD,
                Instruction.RIGHT,
                Instruction.FORWARD,
                Instruction.FORWARD,
                Instruction.RIGHT,
                Instruction.FORWARD,
                Instruction.RIGHT,
                Instruction.RIGHT,
                Instruction.FORWARD
        );
        MowerCommand mowerCommand = new MowerCommand(mower, instructions);

        mowerCommand.executeInstructions();

        assertEquals(new Position(5, 1), mower.getPosition(), "Mower should end at position (5,1).");
        assertEquals(Orientation.EAST, mower.getOrientation(), "Mower should be facing EAST.");
    }
}

