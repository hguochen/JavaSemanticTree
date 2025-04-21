package libraries.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ByteWriteDemo {
    public static void main(String[] args) throws IOException {
        InputStream in = new FileInputStream("./src/libraries/io/source.jpg");
        OutputStream out = new FileOutputStream("./src/libraries/io/copy.jpg");

        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.close();
    }
}
