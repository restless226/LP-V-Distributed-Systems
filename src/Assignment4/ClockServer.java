package Assignment4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ClockServer {

    private static Map<String, ClientData> clientData = new HashMap<>();

    private static class ClientData {
        private Date clockTime;
        private long timeDifference;
        private Socket connector;

        public ClientData(Date clockTime, long timeDifference, Socket connector) {
            this.clockTime = clockTime;
            this.timeDifference = timeDifference;
            this.connector = connector;
        }
    }

    public static void main(String[] args) {
        int serverPort = 8080; // Server port

        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);
            System.out.println("Clock server started...");

            // Create a thread to accept connections from clients
            Thread connectThread = new Thread(() -> {
                try {
                    while (true) {
                        Socket clientSocket = serverSocket.accept();

                        // Create a thread to handle each client
                        Thread clientThread = new Thread(() -> {
                            try {
                                while (true) {
                                    byte[] buffer = new byte[1024];
                                    int bytesRead = clientSocket.getInputStream().read(buffer);

                                    if (bytesRead > 0) {
                                        String timeString = new String(buffer, 0, bytesRead);
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        Date clockTime = dateFormat.parse(timeString);
                                        long timeDifference = System.currentTimeMillis() - clockTime.getTime();

                                        String clientAddress =
                                                clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort();
                                        clientData.put(clientAddress, new ClientData(clockTime, timeDifference,
                                                clientSocket));

                                        System.out.println("Client Data updated with: " + clientAddress);
                                    }
                                }
                            } catch (IOException | ParseException e) {
                                e.printStackTrace();
                            }
                        });

                        clientThread.start();
                        System.out.println(clientSocket.getInetAddress().getHostAddress() + " got connected " +
                                "successfully");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // Create a thread to synchronize all clocks
            Thread synchronizeThread = new Thread(() -> {
                try {
                    while (true) {
                        System.out.println("New synchronization cycle started.");
                        System.out.println("Number of clients to be synchronized: " + clientData.size());

                        if (!clientData.isEmpty()) {
                            long totalDifference = 0;

                            for (ClientData client : clientData.values()) {
                                totalDifference += client.timeDifference;
                            }

                            long averageDifference = totalDifference / clientData.size();

                            for (ClientData client : clientData.values()) {
                                Date synchronizedTime = new Date(System.currentTimeMillis() + averageDifference);

                                String timeString =
                                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(synchronizedTime);
                                client.connector.getOutputStream().write(timeString.getBytes());
                                client.connector.getOutputStream().flush();
                            }
                        } else {
                            System.out.println("No client data. Synchronization not applicable.");
                        }

                        System.out.println();

                        Thread.sleep(5000); // Wait for 5 seconds
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });

            // Start the connect and synchronize threads
            connectThread.start();
            synchronizeThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
