package minnie.handlers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.ListView;
import javafx.util.Duration;

import java.util.List;


public class ListViewAutoRefresher {

    private List<ListView> lists;

    public ListViewAutoRefresher(List<ListView> lists) {
        this.lists = lists;
    }

    private KeyFrame keyFrame;

    public void setKeyFrame() {
        System.out.println("Refreshed list");
        for (ListView list : lists) {
            list.refresh();
        }
    }

    public void startHandler() {

        keyFrame = new KeyFrame(
                Duration.millis(2500),
                ae -> setKeyFrame());

        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


}
