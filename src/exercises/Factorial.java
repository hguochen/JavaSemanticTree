package exercises;

public class Factorial {
    /**
     * Computes the factorial of given parameter
     * @param n  nth factorial
     * @return result of nth factorial
     */
    public static int factorial(int n) {
        if (n == 1) return 1;
        return factorial(n - 1) * n;
    }
    public static void main(String[] args) {
        System.out.println(factorial(5));
    }
}
