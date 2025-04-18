package libraries;

import java.util.BitSet;

/**
 * BitSet represents a set of bits(booleans). It's an efficient way to store and operate on binary data.
 * More efficient than a boolean[] array.
 *
 * Features
 * - flags or feature toggles
 * - efficient representation of sets of non-negative integers
 * - allows bitwise operations
 * - memory efficient data processing
 * - not thread safe by default
 */
public class BitSetDemo {
    public static void main(String[] args) {
        BitSet bits = new BitSet(16);
        System.out.println(bits);

        for (int i = 0; i < 16; i++) {
            // sets the bit at the specified index to true
            if (i % 2 == 0) bits.set(i);
            // sets the bit at the index to false
            if (i % 4 == 0) bits.clear(i);
        }
        System.out.println(bits);

        // returns the value of the bit at index
        System.out.println(bits.get(6));
        // flips the bit at index
        bits.flip(6);
        System.out.println(bits);

        BitSet bits2 = new BitSet(16);

        // bitwise AND
        bits.and(bits2);

        // bitwise OR
        bits.or(bits2);

        // bitwise XOR
        bits.xor(bits2);
    }
}
