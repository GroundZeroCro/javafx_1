package minnie.adapters;

import javafx.scene.control.ListCell;
import minnie.controller.PassiveController;
import minnie.service.FirebaseService;
import minnie.model.Order;

public class PassiveAdapter extends ListCell<Order> {

    private PassiveController controller;
    private FirebaseService firebaseService;

    public PassiveAdapter(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @Override
    protected void updateItem(Order order, boolean empty) {
        super.updateItem(order, empty);
        if (order== null) {


        } else {
            controller = new PassiveController(order, firebaseService);
            setGraphic(controller.getParentPane());
        }
    }
}
