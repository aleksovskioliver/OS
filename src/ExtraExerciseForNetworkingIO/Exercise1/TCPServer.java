//package ExtraExerciseForNetworkingIO;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class TCPServer extends Thread {
//    private int port;
//    private String path;
//    BufferedWriter writer;
//
//    public TCPServer(String path,int port){
//        this.path = path;
//        this.port = port;
//    }
//
//    @Override
//    public void run() {
//        ServerSocket serverSocket = null;
//
//        try {
//            serverSocket = new ServerSocket(port);
//            writer = new BufferedWriter(new FileWriter(path));
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//        while (true){
//            Socket socket = null;
//            try {
//                socket = serverSocket.accept();
//                new WorkerThread(socket,writer).start();
//
//            } catch (IOException exception) {
//                exception.printStackTrace();
//            }
//        }
//
//    }
//}
