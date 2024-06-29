package com.kata.mower.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Models the rectangular lawn with its dimensions
 */
@Getter
@Setter
@AllArgsConstructor
public class Lawn {
    private int width;

    private int height;

    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x <= width && y >= 0 && y <= height;
    }
}
