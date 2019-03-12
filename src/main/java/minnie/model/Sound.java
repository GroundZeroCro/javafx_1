package minnie.model;

public class Sound {

    // If variable rises, means new order is arrived, sound notification is being played.
    private int previousActiveCount;
    private boolean shouldPlaySound;

    public Sound setShouldPlaySound(int currentActiveCount) {
        this.shouldPlaySound = previousActiveCount < currentActiveCount;
        return this;
    }

    public boolean shouldPlaySound() {
        return shouldPlaySound;
    }

    public Sound setPreviousActiveCount(int previousActiveCount) {
        this.previousActiveCount = previousActiveCount;
        return this;
    }
}
