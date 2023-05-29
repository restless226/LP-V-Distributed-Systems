package Assignment4;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockClient {

    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Server IP address
        int serverPort = 8080; // Server port

        try {
            Socket socket = new Socket(serverAddress, serverPort);

            // Create a thread to send time to the server
            Thread sendThread = new Thread(() -> {
                try {
                    while (true) {
                        Date currentTime = new Date();
                        String timeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime);

                        // Send the current time to the server
                        socket.getOutputStream().write(timeString.getBytes());
                        socket.getOutputStream().flush();

                        System.out.println("Recent time sent successfully");

                        Thread.sleep(5000); // Wait for 5 seconds
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });

            // Create a thread to receive synchronized time from the server
            Thread receiveThread = new Thread(() -> {
                try {
                    while (true) {
                        byte[] buffer = new byte[1024];
                        int bytesRead = socket.getInputStream().read(buffer);

                        if (bytesRead > 0) {
                            String synchronizedTime = new String(buffer, 0, bytesRead);
                            System.out.println("Synchronized time at the client is: " + synchronizedTime);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // Start the send and receive threads
            sendThread.start();
            receiveThread.start();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}