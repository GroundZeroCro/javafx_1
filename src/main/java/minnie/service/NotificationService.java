package minnie.service;

import javafx.scene.media.Media;
import minnie.model.Sound;
import minnie.utils.SoundPlayer;

public class NotificationService {

    private Sound soundModel = new Sound();
    private Media orderReceivedNotification = new Media(this.getClass().getResource("/sounds/notification_softer.mp3").toExternalForm());

    public void startService(int activeOrdersCount) {

        soundModel.setShouldPlaySound(activeOrdersCount);
        if (soundModel.shouldPlaySound()) {
            SoundPlayer.playNotification(orderReceivedNotification);
        }
        // Reset
        soundModel.setPreviousActiveCount(activeOrdersCount);
    }
}
