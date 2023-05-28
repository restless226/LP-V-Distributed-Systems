package JavaSockets;

import java.io.*;
import java.net.*;
import java.util.*;

public class IServer {
    public static void main(String[] args) throws UnknownHostException, IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(1342);
            System.out.println("Server running at 1342");
            Socket acceptedSocket = serverSocket.accept();

            Scanner sc = new Scanner(acceptedSocket.getInputStream());
            int number;
            number = sc.nextInt();
            System.out.println("received number from client as: " + number);

            int result = number * number;

            PrintStream p = new PrintStream(acceptedSocket.getOutputStream());
            p.println(result);
        } catch (Exception e) {
            System.out.println("Exception at IServer: " + e);
        }
    }
}
