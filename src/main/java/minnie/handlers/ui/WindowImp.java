package minnie.handlers.ui;

import javafx.concurrent.Task;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import minnie.constants.HandlerReferences;
import minnie.model.Window;

public class WindowImp {

    private Stage stage;
    private AnchorPane mainViewParent;
    private Window window = new Window();
    private WindowLogic windowLogic;

    public WindowImp(AnchorPane mainViewParent) {
        this.mainViewParent = mainViewParent;
    }

    public void onClickWindow() {
        window.incrementWIndowsToolHolderHelper();
        // If there is distance between first and second click maximizer will be restarted.
        resetMaximizingCounter();
        if (window.shouldMaximizeScreen()) {
            loadWindowHandler(HandlerReferences.MAXIMIZE);
        }
    }

    // Managing root window screen handlers.
    public void loadWindowHandler(String handlerType) {
        if (stage == null) {
            stage = (Stage) mainViewParent.getScene().getWindow();
        }
        windowLogic = new WindowManager(stage, window).getHandler(handlerType);
        windowLogic.changeWindow();
    }

    private void resetMaximizingCounter() {
        Task task = new Task<Void>() {
            @Override
            protected Void call() throws InterruptedException {
                Thread.sleep(1000);
                window.resetWindowsToolHolderHelper();
                return null;
            }
        };

        Thread thread = new Thread(task);
        thread.start();
    }
}
