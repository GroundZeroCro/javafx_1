package minnie.model;

public class User {

    private String username;
    private String phone;
    private String address;
    private String comment;
    private Boolean homeDelivery;

    User() {}

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getComment() {
        return comment;
    }

    public Boolean getHomeDelivery() {
        return homeDelivery;
    }
}