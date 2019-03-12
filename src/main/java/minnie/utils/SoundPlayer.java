package minnie.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundPlayer {

    public static MediaPlayer mediaPlayer;

    public static void playNotification(Media notification) {
        mediaPlayer = new MediaPlayer(notification);
        mediaPlayer.play();
    }
}
