package advancedTypes.hashtable;

public class HashTableDemo {
    public static void main(String[] args) {
        MyHashTable<String, Integer> map = new MyHashTable<>();
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("orange", 3);

        System.out.println(map.get("banana"));
        map.remove("banana");
        System.out.println(map.containsKey("banana"));
    }
}
