package libraries.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WriterDemo {
    public static void main(String[] args) throws IOException {
        // write to a text file
        BufferedWriter writer = new BufferedWriter(new FileWriter("./src/libraries/io/output.txt"));
        writer.write("Hello, Java I/O!");
        writer.newLine();
        writer.close();

        BufferedWriter writer2 = new BufferedWriter(new FileWriter("./src/libraries/io/output2.txt"));
        writer2.write("Hello World!");
        writer2.newLine();
        writer2.write("May Lily good health!");
        writer2.newLine();

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String input = scanner.next();
            if (input.equals("done")) break;
            writer2.write(input);
            writer2.newLine();
        }
        writer2.close();
    }
}
