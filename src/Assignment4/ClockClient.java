package Assignment4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClockClient {
    public static void main(String[] args) {
        try {
            String serverAddress = "127.0.0.1";
            int port = 8080;

            Socket socket = new Socket(serverAddress, port);
            System.out.println("Connected to clock server");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                // Receive synchronized time from server
                String synchronizedTime = in.readLine();
                System.out.println("Synchronized time at the client is: " + synchronizedTime);

                Thread.sleep(5000); // Sleep for 5 seconds
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}