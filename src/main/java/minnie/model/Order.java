package minnie.model;

import minnie.utils.TimeStamp;

import java.util.List;


public class Order {

    private String orderId;
    private String userId;
    private String orderTime;
    private int timeStamp;
    private Boolean orderCompleted;
    private double tax;
    private double totalAmount;
    private Boolean orderReceived;
    private User userData;
    private List<OrderItem> orderList;

    public Order setOrderCompleted(Boolean orderCompleted) {
        this.orderCompleted = orderCompleted;
        return this;
    }

    public Order() {
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public String getUserId() {
        return userId;
    }

    public double getBasicPrice() {
        double totalPrice = 0;

        for (OrderItem item : orderList) {
            totalPrice += item.getItemPrice() + item.getExtrasPrice();
        }

        return totalPrice;
    }

    public double getTax() {
        return getBasicPrice() * 0.25;
    }

    public double getTotalAmount() {
        return getBasicPrice() + getTax();
    }

    public String getTimePassed() {
        return TimeStamp.timePassed(orderTime);
    }

    public String getOrderId() {
        return orderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public Boolean getOrderCompleted() {
        return orderCompleted;
    }

    public User getUserData() {
        return userData;
    }

    public List<OrderItem> getOrderList() {
        return orderList;
    }

    public Boolean getOrderReceived() {
        return orderReceived;
    }
}






