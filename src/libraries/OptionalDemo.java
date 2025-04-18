package libraries;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * Optional is a container object that may or may not contain a non-null value. It's introduced to help with NullPointerException.
 *
 * Why use optional?
 * - makes it clear a value might be present
 * - encourage functional programming style(map, filter etc)
 * - helps avoid null checks and NullPointerException
 */
public class OptionalDemo {
    public static void main(String[] args) {
        // create an optional
        Optional<String> name = Optional.of("Alice"); // non-null value
        Optional<String> noVal = Optional.empty(); // empty box
        Optional<String> maybeName = Optional.ofNullable(null); // could be null

        // always evaluates the default
        String user = maybeName.orElse("Default user");
        System.out.println(user);

        // is lazy, only runs if needed
//        String userLazy = maybeName.orElseGet(() -> fetchDefaultUser());

        // throws an exception if empty
//        String userError = maybeName.orElseThrow(() -> new RuntimeException("User not found"));

        // transforms with map
        Optional<String> upper = name.map(String::toUpperCase);
        upper.ifPresent((value) -> System.out.println("value is :" + value));
        Optional<Integer> length = name.map(String::length);
        length.ifPresent((value) -> System.out.println(value));

        // filter
        Optional<String> shortName = name.filter((n) -> n.length() <= 4);
        if (!shortName.isPresent()) System.out.println("shortName is empty");

        // Common Pattern Without vs With Optional
        // before:
//        String result;
//        if (user != null) {
//            result = user.getName();
//        } else {
//            result = "Unknown";
//        }
        // after:
//        String result = Optional.ofNullable(user)
//                .map(User::getName)
//                .orElse("Unknown");
        Optional<String> hasVal = Optional.of("ABCEDFG");

        if (noVal.isPresent()) System.out.println("this won't get displayed");
        else System.out.println("noVal has no value");

        if (hasVal.isPresent()) System.out.println("this string in hasVal is: " + hasVal.get());

        String defStr = noVal.orElse("default string");
        System.out.println(defStr);

        // conditional return with filter()
        Integer year = 2016;
        Optional<Integer> yearOptional = Optional.of(year);
        // if the wrapped value passes testing by the predicate, then the optional is returned as is
        // else. it will return an empty optional
        // filter method here is used to reject wrapped values based on predefined rule
        // for eg. we can use it to reject a wrong email format or password that is not strong enough
        boolean is2016 = yearOptional.filter(y -> y == 2016).isPresent(); // true
        boolean is2017 = yearOptional.filter(y -> y == 2017).isPresent(); // false
        System.out.println("is2016: " + is2016);
        System.out.println("is2017: " + is2017);

        // practical use of map and filter together
        // let's assume we want to check the correctness of a password input by a user. we can clean the password
        // using a map transformation and check its correctness using a filter
        String password = " password ";
        Optional<String> passOpt = Optional.of(password);
        boolean correctPassword = passOpt
                .map(String::trim)
                .filter(pw -> pw.equals("password"))
                .isPresent();
        System.out.println(correctPassword); // true
    }
}
