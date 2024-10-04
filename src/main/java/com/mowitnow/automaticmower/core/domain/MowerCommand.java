package com.mowitnow.automaticmower.core.domain;

import java.util.List;

public record MowerCommand(Mower mower, List<Instruction> instructions) {

    public void executeInstructions() {
        instructions.forEach(this::executeInstruction);
    }

    private void executeInstruction(Instruction instruction) {
        switch (instruction) {
            case FORWARD -> mower.moveForward();
            case LEFT -> mower.turnLeft();
            case RIGHT -> mower.turnRight();
        }
    }
}
