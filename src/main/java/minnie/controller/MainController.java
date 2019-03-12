package minnie.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import minnie.adapters.ActiveAdapter;
import minnie.adapters.PassiveAdapter;
import minnie.adapters.SummaryAdapter;
import minnie.business.EarningSummary;
import minnie.constants.HandlerReferences;
import minnie.handlers.ListViewAutoRefresher;
import minnie.handlers.ui.WindowImp;
import minnie.model.Order;
import minnie.service.FirebaseService;
import minnie.service.NotificationService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static java.util.Arrays.asList;

public class MainController implements Initializable {

    private ObservableList activeItemsObservable = FXCollections.observableArrayList();
    private ObservableList passiveItemsObservable = FXCollections.observableArrayList();
    private ObservableList monthlyEarningsObservable = FXCollections.observableArrayList();

    private FirebaseService firebaseService;
    private NotificationService notificationService = new NotificationService();
    private EarningSummary earningSummary = new EarningSummary();

    @FXML
    private AnchorPane mainViewParent;
    @FXML
    private HBox windowToolHolder;
    @FXML
    private ListView activeOrdersList, passiveOrdersList, earningsPerMonthList;
    @FXML
    private TextField deliveryTime;
    @FXML
    private Button changeDeliveryTime;
    @FXML
    private ImageView closeApplicationButton, minimizeApplicationButton, maximizeApplicationButton;
    @FXML
    private Label deliveryChangeNotification;

    public MainController() {
        firebaseService = new FirebaseService(MainController.this);
    }

    // Active and finished orders separated.
    public void updateOrders(List<Order> activeOrders, List<Order> passiveOrders) {

        notificationService.startService(activeOrders.size());

        sortOrders(activeOrders);
        sortOrders(passiveOrders);

        System.out.println(passiveOrders);
        activeItemsObservable.addAll(activeOrders);
        passiveItemsObservable.addAll(passiveOrders);
        monthlyEarningsObservable.addAll(earningSummary.calculateEarnings(activeOrders, passiveOrders));

        inflateData();
    }

    private void sortOrders(List<Order> orders) {
        orders.sort((o1, o2) -> o2.getTimeStamp() - o1.getTimeStamp());
    }

    public void clearData() {

        activeItemsObservable.clear();
        passiveItemsObservable.clear();
        monthlyEarningsObservable.clear();
    }

    private void inflateData() {

        activeOrdersList.setItems(activeItemsObservable);
        passiveOrdersList.setItems(passiveItemsObservable);
        earningsPerMonthList.setItems(monthlyEarningsObservable);

        activeOrdersList.setCellFactory(param -> new ActiveAdapter(firebaseService));
        passiveOrdersList.setCellFactory(param -> new PassiveAdapter(firebaseService));
        earningsPerMonthList.setCellFactory(param -> new SummaryAdapter());
    }

    private boolean deliveryTimeExists() {
        return !deliveryTime.getText().isEmpty();
    }

    private void startDeliveryNotification() {
        changeDeliveryNotificationMessage("Successfully changed");
    }

    private void changeDeliveryNotificationMessage(String message) {
        deliveryChangeNotification.setText(message);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Implemented refresher for fixing non updated ListViewFx.
        autoListRefresher();
        // Client can change delivery time in user android app.
        deliveryTimeListener();
        // Used to manipulate with main window ui in windows.
        windowUiHandler();
    }

    private void autoListRefresher() {
        ListViewAutoRefresher refresher = new ListViewAutoRefresher(asList(activeOrdersList, passiveOrdersList));
        refresher.setKeyFrame();
        refresher.startHandler();
    }

    private void deliveryTimeListener() {
        changeDeliveryTime.setOnMouseClicked(event -> {
            if (deliveryTimeExists()) {
                firebaseService.changeDeliveryTime(deliveryTime.getText());
                startDeliveryNotification();
            }
        });
    }

    private void windowUiHandler() {
        WindowImp windowImp = new WindowImp(mainViewParent);
        closeApplicationButton.setOnMouseClicked(event -> windowImp.loadWindowHandler(HandlerReferences.TERMINATE));
        minimizeApplicationButton.setOnMouseClicked(event -> windowImp.loadWindowHandler(HandlerReferences.MINIMIZE));
        maximizeApplicationButton.setOnMouseClicked(event -> windowImp.loadWindowHandler(HandlerReferences.MAXIMIZE));
        // Resizing window on double tab click
        windowToolHolder.setOnMouseClicked(event -> windowImp.onClickWindow());
    }
}
