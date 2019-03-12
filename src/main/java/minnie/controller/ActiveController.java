package minnie.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.AnchorPane;
import minnie.service.FirebaseService;
import minnie.model.Order;
import minnie.utils.Constants;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ActiveController implements Initializable {

    private ObservableList<String> orderItemsObservable = FXCollections.observableArrayList();

    @FXML
    Label timePassedLabel, usernameLabel, addressLabel, phoneLabel, deliveryLabel, timeLabel, idLabel, totalPriceLabel, priceLabel, statusLabel;
    @FXML
    private AnchorPane parentPane;
    @FXML
    private ListView orderItemsList;
    @FXML
    private Button changeOrderStatusButton;

    private FXMLLoader mLoader;
    private Order order;
    private FirebaseService firebaseService;

    public ActiveController(Order order, FirebaseService firebaseService) {
        this.order = order;
        this.firebaseService = firebaseService;

        mLoader = new FXMLLoader(this.getClass().getResource("/fxml/item_main.fxml"));
        mLoader.setController(this);
        try {
            mLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        timeLabel.setText(order.getOrderTime());
        timePassedLabel.setText(order.getTimePassed());
        timeLabel.setText(order.getOrderTime());
        usernameLabel.setText(order.getUserData().getUsername());
        addressLabel.setText(order.getUserData().getAddress());
        phoneLabel.setText(order.getUserData().getPhone());
        deliveryLabel.setText(order.getUserData().getHomeDelivery() ? Constants.yes : Constants.no);
        idLabel.setText(order.getOrderId());
        priceLabel.setText(String.format("%.2f", order.getBasicPrice()) + " + " + String.format("%.2f", order.getTax()) + " PDV");
        totalPriceLabel.setText(String.format("%.2f", order.getTotalAmount()) + " kn");
        statusLabel.setText(order.getOrderCompleted() ? Constants.orderCompleted : Constants.orderNotCompleted);

        changeOrderStatusButton.setText(order.getOrderCompleted() ? Constants.orderNotCompleted : Constants.orderCompleted);

        fetchData();
        inflateData();
    }

    private void fetchData() {
        for (int i = 0; i < order.getOrderList().size(); i++) {
            orderItemsObservable.add(getItemOrderStructure(i));
        }
    }

    private String getItemOrderStructure(int i) {
        return hasExtraMenu(i) ?
                i + 1 + ". " + order.getOrderList().get(i).getItemName() + " + " + order.getOrderList().get(i).getExtrasName()
                : i + 1 + ". " + order.getOrderList().get(i).getItemName();

    }

    private Boolean hasExtraMenu(int i) {
        return !order.getOrderList().get(i).getExtrasName().equals("");
    }

    public void inflateData() {

        orderItemsList.setItems(orderItemsObservable);
        orderItemsList.setCellFactory(ComboBoxListCell.forListView(orderItemsObservable));
    }

    public AnchorPane getParentPane() {
        return parentPane;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Changing order to another screen
        changeOrderStatusButton.setOnAction((event) -> {
            boolean orderStatus;

            if (statusLabel.getText().equals(Constants.orderCompleted)) {
                orderStatus = false;
            } else {
                orderStatus = true;
            }
            System.out.println(orderStatus);
            firebaseService.changeOrderCompletedStatus(orderStatus, order.getUserId(), order.getOrderId());
        });
    }
}
