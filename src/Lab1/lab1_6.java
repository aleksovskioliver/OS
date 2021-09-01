package Lab1;

import java.io.*;

public class lab1_6 {

    public static void main(String[] args) throws IOException {

        String srcPath = "C:\\Users\\aleks\\Desktop\\Faks predavanja\\4semestar 2021\\OSJAVA\\src\\Lab1\\izvor.txt";
        String destPath = "C:\\Users\\aleks\\Desktop\\Faks predavanja\\4semestar 2021\\OSJAVA\\src\\Lab1\\destinacija.txt";

        copyTextFile(srcPath,destPath);

    }

    public static int countVowels(String line){
        int vowels = 0;
        line = line.toLowerCase();
        for (int i=0;i<line.length();i++){
            char ch = line.charAt(i);
            if (ch=='а'|| ch=='е' || ch=='и' ||ch=='о' || ch=='у')
                vowels++;
        }
        return vowels;
    }
    public static void copyTextFile(String srcPath,String destPath) throws IOException {
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(srcFile));
            writer = new BufferedWriter(new FileWriter(destFile));
            String line = null;

            while ((line=reader.readLine()) != null){
                int vowels = countVowels(line);
                writer.write(String.format("%d\n",vowels));
            }

        }catch (IOException exception){
            System.out.println("IOException thrown - copyTextFile");
        }finally {
            if (reader!=null) reader.close();
            if (writer!=null){
                writer.flush();
                writer.close();
            }
        }
    }
}
