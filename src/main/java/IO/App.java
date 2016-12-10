package IO;

import java.io.*;

/**
 * Created by sunny-chen on 16/11/9.
 */
public class App {

    public static String path = "/Users/sunny-chen/Documents/JAVA/SharpJava/src/main/java/IO/file/";

    public static void main(String[] args) {
        App app = new App();
        try {
//            app.createFile();
//            app.readFileByStream();
//            app.fileCopyByInputStream();
            app.readStrByReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建文件
     */
    public void createFile() throws IOException {
        File file = new File(path + "img1.jpg");
        System.out.println("该分区大小" + file.getTotalSpace() / (1024 * 1024 * 1024) + "G");
        file.mkdirs();
//        file.delete();
        System.out.println("文件名  " + file.getName());
    }

    /**
     * 字节流读取文件
     */
    public void readFileByStream() throws IOException {
        int count = 0;
        InputStream is = new FileInputStream(new File(path + "img1.jpg"));
        while (is.read() != -1) {
            count++;
        }
        System.out.println("长度是：" + count + "字节");
        is.close();
    }

    /**
     * 文件字节流操作。
     */
    public void fileCopyByInputStream() throws IOException {
        String copyPath = "/Users/sunny-chen/Documents/JAVA/SharpJava/src/main/java/IO/file/img2.jpg";
        byte[] buffer = new byte[512];                                    //读写缓冲区，一次读取512字节。
        int numberRead = 0;
        FileInputStream input = new FileInputStream(path);
        FileOutputStream out = new FileOutputStream(copyPath);
        while ((numberRead = input.read(buffer)) != -1) {
            out.write(buffer, 0, numberRead);
        }
        input.close();
        out.close();
        System.out.println("拷贝成功");
    }

    public void readStrByReader() throws IOException {
        char[] buffer = new char[1];
        int numberReader = 0;
        FileReader reader = new FileReader(path + "/text.txt");
        FileWriter writer = new FileWriter(path + "/copy.txt");
//        PrintWriter writer = new PrintWriter(System.out);
        while ((numberReader = reader.read(buffer)) != -1) {
            writer.write(buffer, 0, numberReader);
        }
        reader.close();
        writer.close();
    }
}
