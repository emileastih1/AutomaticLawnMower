package com.mowitnow.automaticmower.core.services;

import com.mowitnow.automaticmower.core.domain.MowerCommand;

import java.util.List;

public class MowerService {
    public void runMowersSequentially(List<MowerCommand> mowerCommands) {
        mowerCommands.stream()
                .peek(MowerCommand::executeInstructions)
                .map(MowerCommand::mower)
                .forEach(System.out::println);
    }
}

