package com.kata.mower.model;

import com.kata.mower.utils.DirectionUtils;
import com.kata.mower.utils.OrientationUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Mower {
    private int x;
    private int y;
    private char orientation;

    public void executeInstructions(String instructions, Lawn lawn) {
        for (char instruction : instructions.toCharArray()) {
            if (instruction == DirectionUtils.RIGHT) {
                turn(DirectionUtils.RIGHT);
            } else if (instruction == DirectionUtils.LEFT) {
                turn(DirectionUtils.LEFT);
            } else if (instruction == 'A') {
                moveForward(lawn);
            }
        }
    }

    public String getPosition() {
        return String.format("%d %d %c", x, y, orientation);
    }

    private void moveForward(Lawn lawn) {
        int newX = x;
        int newY = y;

        switch (orientation) {
            case OrientationUtils.NORTH -> newY++;
            case OrientationUtils.EAST -> newX++;
            case OrientationUtils.SOUTH -> newY--;
            case OrientationUtils.WEST -> newX--;
        }

        if (lawn.isValidPosition(newX, newY)) {
            x = newX;
            y = newY;
        }
    }

    private void turn(char direction) {
        switch (direction) {
            case DirectionUtils.LEFT -> {
                switch (orientation) {
                    case OrientationUtils.NORTH -> setOrientation(OrientationUtils.WEST);
                    case OrientationUtils.EAST -> setOrientation(OrientationUtils.NORTH);
                    case OrientationUtils.SOUTH -> setOrientation(OrientationUtils.EAST);
                    case OrientationUtils.WEST -> setOrientation(OrientationUtils.SOUTH);
                    default -> throw new IllegalStateException("Unexpected orientation: " + orientation);
                }
            }
            case DirectionUtils.RIGHT -> {
                switch (orientation) {
                    case OrientationUtils.NORTH -> setOrientation(OrientationUtils.EAST);
                    case OrientationUtils.EAST -> setOrientation(OrientationUtils.SOUTH);
                    case OrientationUtils.WEST -> setOrientation(OrientationUtils.NORTH);
                    case OrientationUtils.SOUTH -> setOrientation(OrientationUtils.WEST);
                    default -> throw new IllegalStateException("Unexpected orientation: " + orientation);
                }
            }
            default -> throw new IllegalStateException("Unexpected direction: " + direction);
        }
    }
}
