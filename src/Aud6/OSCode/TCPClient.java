//package Aud6.OSCode;
//
//import java.io.DataOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.net.Socket;
//import java.net.UnknownHostException;
//import java.util.TreeMap;
//
//public class TCPClient extends Thread {
//    String serverName;
//    int port;
//    String folderToSearch;
//
//    public TCPClient(String serverName,int port,String folderToSearch){
//        this.serverName = serverName;
//        this.port = port;
//        this.folderToSearch = folderToSearch;
//    }
//
//    @Override
//    public void run() {
//        int numFiles = getFiles(this.folderToSearch);
//        try {
//            sendDataToServer(serverName,port,numFiles);
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//    }
//
//    private static int getFiles(String folderToSearch){
//        File file = new File(folderToSearch);
//        File[] files = file.listFiles();
//        int numFiles = 0;
//        for (File f : files){
//            if (f.isFile() && f.length()<20*1024) {
//                numFiles++;
//            }
//        }
//        return numFiles;
//    }
//    private static void sendDataToServer(String serverName,int port,int numFiles) throws IOException {
//        Socket socket = null;
//
//        try {
//            socket = new Socket(serverName,port);
//            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//            dos.writeInt(numFiles);
//
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }finally {
//            if(socket!=null) socket.close();
//        }
//    }
//
//
//}
