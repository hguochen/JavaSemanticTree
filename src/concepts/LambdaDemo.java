package concepts;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LambdaDemo {
    public static void main(String[] args) {
        // 1. Print each element of a list
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        // use forEach with a lambda to print each name
        names.forEach((element) -> System.out.println(element));
        names.forEach(System.out::println);

        // 2. sort a list of strings by length
        List<String> words = Arrays.asList("elephant", "cat", "hippopotamus", "dog");
        // Sort using Collections.sort and a lambda
        Collections.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));
        System.out.println(words);

        // 3. filter even numbers from a list
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        // Use stream() + filter() to get even numbers
        List<Integer> evenNumbers = numbers.stream().filter((num) -> num % 2 == 0).collect(Collectors.toList());
        System.out.println(evenNumbers);

        // 4. map Strings to Uppercase
        List<String> animals = Arrays.asList("dog", "cat", "horse");
        // Use map() to convert to uppercase
        List<String> animalsUppercase = animals.stream().map((animal) -> animal.toUpperCase()).collect(Collectors.toList());
        System.out.println(animalsUppercase);

        // 5. remove null or empty strings
        List<String> cartoon = Arrays.asList("Tom", "", null, "Jerry", " ");
        // Filter out null or empty strings using filter()
        List<String> cartoonChars = cartoon.stream()
                .filter(Objects::nonNull)
                .filter(character -> !character.trim().isEmpty())
                .collect(Collectors.toList());
        System.out.println(cartoonChars);

        // 6. get squares of distinct numbers
        List<Integer> numbers2 = Arrays.asList(2, 3, 3, 4, 5);
        // Stream -> distinct() -> map(x -> x*x)
        List<Integer> numSquares = numbers2.stream()
                .distinct()
                .map(num -> num * num)
                .collect(Collectors.toList());
        System.out.println(numSquares);

        // 7. count words longer than 5 characters
        List<String> words2 = Arrays.asList("apple", "banana", "strawberry", "kiwi");
        // Use filter + count
        Long wordsLongerThan5 = words2.stream()
                .filter(word -> word.length() > 5)
                .count();
        System.out.println(wordsLongerThan5);

        // 8. join strings with a delimiter
        List<String> items = Arrays.asList("a", "b", "c");
        // Output: "a,b,c"
        String itemResult = items.stream()
                .collect(Collectors.joining(","));
        System.out.println(itemResult);
    }
}
