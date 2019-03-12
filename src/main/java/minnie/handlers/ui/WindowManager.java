package minnie.handlers.ui;

import javafx.stage.Stage;
import minnie.constants.HandlerReferences;
import minnie.model.Window;

public class WindowManager {

    private Stage stage;
    private Window window;

    public WindowManager(Stage stage, Window window) {
        this.stage = stage;
        this.window = window;
    }

    public WindowLogic getHandler(String type) {
        if (type.equals(HandlerReferences.MAXIMIZE)) {
            return new MaximizeHandler(stage, window);
        } else if (type.equals(HandlerReferences.MINIMIZE)) {
            return new MinimizeHandler(stage);
        } else {
            return new TerminateHandler(stage);
        }
    }
}
