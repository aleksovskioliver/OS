//package Aud6.code;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class TCPServer extends Thread{
//
//    String path;
//    int port;
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
//            this.writer = new BufferedWriter(new FileWriter(path));
//            this.serverSocket = new ServerSocket(port);
//            while (true){
//                Socket socket = this.serverSocket.accept();
//
//
//                ServerWorkerThread socketWorker  = new ServerWorkerThread(socket,writer);
//                socketWorker.start();
//            }
//
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//    }
//}
