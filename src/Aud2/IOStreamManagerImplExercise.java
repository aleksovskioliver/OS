package Aud2;
import java.io.*;

public class IOStreamManagerImplExercise implements IOStreamManager{

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
        }catch (IOException e){
            System.out.println("Exception by copyFilesByteByByte");
        }finally {
            if (inputStream!=null)
                inputStream.close();
            if (outputStream!=null)
                outputStream.close();
        }
    }

    @Override
    public void printContentOfTxtFile(File f, PrintStream printer) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(f));
            String line = null;

            while ((line = reader.readLine()) != null){
                printer.println(line);
                printer.flush();
            }
        }catch (IOException e){
            System.out.println("Exception by printContentOfTxtFile");
        }finally {
            if (reader!=null)
                reader.close();
            if (printer!=null)
                printer.close();
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
                writer.flush();
            }
        }catch (IOException e){
            System.out.println("Exception by readContentFromStdInput");
        }finally {
            if (reader!=null)
                reader.close();
            if (writer!=null)
                writer.close();
        }
    }
    @Override
    public void writeToTextFile(File to, String text, Boolean append) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(to,append));
            writer.write(text);
            writer.flush();
        }catch (IOException e){
            System.out.println("Exception by writeToTextFile");
        }finally {
            if (writer != null)
                writer.close();
        }
    }

    @Override
    public void memoryUnsafeTextFileCopy(File from, File to) throws IOException {

    }

    @Override
    public void memorySafeTextFileCopy(File from, File to) throws IOException {
        BufferedWriter writer = null;
        BufferedReader reader = null;
        try {
            writer = new BufferedWriter(new FileWriter(to));
            reader = new BufferedReader(new FileReader(from));
            String line = null;

            while ((line = reader.readLine()) != null){
                writer.write(line);
                writer.flush();
            }
        }catch (IOException e){
            System.out.println("Exception by memorySafeTextFileCopy");
        }finally {
            if (writer != null)
                writer.close();
        }
    }

    @Override
    public void readFilesWithLineNumber(File from, OutputStream is) throws IOException {
        BufferedReader reader = null;
        //BufferedWriter writer = null;
        PrintWriter writer = null;
        int lineNum = 1;
        try {
            reader = new BufferedReader(new FileReader(from));
         //   Bufferwriter = new BufferedWriter(new OutputStreamWriter(is));
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(is)));
            String line = null;

            while ((line = reader.readLine()) != null){
                writer.write(String.format("%d:%s",lineNum++,line));
                writer.flush();
            }
        }catch (IOException e){
            System.out.println("Exception by memorySafeTextFileCopy");
        }finally {
            if (reader!=null)
                reader.close();
            if (writer != null)
                writer.close();
        }

    }

    @Override
    public void writeBinaryDataToBFile(File to, Object... objects) throws IOException {
        DataOutputStream dataOutputStream = null;
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(to));
            for (Object o: objects){
                if (o instanceof String)
                    dataOutputStream.writeUTF((String) o);
                else if (o instanceof Integer)
                    dataOutputStream.writeInt((Integer) o);
                else if (o instanceof Double)
                    dataOutputStream.writeDouble((Double) o);
            }
        }catch (IOException e){
            System.out.println("Exception by writeBinaryDataToBFile");
        }finally {
            if (dataOutputStream!=null)
            {
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
            for (Object o : objects){
                if (o instanceof Integer)
                     o = dataInputStream.readInt();
                else if (o instanceof String)
                    o = dataInputStream.readUTF();
                else if (o instanceof Double)
                    o = dataInputStream.readDouble();

                System.out.println(o);
            }
        }catch (IOException e){
            System.out.println("Exception by writeBinaryDataToBFile");
        }finally {
            if (dataInputStream!=null)
            {
                dataInputStream.close();
            }
        }
    }

    @Override
    public void writeToRandomAccessFile(File to) throws IOException {
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(to,"rw");
            for (int i=0;i<10;i++){
                randomAccessFile.writeDouble(i*1.5);
            }
            randomAccessFile.writeUTF("THE END");
        }catch (IOException e){
            System.out.println("Exception by writeBinaryDataToBFile");
        }finally {
            if (randomAccessFile!=null)
            {
                randomAccessFile.close();
            }
        }
    }

    @Override
    public void readFromRandomAccessFile(File from, PrintStream out) throws IOException {
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(from,"r");
            for (int i=0;i<10;i++){
                out.println(randomAccessFile.readDouble());
            }
            out.println(randomAccessFile.readUTF());
        }catch (IOException e){
            System.out.println("Exception by writeBinaryDataToBFile");
        }finally {
            if (randomAccessFile!=null)
            {
                randomAccessFile.close();
            }
        }
    }
}
