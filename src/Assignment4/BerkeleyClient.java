package Assignment4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class BerkeleyClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter no of clients: ");
        int n = scanner.nextInt();
        try {
            // Connect to the server
            for (int i = 0; i < n + 1; i++) {
                Socket socket = new Socket("localhost", 12345);

                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

                long clientTime = System.currentTimeMillis();
                System.out.println("clientTime at client " + i + " before sending: " + clientTime);

                if (i == n) outputStream.writeLong(-1);
                else outputStream.writeLong(clientTime);

                String acknowledgment = inputStream.readUTF();
                System.out.println("acknowledgment for client " + i + " is: " + acknowledgment + "\n");

                socket.close();
            }

            Socket clientSocket = new Socket("localhost", 12345);

            DataInputStream timeInputStream = new DataInputStream(clientSocket.getInputStream());

            long averageTime = timeInputStream.readLong();

            long adjustedTime = System.currentTimeMillis() + (averageTime - System.currentTimeMillis());

            System.out.println("Adjusted time at client's end: " + adjustedTime);

            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}