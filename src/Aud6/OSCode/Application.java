//package Aud6.OSCode;
//
//public class Application {
//
//    private final static String IP_ADDRES_SERVER = "localhost";
//    private final static int port = 4498;
//    private final static String FOLDER_PATH = "C:\\Users\\aleks\\Desktop";
//    private final static String FILE_PATH_TO_RESULT = "C:\\Users\\aleks\\Desktop\\Faks predavanja\\4semestar 2021\\OSJAVA\\src\\Aud6\\OSCode\\results.txt";
//
//    public static void main(String[] args) {
//        TCPServer server = new TCPServer(FILE_PATH_TO_RESULT,port);
//        server.start();
//
//        TCPClient client = new TCPClient(IP_ADDRES_SERVER,port,FOLDER_PATH);
//        client.start();
//    }
//
//}
