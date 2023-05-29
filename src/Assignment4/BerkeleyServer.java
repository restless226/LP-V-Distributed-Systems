package Assignment4;

import java.io.*;
import java.net.*;
import java.util.*;

public class BerkeleyServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            List<Long> clientTimes = new ArrayList<>();
            System.out.println("Server is running. Waiting for clients...");
            boolean stop = true;
            while (true) {
                Socket socket = serverSocket.accept();
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                long clientTime = inputStream.readLong();
                System.out.println("clientTime received at Server's end for client " + clientTimes.size() + " is: " + clientTime);
                if (clientTime == -1) stop = false;
                else clientTimes.add(clientTime);
                outputStream.writeUTF(String.valueOf(new Date(clientTime)));
                socket.close();
                if (!stop) break;
            }
            long sum = 0;
            for (long time : clientTimes) sum += time;
            long averageTime = sum / clientTimes.size();
            Socket socket = serverSocket.accept();
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeLong(averageTime);
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
