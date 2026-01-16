package oop;

/**
 * Java also allows constructor overloading. Which constructor is being used depends on the parameter declarations
 */
class Vehicle {
    private String make;
    private String model;

    Vehicle(String make, String model) {
        this.make = make;
        this.model = model;
    }

    Vehicle(String make) {
        this.make = make;
        this.model = "UNDEFINED";
    }

    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public void driver(String user) {
        System.out.println(user + " is driving car " + this.make + " and model " + this.model);
    }

    public void driver(String user, String gender) {
        System.out.println(user + " " + gender + " is driving car " + this.make + " and model " + this.model);
    }
}

public class ConstructorOverload {
    public static void main(String[] args) {
        Vehicle modelY = new Vehicle("Tesla", "Model Y");
        Vehicle toyCar = new Vehicle("Audi");

        System.out.println("Vehicle 1: " + modelY.getMake() + ". model: " + modelY.getModel());
        System.out.println("Vehicle 2: " + toyCar.getMake() + ". model: " + toyCar.getModel());
    }
}
