package libraries;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Random class is used to generate pseudo-random numbers.
 *
 * Random is not thread safe. for concurrent apps, use ThreadLocalRandom
 * int r = ThreadLocalRandom.current().nextInt(1, 7); // [1,7)
 */
public class RandomDemo {
    public static void main(String[] args) {
        // default random constructor
        Random rand = new Random();

        // same seed value, same sequence every time
        Random rand2 = new Random(42);

        // generates a random integer
        System.out.println(rand.nextInt());
        // generates a random number between 0(inclusive) and given number(exclusive)
        System.out.println(rand.nextInt(100));

        // generates a random double between 0.0 and 1.0
        System.out.println(rand.nextDouble());
        // generates a random float between 0.0 and 1.0
        System.out.println(rand.nextFloat());

        // generates a random boolean value
        System.out.println(rand.nextBoolean());

        // generates a random long
        System.out.println(rand.nextLong());

        // fill array with random bytes
        byte[] bytes = new byte[10];
        rand.nextBytes(bytes);
        System.out.println(bytes);

        // roll a 6 sided die
        Random randDie = new Random();
        int dice = randDie.nextInt(6) + 1; // [1,6]
        System.out.println("Rolled: " + dice);

        // flip a coin
        boolean coin = rand.nextBoolean();
        System.out.println(coin ? "Heads" : "Tails");

        // pick a random element from list
        List<String> colors = Arrays.asList("Red", "Green", "Blue", "Yellow");
        String pick = colors.get(rand.nextInt(colors.size()));
        System.out.println("Random pick: " + pick);
    }
}
