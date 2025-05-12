package advancedTypes.arrays;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * MyArrayList is an implementation of dynamic array, just like ArrayList in Java.
 * @param <T>
 */
public class MyArrayList<T> {
    private T[] array;
    private Class<T> clazz;
    // index points to the last index in the array that's not null
    private int index;
    private int capacity;

    /**
     * Default constructor without the array size specified
     */
    public MyArrayList(Class<T> clazz) {
        this.capacity = 10;
        this.clazz = clazz;
        this.array = (T[]) Array.newInstance(clazz, this.capacity);
        this.index = -1;
    }

    /**
     * Overloaded constructor with the array size specified by 'size'
     * @param size initial size of array
     */
    public MyArrayList(Class<T> clazz, int size) {
        this.capacity = size;
        this.array = (T[]) Array.newInstance(clazz, this.capacity);
        this.index = -1;
    }

    @Override
    public String toString() {
        if (this.index < 0) return "[]";
        StringBuilder str = new StringBuilder();
        str.append("capacity: ").append(this.capacity).append(" array size: ").append(this.index + 1).append(" ").append("[");
        System.out.println("index in toString: " + this.index);
        for (int i = 0; i <= this.index; i++) {

            if (this.array[i] == null) break;
            str.append(this.array[i].toString());
            str.append(", ");
        }
        str.delete(str.length() - 2, str.length());
        str.append("]");
        return str.toString();
    }

    /**
     * Adds an element to the end.
     * @param element
     */
    public void add(T element) {
        this.ensureCapacity();
        this.index += 1;
        this.array[this.index] = element;
        return;
    }

    /**
     * Insert an element at a specific index
     * @param index
     * @param element
     */
    public void add(int index, T element) {
        if (index > this.index || index < -1) throw new IndexOutOfBoundsException("index: " + index + " is out of " +
                "bounds");
        this.ensureCapacity();
        this.index += 1;
        for (int i = this.index; i > index; i--) {
            this.array[i] = this.array[i-1];
        }
        this.array[index] = element;
    }

    /**
     * Returns the element at the given index
     */
    public T get(int index) {
        if (index > this.index || index < -1) throw new IndexOutOfBoundsException("index: " + index + " is out of " +
                "bounds");
        return this.array[index];
    }

    /**
     * Removes element at index and shifts remaining
     */
    public T remove(int index) {
        if (index > this.index) throw new IndexOutOfBoundsException("index: " + index + "is out of bounds");
        T element = this.array[index];
        for (int i = index; i < this.index; i++) {
            this.array[i] = this.array[i+1];
        }
        this.array[this.index] = null;
        this.index -= 1;
        return element;
    }

    /**
     * Returns the number of elements
     */
    public int size() {
        return this.index + 1;
    }

    /**
     * Returns true if list is empty, false otherwise
     */
    public boolean isEmpty() {
        if (this.index == - 1) return true;
        return false;
    }

    /**
     * Empties the list
     */
    public void clear() {
        for (int i = 0; i <= this.index; i++) {
            this.array[i] = null;
        }
    }

    /**
     * Doubles the internal array when full
     */
    public void ensureCapacity() {
        if (this.index + 1 >= this.capacity) {
            T[] newArray = (T[]) Array.newInstance(this.clazz, this.capacity * 2);
            for (int i = 0; i <= this.index; i++) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;
            this.capacity *= 2;
        }
    }

    /**
     * Returns true if array contains the request element, false otherwise
     */
    public boolean contains(T element) {
        if (indexOf(element) >= 0) return true;
        return false;
    }

    /**
     * Returns the index of the given element. returns null if element is not found
     */
    public int indexOf(T element) {
        for (int i = 0; i <= this.index; i++) {
            if (this.array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }
}
