package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
    private static final String fileName = "contacts.csv";


    public void saveContract(Contract contract){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String contactData = formatContractData(contract);
            writer.write(contactData);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error cannot save contract: " + e.getMessage());
        }
    }

    private String formatContractData(Contract contract){
        if (contract instanceof SalesContract){
            return formatSaleContract((SalesContract) contract);
        }else if (contract instanceof LeaseContract){
            return formatLeaseContract((LeaseContract) contract);
        } else {
            throw new IllegalArgumentException("Invalid contract type");
        }
    }

    private String formatSaleContract(SalesContract salesContract){

        return "SALE |" + salesContract.getDate() + "|" + salesContract.getCustomerName() + "|" +
                salesContract.getCustomerEmail() + "|" + salesContract.getVehicleSold().getVin() + "|" +
                salesContract.getVehicleSold().getMake() + "|" +
                salesContract.getVehicleSold().getModel() + "|" + salesContract.getVehicleSold().getVehicleType() + "|" +
                salesContract.getVehicleSold().getColor() + "|" + salesContract.getVehicleSold().getOdometer() + "|" +
                salesContract.getVehicleSold().getPrice() + "|" + salesContract.getSalesTaxAmount() + "|" +
                salesContract.getRecordingFee() + "|" + salesContract.getProcessingFee() + "|" +
                salesContract.getTotalPrice() + "|" + salesContract.isFinance() + "|" + salesContract.getMonthlyPayment();

    }

    private String formatLeaseContract(LeaseContract leaseContract){

        return "LEASE |" + leaseContract.getDate() + "|" + leaseContract.getCustomerName() + "|" +
                leaseContract.getCustomerEmail() + "|" + leaseContract.getVehicleSold().getVin() + "|" +
                leaseContract.getVehicleSold().getMake() + "|" +
                leaseContract.getVehicleSold().getModel() + "|" + leaseContract.getVehicleSold().getVehicleType() + "|" +
                leaseContract.getVehicleSold().getColor() + "|" + leaseContract.getVehicleSold().getOdometer() + "|" +
                leaseContract.getVehicleSold().getPrice() + "|" + leaseContract.getExpectedEndingValue() + "|" +
                leaseContract.getLeaseFee() + "|"  +
                leaseContract.getTotalPrice() + "|" + leaseContract.getMonthlyPayment();

    }


}
