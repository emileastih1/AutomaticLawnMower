package com.mowitnow;

import com.mowitnow.automaticmower.core.services.MowerService;
import com.mowitnow.automaticmower.infrastructure.InputFileParser;

public class AutomaticLawnMowerApp {

    private static final String DEFAULT_MOWING_FILE_INSTRUCTIONS = "mowing-instructions.txt";

    public static void main(String[] args) {
        try {
            String inputFilePath;

            if (args.length > 0 && args[0] != null && !args[0].isEmpty()) {
                inputFilePath = args[0];
            } else {
                inputFilePath = DEFAULT_MOWING_FILE_INSTRUCTIONS;
            }
            runApplication(inputFilePath);

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public static void runApplication(String inputFilePath) throws Exception {
        InputFileParser parser = new InputFileParser();
        parser.parseFile(inputFilePath);

        MowerService service = new MowerService();
        service.runMowersSequentially(parser.getMowerCommands());
    }
}
