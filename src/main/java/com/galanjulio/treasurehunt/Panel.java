package com.galanjulio.treasurehunt;

import javax.swing.*;

/**
 * Name: Julio Galan
 * CSID: 5685924
 */
public class Panel {

    // The index of this specific Panel
    private int index;

    // Boolean variable to check if this panel contains a treasure
    private boolean treasure;

    // The button belonging to this Panel
    private JButton button;

    public Panel(int index) {
        // Set the index to the one passed from the constructor
        this.index = index;

        // 50/50 chance of this panel containing a treasure
        // This is assigned from the static final random instance from the main class
        this.treasure = Main.RANDOM.nextBoolean();

        // Create the button from this Panel
        this.button = new JButton("???");

        // Set the button visible
        this.button.setVisible(true);
    }

    public JButton getButton() {
        // Return the button belonging to this Panel
        return button;
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
