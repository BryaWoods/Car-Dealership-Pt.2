package com.pluralsight;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Dealership dealership;
    private Contract contract;
    private SalesContract salesContract;
    private LeaseContract leaseContract;
    private ContractFileManager contractFileManager;
    private Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public void display() {
        init();
        boolean quit = false;
        while (!quit) {
            System.out.println(" ");
            System.out.println("✧✦✧ 𝓜𝓔𝓝𝓤 ✧✦✧");
            System.out.println("1. Get vehicles by price");
            System.out.println("2. Get vehicles by make and model");
            System.out.println("3. Get vehicles by year");
            System.out.println("4. Get vehicles by color");
            System.out.println("5. Get vehicles by mileage");
            System.out.println("6. Get vehicles by type");
            System.out.println("7. Get all vehicles");
            System.out.println("8. Add/Remove vehicle");
            System.out.println("9. Sell/Lease vehicle");
            System.out.println("99. Quit");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    processGetByPriceRequest();
                    break;
                case "2":
                    processGetByMakeModelRequest();
                    break;
                case "3":
                    processGetByYearRequest();
                    break;
                case "4":
                    processGetByColorRequest();
                    break;
                case "5":
                    processGetByMileageRequest();
                    break;
                case "6":
                    processGetByVehicleTypeRequest();
                    break;
                case "7":
                    processGetAllVehiclesRequest();
                    break;
                case "8":
                    addOrRemoveMenu();
                    break;
                case "9":
                    processSellLease();
                    break;
                case "99":
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void processGetByPriceRequest() {
        System.out.print("Enter minimum price: ");
        double min = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double max = scanner.nextDouble();
        List<Vehicle> vehicles = dealership.getVehiclesByPrice(min, max);
        displayVehicles(vehicles);
    }

    public void processGetByMakeModelRequest() {


        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }

    public void processGetByYearRequest() {
        int min = getIntInput("Enter minimum year: ");

        int max = getIntInput("Enter maximum year: ");
        scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByYear(min, max);
        displayVehicles(vehicles);
    }

    public void processGetByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);
    }

    public void processGetByMileageRequest() {

        int min = getIntInput("Enter minimum mileage: ");

        int max = getIntInput("Enter maximum mileage: ");
        scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByMileage(min, max);
        displayVehicles(vehicles);
    }

    public void processGetByVehicleTypeRequest() {
        System.out.print("Enter vehicle type: ");
        String vehicleType = scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByType(vehicleType);
        displayVehicles(vehicles);
    }

    public void processGetAllVehiclesRequest() {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    public void processAddVehicleRequest() {

        int vin = getIntInput("Enter vehicle vin");
        scanner.nextLine();

        System.out.print("Enter vehicle make: ");
        String make = scanner.nextLine();

        System.out.print("Enter vehicle model: ");
        String model = scanner.nextLine();

        int year = getIntInput("Enter vehicle year: ");

        scanner.nextLine();

        System.out.print("Enter vehicle price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter vehicle color: ");
        String color = scanner.nextLine();

        int mileage = getIntInput("Enter vehicle mileage: ");
        scanner.nextLine();

        System.out.print("Enter vehicle type (Car, Truck, SUV, Motorcycle): ");
        String type = scanner.nextLine();

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);

        dealership.addVehicle(vehicle);
        System.out.println("Vehicle added successfully!");
        DealershipFileManager manager = new DealershipFileManager();
        manager.saveDealership(dealership);
    }

    public void processRemoveVehicleRequest() {

        int vin = getIntInput("Enter the VIN of the vehicle you wish to remove: ");
        scanner.nextLine();

        boolean vehicleRemoved = false;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                dealership.removeVehicle(vehicle);
                System.out.println("Vehicle removed successfully!");
                vehicleRemoved = true;
                break;
            }
        }

        if (!vehicleRemoved) {
            System.out.println("Vehicle not found. Please try again.");
            return;
        }

        DealershipFileManager manager = new DealershipFileManager();
        manager.saveDealership(dealership);
    }

    private void init() {
        DealershipFileManager manager = new DealershipFileManager();
        dealership = manager.getDealership();
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        System.out.println("                                          ✦✧✦☾𝓘𝓃𝔱𝓮𝔯𝓰𝔞𝔩𝒶𝓬𝒕𝔦𝓬 𝔇𝓻𝓮𝐚𝔪 𝓒𝐚𝔯𝔰☽✦✧✦");
        System.out.println("⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚");
        System.out.println("Vin   | Year | Make            | Model        | Type         | Color    |  Mileage | Price");
        System.out.println("✧✦      ✦✧✦✧   ✦✧✦✧              ✦✧✦✧✦          ✦✧✦✧           ✦✧✦✧✦       ✦✧✦✧✦✧✦   ✧✦✧✦✧✦");
        for (Vehicle vehicle : vehicles) {

            String format = "%-5s | %-4d | %-15s | %-12s | %-12s | %-8s | %-7d | $%.2f\n";
            System.out.printf(format,
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    vehicle.getPrice()
            );

        }
        System.out.println("⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚⊹⋆｡༄⋆˚");

    }

    public void addOrRemoveMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Options");
            System.out.println("A: Add a vehicle.");
            System.out.println("R: Remove a vehicle.");
            System.out.println("X: Save and exit");


            String input = scanner.nextLine().toUpperCase();

            switch (input) {
                case "A":
                    processAddVehicleRequest();
                    break;
                case "R":
                    processRemoveVehicleRequest();
                    break;
                case "X":
                    System.out.println("Saving... Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try Again.");

            }

        }
    }

    public void processSellLease() {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Options");
            System.out.println("S: Sell a vehicle.");
            System.out.println("L: Lease vehicle.");
            System.out.println("X: Save and exit");


            String input = scanner.nextLine().toUpperCase();

            switch (input) {
                case "S":
                    processSellVehicle();
                    break;
                case "L":
                    processLeaseVehicle();
                    break;
                case "X":
                    System.out.println("Saving... Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try Again.");

            }
        }
    }

    public void processSellVehicle(){

        ContractFileManager contractFileManager = new ContractFileManager();
        DealershipFileManager dealershipFileManager = new DealershipFileManager();


        System.out.print("Enter vehicle VIN: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        Vehicle vehicleBeingSold = dealership.findVehicleByVin(vin);

        if (vehicleBeingSold == null){
            System.out.println("No vehicle found!");
            return;
        }

        String date = LocalDate.now().toString();

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter customer email: ");
        String customerEmail = scanner.nextLine();

        System.out.print("Do you want to finance the vehicle? (yes/no): ");
        String financeOption = scanner.nextLine();
        boolean isFinance = financeOption.equalsIgnoreCase("yes");

        SalesContract salesContract = new SalesContract(date,customerName,customerEmail,vehicleBeingSold,isFinance);



        salesContract.setDate(LocalDate.now().toString());
        salesContract.setCustomerName(customerName);
        salesContract.setCustomerEmail(customerEmail);
        salesContract.setVehicleSold(vehicleBeingSold);
        salesContract.setFinance(isFinance);

        dealership.removeVehicle(vehicleBeingSold);
        dealershipFileManager.saveDealership(dealership);

        contractFileManager.saveContract(salesContract);
        System.out.println("Vehicle sold and contract saved successfully!");




    }

    public void processLeaseVehicle() {

        ContractFileManager contractFileManager = new ContractFileManager();
        DealershipFileManager dealershipFileManager = new DealershipFileManager();


        int vin = getIntInput("Enter vehicle VIN: ");
        scanner.nextLine();

        Vehicle vehicleBeingLeased = dealership.findVehicleByVin(vin);

        if (vehicleBeingLeased == null) {
            System.out.println("No vehicle found!");
            return;

        }

        String date = LocalDate.now().toString();

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter customer email: ");
        String customerEmail = scanner.nextLine();

        LeaseContract leaseContract = new LeaseContract(date,customerName,customerEmail,vehicleBeingLeased);

        leaseContract.setDate(LocalDate.now().toString());
        leaseContract.setCustomerName(customerName);
        leaseContract.setCustomerEmail(customerEmail);
        leaseContract.setVehicleSold(vehicleBeingLeased);

        dealership.removeVehicle(vehicleBeingLeased);

        dealershipFileManager.saveDealership(dealership);


        contractFileManager.saveContract(leaseContract);

        System.out.println("Vehicle leased and contract saved successfully!");
    }

    public int getIntInput(String prompt) {
        int input = 0;
        boolean isValid = false;

        while (!isValid) {
            System.out.print(prompt);

            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                isValid = true;
            } else {

                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }

        return input;
    }

}