package exercises;

/**
 * Calculates the area and perimeter of a square.
 */
public class SquareCalculator {
    public static void main(String[] args) {
        int length = 4;
        double area = Math.pow(length, 2);
        double perimeter = 4 * length;

        System.out.println("area of square of length " + length + " is: " + area);
        System.out.println("perimeter of square of length " + length + " is: " + perimeter);
    }
}
