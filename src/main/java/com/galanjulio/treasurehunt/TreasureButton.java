package com.galanjulio.treasurehunt;

// Extend BoardButton to avoid repeating ourselves with multiple fields
// https://en.wikipedia.org/wiki/Don%27t_repeat_yourself
public class TreasureButton extends BoardButton {

    public TreasureButton(int index) {
        // Call the super method from BoardButton, providing it with an index parameter
        super(index);
    }
}
