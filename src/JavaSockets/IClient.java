package JavaSockets;

import java.io.*;
import java.net.*;
import java.util.*;

public class IClient {
    public static void main(String[] args) throws IOException {
        try {
            Socket clientSocket = new Socket("127.0.0.1", 1342);
            System.out.println("Client running at 1342");

            System.out.print("Enter the number: ");
            Scanner sc = new Scanner(System.in);
            int number = sc.nextInt();

            PrintStream p = new PrintStream(clientSocket.getOutputStream());
            p.println(number);

            Scanner sc1 = new Scanner(clientSocket.getInputStream());
            int receivedNumber = sc1.nextInt();
            System.out.println("receivedNumber from IServer after squaring: " + receivedNumber);
        } catch (Exception e) {
            System.out.println("Exception at IClient: " + e);
        }
    }
}
