package libraries.collections;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;
import java.util.stream.Collectors;

public class StackDemo {
    public static void main(String[] args) {
        // create a stack
        Stack<Integer> intStack = getIntegerStack();

        // add into a stack
        // .push returns the element that was added
        int res = intStack.push(1);
        System.out.println(res);
        // does not return anything
        intStack.addElement(2);

        // retrieve from a stack by removing the element
        int element = intStack.pop();
        System.out.println(element); // 2

        // retrieve from a stack without removing it
        int element2 = intStack.peek();
        System.out.println(element2); // 1

        // search for an element in a stack
        // the item that is on the top of the stack is considered to be at position 1
        // if object is not found, search() will return -1
        intStack.push(33);
        intStack.push(44);
        intStack.push(55);
        int index = intStack.search(33); // 3
        System.out.println(index);

        // getting index of element
        int indexOf = intStack.indexOf(44);
        System.out.println(indexOf); // 2

        // lastIndexOf() will always find the index of the element that's closest to the top of the stack.
        int lastIndexOf = intStack.lastIndexOf(44);
        System.out.println(lastIndexOf); // 2

        // remove elements from the stack
        // removeElement() used to remove the first occurrence of the given element
        intStack.removeElement(44);
        System.out.println(intStack);
        // we can also use removeElementAt() to delete elements under a specified index in the stack
        intStack.removeElementAt(0);
        System.out.println(intStack);
        // remove multiple elements
        List<Integer> intList = Arrays.asList(1,2,3,4,5);
        intStack.addAll(intList);
        intStack.add(500);
        // remove all elements in intStack that's also present in intList
        intStack.removeAll(intList);
        System.out.println(intStack);

        // iterate over a stack
        ListIterator<Integer> it = intStack.listIterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        // stream API for stack
        List<Integer> filtered = intStack
                .stream()
                .filter(ele -> ele <= 100)
                .collect(Collectors.toList());
        System.out.println(filtered);
    }

    private static Stack<Integer> getIntegerStack() {
        Stack<Integer> intStack = new Stack<>();
        return intStack;
    }
}
