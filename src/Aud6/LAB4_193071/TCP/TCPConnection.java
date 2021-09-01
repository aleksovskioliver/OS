package Aud6.LAB4_193071.TCP;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPConnection {

    public static Socket socket = null;
    static {
        try {
            socket = new Socket("194.149.135.49", 9753);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static class Send extends Thread{
        private Socket socket = null;
        private String ipAdress;
        private int port;
        private DataInputStream reader = null;

        public Send(String ipAdress, int port, Socket socket) {
            this.socket = socket;
            this.ipAdress = ipAdress;
            this.port = port;
        }

        @Override
        public void run() {
            try {
                System.out.println("Connected :" + socket.getInetAddress());
                reader = new DataInputStream(socket.getInputStream());
                String line = null;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Fail connection!");
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }
    }
    static class Receive extends Thread{
        private String ipAdress;
        private int port;
        private Socket socket = null;
        private BufferedWriter writer = null;
        private Scanner scanner = null;

        public Receive(String ipAdress, int port, Socket socket) {
            this.ipAdress = ipAdress;
            this.port = port;
            this.socket = socket;
        }

        public void run() {
            try {
                scanner = new Scanner(System.in);
                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                String line = null;
                while ((line = scanner.nextLine()) != null) {
                    writer.write(line);
                    writer.write("\n");
                    writer.flush();
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Fail connection");
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
                if (scanner != null){
                    scanner.close();
                }
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
    public static void main(String[] args) {
        Send client1 = new Send("194.149.135.49", 9753, socket);
        client1.start();
        Receive client2 = new Receive("194.149.135.49", 9753, socket);
        client2.start();
    }
}
