package com.mowitnow.automaticmower.infrastructure;

import com.mowitnow.automaticmower.core.domain.*;
import com.mowitnow.automaticmower.infrastructure.exception.MowerException;

import java.util.List;

class FileParserValidator {

    private static final String SPACE = " ";

    void validateLines(List<String> lines) throws MowerException {
        if (lines == null || lines.isEmpty()) {
            throw new MowerException("Input file is empty or could not be read.");
        }
    }

    Lawn validateAndParseLawn(List<String> lines) throws MowerException {
        String lawnLine = lines.getFirst();
        if (lawnLine == null || lawnLine.isEmpty()) {
            throw new MowerException("Lawn dimensions line is missing.");
        }

        String[] lawnDimensions = lawnLine.split(SPACE);

        if (lawnDimensions.length != 2) {
            throw new MowerException("Invalid lawn dimensions. Expected format: 'X Y'.");
        }

        try {
            int xBoundary = Integer.parseInt(lawnDimensions[0]);
            int yBoundary = Integer.parseInt(lawnDimensions[1]);

            if (xBoundary < 0 || yBoundary < 0) {
                throw new MowerException("Lawn dimensions must be non-negative integers.");
            }

            return new Lawn(xBoundary, yBoundary);
        } catch (NumberFormatException e) {
            throw new MowerException("Lawn dimensions must be integers.", e);
        }
    }

    void validateMowerLines(List<String> mowerLines) throws MowerException {
        if (mowerLines == null || mowerLines.isEmpty()) {
            throw new MowerException("No mower data found in input file.");
        }

        if (mowerLines.size() % 2 != 0) {
            throw new MowerException("Mower data lines are not in pairs.");
        }
    }

    Mower validateAndParseMower(int line, String mowerPositionLine, Lawn lawn) throws MowerException {
        if (mowerPositionLine == null || mowerPositionLine.isEmpty()) {
            throw new MowerException("Mower position line is missing for mower " + line + ".");
        }

        String[] mowerCoordinates = mowerPositionLine.split(SPACE);

        if (mowerCoordinates.length != 3) {
            throw new MowerException("Invalid mower position format at mower " + line + ". Expected format: 'X Y O'.");
        }

        try {
            int x = Integer.parseInt(mowerCoordinates[0]);
            int y = Integer.parseInt(mowerCoordinates[1]);
            char orientationCode = mowerCoordinates[2].charAt(0);

            if (x < 0 || y < 0) {
                throw new MowerException("Mower coordinates must be non-negative integers at mower " + line + ".");
            }

            Position position = new Position(x, y);

            if (!lawn.isWithinBounds(position)) {
                throw new MowerException("Mower position out of lawn bounds at mower " + line + ".");
            }

            Orientation orientation = Orientation.getByCode(orientationCode);

            return new Mower(position, orientation, lawn);

        } catch (NumberFormatException e) {
            throw new MowerException("Mower coordinates must be integers at mower " + line + ".", e);
        } catch (MowerException e) {
            throw new MowerException("Invalid orientation at mower " + line + ": " + e.getMessage());
        }
    }

    List<Instruction> validateAndParseInstructions(int line, String mowerInstructionsLine) throws MowerException {
        if (mowerInstructionsLine == null || mowerInstructionsLine.trim().isEmpty()) {
            throw new MowerException("Instructions are empty for mower " + line + ".");
        }

        try {
            return mowerInstructionsLine.chars().
                    mapToObj(c -> Instruction.getByCode((char) c))
                    .toList();
        } catch (MowerException e) {
            throw new MowerException("Invalid instruction in mower " + line + ": " + e.getMessage());
        }
    }

}
