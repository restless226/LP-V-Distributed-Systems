package Assignment4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockServer {
    public static void main(String[] args) {
        try {
            int port = 8080;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Clock server started...");

            // Accept client connections
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Start a new thread to handle client
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                while (true) {
                    // Send current time to client
                    String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                    out.println(currentTime);

                    Thread.sleep(5000); // Sleep for 5 seconds
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}