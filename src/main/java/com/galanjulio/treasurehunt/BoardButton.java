package com.galanjulio.treasurehunt;

import javax.swing.*;

/**
 * Name: Julio Galan
 * CSID: 5685924
 */
public class BoardButton extends JButton {

    // The index of this specific BoardButton
    private int index;

    // Boolean variable to check if this panel contains a treasure
    private boolean treasure;

    public BoardButton(int index) {
        // Set the index to the one passed from the constructor
        this.index = index;

        // 50/50 chance of this panel containing a treasure
        // This is assigned from the static final random instance from the main class
        this.treasure = Main.RANDOM.nextBoolean();

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

    public boolean isTreasure() {
        // Return weather or not this panel is a treasure
        return treasure;
    }
}
