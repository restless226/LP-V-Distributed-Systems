package Practice;

import java.util.Scanner;

public class A5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter no of nodes in the ring: ");
        int n = scanner.nextInt();
        System.out.println("Ring Formed Is As Below: ");
        for (int i = 0; i < n; i++) {
            System.out.print(i + " ");
        }
        System.out.println("0");
        int token = 0, choice = 1;

        do {
            System.out.println("Enter sender: ");
            int sender = scanner.nextInt();
            System.out.println("Enter receiver: ");
            int receiver = scanner.nextInt();
            System.out.println("Enter message: ");
            String message = scanner.nextLine();

            System.out.print("Token Passing: ");

            for (int i = token; i < sender; i++) {
                System.out.print(" " + i + "->");
            }

            if (receiver == sender + 1) {
                System.out.println("Sender: " + sender + " Sending The message: " + message);
                System.out.println("Receiver: " + receiver + " Received The message: " + message);
            } else {
                System.out.println(" " + sender);
                System.out.println("Sender:" + sender + " Sending message: " + message);
                for (int i = sender; i != receiver; i = (i + 1) % n) {
                    System.out.println("message: " + message + " Forwarded By: " + i);
                }
                System.out.println("Receiver: " + receiver + " Received The message: " + message);
            }

            token = sender;
            System.out.print("Do You Want To Send message Again? If YES Enter 1, If NO Enter 0: ");
            choice = scanner.nextInt();
        } while (choice == 1);
    }
}
