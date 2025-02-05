package concepts;

/**
 * In this class, we demonstrate using break statement as a form of goto.
 * labelled break statement: break label;
 *
 * the same can be used for continue label as well.
 */
public class BreakDemo {
    public static void main(String[] args) {
        boolean t = true;

        first: {
            second: {
                third: {
                    System.out.println("Before the break");
                    if(t) break second; // break out of second block
                    System.out.println("this won't execute");
                }
                System.out.println("this won't execute");
            }
            System.out.println("this is after second block.");
        }

        // one of the most common uses for a labeled break statement is to exit from nested loops.
        // using break to exit from nested loops

        outer: for(int i = 0; i < 3; i++) {
            System.out.print("Pass " + i + ": ");
            for (int j = 0; j < 100; j++) {
                if (j ==10) break outer;// exit both loops
                System.out.print(j + " ");
            }
            System.out.println("this will not print");
        }
        System.out.println("loop complete");
    }
}
