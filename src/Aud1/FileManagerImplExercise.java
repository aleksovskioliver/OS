package Aud1;
import java.io.*;
import java.util.Date;

public class FileManagerImplExercise implements FileManager {

    @Override
    public void createNewFile(String file) throws FileExistException, IOException {
        File f = new File(file);
        if (f.exists())
            throw new FileExistException();
        f.createNewFile();
    }

    @Override
    public File[] getFilesInFolder(File file) throws FileNotFoundException {
        if (!file.exists())
            throw new FileNotFoundException();
        if (!file.isDirectory())
            throw new FileNotFoundException();
        return file.listFiles();
    }

    @Override
    public void printFileName(File file, PrintStream writer) throws FileNotFoundException {
        File[] files = getFilesInFolder(file);
        for (File f : files) {
            writer.println(f.getName());
        }
    }

    @Override
    public String getAbsolutePath(String relativePath) throws FileNotFoundException {
        File f = new File(relativePath);
        if (!f.exists())
            throw new FileNotFoundException();
        return f.getAbsolutePath();
    }

    @Override
    public long getFileSize(String file) throws FileNotFoundException {
        File f = new File(file);
        if (!f.exists())
            throw new FileNotFoundException();
        return f.length();
    }

    @Override
    public void printFilePermissions(File f, PrintStream writer) throws FileNotFoundException {
        if (!f.exists())
            throw new FileNotFoundException();

        writer.printf("Read: %x", f.canRead());
        writer.printf("Write: %x", f.canWrite());
        writer.printf("Execute: %x", f.canExecute());
    }

    @Override
    public void createFolder(String folder) throws FileExistException {
        File f = new File(folder);
        if (f.exists())
            throw new FileExistException();
        f.mkdir();
    }

    @Override
    public void renameFile(File src, File dest) throws FileExistException, FileNotFoundException {
        if (!src.exists())
            throw new FileNotFoundException();
        if (dest.exists())
            throw new FileExistException();
        src.renameTo(dest);
    }

    @Override
    public Date getLastModified(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        if (!f.exists())
            throw new FileNotFoundException();
        return new Date(f.lastModified());
    }

    @Override
    public boolean deleteFolder(File folder) throws FileNotFoundException {
        if (!folder.exists())
            throw new FileNotFoundException();
        if (!folder.isDirectory())
            throw new FileNotFoundException();

        File[] files = folder.listFiles();
        for (File f : files) {
            if (f.isDirectory())
                return deleteFolder(f);
            if (f.isFile())
                f.delete();
        }
        return folder.delete();
    }

    @Override
    public File[] filterImagesFilesInDir(String dirPath) throws FileNotFoundException {
        File folder = new File(dirPath);
        if (!folder.exists())
            throw new FileNotFoundException();
        if (!folder.isDirectory())
            throw new FileNotFoundException();

        return folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return (name.endsWith(".jpg") || name.endsWith(".png"));
            }
        });
    }

    @Override
    public void filterImagesFilesInDirRec(File file, PrintStream out) throws FileNotFoundException {
        if (!file.exists())
            throw new FileNotFoundException();
        if (!file.isDirectory())
            throw new FileNotFoundException();

        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory())
                filterImagesFilesInDirRec(f, out);
            if (f.isFile() && (f.getName().endsWith(".jpg") || f.getName().endsWith(".png")))
                out.println(f.getName());
        }
    }
}
