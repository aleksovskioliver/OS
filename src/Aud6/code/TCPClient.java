//package Aud6.code;
//
//import java.io.*;
//import java.net.Socket;
//
//public class TCPClient extends Thread{
//
//    String serverAddress;
//    int serverPort;
//    String folderPath;
//
//
//    public TCPClient(String serverAddress,int serverPort,String folderPath){
//        this.serverAddress = serverAddress;
//        this.serverPort = serverPort;
//        this.folderPath = folderPath;
//    }
//
//    private static int getFiles(String folderPath){
//        File file = new File(folderPath);
//        int num = 0;
//        FilenameFilter filenameFilter = new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                String lowerCaseName = name.toLowerCase();
//                if (lowerCaseName.endsWith(".txt") || lowerCaseName.endsWith(".pdf"))
//                    return true;
//                else
//                    return false;
//            }
//        };
//
//        File[] files = file.listFiles(filenameFilter);
//        for (File f : files){
//            if (f.isFile() && f.length()>10*1024 && f.length()<100*1024)
//                num++;
//            else if (f.isDirectory())
//                getFiles(f.getName());
//        }
//        return num;
//    }
//
//    private static void sendDataToServer(String serverAddress,int port,int numFiles){
//        Socket socket = null;
//        try {
//            socket = new Socket(serverAddress,port);
//            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//            dos.writeInt(numFiles);
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }finally {
//            if (socket!=null) {
//                try {
//                    socket.close();
//                } catch (IOException exception) {
//                    exception.printStackTrace();
//                }
//            }
//        }
//    }
//
//    @Override
//    public void run() {
//        int numFiles = getFiles(this.folderPath);
//        sendDataToServer(this.serverAddress,this.serverPort,numFiles);
//    }
//}
