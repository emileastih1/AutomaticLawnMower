package com.mowitnow.automaticmower.core.services;

import com.mowitnow.automaticmower.core.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MowerServiceTest {

    List<MowerCommand> mowerCommands = new ArrayList<>();

    @BeforeEach
    void setUp() {
        mowerCommands = getMowerCommands();
    }

    @Test
    public void testRunMowersSequentially_ShouldExecuteCommandsAndPrintFinalPositions() {
        MowerService mowerService = new MowerService();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            mowerService.runMowersSequentially(mowerCommands);

            String output = outputStream.toString().trim();
            String[] lines = output.split(System.lineSeparator());

            assertEquals(2, lines.length, "Should output two lines for two mowers.");

            assertEquals("1 3 N", lines[0].trim(), "First mower should end at position 1 3 N.");
            assertEquals("5 1 E", lines[1].trim(), "Second mower should end at position 5 1 E.");

        } finally {
            System.setOut(originalOut);
        }
    }

    private static List<MowerCommand> getMowerCommands() {
        Lawn lawn = new Lawn(5, 5);

        List<Instruction> instructions1 = List.of(Instruction.LEFT, Instruction.FORWARD, Instruction.LEFT, Instruction.FORWARD, Instruction.LEFT, Instruction.FORWARD, Instruction.LEFT, Instruction.FORWARD, Instruction.FORWARD);
        MowerCommand mowerCommand1 = getMowerCommand(new Position(1, 2), Orientation.NORTH, instructions1, lawn);

        List<Instruction> instructions2 = List.of(Instruction.FORWARD, Instruction.FORWARD, Instruction.RIGHT, Instruction.FORWARD, Instruction.FORWARD, Instruction.RIGHT, Instruction.FORWARD, Instruction.RIGHT, Instruction.RIGHT, Instruction.FORWARD);
        MowerCommand mowerCommand2 = getMowerCommand(new Position(3, 3), Orientation.EAST, instructions2, lawn);

        return List.of(mowerCommand1, mowerCommand2);
    }

    private static MowerCommand getMowerCommand(Position position, Orientation orientation, List<Instruction> instructions, Lawn lawn) {
        Mower mower = new Mower(position, orientation, lawn);
        return new MowerCommand(mower, instructions);
    }
}
