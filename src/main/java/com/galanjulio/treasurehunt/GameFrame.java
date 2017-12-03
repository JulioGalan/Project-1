package com.galanjulio.treasurehunt;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: Julio Galan
 * CSID: 5685924
 */
public class GameFrame extends JFrame {

    // Create the necessary labels (last move, treasures left, treasures found, and tries left)
    private JLabel lastMoveLabel;
    private JLabel treasuresLeftLabel;
    private JLabel treasuresFoundLabel;
    private JLabel triesLeftLabel;

    // Create a List of Panels to store all necessary boardButtons
    private List<BoardButton> boardButtons = new ArrayList<>();

    // Create necessary ints (tries left, treasures left, and treasures found)
    private int triesLeft;
    private int treasuresLeft;
    private int treasuresFound;

    GameFrame(String title) {
        // Let the title of the GUI be set when creating a new instance of this 'GameFrame' class
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

        // Set the starting points for the x and y locations
        int x = 270;
        int y = 80;

        int index = 1;
        for (int i = 0; i < 100; i++) {
            // Create a new boardButton and give it an index parameter of 'i'
            BoardButton boardButton = new BoardButton(i);

            // Add the newly created boardButton to our list
            boardButtons.add(boardButton);

            // Grab the boardButton's button
            JButton button = boardButton.getButton();

            // Add an action listener for this button
            button.addActionListener(event -> {

                // Disable the button so they can't click one button more than once
                button.setEnabled(false);

                // Set the button's text to "$" if the boardButton is a treasure and "O" if it's not
                button.setText(boardButton.isTreasure() ? "$" : "O");

                // Checks if the boardButton is a treasure
                if (boardButton.isTreasure()) {

                    // Decrease the number of treasures left
                    treasuresLeft--;

                    // Increase the number of treasures found
                    treasuresFound++;
                }

                // Decrease the number of tries left
                triesLeft--;

                // Update all labels and if the boardButton is a treasure set the parameter to "Treasure" else to "Miss"
                updateLabels(boardButton.isTreasure() ? "Treasure" : "Miss");

                // Check if the amount of treasures left is 0
                if (getTreasuresLeft() == 0) {
                    // Disable all buttons
                    boardButtons.forEach(panels -> panels.getButton().setEnabled(false));

                    updateLabels("You won");
                    return;
                }

                // Check if they don't have any more tries
                if (triesLeft == 0) {
                    // Disable all buttons
                    boardButtons.forEach(panels -> panels.getButton().setEnabled(false));

                    // Check if the amount of treasures left is greater than one
                    if (getTreasuresLeft() > 1) {

                        // They have no more tries left and there's more treasure to be found, they lost
                        updateLabels("You lose");
                    } else {

                        // They have no more tries left and there's NO more treasure to be found, they won
                        updateLabels("You won");
                    }
                }
            });

            // Set the size of the button
            button.setSize(60, 30);

            // Set the location of this button
            button.setLocation(x, y);

            // Add the boardButton's button to this GUI
            add(button);

            // Set the new x coordinate
            x += 60;

            // Check if the index divided by 4 is equal to 0
            if (index % 10 == 0) {

                // If the above conditional succeeds, increase the y by 30 & set the x back to 270
                y += 30;
                x = 270;
            }

            // Increment the index
            index++;
        }

        // Set the default value of the treasures found (which is always 0 at first)
        // It's not necessary to explicitly state an int to 0 (as it'll default to that) but this makes the project look cleaner
        treasuresFound = 0;

        // Set the tries left to the amount of treasures there are
        triesLeft = getTreasuresLeft();

        // Set the treasures left
        treasuresLeft = getTreasuresLeft();

        // Create the last move label
        lastMoveLabel = new JLabel("Last move: ");
        // Set the location of this label
        lastMoveLabel.setLocation(270, 415);
        // Make sure to set this invisible first since they have no moves made
        lastMoveLabel.setVisible(false);
        // Set the preferred size
        lastMoveLabel.setSize(200, 10);
        // Add the last move label to the GUI
        add(lastMoveLabel);

        // Create the treasures left label
        treasuresLeftLabel = new JLabel("Treasures left: " + treasuresLeft);
        // Set the location of this label
        treasuresLeftLabel.setLocation(35, 110);
        // Set the preferred size
        treasuresLeftLabel.setSize(treasuresLeftLabel.getPreferredSize());
        // Add the treasures left label to the GUI
        add(treasuresLeftLabel);

        // Create the treasures found label
        treasuresFoundLabel = new JLabel("Treasures found: " + treasuresFound);
        // Set the location of this label
        treasuresFoundLabel.setLocation(35, 130);
        // Set the preferred size
        treasuresFoundLabel.setSize(treasuresFoundLabel.getPreferredSize());
        // Add the treasures found label to the GUI
        add(treasuresFoundLabel);

        // Create the tries left label
        triesLeftLabel = new JLabel("Tries left: " + triesLeft);
        // Set the location of this label
        triesLeftLabel.setLocation(35, 150);
        // Set the preferred size
        triesLeftLabel.setSize(triesLeftLabel.getPreferredSize());
        // Add the tries left label to the GUI
        add(triesLeftLabel);
    }

    private void updateLabels(String lastMove) {
        // Simple method that we'll use to update a number inside of the labels

        // Check if we should only add the parameter and not append "Last Move"
        boolean onlyAddParam = lastMove.contains("won") || lastMove.contains("lose");

        // First we update the last move label
        this.lastMoveLabel.setText(onlyAddParam ? lastMove : "Last Move: " + lastMove);
        // Set the last move label visible
        this.lastMoveLabel.setVisible(true);

        // Update the treasures left label
        this.treasuresLeftLabel.setText("Treasures left: " + getTreasuresLeft());

        // Update the treasures found label
        this.treasuresFoundLabel.setText("Treasures found: " + treasuresFound);

        // Update the tries left label
        this.triesLeftLabel.setText("Tries left: " + triesLeft);
    }

    private int getTreasuresLeft() {
        // Start off the amount of treasures left to 0
        int left = 0;

        // Loop through all the boardButtons
        for (BoardButton boardButton : boardButtons) {

            // Check if the boardButton is a treasure AND check if the boardButton's button is enabled
            if (boardButton.isTreasure() && boardButton.getButton().isEnabled()) {

                // Increment the amount of treasures left
                left++;
            }
        }

        // Return the amount of treasures left
        return left;
    }
}
