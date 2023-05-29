package Assignment6;

import java.util.Scanner;

public class Bully {
    boolean[] state; // to represent the state of each process.
    int coordinator; // to store the index of the current coordinator.

    Bully(int n) {
        // [IMPORTANT] the code assumes that the initial coordinator is process n - 1.
        this.coordinator = n - 1;
        this.state = new boolean[n];
        for (int i = 0; i < n; i++) {
            state[i] = true;
        }
    }

    public void viewBully(int n) {
        System.out.println("Active processes are:\n");
        for (int i = 0; i < n; i++) {
            if (this.state[i]) System.out.print("P" + i + " ");
        }
        System.out.println();
    }

    public void viewInitialBully(int n) {
        System.out.println("Initially all " + n + " processes are active");
        System.out.println("Process " + (n - 1) + " is the coordinator\n");
    }

    // The up method is responsible for bringing a process up
    // and initiating the election process if necessary.
    public void up(int processId, int n) {
        if (this.state[processId]) {
            System.out.println("process " + processId + " is already up");
        } else {
            this.state[processId] = true;
            System.out.println("process " + processId + " is up successfully.");
            election(processId, n);
        }
    }

    public void down(int processId) {
        if (!this.state[processId]) {
            System.out.println("process " + processId + " is already down.");
        } else {
            this.state[processId] = false;
            System.out.println("process " + processId + " is down successfully.");
        }
    }

    // responsible for sending a message from a process to initiate an election if necessary.
    public void election(int processId, int n) {
        if (this.state[processId]) {
            if (this.state[this.coordinator]) {
                System.out.println("There is no need for election as current coordinator process is up");
            } else {
                // As the coordinator process is down process with processId proceeds with an election process
                System.out.println("As the coordinator process is down process with processId proceeds with an " +
                        "election process");
                System.out.println("process " + processId + " is going to hold an election");
                for (int i = processId + 1; i < n; i++) {
                    System.out.println("election message sent from process " + processId + " to process" + (i + 1));
                }
                for (int i = n - 1; i >= processId; i--) {
                    if (state[i]) {
                        this.coordinator = i;
                        System.out.println("process " + i + " has become new coordinator");
                        System.out.println("sending coordinator message from process" + i + " to all processes.");
                        break;
                    }
                }
            }
        } else {
            System.out.println("Unable to send a message as process " + processId + " is down.");
        }
    }

    public void sendCoordinatorMessage(int processId, int n) {
        if (this.state[processId]) {
            System.out.println("Process " + processId + " sends coordinator message to Process " + this.coordinator);
            if (!this.state[coordinator]) election(processId, n);
        } else {
            System.out.println("Unable to send message as process " + processId + " is down.");
        }
    }

    public void getCoordinator() {
        if (this.state[coordinator]) {
            System.out.println("Process " + this.coordinator + " is coordinator process as of now");
        } else {
            System.out.println("Unable to send message as coordinator process is down");
            System.out.println("Please initiate an election to elect new coordinator");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int processId;

        System.out.println("Enter no of processes: ");
        int n = scanner.nextInt();
        Bully bully = new Bully(n);

        bully.viewInitialBully(n);

        while (true) {
            System.out.println("..................");
            System.out.println("1. Up a process.");
            System.out.println("2. Down a process");
            System.out.println("3. Conduct election");
            System.out.println("4. View Bully");
            System.out.println("5. Send a message to coordinator process");
            System.out.println("6. Get coordinator process");
            System.out.println("7. Exit");
            System.out.println("..................");

            System.out.println("Enter you choice from above list: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter process id to perform up operation: ");
                    processId = scanner.nextInt();
                    bully.up(processId, n);
                    break;
                case 2:
                    System.out.println("Enter process id to perform down operation: ");
                    processId = scanner.nextInt();
                    bully.down(processId);
                    break;
                case 3:
                    System.out.println("Enter process id which will conduct election: ");
                    processId = scanner.nextInt();
                    bully.election(processId, n);
                    break;
                case 4:
                    bully.viewBully(n);
                    break;
                case 5:
                    System.out.println("Enter process id which will send a message to coordinator: ");
                    processId = scanner.nextInt();
                    bully.sendCoordinatorMessage(processId, n);
                    break;
                case 6:
                    bully.getCoordinator();
                    break;
                case 7:
                    System.exit(0);
            }
        }
    }
}
