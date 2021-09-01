package Lab1;

import Aud2.IOStreamManager;
import Aud2.IOStreamManagerImpl;

import java.io.File;
import java.io.FileNotFoundException;

public class lab1_3 {
    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "C:\\Users\\aleks\\Desktop\\Faks predavanja\\4semestar 2021\\OSJAVA\\src\\Lab1\\asdfs";

        System.out.println(najgolemDokument(filePath).getAbsolutePath());
    }
    public static File najgolemDokument (String filePath) throws FileNotFoundException {
            File file = new File(filePath);
            if (!file.exists()) throw new FileNotFoundException();
            if (!file.isDirectory()) throw new FileNotFoundException();

            File[] files = file.listFiles();
            long max = files[0].length();
            File fileReturn = null;
            for (File f : files) {
                if (f.length() > max) {
                    max = f.length();
                    fileReturn = f;
                }
            }
            return fileReturn;
        }
}