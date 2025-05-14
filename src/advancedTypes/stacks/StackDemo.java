package advancedTypes.stacks;

public class StackDemo {
    public static void main(String[] args) {
        MyStacks<String> stack = new MyStacks<>();
        stack.push("monday");
        stack.push("tuesday");
        stack.push("wednesday");
        stack.push("thursday");
        stack.push("friday");
        stack.push("saturday");
        stack.push("sunday");
        System.out.println(stack);
        stack.clear();
        System.out.println(stack);
    }
}
