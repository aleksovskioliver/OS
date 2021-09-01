//package ExtraExerciseForNetworkingIO.Exercise3;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class TCPServer extends Thread{
//    int port;
//    ServerSocket serverSocket;
//
//    public TCPServer(int port){
//        this.port = port;
//    }
//
//    @Override
//    public void run() {
//        try {
//            serverSocket = new ServerSocket(port);
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//        while (true) {
//            Socket socket;
//            try {
//                socket = serverSocket.accept();
//                new WorkerThread(socket).start();
//            } catch (IOException exception) {
//                exception.printStackTrace();
//            }
//        }
//    }
//}
