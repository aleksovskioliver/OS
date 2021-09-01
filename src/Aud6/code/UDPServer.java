package Aud6.code;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

public class UDPServer {
    private DatagramSocket socket;
    private byte[] buffer = new byte[256];

    public UDPServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    public void run() {
        System.err.println("UDP Server running...");
        while(true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            try {
                socket.receive(packet);
                String request = new String(packet.getData(), 0, packet.getLength());
                System.err.println("Received: " + request);

                packet = new DatagramPacket(buffer, buffer.length, packet.getAddress(), packet.getPort());
                socket.send(packet);
                System.err.println("Echoed back message!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws SocketException {
        UDPServer server = new UDPServer(9753);
        server.run();
    }

}