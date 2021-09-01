package Aud2;

import java.io.*;

public interface IOStreamManager {
    void copyFilesByteByByte(File from, File to) throws IOException;

    void printContentOfTxtFile(File f, PrintStream printer) throws IOException;

    void readContentFromStdInput(OutputStream to) throws IOException;

    void writeToTextFile(File to,String text,Boolean append) throws IOException;

    void memoryUnsafeTextFileCopy(File from,File to) throws IOException;

    void memorySafeTextFileCopy(File from,File to) throws IOException;

    void readFilesWithLineNumber(File from,OutputStream is) throws IOException;

    void writeBinaryDataToBFile(File to,Object... objects) throws IOException;

    void readBinaryDataFromBFile(File from,Object... objects) throws IOException;

    void writeToRandomAccessFile(File to) throws IOException;

    void readFromRandomAccessFile(File from,PrintStream out) throws IOException;


}
