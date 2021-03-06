package com.galanjulio.treasurehunt;

/**
 * Name: Julio Galan
 * CSID: 5685924
 */

// Extend BoardButton to avoid repeating ourselves with multiple fields
// https://en.wikipedia.org/wiki/Don%27t_repeat_yourself
public class MineButton extends BoardButton {

    @Override
    public String getTextAfterClick() {
        return "X";
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    public MineButton(int index) {
        // Call the super method from BoardButton, providing it with an index parameter
        super(index);
    }
}
