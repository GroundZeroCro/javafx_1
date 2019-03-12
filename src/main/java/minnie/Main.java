package minnie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import minnie.db.FirebaseInstance;

import java.net.URL;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        new FirebaseInstance().initialize();

        Parent root = FXMLLoader.load(Objects.<URL>requireNonNull(this.getClass().getResource("/fxml/main_view.fxml")));
        stage.setTitle("FastFood Manager");
        stage.setScene(new Scene(root, 1100, 800));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

        final Delta dragDelta = new Delta();
        root.setOnMousePressed(mouseEvent -> {
            // record a delta distance for the drag and drop operation.
            dragDelta.x = stage.getX() - mouseEvent.getScreenX();
            dragDelta.y = stage.getY() - mouseEvent.getScreenY();
        });
        root.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() + dragDelta.x);
            stage.setY(mouseEvent.getScreenY() + dragDelta.y);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }


    class Delta {
        double x, y;
    }

}

