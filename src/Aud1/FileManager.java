package Aud1;

import java.io.*;
import java.util.Date;

public interface FileManager {

    void createNewFile(String filePath) throws FileExistException, IOException;

    File[] getFilesInFolder(File file) throws FileNotFoundException;

    void printFileName(File file, PrintStream writer) throws FileNotFoundException;

    String getAbsolutePath(String relativePath) throws FileNotFoundException;

    long getFileSize(String filePath) throws FileNotFoundException;

    void printFilePermissions(File f,PrintStream writer) throws FileNotFoundException;

    void createFolder(String folderPath) throws FileExistException;

    void renameFile(File src,File dest) throws FileExistException, FileNotFoundException;

    Date getLastModified(String filePath) throws FileNotFoundException;

    boolean deleteFolder(File folder) throws FileNotFoundException;

    File[] filterImagesFilesInDir(String dirPath) throws FileNotFoundException;

    void filterImagesFilesInDirRec(File file,PrintStream out) throws FileNotFoundException;


}
