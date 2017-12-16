package com.galanjulio.treasurehunt;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: Julio Galan
 * CSID: 5685924
 */
public class GameFrame extends JFrame {

    // Create the necessary labels (last move, treasures left, treasures found, tries left, and points)
    private JLabel lastMoveLabel;
    private JLabel treasuresLeftLabel;
    private JLabel treasuresFoundLabel;
    private JLabel triesLeftLabel;
    private JLabel pointsLabel;

    // Create a List of Panels to store all necessary boardButtons
    private List<BoardButton> boardButtons = new ArrayList<>();

    // Create necessary ints (tries left, treasures left, treasures found, and points)
    private int triesLeft;
    private int treasuresLeft;
    private int treasuresFound;
    private int points;

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

        // Start an index which will be used to correctly space out the buttons
        int index = 1;

        // Instantiate a BoardButton which we'll use inside the for loop
        BoardButton previous = null;

        for (int i = 0; i < 100; i++) {
            // Randomly create a new certain type of button using our static final Random instance from our Main class
            BoardButton boardButton = Main.RANDOM.nextBoolean() ? new TreasureButton(i) : new MissButton(i);

            // Check if the previous button is not null and if it is a treasurebutton
            if (previous != null && previous.isTreasure()) {

                // Set the boardbutton to a minebutton
                boardButton = new MineButton(i);
            }

            // Set the previous
            previous = boardButton;

            // Add the newly created boardButton to our list
            boardButtons.add(boardButton);

            // Grab the boardButton's button
            JButton button = boardButton.getButton();

            // We need a final board button here as it's required for use of lambdas
            // I changed the button above based on a couple conditions in regards to treasure positioning
            // so a final instance of the boardbutton is required here.
            final BoardButton finalBoardButton = boardButton;

            // Add an action listener for this button
            button.addActionListener(event -> {

                // Disable the button so they can't click one button more than once
                button.setEnabled(false);

                // Set the button's text to "$" if the boardButton is a treasure and "O" if it's not
                button.setText(finalBoardButton.getTextAfterClick());

                // Checks if the boardButton is a treasure
                if (finalBoardButton.isTreasure()) {

                    // Cast finalBoardButton to TreasureButton to use its methods
                    TreasureButton treasureButton = (TreasureButton) finalBoardButton;

                    // Decrease the number of treasures left
                    treasuresLeft--;

                    // Increase the number of treasures found
                    treasuresFound++;

                    // Increase the points
                    points += treasureButton.getPoints();
                }

                // Decrease the number of tries left
                triesLeft--;

                // Update all labels and if the boardButton is a treasure set the parameter to "Treasure" else if it's a miss then "Miss" else it's a mine so they lose
                updateLabels(finalBoardButton.isTreasure() ? "Treasure" : finalBoardButton instanceof MissButton ? "Miss" : "You lose");

                // Check if the amount of treasures left is 0
                if (getTreasuresLeft() == 0) {
                    // Disable all buttons
                    boardButtons.forEach(panels -> {
                        panels.getButton().setText(panels.getTextAfterClick());
                        panels.getButton().setEnabled(false);
                    });

                    updateLabels("You won");
                    return;
                }

                // Check if they don't have any more tries OR if they clicked a minebutton
                if (triesLeft == 0 || finalBoardButton instanceof MineButton) {
                    // Disable all buttons
                    boardButtons.forEach(panels -> {
                        panels.getButton().setText(panels.getTextAfterClick());
                        panels.getButton().setEnabled(false);
                    });

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

            // Check if the remainder of (index/10) is 0
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
        triesLeft = giveStartingTreasures();

        // Set the treasures left
        treasuresLeft = giveStartingTreasures();

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

        // Create the points label
        pointsLabel = new JLabel("Points: " + points);
        // Set the location of this label
        pointsLabel.setLocation(35, 170);
        // Set the preferred size
        pointsLabel.setSize(pointsLabel.getPreferredSize());
        // Add the points label to the GUI
        add(pointsLabel);

        // Create a button to go back to the menu
        JButton menuButton = new JButton("Menu");
        // Set the location of the button
        menuButton.setLocation(50, 25);
        // Set the preferred size
        menuButton.setSize(menuButton.getPreferredSize());
        // Set the action of this button
        menuButton.addActionListener(event -> {
            // Set the menu frame visible
            Main.getInstance().getMenuFrame().setVisible(true);
            // Set the menu frame to front
            Main.getInstance().getMenuFrame().toFront();

            // Hide the game frame
            this.setVisible(false);
        });
        // Add the menu button to the GUI
        add(menuButton);
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

        // Update the points label
        this.pointsLabel.setText("Points: " + points);
    }

    private int getTreasuresLeft() {
        // Return the amount of treasures that are left on the board
        return treasuresLeft;
    }

    private int giveStartingTreasures() {
        // Instantiate an int at 0
        int toReturn = 0;

        // Loop through every boardbutton we have
        for (BoardButton boardButton : boardButtons) {

            // Check if the boardbutton is a treasure
            if (boardButton.isTreasure()) {

                // Increment the amount which we'll return
                toReturn++;
            }
        }

        // Return the amount of treasures
        return toReturn;
    }
}
