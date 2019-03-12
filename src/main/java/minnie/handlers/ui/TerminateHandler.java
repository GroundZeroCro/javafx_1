package minnie.handlers.ui;

import javafx.stage.Stage;

public class TerminateHandler extends WindowLogic {

    public TerminateHandler(Stage stage) {
        super(stage);
    }

    @Override
    public void changeWindow() {
        stage.close();
    }
}
