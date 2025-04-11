package libraries.collections;

import java.util.EnumSet;

/**
 * EnumSet is a Set implementation designed specifically for Enums.
 *
 * Features
 * - Only works with Enums - Can't use with non enum types
 * - Backed by bit vector - makes it more efficient than other Set implementations
 * - Maintains natural enum order - Elements are stored in order as they appear in the enum declaration
 * - Does not allow null values - throws NullPointerException
 * - Compact memory usage - Stores enums as a bitmask, making it more efficient than HashSet
 *
 * When to use EnumSet?
 * - when dealing with enum based collections
 * - when you need fast performance for set operations
 * - when memory efficient is important
 */
public class EnumSetDemo {
    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public static void main(String[] args) {
        EnumSet<Day> weekend = EnumSet.of(Day.SATURDAY, Day.SUNDAY);
        System.out.println(weekend);

        // creating an enumset of all enum values
        EnumSet<Day> allDays = EnumSet.allOf(Day.class);
        System.out.println(allDays);

        // creating an empty set of Day enums
        EnumSet<Day> emptySet = EnumSet.noneOf(Day.class);
        System.out.println(emptySet);

        // creating an enum set range
        EnumSet<Day> workdays = EnumSet.range(Day.MONDAY, Day.FRIDAY);
        System.out.println(workdays);
    }
}
