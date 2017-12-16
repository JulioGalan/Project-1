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

    // The menu frame
    private JFrame menuFrame;

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
        // Create a new GameFrame instance and pass it the program name
        gameFrame = new GameFrame(PROGRAM_NAME);

        // Set the game frame invisible for now
        gameFrame.setVisible(false);

        // Create a new MenuFrame instance and pass it the program name
        menuFrame = new MenuFrame(PROGRAM_NAME);

        // Set the menu frame visible
        menuFrame.setVisible(true);

        // Set the menu frame to be the one in front
        menuFrame.toFront();
    }

    public static Main getInstance() {
        // Create a static getter to access an instance of this class
        return instance;
    }

    public JFrame getGameFrame() {
        // Return an instance of our main (game) frame
        return gameFrame;
    }

    public JFrame getMenuFrame() {
        // Return an instance of our menu frame
        return menuFrame;
    }
}
