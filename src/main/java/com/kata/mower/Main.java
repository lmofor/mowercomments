package com.kata.mower;

import com.kata.mower.model.Lawn;
import com.kata.mower.model.Mower;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Slf4j
public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/input.txt"));

            // Read the lawn dimensions
            int lawnWidth = scanner.nextInt();
            int lawnHeight = scanner.nextInt();
            scanner.nextLine(); // move to next line

            Lawn lawn = new Lawn(lawnWidth, lawnHeight);

            while (scanner.hasNextLine()) {
                // Read mower initial position and orientation
                int initialX = scanner.nextInt();
                int initialY = scanner.nextInt();
                char orientation = scanner.next().charAt(0);
                scanner.nextLine(); // move to next line

                // Read mower instructions
                String instructions = scanner.nextLine();

                // Create mower object
                Mower mower = new Mower(initialX, initialY, orientation);

                // Execute instructions
                mower.executeInstructions(instructions, lawn);

                // Output final position of the mower
                System.out.println(mower.getPosition());
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            log.error("Error: Input file not found {}", e.getMessage(), e);
        }
    }
}