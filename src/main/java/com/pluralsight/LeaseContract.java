package com.pluralsight;

public class LeaseContract extends Contract{

    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold,
                         double expectedEndingValue, double leaseFee) {
        super(date, customerName, customerEmail, vehicleSold);
        this.expectedEndingValue = expectedEndingValue;
        this.leaseFee = leaseFee;
    }

    @Override
    public double getTotalPrice() {
        return 0;
    }

    @Override
    public double getMonthlyPayment() {
        return 0;
    }
}
