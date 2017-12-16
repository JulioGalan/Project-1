package com.galanjulio.treasurehunt;

import javax.swing.*;

public class MenuFrame extends JFrame {

    MenuFrame(String title) {
        // Let the title of the GUI be set when creating a new instance of this 'MenuFrame' class
        super(title);

        // Set the size of the GUI
        setSize(1000, 500);

        // Don't let people resize this GUI (messes up location of labels, buttons, and text fields)
        setResizable(false);

        // We don't need the location to be relative to anything, so we set it to null
        setLocationRelativeTo(null);

        // Set the GUI layout to null
        setLayout(null);

        // Set the default close operation to exit when it's closed
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //240,300
        // Create a button to go back to the menu
        JButton menuButton = new JButton("Start Game");
        // Set the location of the button
        menuButton.setLocation(400, 200);
        // Set the preferred size
        menuButton.setSize(menuButton.getPreferredSize());
        // Set the action listener for this button
        menuButton.addActionListener(event -> {
            // Set this frame invisible
            this.setVisible(false);

            // Set the game frame visible and set it to front
            Main.getInstance().getGameFrame().setVisible(true);
            Main.getInstance().getGameFrame().toFront();
        });
        // Add the menu button to the GUI
        add(menuButton);
    }

}
