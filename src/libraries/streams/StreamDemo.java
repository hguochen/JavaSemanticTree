package libraries.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A Java Stream is a component that can do internal iteration of its elements. This is similar to Java Collections iteration
 * features, but for Java Iterable, you'll have to implement the iteration of elements manually.
 *
 * what is Stream processing?
 * - you can attach processing routines called listeners to a stream.
 * - these listeners are called once for each element in the stream
 * - each listener processes each element in the stream
 * - these listeners form a chain
 *
 * Stream interface splits into 2 categories of operations:
 * - terminal -> an operation that starts the internal iteration of elements, calls all the listeners, and return a
 * result
 * - non-terminal -> an operation that adds a listener to the stream without doing anything else
 */
public class StreamDemo {
    public static void main(String[] args) {
        // obtain a stream from a Java collection
        List<String> items = new ArrayList<>();
        items.add("one");
        items.add("two");
        items.add("three");
        Stream<String> stream = items.stream();

        /**
         * NON-TERMINAL OPERATIONS
         */

        /**
         * filter()
         * - filter out elements from a java stream
         * - filter() method takes a Predicate, which is called for each element in the stream
         */
        Stream<String> longStringsStream = stream.filter((value) -> value.length() >= 3);

        /**
         * map()
         * - converts an element to another object
         */
        List<String> list2 = new ArrayList<>();
        Stream<String> stream2 = list2.stream();
        Stream<String> streamMapped = stream2.map(String::toUpperCase);

        /**
         * flatMap()
         * - maps a single element into multiple elements
         * - "flatten" each element from a complex structure, which consists of multiple internal elements to a
         * "flat" stream consisting only of these internal elements
         */
        List<String> stringList = new ArrayList<>();
        stringList.add("One flew over the cuckoo's nest");
        stringList.add("To kill a mockingbird");
        stringList.add("Gone with the wind");

        Stream<String> stream3 = stringList.stream();
        // flatMap has to return another Stream representing the flat mapped elements
        stream3.flatMap((value) -> {
            String[] split = value.split(" ");
            // returns a String arrayList collection with each element being a word
            return (Stream<String>) Arrays.stream(split);
        }).forEach(System.out::println);
        System.out.println("---end of flatMap---");

        /**
         * distinct()
         * - return a new stream which will only contain the distinct elements from the original stream
         */
        List<String> list4 = new ArrayList<String>();

        list4.add("one");
        list4.add("two");
        list4.add("three");
        list4.add("one");
        Stream<String> stream4 = list4.stream();
        List<String> distinctStrings = stream4
                .distinct()
                .collect(Collectors.toList());
        System.out.println(distinctStrings);

        /**
         * limit()
         * - limit the number of elements in a stream to a number given to the limit method param
         */
        Stream<String> stream5 = list4.stream();
        stream5.limit(2)
                .forEach(System.out::println);

        /**
         * peek()
         * - takes a Consumer as a param
         * - the Consumer gets called for each element in the stream
         * - returns a new Stream which contains all the elements in the original stream
         * - no transformation of the elements are done
         */
        List<String> list6 = new ArrayList<String>();

        list6.add("abc");
        list6.add("def");
        Stream<String> stream6 = list6.stream();
        Stream<String> streamPeeked = stream6.peek(System.out::println);
        System.out.println(streamPeeked);

        /**
         * generate()
         * - takes in a Supplier, which generates each element
         * - suitable for generating constant streams, streams of random elements
         */
        int[] fibInitial = {0, 1};
        Stream<Integer> fibGenerate = Stream.generate(() -> {
            int result = fibInitial[1];
            int fib3 = fibInitial[0] + fibInitial[1];
            fibInitial[0] = fibInitial[1];
            fibInitial[1] = fib3;
            return result;
        });
        fibGenerate.limit(10).forEach(System.out::println);

        /**
         * iterate()
         * - creating an infinite stream
         * - first param is the initial element
         * - 2nd param is a UnaryOperator
         */
        Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);
        streamIterated.forEach(System.out::println);

        /**
         * TERMINAL OPERATIONS
         * - terminal operations typically return a single value
         * - once a terminal operation is invoked on a stream, the iteration of the stream and any chained streams
         * will get started.
         * - once iteration is done, the result of the terminal operation is returned.
         * - terminal operations typically does NOT return a new Stream instance.
         */

        /**
         * anyMatch()
         * - takes a single Predicate param, applies the Predicate param to each element
         * - if any Predicated element returns true, the anyMatch() method returns true.
         */
        Stream<String> stream7 = stringList.stream();
        boolean anyMatch = stream7.anyMatch(value -> value.startsWith("One"));
        System.out.println(anyMatch);

        /**
         * allMatch()
         * - takes a single Predicate param, applies the Predicate param to each element
         * - if all Predicated element returns true, then allMatch() method returns true, false otherwise.
         */
        Stream<String> stream8 = stringList.stream();
        boolean allMatch = stream8.allMatch(value -> value.startsWith("One"));
        System.out.println(allMatch);

        /**
         * noneMatch()
         * - takes a single Predicate param, applies the Predicate param to each element
         * - if none of the Predicated element returns true, then noneMatch() method returns true, false otherwise.
         * ie. there's at least 1 match in the predicated elements
         */
        List<String> list9 = new ArrayList<>();
        list9.add("abc");
        list9.add("def");
        Stream<String> stream9 = list9.stream();
        boolean noneMatch = stream9.noneMatch(value -> value.equals("xyz"));
        System.out.println(noneMatch);

        /**
         * collect()
         * - collects the elements in the stream in a collection or object of some kind
         */
        Stream<String> stream10 = stringList.stream();
        List<String> stringAsUppercaseList = stream10
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(stringAsUppercaseList);

        /**
         * count()
         * - counts the elements and return an integer
         */
        Stream<String> stream11 = stringList.stream();
        // count the number of words in all the elements combined
        long count = stream11.flatMap((value) -> {
            String[] split = value.split(" ");
            return (Stream<String>) Arrays.stream(split);
        }).count();
        System.out.println(count);

        /**
         * findAny()
         * - find a single element from the stream
         */
        Stream<String> stream12 = items.stream();
        Optional<String> anyElement = stream12.findAny();
        System.out.println(anyElement.get());

        /**
         * findFirst()
         * - finds the first element in the stream
         */
        Stream<String> stream13 = items.stream();
        Optional<String> firstElement = stream13.findFirst();
        System.out.println(firstElement.get());

        /**
         * forEach()
         * - applies a Consumer(java.util.function.Consumer) to each element in the stream
         */
        Stream<String> stream14 = items.stream();
        stream14.forEach(System.out::println);

        /**
         * min()
         * - returns the minimum element
         * - takes in a Comparator implementation
         */
        Stream<String> stream15 = items.stream();
        Optional<String> min = stream15.min((value1, value2) -> {
            return value1.compareTo(value2);
        });
        System.out.println(min.get());

        /**
         * max()
         * - returns the maximum elment
         * - takes in a Comparator implementation
         */
        Stream<String> stream16 = items.stream();
        Optional<String> max = stream16.max((value1, value2) -> {
            return value1.compareTo(value2);
        });
        System.out.println(max.get());

        /**
         * reduce()
         * - reduce all elements int he stream into a single element
         */
        Stream<String> stream17 = stringList.stream();
        Optional<String> reduced = stream17.reduce((value, combinedValue) -> {
            return combinedValue + " " + value;
        });
        System.out.println(reduced.get());

        /**
         * toArray()
         * - returns an array of object containing all the elements
         */
        Stream<String> stream18 = stringList.stream();
        Object[] strs = stream18.toArray();

        /**
         * concat()
         * - concatenate two streams into one.
         * - the result is a new stream which contains all the elements from the first stream, followed by all the
         * elements from the second stream
         */
        List<String> stringList2 = new ArrayList<>();
        stringList2.add("Lord of the Rings");
        stringList2.add("Planet of the Rats");
        stringList2.add("Phantom Menace");

        Stream<String> stream19 = stringList.stream();
        Stream<String> stream20 = stringList2.stream();
        Stream<String> concatStream = Stream.concat(stream19, stream20);
        List<String> stringListCombined = concatStream.collect(Collectors.toList());
        System.out.println(stringListCombined);

        // create stream from array
        // static method of() which can be used to create a stream() from one or more objects
        Stream<String> streamOf = Stream.of("one", "two", "three");
        List<String> newList = streamOf.collect(Collectors.toList());
        System.out.println(newList);
    }
}
