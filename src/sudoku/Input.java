package sudoku;

import processing.core.PApplet;

public class Input {

    public static PApplet parent;
    private static boolean[] wasHeld = new boolean[3];
    private static boolean[] isHeld = new boolean[3];

    /**
     * Runs every frame to update the mouse status of this frame and the previous
     */
    public static void updateInput() {
        for (int i = 0; i < 3; i++) {
            wasHeld[i] = isHeld[i];
            isHeld[i] = parent.mousePressed && parent.mouseButton == Button.values()[i].keycode;
        }
    }

    /**
     * Wraps the default way of getting mouse input with Processing into a neater and more accessible method
     *
     * @param button the mouse button to check
     * @param event  the type of event to check for
     * @return the status of the event and mouse queried
     */
    public static boolean getMouseButton(Button button, Event event) {
        switch (event) {
            case PRESS:
                return !wasHeld[button.ordinal()] && isHeld[button.ordinal()];
            case HELD:
                return isHeld[button.ordinal()];
            case RELEASE:
                return wasHeld[button.ordinal()] && !isHeld[button.ordinal()];
        }

        return false;
    }

    public enum Button {
        LEFT(37), MIDDLE(3), RIGHT(39);

        private final int keycode;

        Button(int keycode) {
            this.keycode = keycode;
        }
    }

    public enum Event {
        PRESS, HELD, RELEASE
    }
}
