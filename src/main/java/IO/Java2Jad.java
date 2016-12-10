package IO;

import java.io.*;

/**
 * Created by sunny-chen on 16/11/26.
 */
public class Java2Jad {

    public static void main(String[] args) throws Exception {
        File dir = new File("/Users/sunny-chen/Desktop/java/thread");
        if (!(dir.exists() && dir.isDirectory())) {
            throw new Exception("目录不存在");
        }

        File[] file = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith("java");
            }
        });

        File destDir = new File("/Users/sunny-chen/Desktop/jad");
        if (!destDir.exists())
            destDir.mkdirs();

        for (File f : file) {
            FileInputStream is = new FileInputStream(f);
            String destName = f.getName().replaceAll("\\.java$", ".jad");
            FileOutputStream os = new FileOutputStream(new File(destDir, destName));
            copy(is, os);
            System.out.println(f.getName() + "成功被复制");
            is.close();
            os.close();
        }
    }

    private static void copy(FileInputStream is, FileOutputStream os) throws IOException {
        int len = 0;
        byte[] buff = new byte[1024];
        while ((len = is.read(buff)) != -1) {
            os.write(buff, 0, len);
        }
    }
}
