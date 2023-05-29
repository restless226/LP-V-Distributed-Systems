package Assignment4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class BerkeleyClient {
    public static void main(String[] args) {
        try {
            // Connect to the server
            for (int i = 0; i < 6; i++) {
                Socket socket = new Socket("localhost", 12345);

                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

                long clientTime = System.currentTimeMillis();
                System.out.println("clientTime at client before sending: " + clientTime);

                if (i == 5) outputStream.writeLong(-1);
                else outputStream.writeLong(clientTime);

                String acknowledgment = inputStream.readUTF();
                System.out.println(acknowledgment);

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