import java.io.*;
import java.net.Socket;

public class WorkerThread extends Thread{

    private Socket socket = null;

    public WorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        BufferedReader reader = null;
        PrintWriter writer = null;

        try {

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            String line = null;

            while (!(line=reader.readLine()).isEmpty()) {
                if(line.compareTo("hello:193071")==0){
                    System.out.printf("Connected:%s:%d   %s\n",socket.getInetAddress(),socket.getPort(),line);
                    writer.write(line);
                    writer.flush();
                }
                else{
                    System.out.println("Neuspesno konektiranje");
                }
            }
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (reader !=null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                writer.close();
            }
            if (socket!=null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
