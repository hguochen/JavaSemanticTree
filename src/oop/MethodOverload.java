package oop;

/**
 * One of the ways Java supports polymorphism. A class can have two or more methods with the same name as long as they
 * have different parameter declarations. This is method overloading.
 */
class Car {
    private String make;
    private String model;

    Car(String make, String model) {
        this.make = make;
        this.model = model;
    }

    public void driver(String user) {
        System.out.println(user + " is driving car " + this.make + " and model " + this.model);
    }

    public void driver(String user, String gender) {
        System.out.println(user + " " + gender + " is driving car " + this.make + " and model " + this.model);
    }
}

public class MethodOverload {
    public static void main(String[] args) {
        Car tesla = new Car("Tesla", "Model Y");
        tesla.driver("Gary");
        tesla.driver("Jason", "Male");
    }
}
