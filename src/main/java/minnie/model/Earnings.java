package minnie.model;

public class Earnings {

    private String date;
    private double monthlyEarnings;

    public Earnings(String date, double monthlyEarnings) {
        this.date = date;
        this.monthlyEarnings = monthlyEarnings;
    }

    public String getDate() {
        return date;
    }

    public Earnings setDate(String date) {
        this.date = date;
        return this;
    }

    public double getMonthlyEarnings() {
        return monthlyEarnings;
    }

    public Earnings setMonthlyEarnings(double monthlyEarnings) {
        this.monthlyEarnings = monthlyEarnings;
        return this;
    }
}