//package Aud6.code;
//
//
//import java.io.BufferedWriter;
//import java.io.DataInputStream;
//import java.io.IOException;
//import java.net.Socket;
//
////socket = IP + port ----->od koja IP i na koja porta e povrzan nekoj client
////socket on Server for each Client
//public class ServerWorkerThread extends Thread{
//
//    private Socket socket;      //ova ni e ip i portata
//    private BufferedWriter writer;
//
//    public ServerWorkerThread(Socket socket,BufferedWriter writer){
//        this.socket = socket;
//        this.writer = writer;
//    }
//
//    public void receiveData(Socket socket) throws IOException {
//        DataInputStream dis = new DataInputStream(socket.getInputStream());
//        int numFiles = dis.readInt();
//        synchronized (ServerWorkerThread.class){
//            writer.write(String.format("%s %d %d",socket.getInetAddress().getHostAddress(),
//                    socket.getPort(),numFiles));
//            writer.newLine();
//            writer.flush();
//        }
//    }
//
//
//    @Override
//    public void run() {
//        try {
//            receiveData(socket);
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//    }
//}
