package advancedTypes.arrays;

public class ArrayDemo {
    public static void main(String[] args) {
        MyArrayList<Integer> arr1 = new MyArrayList<>(Integer.class);
        System.out.println(arr1);
        MyArrayList<String> arr2 = new MyArrayList<>(String.class, 20);
        System.out.println(arr2);

        arr1.add(0);
        System.out.println(arr1);
        arr1.add(1);
        System.out.println(arr1);
        arr1.add(2);
        System.out.println(arr1);
        arr1.add(3);
        System.out.println(arr1);
        arr1.add(4);
        System.out.println(arr1);
        arr1.add(5);
        System.out.println(arr1);
        arr1.add(6);
        System.out.println(arr1);
        arr1.add(7);
        System.out.println(arr1);
        arr1.add(8);
        System.out.println(arr1);
        arr1.add(9);
        System.out.println(arr1);
        arr1.add(10);
        System.out.println(arr1);
        arr1.add(11);
        System.out.println(arr1);
        arr1.add(12);
        System.out.println(arr1);
        arr1.add(13);
        System.out.println(arr1);
        arr1.add(14);
        System.out.println(arr1);
        arr1.add(15);
        System.out.println(arr1);
        arr1.add(16);
        System.out.println(arr1);
        arr1.add(17);
        System.out.println(arr1);
        arr1.add(18);
        System.out.println(arr1);
        arr1.add(19);
        System.out.println(arr1);
        arr1.add(20);
        System.out.println(arr1);
        arr1.add(21);
        System.out.println(arr1);
        arr1.add(21, 9999);
        System.out.println(arr1);
        arr1.remove(21);
        System.out.println(arr1);
        System.out.println(arr1.contains(40));
    }
}
