package com.galanjulio.treasurehunt;

import javax.swing.*;

/**
 * Name: Julio Galan
 * CSID: 5685924
 */
public abstract class BoardButton extends JButton {

    // The index of this specific BoardButton
    private int index;

    // Get the text that will be shown after a user clicks on the button
    public abstract String getTextAfterClick();

    // A check to see if this button is a treasure button
    public abstract boolean isTreasure();

    public BoardButton(int index) {
        // Set the index to the one passed from the constructor
        this.index = index;
        // Name the button
        setText("???");

        // Set the button visible
        setVisible(true);
    }

    public JButton getButton() {
        // Return the button belonging to this BoardButton
        // Just return the instance of JButton from this class' superclass
        return this;
    }

    public int getIndex() {
        // Return this panel's index
        return index;
    }
}
