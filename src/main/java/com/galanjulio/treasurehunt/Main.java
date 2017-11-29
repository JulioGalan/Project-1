package com.galanjulio.treasurehunt;

import javax.swing.*;
import java.util.Random;

/**
 * Name: Julio Galan
 * CSID: 5685924
 */
public class Main {

    // Create a static final Random instance
    public static final Random RANDOM = new Random();

    // The program name, static and final since we won't be needing to change it
    private static final String PROGRAM_NAME = "Treasure Hunt v1.0";

    // Create a private static instance of our Main class to be able to access it anywhere
    private static Main instance;

    // The main game frame
    private JFrame gameFrame;

    public static void main(String[] args) {
        // Just making it look a little pretty so it's not default styling
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Call a new instance of the Main class which starts the game
        instance = new Main();
    }

    private Main() {
        // Create a new GameFrame instance and pass it the program name + 5 rows
        gameFrame = new GameFrame(PROGRAM_NAME);

        // Set the game frame visible
        gameFrame.setVisible(true);

        // Set the game frame to be the one in front (in case we want to add other frames in the future)
        gameFrame.toFront();
    }

    public static Main getInstance() {
        // Create a static getter to access an instance of this class
        return instance;
    }

    public JFrame getGameFrame() {
        // Return an instance of our main (game) frame
        return gameFrame;
    }
}
