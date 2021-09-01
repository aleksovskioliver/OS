package Lab2;

import java.io.File;

public class FileScanner extends Thread {

    private String fileToScan;
    //TODO: Initialize the start value of the counter
    private static Long counter = 0L;

    public FileScanner (String fileToScan) {
        this.fileToScan=fileToScan;
        //TODO: Increment the counter on every creation of FileScanner object
        synchronized (this){
            counter++;
        }
    }

    public static void printInfo(File file)  {

        /*
         * TODO: Print the info for the @argument File file, according to the requirement of the task
         * */
        //dir: lab1 - reshenija 100
        if (file.isDirectory()){
            System.out.printf("dir: %s - resenija %d\n",file.getName(),getDirectoryTotalFiles(file));
        }
        //file: spisok.pdf 29198
        else if (file.isFile()){
            System.out.printf("file: %s %d\n",file.getName(),file.length());
        }

    }
    public static long getDirectoryTotalFiles(File f){
        if (!f.isDirectory())
            return 0;

        int totalFiles = 0;
        for (File file : f.listFiles()){
            if (file.isFile())
                totalFiles++;
            else if (file.isDirectory())
                totalFiles += getDirectoryTotalFiles(file);
        }
        return totalFiles;
    }

    public static Long getCounter () {
        return counter;
    }


    public void run() {

        //TODO Create object File with the absolute path fileToScan.
        File file = new File(fileToScan);

        //TODO Create a list of all the files that are in the directory file.
        File [] files = file.listFiles();


        for (File f : files) {

            /*
             * TODO If the File f is not a directory, print its info using the function printInfo(f)
             * */
            if (!f.isDirectory())
                printInfo(f);

            /*
             * TODO If the File f is a directory, create a thread from type FileScanner and start it.
             * */
            if (f.isDirectory()){
                FileScanner newThread = new FileScanner(f.getAbsolutePath());
                newThread.start();

//TODO: wait for all the FileScanner-s to finish
                try {
                    newThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }

    }

    public static void main (String [] args) throws InterruptedException {
        String FILE_TO_SCAN = "C:\\Users\\Oliver\\Desktop\\stipendija2020";

        //TODO Construct a FileScanner object with the fileToScan = FILE_TO_SCAN
        FileScanner fileScanner = new FileScanner(FILE_TO_SCAN);

        //TODO Start the thread from type FileScanner
        fileScanner.start();

        //TODO wait for the fileScanner to finish
        fileScanner.join();

        //TODO print a message that displays the number of thread that were created
        System.out.println(getCounter());

    }
}