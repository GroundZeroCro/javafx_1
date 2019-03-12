package minnie.business;

import minnie.model.Earnings;
import minnie.model.Order;
import minnie.model.OrderItem;
import minnie.utils.TimeStamp;

import java.util.ArrayList;
import java.util.List;

public class EarningSummary {

    public List<Earnings> calculateEarnings(List<Order> activeOrders, List<Order> passiveOrders) {
        return getEarningsPerMonth(sumOrders(activeOrders, passiveOrders));
    }

    private List<Order> sumOrders(List<Order> activeOrders, List<Order> passiveOrders) {
        List<Order> totalOrders = new ArrayList<>();
        totalOrders.addAll(activeOrders);
        totalOrders.addAll(passiveOrders);
        return totalOrders;
    }

    private List<Earnings> getEarningsPerMonth(List<Order> orders) {

        List<Earnings> earnings = new ArrayList<>();
        // Years.
        // Calculating only till current year (included).
        for (int i = 2019; i <= getTimeStampYear(TimeStamp.getCurrentTime()); i++) {
            // Months
            for (int j = 1; j <= 12; j++) {
                // Adding month key and earnings value to map.
                if (collectEarningPerMonth(j, i, orders) != 0.0) {
                    earnings.add(new Earnings(j + "/" + i, collectEarningPerMonth(j, i, orders)));
                }
            }
        }
        return earnings;
    }

    private Double collectEarningPerMonth(int month, int year, List<Order> orders) {

        double monthlyEarnings = 0.0;

        for (Order order : orders) {
            if (month == getTimeStampMonth(order.getOrderTime()) && year == getTimeStampYear(order.getOrderTime())) {
                for (OrderItem item : order.getOrderList()) {
                    monthlyEarnings += item.getItemPrice() + item.getExtrasPrice();
                }
            }
        }
        return monthlyEarnings;
    }

    // Fetching month from preformed time orderTime string.
    private int getTimeStampMonth(String orderTime) {
        return Integer.parseInt(orderTime.substring(12, 14));
    }

    // Fetching year from preformed time orderTime string.
    private int getTimeStampYear(String orderTime) {
        return Integer.parseInt(orderTime.substring(15, 19));
    }
}
