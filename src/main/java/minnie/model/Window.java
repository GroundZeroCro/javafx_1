package minnie.model;

public class Window {

    // Counting the amount of time windowsToolHolder has been pressed.
    // If it's been pressed 2 times, screen is maximized (0/1)
    // Value resets after period.
    private int windowsToolHolderHelper;

    public Window() {
        this.windowsToolHolderHelper = 0;
    }

    public int getWindowsToolHolderHelper() {
        return windowsToolHolderHelper;
    }

    public void resetWindowsToolHolderHelper() {
        this.windowsToolHolderHelper = 0;
    }

    public void incrementWIndowsToolHolderHelper() {
        this.windowsToolHolderHelper ++;
    }

    public boolean shouldMaximizeScreen() {
        return windowsToolHolderHelper >= 2;
    }
}
