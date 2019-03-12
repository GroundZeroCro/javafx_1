package minnie.model;

public class OrderItem {

    private String itemId;
    private String itemName;
    private Double itemPrice;
    private String extrasName;
    private Double extrasPrice;

    OrderItem(){}

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public String getExtrasName() {
        return extrasName;
    }

    public Double getExtrasPrice() {
        return extrasPrice;
    }
}