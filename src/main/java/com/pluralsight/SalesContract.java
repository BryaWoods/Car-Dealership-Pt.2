package com.pluralsight;

public class SalesContract extends Contract {

    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean finance;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold,
                         double salesTaxAmount, double recordingFee, double processingFee, boolean finance) {
        super(date, customerName, customerEmail, vehicleSold);
        this.salesTaxAmount = salesTaxAmount;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.finance = finance;
    }

    public double getSalesTaxAmount() {
        double salesTaxRate = .05;
        return getVehicleSold().getPrice() * salesTaxRate;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public double getRecordingFee() {
        return 100;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return getVehicleSold().getPrice() < 10000 ? 295 : 495;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public String isFinance() {
        return finance ? "YES" : "NO";
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice() + getSalesTaxAmount() + getRecordingFee() + getProcessingFee();
    }

    @Override
    public double getMonthlyPayment() {
        if (!finance) {
            return 0;
        }

        double principal = getTotalPrice();
        double monthlyRate;
        int term;

        if (principal >= 10000) {
            monthlyRate = 0.0425 /12.0;
            term = 48;
        } else {
            monthlyRate = 0.0525 / 12.0;
            term = 24;
        }

        return principal * (monthlyRate / (1 - Math.pow(1 + monthlyRate, -term)));
    }
}
