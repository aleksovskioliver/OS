package Aud6.LAB4_193071.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer extends Thread{

    private DatagramSocket socket;

    private byte[] buf = new byte[256];

    public UDPServer(int port) {
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        DatagramPacket packet = new DatagramPacket(buf,buf.length);

        while(true) {
            try {
                socket.receive(packet);
                String received = new String(packet.getData(),0, packet.getLength());
                System.out.println("RECEIVED:"+received);
                InetAddress address = packet.getAddress();
                int port = packet.getPort();

                packet = new DatagramPacket(buf,buf.length,address,port);
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        UDPServer server = new UDPServer(9753);
        server.start();
    }
}
