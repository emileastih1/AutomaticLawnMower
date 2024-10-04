package com.mowitnow.automaticmower.core.domain;

import com.mowitnow.automaticmower.infrastructure.exception.MowerException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InstructionTest {

    @Test
    public void testGetByCode_ValidCode_ShouldReturnInstruction() {
        // Given
        char codeForward = 'A';
        char codeLeft = 'G';
        char codeRight = 'D';

        // When
        Instruction instructionForward = Instruction.getByCode(codeForward);
        Instruction instructionLeft = Instruction.getByCode(codeLeft);
        Instruction instructionRight = Instruction.getByCode(codeRight);

        // Then
        assertEquals(Instruction.FORWARD, instructionForward, "Code 'A' should return Instruction.FORWARD.");
        assertEquals(Instruction.LEFT, instructionLeft, "Code 'G' should return Instruction.LEFT.");
        assertEquals(Instruction.RIGHT, instructionRight, "Code 'D' should return Instruction.RIGHT.");
    }

    @Test
    public void testGetByCode_LowerCaseCode_ShouldReturnInstruction() {
        // Given
        char codeForward = 'a';
        char codeLeft = 'g';
        char codeRight = 'd';

        // When
        Instruction instructionForward = Instruction.getByCode(codeForward);
        Instruction instructionLeft = Instruction.getByCode(codeLeft);
        Instruction instructionRight = Instruction.getByCode(codeRight);

        // Then
        assertEquals(Instruction.FORWARD, instructionForward, "Lowercase 'a' should return Instruction.FORWARD.");
        assertEquals(Instruction.LEFT, instructionLeft, "Lowercase 'g' should return Instruction.LEFT.");
        assertEquals(Instruction.RIGHT, instructionRight, "Lowercase 'd' should return Instruction.RIGHT.");
    }

    @Test
    public void testGetByCode_InvalidCode_ShouldThrowException() {
        // Given
        char invalidCode = 'X';

        // When & Then
        Exception exception = assertThrows(MowerException.class, () -> Instruction.getByCode(invalidCode));
        assertEquals("Cannot determine instruction for code: X", exception.getMessage());
    }

}

