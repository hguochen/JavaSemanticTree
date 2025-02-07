package exercises;

/**
 * Checks if a number is even.
 */
public class EvenNumber {
    public static void main(String[] args) {
        int number = 3;

        if (number % 2 == 0) {
            System.out.println("Number " + number + " is even.");
        } else {
            System.out.println("Number " + number + " is odd.");
        }
    }
}
