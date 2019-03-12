package minnie.adapters;

import javafx.scene.control.ListCell;
import minnie.controller.ActiveController;
import minnie.service.FirebaseService;
import minnie.model.Order;

public class ActiveAdapter extends ListCell<Order> {

    private ActiveController controller;
    private FirebaseService firebaseService;

    public ActiveAdapter(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @Override
    protected void updateItem(Order order, boolean empty) {
        super.updateItem(order, empty);
        if (order == null) {


        } else {

            controller = new ActiveController(order, firebaseService);

            setGraphic(controller.getParentPane());

        }
    }
}
