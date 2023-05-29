package JavaSockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class IClient {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a number:");
            int n = scanner.nextInt();
            Socket socket = new Socket("127.0.0.1", 3000);
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeInt(n);
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            int result = inputStream.readInt();
            System.out.println("result: " + result);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
