import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient extends Thread{

    private String serverName;
    private int serverPort;

    public TCPClient(String serverName, int serverPort) {
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    @Override
    public void run() {
        Socket socket = null;

        Scanner scanner = null;
        PrintWriter writer = null;
        BufferedReader reader = null;

        try {
            socket = new Socket(serverName,serverPort);
            writer = new PrintWriter(socket.getOutputStream());

            scanner = new Scanner(System.in);
            while (true) {
                String line = scanner.nextLine();
                writer.println(line);
                writer.flush();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TCPClient client = new TCPClient("194.149.135.49",9000);
        client.start();
    }
}
