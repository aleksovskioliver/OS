//package Aud6.OSCode;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class TCPServer extends Thread{
//    int port;
//    String path;
//    ServerSocket serverSocket = null;
//    BufferedWriter writer = null;
//
//    public TCPServer(String path,int port){
//        this.path = path;
//        this.port = port;
//    }
//
//    @Override
//    public void run() {
//        try {
//            this.writer = new BufferedWriter(new FileWriter(path,true));
//            this.serverSocket = new ServerSocket(port);
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//
//        try {
//            while (true) {
//                Socket socket = serverSocket.accept();
//                new Worker(socket,writer).start();
//            }
//
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//    }
//}
