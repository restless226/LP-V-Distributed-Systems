package A5;

import java.util.*;

public class TokenRing {
    public static void viewRing(int n) {
        System.out.println("Ring formed is as below:");
        for (int i = 0; i < n - 1; i++) {
            System.out.print(i + "->");
        }
        System.out.println("0");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter no of nodes in the ring: ");
        int n = scanner.nextInt();

        TokenRing.viewRing(n);

        int token = 0;
        int choice = 1;

        while (choice == 1) {
            int sender, receiver;
            String message;

            System.out.println("Enter sender: ");
            sender = scanner.nextInt();

            System.out.println("Enter receiver: ");
            receiver = scanner.nextInt();

            scanner.nextLine();
            System.out.println("Enter message: ");
            message = scanner.nextLine();

            System.out.println("Passing token from node 0 to sender node...");
            for (int i = token; i < sender; i++) {
                System.out.println("At node " + i);
            }
            System.out.println();

            System.out.println("At sender node: " + sender + " Sending The message: " + message);
            System.out.println();

            if (receiver == sender + 1) {
                System.out.println("Receiver: " + receiver + " Received The message: " + message);
            } else {
                for (int i = sender; i != receiver; i = (i + 1) % n) {
                    System.out.println("message: " + message + " Forwarded By: " + i);
                }
                System.out.println("Receiver: " + receiver + " Received The message: " + message);
            }
            System.out.println();

            token = sender;

            System.out.print("Do You Want To Send message Again? If YES Enter 1, If NO Enter 0: ");
            choice = scanner.nextInt();
        }
    }
}