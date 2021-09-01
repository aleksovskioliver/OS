package Aud2;

import java.io.*;
import java.nio.BufferOverflowException;
import java.nio.DoubleBuffer;

public class IOStreamManagerImpl implements IOStreamManager{

    @Override
    public void copyFilesByteByByte(File from, File to) throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        int c = -1;

        try {
            while ((c = inputStream.read()) != -1){
                outputStream.write(c);
                outputStream.flush();
            }
        } catch (IOException exception) {
            System.out.println("IOException thrown - copyFilesByteByByte");
        }finally {
            if (inputStream!=null) inputStream.close();
            if (outputStream!=null) outputStream.close();;
        }
    }

    @Override
    public void printContentOfTxtFile(File f, PrintStream printer) throws IOException {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(f));
            String line = null;

            while ((line = reader.readLine())!=null){
                printer.println(line);
            }
        } catch (IOException exception) {
            System.out.println("IOException thrown - printContentOfTxtFile");
        }finally {
            if (reader!=null) reader.close();

        }
    }

    @Override
    public void readContentFromStdInput(OutputStream to) throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            writer = new BufferedWriter(new OutputStreamWriter(to));
            String line = null;

            while ((line = reader.readLine()) != null){
                writer.write(line);
                writer.newLine();
            }
        }catch (IOException exception) {
            System.out.println("IOException thrown - readContentFromStdInput");
        }finally {
            if (reader!=null) reader.close();
            if (writer!=null){
                writer.flush();
                writer.close();
            }
        }
    }

    @Override
    public void writeToTextFile(File to, String text, Boolean append) throws IOException {
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(to,append));
            writer.write(text);
        }catch (IOException exception) {
            System.out.println("IOException thrown - writeToTextFile");
        }finally {
            if (writer!=null){
                writer.flush();
                writer.close();
            }
        }
    }

    @Override
    public void memoryUnsafeTextFileCopy(File from, File to) throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        StringBuilder sb = new StringBuilder();

        try {
            reader = new BufferedReader(new FileReader(from));
            writer = new BufferedWriter(new FileWriter(to));
            String line = null;

            while ((line = reader.readLine()) != null){
                sb.append(line).append("\n");
            }
            writer.write(sb.toString());
        }catch (IOException exception) {
            System.out.println("IOException thrown - memoryUnsafeTextFileCopy");
        }finally {
            if (reader!=null) reader.close();
            if (writer!=null){
                writer.flush();
                writer.close();
            }
        }
    }

    @Override
    public void memorySafeTextFileCopy(File from, File to) throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;
       // StringBuilder sb = new StringBuilder();

        try {
            reader = new BufferedReader(new FileReader(from));
            writer = new BufferedWriter(new FileWriter(to));
            String line = null;

            while ((line = reader.readLine()) != null){
               //sb.append(line).append("\n");
                writer.write(line);
            }
           // writer.write(sb.toString());
        }catch (IOException exception) {
            System.out.println("IOException thrown - memorySageTextFileCopy");
        }finally {
            if (reader!=null) reader.close();
            if (writer!=null){
                writer.flush();
                writer.close();
            }
        }
    }

    @Override
    public void readFilesWithLineNumber(File from, OutputStream is) throws IOException {
        BufferedReader reader = null;
        PrintWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(from));
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(is)));
            int lineCount = 1;
            String line = null;

            while ((line = reader.readLine()) != null){
                writer.println(String.format("%d:%s",lineCount++,line));
            }
        }catch (IOException exception) {
            System.out.println("IOException thrown - readFilesWithLineNumber");
        }finally {
            if (reader!=null) reader.close();
            if (writer!=null){
                writer.flush();
                writer.close();
            }
        }
    }

    @Override
    public void writeBinaryDataToBFile(File to, Object... objects) throws IOException {
        DataOutputStream dataOutputStream = null;

        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(to));

            for (Object o:objects){
                if (o instanceof String)
                    dataOutputStream.writeUTF((String) o);
                else if (o instanceof Integer)
                    dataOutputStream.writeInt((Integer) o);
                else if (o instanceof Double)
                    dataOutputStream.writeDouble((Double) o);
            }

        }catch (IOException exception) {
            System.out.println("IOException thrown - writeBinaryDataToBFile");
        }finally {
            if (dataOutputStream!=null){
                dataOutputStream.flush();
                dataOutputStream.close();
            }
        }
    }

    @Override
    public void readBinaryDataFromBFile(File from, Object... objects) throws IOException {
        DataInputStream dataInputStream = null;

        try {
            dataInputStream = new DataInputStream(new FileInputStream(from));

            for (Object o:objects){
                if (o instanceof String)
                   o = dataInputStream.readUTF();
                else if (o instanceof Integer)
                    o = dataInputStream.readInt();
                else if (o instanceof Double)
                    o = dataInputStream.readDouble();

                System.out.println(o);
            }

        }catch (IOException exception) {
            System.out.println("IOException thrown - readBinaryDataFromBFile");
        }finally {
            if (dataInputStream!=null){
                dataInputStream.close();
            }
        }
    }

    @Override
    public void writeToRandomAccessFile(File to) throws IOException {
        RandomAccessFile randomAccessFile = null;

        try {
            randomAccessFile = new RandomAccessFile(to,"rw");

            for (int i=0;i<10;i++)
                randomAccessFile.writeDouble(i*1.4);

            randomAccessFile.writeUTF("THE END");
        }catch (IOException exception) {
            System.out.println("IOException thrown - writeToRandomAccessFile");
        }finally {
            if (randomAccessFile!=null){
                randomAccessFile.close();
            }
        }
    }

    @Override
    public void readFromRandomAccessFile(File from, PrintStream out) throws IOException {
        RandomAccessFile randomAccessFile = null;

        try {
            randomAccessFile = new RandomAccessFile(from,"r");

            for (int i=0;i<10;i++)
                out.println(randomAccessFile.readDouble());
            out.println(randomAccessFile.readUTF());
        }catch (IOException exception) {
            System.out.println("IOException thrown - readFromRandomAccessFile");
        }finally {
            if (randomAccessFile!=null){
                randomAccessFile.close();
            }
        }
    }
}
