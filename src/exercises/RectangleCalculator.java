package exercises;

/**
 * Calculates the area and perimeter of a rectangle.
 */
public class RectangleCalculator {
    public static void main(String[] args) {
        int length = 4;
        int breadth = 2;

        int area = length * breadth;
        int perimeter = 2 * length * breadth;

        System.out.println("area of rectangle of length " + length + " breadth " + breadth + " is: " + area);
        System.out.println("perimeter of rectangle of length " + length + " breadth " + breadth + " is: " + perimeter);
    }
}
