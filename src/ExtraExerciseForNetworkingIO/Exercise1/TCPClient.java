//package ExtraExerciseForNetworkingIO;
//
//import java.io.DataOutputStream;
//import java.io.File;
//import java.io.FilenameFilter;
//import java.io.IOException;
//import java.net.Socket;
//import java.net.UnknownHostException;
//
//public class TCPClient extends Thread{
//
//    String serverAddress;
//    int serverPort;
//    String folderTxtOutput;
//
//
//    public TCPClient(String serverAddress,int serverPort,String folderTxtOutput){
//        this.serverAddress = serverAddress;
//        this.serverPort = serverPort;
//        this.folderTxtOutput = folderTxtOutput;
//    }
//    private static long getFiles(String folderTxtOutput){
//        File file = new File(folderTxtOutput);
//        long totalSize=0;
//     //   long lastModifed = 0;
//        FilenameFilter filenameFilter = new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                String lowerCaseName = name.toLowerCase();
//                return (lowerCaseName.endsWith(".txt") || lowerCaseName.endsWith(".dat"));
//            }
//        };
//
//        File[] files = file.listFiles(filenameFilter);
//        for (File f: files) {
//            if (f.isFile() && f.length() < 512 * 1024){
//                totalSize += f.length();
//        }
//            else if(f.isDirectory())
//                getFiles(f.getName());
//        }
//        return totalSize;
//    }
//    private static void sendDataToServer(String serverAddress,int port,long totalSize){
//        Socket socket = null;
//        try {
//            socket = new Socket(serverAddress,port);
//            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//            dos.writeLong(totalSize);
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//    }
//
//    @Override
//    public void run() {
//        long totalSize = getFiles(this.folderTxtOutput);
//        sendDataToServer(this.serverAddress,this.serverPort,totalSize);
//    }
//}
