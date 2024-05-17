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

    public double getExpectedEndingValue() {
        return getVehicleSold().getPrice() * 0.50;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return getVehicleSold().getPrice() * 0.07;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice() + getLeaseFee();
    }

    @Override
    public double getMonthlyPayment() {
        double principal = getTotalPrice();
        double monthlyRate = 0.04 / 12.0;
        int term = 36;
        return principal * (monthlyRate / (1 - Math.pow(1 + monthlyRate, -term)));
    }
}
