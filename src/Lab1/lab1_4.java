package Lab1;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.io.File;


public class lab1_4 {
    public static void main(String[] args) throws IOException {
       BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
       String line = reader.readLine();

       //String testPath = "C:\\Users\\aleks\\Desktop\\Faks predavanja\\4semestar 2021\\OSJAVA\\src\\Lab1\\random";

        File[] files = filterFileFromDir(line);

        System.out.println(Arrays.stream(files).findFirst().get().getAbsolutePath());
    }


    public static File[] filterFileFromDir (String dirPath) throws FileNotFoundException {
        File file = new File(dirPath);
        if (!file.exists()) throw new FileNotFoundException();
        if (!file.isDirectory()) throw new FileNotFoundException();

        return file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (dir.length()>1024 && dir.length()<(1024*1024)) {
                    return name.endsWith(".txt") || name.endsWith(".out");
                }
                return false;
            }
        });
    }
}