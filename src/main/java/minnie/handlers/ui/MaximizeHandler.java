package minnie.handlers.ui;

import javafx.stage.Stage;
import minnie.model.Window;

public class MaximizeHandler extends WindowLogic {

    private Window window;

    public MaximizeHandler(Stage stage, Window window) {
        super(stage);
        this.window = window;
    }

    @Override
    public void changeWindow() {
        if (stage.isMaximized()) {
            stage.setMaximized(false);
        } else {
            stage.setMaximized(true);
        }
    }
}
