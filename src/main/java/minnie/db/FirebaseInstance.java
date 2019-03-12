package minnie.db;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.IOException;

public class FirebaseInstance {

    public FirebaseApp initialize() {

        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(FirebaseInstance.class.getResourceAsStream("/other/serviceKey.json")))
                    .setDatabaseUrl("https://minnie-fast-food.firebaseio.com/")
                    .build();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        FirebaseApp.initializeApp(options);
        return FirebaseApp.getInstance();
    }
}
