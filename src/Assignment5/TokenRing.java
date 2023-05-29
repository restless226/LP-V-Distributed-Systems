package Assignment5;

import java.util.*;

public class TokenRing {
    public void viewRing(int n) {
        System.out.println("Ring formed is as below:");
        for (int i = 0; i < n - 1; i++) System.out.print(i + "->");
        System.out.println("0\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter no of nodes in the ring: ");
        int n = scanner.nextInt();
        TokenRing tokenRing = new TokenRing();
        tokenRing.viewRing(n);
        int choice = 1;
        while (choice == 1) {
            int token = 0;
            System.out.println("Enter sender: ");
            int sender = scanner.nextInt();
            System.out.println("Enter receiver: ");
            int receiver = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter message: ");
            String message = scanner.nextLine();
            System.out.println("Passing token from node 0 to sender node...");
            for (int i = token; i < sender; i++) {
                System.out.println("At node " + i);
            }
            System.out.println("\nAt sender node: " + sender + " Sending The message: " + message + "\n");
            if (receiver != sender + 1) {
                for (int i = sender; i != receiver; i = (i + 1) % n) {
                    System.out.println("message: " + message + " Forwarded By: " + i);
                }
            }
            System.out.println("\nReceiver: " + receiver + " Received The message: " + message + "\n");
            System.out.print("Do You Want To Send message Again? If YES Enter 1, If NO Enter 0: ");
            choice = scanner.nextInt();
        }
    }
}