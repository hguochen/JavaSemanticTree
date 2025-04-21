package libraries.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReaderDemo {
    public static void main(String[] args) throws IOException {
        // read a text file
//        Buffered Streams reduce I/O operations by reading/writing larger chunks at once. Always prefer buffered versions for performance:
//        BufferedReader, BufferedWriter
//        BufferedInputStream, BufferedOutputStream
        BufferedReader reader = new BufferedReader(new FileReader("./src/libraries/io/example.txt"));
        String line;

        while((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();

        // read a file and print it's contents
        BufferedReader reader2 = new BufferedReader(new FileReader("./src/libraries/io/output.txt"));
        String line2;

        while ((line2 = reader2.readLine()) != null) {
            System.out.println(line2);
        }
        reader.close();
    }
}
