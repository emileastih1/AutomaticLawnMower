package com.mowitnow.automaticmower.infrastructure;

import com.mowitnow.automaticmower.core.domain.Instruction;
import com.mowitnow.automaticmower.core.domain.Lawn;
import com.mowitnow.automaticmower.core.domain.Mower;
import com.mowitnow.automaticmower.core.domain.MowerCommand;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class InputFileParser {

    private final List<MowerCommand> mowerCommands = new ArrayList<>();
    private final FileParserValidator validator = new FileParserValidator();

    public void parseFile(String filePath) throws Exception {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource(filePath).toURI()), StandardCharsets.UTF_8);

        validator.validateLines(lines);

        Lawn lawn = validator.validateAndParseLawn(lines);

        List<String> mowerLines = lines.subList(1, lines.size());
        validator.validateMowerLines(mowerLines);
        processMowerDataInPairs(mowerLines, lawn);
    }

    private void processMowerDataInPairs(List<String> mowerLines, Lawn lawn) {
        IntStream.range(0, mowerLines.size() / 2)
                .forEach(line -> {
                    String mowerPositionLine = mowerLines.get(line * 2);
                    String mowerInstructionsLine = mowerLines.get(line * 2 + 1);

                    Mower mower = validator.validateAndParseMower(line, mowerPositionLine, lawn);

                    List<Instruction> instructions = validator.validateAndParseInstructions(line, mowerInstructionsLine);

                    MowerCommand mowerCommand = new MowerCommand(mower, instructions);
                    mowerCommands.add(mowerCommand);
                });
    }

    public List<MowerCommand> getMowerCommands() {
        return mowerCommands;
    }
}


