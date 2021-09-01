package Aud6.code;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {

    private DatagramSocket socket;
    private byte[] buffer;
    //private InetAddress address = InetAddress.getByName("194.149.135.49");

    public UDPClient() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
    }

    public void send() throws IOException {
        System.out.println("UDP Client started...");
            String message = "193071";
            buffer = message.getBytes();

            //Probav so 194.149.135.49 no ne raboti na ovaa IP, zatoa go napraviv da raboti na localHost.
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), 9753);
            socket.send(packet);
            System.out.println("Sent!");

            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            System.out.println("Response from server: " + new String(packet.getData(), 0, packet.getLength()));
    }
    public static void main(String[] args) throws IOException {
        UDPClient client = new UDPClient();
        client.send();
    }

}

