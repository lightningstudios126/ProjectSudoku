package main.java.sudoku;

import main.java.sudoku.util.DrawableElement;
import main.java.sudoku.util.Input;
import main.java.sudoku.util.SolarizedColours;
import processing.core.PApplet;

public class DigitBoard extends DrawableElement {
    private static final int sideLength = 60;
    private static final int spacing = 10;

    private int[] digits;

    public int selectedDigit = 1;

    public DigitBoard(PApplet parent) {
        super(parent);

        this.parent = parent;

        digits = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    }


    @Override
    public void update() {
        int cellX;
        int cellY;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                cellX = this.position.x + x * (sideLength + spacing);
                cellY = this.position.y + y * (sideLength + spacing);

                if ((parent.mouseX > cellX && parent.mouseX < cellX + sideLength) &&
                        (parent.mouseY > cellY && parent.mouseY < cellY + sideLength)) {//Selects digit
                    if (selectedDigit != digits[x + y * 3] && Input.getMouseButton(Input.Button.LEFT, Input.Event.PRESS)) {
                        selectedDigit = digits[x + y * 3];
                    }
                }
            }
        }
        // use keyboard
        if (parent.keyPressed && Character.isDigit(parent.key) && parent.key != '0') {//Because of java reading the mouse as a weird input
            selectedDigit = ((int) parent.key - 60 + 12);
        }

        // use scroll wheel
        if (Input.getMouseScroll() != 0) {
            selectedDigit = (selectedDigit + 8 + Input.getMouseScroll()) % 9 + 1;
        }
    }

    @Override
    public void draw() {
        parent.pushStyle();
        parent.textFont(parent.createFont("Consolas", 50, true));

        parent.noStroke();
        int cellX;
        int cellY;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                cellX = this.position.x + x * (sideLength + spacing);
                cellY = this.position.y + y * (sideLength + spacing);

                if (digits[x + y * 3] == selectedDigit) {
                    parent.fill(SolarizedColours.getColour(4));
                    parent.rect(cellX, cellY, sideLength, sideLength);
                } else {
                    parent.fill(SolarizedColours.getColour(3));

                    parent.rect(cellX, cellY, sideLength, sideLength);
                }


                parent.fill(SolarizedColours.getText());
                parent.text(digits[x + y * 3], cellX + sideLength / 4, cellY + sideLength - (sideLength / 4));
            }
        }
        parent.popStyle();
    }
}

