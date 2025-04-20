package libraries;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ScannerDemo {
    public static void main(String[] args) throws IOException {
        Scanner conin = new Scanner(System.in);

        int count = 0;
        double sum = 0.0;

        System.out.println("Enter numbers to average: ");

        // Write output to a file.
        FileWriter fout = new FileWriter("test.txt");
        fout.write("2 3.4 5 6 7.4 9.1 10.5 done");
        fout.close();

        // read and sum numbers
        while (conin.hasNext()) {
            if (conin.hasNextDouble()) {
                sum += conin.nextDouble();
                count++;
            } else {
                String str = conin.next();
                if (str.equals("done")) break;
                else {
                    System.out.println("Data format error.");
                    return;
                }
            }
        }
        conin.close();
        System.out.println("Average is: " + sum / count);
    }
}
