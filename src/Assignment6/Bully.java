package Assignment6;

import java.util.Scanner;

public class Bully {
    static boolean[] state = new boolean[5]; // to represent the state of each process.
    // [IMPORTANT] the code assumes that the initial coordinator is process 5.
    int coordinator; // to store the index of the current coordinator.

    // The up method is responsible for bringing a process up
    // and initiating the election process if necessary.
    public static void up(int process) {
        if (state[process - 1]) {
            System.out.println("process " + process + " is already up");
        } else {
            state[process - 1] = true;
            System.out.println("process " + process + " is going to hold an election...");
            // send election messages to the processes with higher indices.
            for (int i = process; i < 5; i++) {
                System.out.println("election message sent from process " + process + " to process" + (i + 1));
            }
            // It enters another loop to find the first process with a higher index (i) that is already up.
            // It starts from currentProcess + 1 and continues until process 5.
            // When it finds such a process, it displays a message indicating
            // that an alive message is sent from that process to the currentProcess,
            // and the loop is terminated using break.

            // The alive message sent back confirms the process's availability
            // and ensures that it becomes the coordinator.
            for (int i = process + 1; i <= 5; i++) {
                if (state[i - 1]) {
                    System.out.println("alive message send from process " + i + " to process " + process);
                    break;
                }
            }
        }
    }

    public static void down(int process) {
        if (!state[process - 1]) {
            System.out.println("process " + process + " is already down.");
        } else {
            state[process - 1] = false;
        }
    }

    // responsible for sending a message from a process to initiate an election if necessary.
    public static void sendMessages(int process) {
        if (state[process - 1]) {
            if (state[4]) {
                System.out.println("message can be sent since a coordinator is already present");
            } else {
                // As the coordinator is down proceed with an election process
                System.out.println("process " + process + " is going to hold an election");
                for (int i = process; i < 5; i++) {
                    System.out.println("election message sent from process " + process + " to process" + (i + 1));
                }

                for (int i = 5; i >= process; i--) {
                    if (state[i - 1]) {
                        System.out.println("sending coordinator message from process" + i + " to all processes.");
                        break;
                    }
                }
            }
        } else {
            System.out.println("Unable to send a message as process " + process + " is down.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        int process;
        for (int i = 0; i < 5; i++) {
            state[i] = true;
        }
        System.out.println("5 active process are:");
        System.out.println("Process up = p1 p2 p3 p4 p5");
        System.out.println("Process 5 is the coordinator");
        do {
            System.out.println(".........");
            System.out.println("1. Up a process.");
            System.out.println("2. Down a process");
            System.out.println("3. Send a message");
            System.out.println("4. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter process id to perform up operation: ");
                    process = sc.nextInt();
                    if (process == 5) {
                        System.out.println("process 5 is coordinator");
                        state[4] = true;
                    } else {
                        up(process);
                    }
                    break;
                case 2:
                    System.out.println("Enter process id to perform down operation: ");
                    process = sc.nextInt();
                    down(process);
                    break;
                case 3:
                    System.out.println("Enter process id which will send message: ");
                    process = sc.nextInt();
                    sendMessages(process);
                    break;
            }
        } while (choice != 4);
    }
}
