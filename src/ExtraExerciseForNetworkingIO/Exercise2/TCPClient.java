//package ExtraExerciseForNetworkingIO.Exercise2;
//
//import java.io.*;
//import java.net.Socket;
//import java.net.UnknownHostException;
//
//public class TCPClient extends Thread {
//
//    String serverAddress;
//    int port;
//    String folderToSearch;
//
//    public TCPClient(String serverAddress,int port,String folderToSearch){
//        this.serverAddress = serverAddress;
//        this.port = port;
//        this.folderToSearch = folderToSearch;
//    }
//    private static long getTotalSize(String folderToSearch){
//        File file = new File(folderToSearch);
//        long totalSize = 0;
//
//        FilenameFilter filenameFilter = new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                String lowerCase = name.toLowerCase();
//                return (name.endsWith(".jpg"));
//            }
//        };
//        File[] files = file.listFiles(filenameFilter);
//        for (File f: files){
//            if (f.isFile())
//                totalSize += f.length();
//            if (f.isDirectory())
//                getTotalSize(f.getAbsolutePath());
//        }
//        return totalSize;
//    }
//    public static void sendDataToServer(String serverAddress,int port,long totalSize) throws IOException {
//        Socket socket = new Socket(serverAddress,port);
//        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//        dos.writeLong(totalSize);
//    }
//
//    @Override
//    public void run() {
//        long totalSize = getTotalSize(folderToSearch);
//        try {
//            sendDataToServer(serverAddress,port,totalSize);
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//    }
//}
