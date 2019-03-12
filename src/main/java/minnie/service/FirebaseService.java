package minnie.service;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import minnie.constants.DbReferences;
import minnie.controller.MainController;
import minnie.model.Order;

import java.util.ArrayList;
import java.util.List;

public class FirebaseService {

    private MainController controller;

    private List<Order> activeOrders = new ArrayList<>();
    private List<Order> passiveOrders = new ArrayList<>();

    public FirebaseService(MainController controller) {
        this.controller = controller;

        loadData();
    }

    public void loadData() {
        databaseInstance().getReference(DbReferences.FAST_FOOD_NAME).addValueEventListener(databaseListener());
    }

    private ValueEventListener databaseListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                controller.clearData();
                activeOrders.clear();
                passiveOrders.clear();

                for (DataSnapshot s : dataSnapshot.child(DbReferences.ACTIVE_ORDERS).getChildren()) {
                    for (DataSnapshot c : s.getChildren()) {
                        Order order = c.getValue(Order.class);
                        if (order.getOrderCompleted()) {
                            passiveOrders.add(order);
                        } else {
                            activeOrders.add(order);
                        }
                    }
                }
                controller.updateOrders(activeOrders, passiveOrders);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

    public void changeOrderCompletedStatus(boolean isOrderCompleted, String userId, String orderId) {
        databaseInstance()
                .getReference(DbReferences.FAST_FOOD_NAME)
                .child(DbReferences.ACTIVE_ORDERS)
                .child(userId)
                .child(orderId)
                .child(DbReferences.FIREBASE_ORDER_COMPLETED)
                .setValue(isOrderCompleted, (databaseError, databaseReference) -> {
                    System.out.println(databaseReference);
                });
    }

    public void changeDeliveryTime(String newTime) {
        databaseInstance()
                .getReference(DbReferences.FAST_FOOD_NAME)
                .child(DbReferences.BUSINESS_INFO)
                .child(DbReferences.DELIVERY_TIME)
                .setValue(newTime, (databaseError, databaseReference) -> {
                    System.out.println(databaseReference);
                });
    }

    private FirebaseDatabase databaseInstance() {
        return FirebaseDatabase.getInstance();
    }
}
