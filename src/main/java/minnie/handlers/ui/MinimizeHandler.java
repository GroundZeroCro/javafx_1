package minnie.handlers.ui;

import javafx.stage.Stage;

public class MinimizeHandler extends WindowLogic {

    public MinimizeHandler(Stage stage) {
        super(stage);
    }

    @Override
    public void changeWindow() {
        stage.setIconified(true);
    }
}
