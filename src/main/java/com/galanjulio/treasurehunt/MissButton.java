package com.galanjulio.treasurehunt;

/**
 * Name: Julio Galan
 * CSID: 5685924
 */

// Extend BoardButton to avoid repeating ourselves with multiple fields
// https://en.wikipedia.org/wiki/Don%27t_repeat_yourself
public class MissButton extends BoardButton {

    @Override
    public String getTextAfterClick() {
        return "O";
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    public MissButton(int index) {
        // Call the super method from BoardButton, providing it with an index parameter
        super(index);
    }

}
