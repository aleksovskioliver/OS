package Aud2;

import java.io.File;
import java.io.IOException;

public class IOStreamManagerMain {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\aleks\\Desktop\\Faks predavanja\\4semestar 2021\\OSJAVA\\src\\Aud2\\In.txt";
        String filePathDest = "C:\\Users\\aleks\\Desktop\\Faks predavanja\\4semestar 2021\\OSJAVA\\src\\Aud2\\Out.txt";

        IOStreamManager manager = new IOStreamManagerImpl();
        manager.printContentOfTxtFile(new File(filePath),System.out);
//        File srcFile = new File(filePath);
//        File destFile = new File(filePathDest);
//        manager.memorySafeTextFileCopy(srcFile,destFile);
    }
}
