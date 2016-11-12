package IO;

import java.io.File;
import java.io.IOException;

/**
 * Created by sunny-chen on 16/11/9.
 */
public class App {

    public static String path = "/Users/sunny-chen/Documents/JAVA/SharpJava/src/main/java/IO/file";

    public static void main(String[] args) {
        App app = new App();
        try {
            app.createFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFile() throws IOException {
        File file =  new File(path);
        file.createNewFile();
    }
}
