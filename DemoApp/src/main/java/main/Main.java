package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to our App");
        menu();
        System.out.println("Successfully sent");
    }

    public static void menu() throws Exception {
        String name = Util.requireText("Please enter your name:");
        String surname = Util.requireText("Please enter your surname: ");
        String url = Util.requireText("Please enter the directory for file:");
        String credential = Util.requireText("Please enter IP and PORT of server:");
        String[] IpAndPort = credential.split(":");
        String ip = IpAndPort[0];
        int port = Integer.parseInt(IpAndPort[1]);
        client(url);
    }

    public static void client(String url) throws Exception {
        Socket socket = new Socket("127.0.0.1", 1111);
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        byte[] bytes = Files.readAllBytes(Paths.get(url));
        dataOutputStream.writeInt(bytes.length);
        dataOutputStream.write(bytes);
    }

    public static void server() throws Exception {
        System.out.println("Server has just started...");
        ServerSocket serverSocket = new ServerSocket(1111);
        Socket connection = serverSocket.accept();
        System.out.println("Client has been connected...");
        InputStream inputStream = connection.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int msgLen = dataInputStream.readInt();
        byte[] arr = new byte[msgLen];
        dataInputStream.readFully(arr);
        Files.write(Paths.get("something.jpg"), arr);
        System.out.println("Server has just stopped...");
    }
}
