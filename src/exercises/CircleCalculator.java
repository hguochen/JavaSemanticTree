package exercises;

/**
 * Calculates the area and perimeter of a circle.
 */
public class CircleCalculator {
    public static void main(String[] args) {
        int radius = 4;
        double piApprox = 3.1415926535;

        double area = piApprox * Math.pow(radius, 2);
        double perimeter = 2 * piApprox * radius;

        System.out.println("area of circle of radius " + radius + " is: " + area);
        System.out.println("perimeter of circle of radius " + radius + " is: " + perimeter);
    }
}
