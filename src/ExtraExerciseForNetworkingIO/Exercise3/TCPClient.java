//package ExtraExerciseForNetworkingIO.Exercise3;
//
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.Socket;
//import java.net.UnknownHostException;
//import java.util.Locale;
//
//public class TCPClient extends Thread{
//
//    private String serverAddress;
//    private int serverport;
//
//    public TCPClient(String serverAddress,int serverport){
//        this.serverAddress = serverAddress;
//        this.serverport = serverport;
//    }
//
//    @Override
//    public void run() {
//        Socket socket;
//        try {
//            socket = new Socket(serverAddress,serverport);
//            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//
//            dos.writeDouble(4.24);
//            dos.writeLong(123123123);
//            dos.writeBoolean(true);
//            dos.writeUTF("UTF string");
//
//            dos.close();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//    }
//}
