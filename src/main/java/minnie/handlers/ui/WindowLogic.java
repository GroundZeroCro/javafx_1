package minnie.handlers.ui;

import javafx.stage.Stage;

abstract public class WindowLogic {

    protected Stage stage;

    public WindowLogic(Stage stage) {
        this.stage = stage;
    }

    public abstract void changeWindow();
}
