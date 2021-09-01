//package ExtraExerciseForNetworkingIO.Exercise3;
//
//import java.io.DataInputStream;
//import java.io.IOException;
//import java.net.Socket;
//
//public class WorkerThread extends Thread{
//    Socket socket;
//
//    public WorkerThread(Socket socket){
//        this.socket = socket;
//    }
//
//    @Override
//    public void run() {
//        try {
//            DataInputStream dis = new DataInputStream(socket.getInputStream());
//
//            Double chitamDouble = dis.readDouble();
//            Long chitamLong = dis.readLong();
//            Boolean chitamBoolean = dis.readBoolean();
//            String chitamString = dis.readUTF();
//
//            System.out.printf("%f %d %s",chitamDouble,chitamLong,chitamBoolean,chitamString);
//            dis.close();
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//    }
//}
