# Functional Interface & Lambda Expression Best Practices 

## 1. Prefer Standard Functional interfaces
### Don't do this
Here's a typical functional interface
```java
@FunctionalInterface
public interface Foo {
    String method(String string);
}
```
We can use this interface as such:
```java
public String add(String string, Foo foo) {
    return foo.method(string);
}
```
### Do this
```java
public String add(String string, Function<String, String> fn) {
    return fn.apply(string);
}
```
Execution will now look like:
```java
Function<String, String> fn = param -> param + " from lambda";
String result = useFoo.add("Message ", fn);
```

## 2. Use @FunctionalInterface notation
### Don't do this
```java
public interface Foo {
    String method();
}
```

### Do this
```java
@FunctionalInterface
public interface Foo {
    String method();
}
```

## 3. Don't overuse default methods in Functional Interfaces
- bad architecture to have too many default methods

## 4. Instantiate Functional Interfaces with Lambda Expressions
### Don't do this
```java
Foo fooByIC = new Foo() {
    @Override
    public String method(String string) {
        return string + " from Foo";
    }
};
```

### Do this
```java
Foo foo = param -> param + " from Foo";
```

## 5. Don't use method overloading with Functional interfaces as parameters
### Don't do this
```java
public interface Processor {
    String process(Callable<String> c) throws Exception;
    String process(Supplier<String> s);
}

public class ProcessorImpl implements Processor {
    @Override
    public String process(Callable<String> c) throws Exception {
        // implementation details
    }

    @Override
    public String process(Supplier<String> s) {
        // implementation details
    }
}
```

### Do this
```java
public interface Processor {
    String processWithCallable(Callable<String> c) throws Exception;
    String processWithSupplier(Supplier<String> s);
}

public class ProcessorImpl implements Processor {
    @Override
    public String processWithCallable(Callable<String> c) throws Exception {
        // implementation details
    }

    @Override
    public String processWithSupplier(Supplier<String> s) {
        // implementation details
    }
}
```

## 6. Keep Lambda expressions short and self explanatory
- Lambda expressions should be an expression, not a narrative
- Lambda expressions should specifically express the functionality they provide

## 7. Avoid blocks of code in Lambda body
- ideally, each lambda expression should only have 1 line of code. Thus, self explanatory 

## 8. Avoid specifying parameter types
### Don't do this
```java
(String a, String b) -> a.toLowerCase() + b.toLowerCase();
```

### Do this
- Compiler can resolve the type of lambda params with `type inference`
```java
(a, b) -> a.toLowerCase() + b.toLowerCase();
```

## 8. Avoid parentheses around single parameter
### Don't do this
```java
(a) -> a.toLowerCase();
```

## Do this
```java
a -> a.toLowerCase();
```

## 9. Use method references
### Don't do this
```java
a -> a.toLowerCase();
```

### Do this
```java
String::toLowerCase;
```

## 10. Avoid mutation in object variables passed to lambda
```java
int[] total = new int[1];
Runnable r = () -> total[0]++;
r.run();
```
- Lambda can't change a value of an object from enclosing scope
- But in the case of mutable object variables, a state could be changed inside Lambda expressions. Which is bad
- Will the object it references have the same state after execution of the lambda? NO!
- Avoid code that can cause unexpected mutations