package com.galanjulio.treasurehunt;

/**
 * Name: Julio Galan
 * CSID: 5685924
 */

// Extend BoardButton to avoid repeating ourselves with multiple fields
// https://en.wikipedia.org/wiki/Don%27t_repeat_yourself
public class TreasureButton extends BoardButton {

    @Override
    public String getTextAfterClick() {
        return "$";
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    public TreasureButton(int index) {
        // Call the super method from BoardButton, providing it with an index parameter
        super(index);
    }

    public int getPoints() {
        // Get a random number from 0-2 and add 1
        return Main.RANDOM.nextInt(2) + 1;
    }
}
