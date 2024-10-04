package com.mowitnow.automaticmower.infrastructure;

import com.mowitnow.automaticmower.core.domain.MowerCommand;
import com.mowitnow.automaticmower.core.domain.Orientation;
import com.mowitnow.automaticmower.core.domain.Position;
import com.mowitnow.automaticmower.infrastructure.exception.MowerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("InputFileParser Unit Tests")
class InputFileParserTest {

    private static final String VALID_INPUT_FILE = "valid-input.txt";
    private static final String EMPTY_INPUT_FILE = "empty-input.txt";
    private static final String INVALID_LAWN_DIMENSIONS_FILE = "invalid-lawn-dimensions.txt";
    private static final String MOWER_LINES_NOT_IN_PAIRS_FILE = "mower-lines-not-in-pairs.txt";
    private static final String INVALID_MOWER_POSITION_FILE = "invalid-mower-position.txt";
    private static final String MOWER_POSITION_OUT_OF_BOUNDS_FILE = "mower-position-out-of-bounds.txt";
    private static final String INVALID_ORIENTATION_FILE = "invalid-orientation.txt";
    private static final String EMPTY_INSTRUCTIONS_FILE = "empty-instructions.txt";
    private static final String INVALID_INSTRUCTIONS_FILE = "invalid-instructions.txt";

    private InputFileParser inputFileParser;

    @BeforeEach
    void setup() {
        inputFileParser = new InputFileParser();
    }

    @Nested
    @DisplayName("Valid File Input Tests")
    class ValidInputTests {

        @Test
        @DisplayName("Given a valid input file, when parseFile is called, then it should parse successfully")
        void testValidInputFile() {
            assertDoesNotThrow(() -> inputFileParser.parseFile(VALID_INPUT_FILE));

            List<MowerCommand> mowerCommands = inputFileParser.getMowerCommands();
            assertNotNull(mowerCommands);
            assertEquals(2, mowerCommands.size());

            MowerCommand firstMowerCommand = mowerCommands.getFirst();
            assertEquals(new Position(1, 2), firstMowerCommand.mower().getPosition());
            assertEquals(Orientation.NORTH, firstMowerCommand.mower().getOrientation());
            assertEquals(9, firstMowerCommand.instructions().size());

            MowerCommand secondMowerCommand = mowerCommands.get(1);
            assertEquals(new Position(3, 3), secondMowerCommand.mower().getPosition());
            assertEquals(Orientation.EAST, secondMowerCommand.mower().getOrientation());
            assertEquals(10, secondMowerCommand.instructions().size());
        }
    }

    @Nested
    @DisplayName("Lawn Dimension Validation Tests")
    class LawnDimensionValidationTests {

        @Test
        @DisplayName("Given an empty input file, when parseFile is called, then it should throw MowerException")
        void testEmptyInputFile() {
            MowerException exception = assertThrows(MowerException.class, () -> inputFileParser.parseFile(EMPTY_INPUT_FILE));
            assertEquals("Input file is empty or could not be read.", exception.getMessage());
        }

        @Test
        @DisplayName("Given an input file with invalid lawn dimensions, when parseFile is called, then it should throw MowerException")
        void testInvalidLawnDimensions() {
            MowerException exception = assertThrows(MowerException.class, () -> inputFileParser.parseFile(INVALID_LAWN_DIMENSIONS_FILE));
            assertTrue(exception.getMessage().contains("Invalid lawn dimensions"));
        }
    }

    @Nested
    @DisplayName("Mower Data Validation Tests")
    class MowerDataValidationTests {

        @Test
        @DisplayName("Given an input file with mower data lines not in pairs, when parseFile is called, then it should throw MowerException")
        void testMowerLinesNotInPairs() {
            MowerException exception = assertThrows(MowerException.class, () -> inputFileParser.parseFile(MOWER_LINES_NOT_IN_PAIRS_FILE));
            assertEquals("Mower data lines are not in pairs.", exception.getMessage());
        }

        @Test
        @DisplayName("Given an input file with invalid mower position, when parseFile is called, then it should throw MowerException")
        void testInvalidMowerPosition() {
            MowerException exception = assertThrows(MowerException.class, () -> inputFileParser.parseFile(INVALID_MOWER_POSITION_FILE));
            assertTrue(exception.getMessage().contains("Invalid mower position format"));
        }

        @Test
        @DisplayName("Given an input file with mower position out of bounds, when parseFile is called, then it should throw MowerException")
        void testMowerPositionOutOfBounds() {
            MowerException exception = assertThrows(MowerException.class, () -> inputFileParser.parseFile(MOWER_POSITION_OUT_OF_BOUNDS_FILE));
            assertTrue(exception.getMessage().contains("Mower position out of lawn bounds"));
        }

        @Test
        @DisplayName("Given an input file with invalid orientation, when parseFile is called, then it should throw MowerException")
        void testInvalidOrientation() {
            MowerException exception = assertThrows(MowerException.class, () -> inputFileParser.parseFile(INVALID_ORIENTATION_FILE));
            assertTrue(exception.getMessage().contains("Invalid orientation"));
        }
    }

    @Nested
    @DisplayName("Instruction Validation Tests")
    class InstructionValidationTests {
        @Test
        @DisplayName("Given an input file with empty instructions, when parseFile is called, then it should throw MowerException")
        void testEmptyInstructions() {
            MowerException exception = assertThrows(MowerException.class, () -> inputFileParser.parseFile(EMPTY_INSTRUCTIONS_FILE));
            assertEquals("Mower data lines are not in pairs.", exception.getMessage());
        }

        @Test
        @DisplayName("Given an input file with invalid instructions, when parseFile is called, then it should throw MowerException")
        void testInvalidInstructions() {
            MowerException exception = assertThrows(MowerException.class, () -> inputFileParser.parseFile(INVALID_INSTRUCTIONS_FILE));
            assertTrue(exception.getMessage().contains("Invalid instruction"));
        }
    }
}

