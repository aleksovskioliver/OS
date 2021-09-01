//package ExtraExerciseForNetworkingIO;
//
//import java.io.BufferedWriter;
//import java.io.DataInputStream;
//import java.io.IOException;
//import java.net.Socket;
//
//public class WorkerThread extends Thread {
//    private Socket socket;
//    private BufferedWriter writer;
//
//    public WorkerThread(Socket socket,BufferedWriter writer){
//        this.socket = socket;
//        this.writer = writer;
//    }
//
//    public void receiveData(Socket socket) throws IOException {
//        DataInputStream dis = new DataInputStream(socket.getInputStream());
//        long totalSize = dis.readLong();
//        synchronized (WorkerThread.class){
//            writer.write(String.format("%s %d %d",socket.getInetAddress().getHostAddress(),
//                    socket.getPort(),totalSize));
//            writer.newLine();
//            writer.flush();
//        }
//    }
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
