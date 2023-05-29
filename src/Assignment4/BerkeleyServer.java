package Assignment4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class BerkeleyServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            List<Long> clientTimes = new ArrayList<>();

            System.out.println("Server is running. Waiting for clients...");

            boolean flag = true;
            while (flag) {
                Socket socket = serverSocket.accept();

                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

                long clientTime = inputStream.readLong();
                System.out.println("clientTime received at Server's end: " + clientTime);


                if (clientTime == -1) flag = false;
                else clientTimes.add(clientTime);

                outputStream.writeUTF("Time received at server's end: " + new Date(clientTime));

                socket.close();

                if (!flag) break;
            }

            long sum = 0;
            for (long time : clientTimes) {
                sum += time;
            }
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
