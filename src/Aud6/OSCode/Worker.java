//package Aud6.OSCode;
//
//import java.io.*;
//import java.net.Socket;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//public class Worker extends Thread {
//    private Socket socket;
//    private BufferedWriter writer = null;
//
//    public Worker(Socket socket,BufferedWriter writer){
//        this.socket = socket;
//        this.writer = writer;
//    }
//
//    @Override
//    public void run() {
//        try {
//            receiveData(socket);
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//
//    }
//    private void receiveData(Socket socket) throws IOException {
//        DataInputStream dis = new DataInputStream(socket.getInputStream());
//        int numFiles = dis.readInt();
//
//        synchronized (Worker.class){
//            writer.write(String.format("%s %d - %d",socket.getInetAddress().getHostAddress(),
//                    socket.getPort(),numFiles));
//            writer.newLine();
//            writer.flush();
//        }
//
//    }
//
//}