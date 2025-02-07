package exercises;

/**
 * Checks if a number is prime.
 *
 * Step 1: If num < 2, return false.
 * Step 2: If num == 2 or num == 3, return true (smallest primes).
 * Step 3: If num is divisible by 2 or 3, return false.
 * Step 4: Check divisibility from 5 to √num, skipping even numbers (i += 6 pattern checks i and i+2).
 */
public class PrimeNumber {
    public static boolean isPrime(int number) {
        if (number < 2) return false;
        if (number == 2 || number == 3) return true; // 2 and 3 are prime
        if (number % 2 == 0 || number % 3 == 0) return false; // eliminate even numbers and multiples of 3

        // Check factors from 5 to √num (skip even numbers)
        for (int i = 5; i * i <= number; i+=6) {
            if (number % i == 0 || number % (i + 2) == 0) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        int num = 29;

        System.out.println("Number " + num + " is prime? " + isPrime(num));
    }
}
