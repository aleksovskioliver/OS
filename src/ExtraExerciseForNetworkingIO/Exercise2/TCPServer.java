//package ExtraExerciseForNetworkingIO.Exercise2;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class TCPServer extends Thread {
//    int port;
//    String path;
//    ServerSocket serverSocket;
//    BufferedWriter writer;
//
//    public TCPServer(String path,int port){
//        this.path = path;
//        this.port = port;
//    }
//
//    @Override
//    public void run() {
//        try {
//            serverSocket = new ServerSocket(port);
//            writer = new BufferedWriter(new FileWriter(path));
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//        try {
//            Socket socket;
//            while (true){
//                socket = serverSocket.accept();
//                new WorkerThread(socket,writer).start();
//            }
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//    }
//}
