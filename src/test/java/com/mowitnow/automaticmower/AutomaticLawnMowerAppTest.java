package com.mowitnow.automaticmower;

import com.mowitnow.AutomaticLawnMowerApp;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class AutomaticLawnMowerAppTest {

    @Test
    public void testRunApplication_WithValidInput_ShouldPrintFinalPositions() throws Exception {
        String inputFilePath = "valid-input.txt";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            AutomaticLawnMowerApp.runApplication(inputFilePath);

            String output = outputStream.toString().trim();
            String[] lines = output.split(System.lineSeparator());

            assertEquals(2, lines.length, "Should output two lines for two mowers.");
            assertEquals("1 3 N", lines[0].trim(), "First mower should end at position 1 3 N.");
            assertEquals("5 1 E", lines[1].trim(), "Second mower should end at position 5 1 E.");

        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testRunApplication_WithInvalidInput_ShouldThrowException() {
        String inputFilePath = "invalid-input.txt";

        Exception exception = assertThrows(Exception.class, () -> {
            AutomaticLawnMowerApp.runApplication(inputFilePath);
        });

        assertNotNull(exception, "An exception should be thrown for invalid input.");
        assertTrue(exception.getMessage().contains("Error parsing input file") ||
                exception.getMessage().contains("Invalid"), "Exception message should indicate parsing error.");
    }
}

