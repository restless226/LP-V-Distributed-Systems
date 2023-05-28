package Assignment6;

import java.util.Scanner;

public class Ring {
    int n, inactiveCount;
    int coordinator;
    boolean[] processState;

    public Ring(int n) {
        this.n = n;
        this.inactiveCount = 0;
        this.processState = new boolean[n];
        // Initialize all processes as active
        for (int i = 0; i < n; i++) {
            this.processState[i] = true;
        }
        this.coordinator = n - 1;
        System.out.println("Process " + n + " is set as initial coordinator");
    }

    //  To deactivate a specific process in the ring.
    //  It checks if the process is already inactive and deactivates it if it's active.
    //  It keeps track of the count of inactive processes.
    public void deactivateProcess(int id) {
        if (id > n || id < 0) {
            System.out.println("Invalid ID");
            return;
        }
        if (!processState[id - 1]) {
            System.out.println("Process is already inactive");
        } else {
            processState[id - 1] = false;
            System.out.println("Process " + id + " is deactivated successfully.");
            inactiveCount += 1;
        }
    }

    public void viewRing() {
        if (this.inactiveCount == n) {
            System.out.println("All processes are inactive at the moment...");
            return;
        }
        System.out.println("Following are the active processes in the ring: ");
        for (int i = 0; i < n; i++) {
            if (processState[i]) System.out.println(i + 1);
        }
    }

    // id represents the ID of the process initiating the election.
    public void election(int id) {
        if (this.inactiveCount == this.n) {
            System.out.println("All processes are inactive at the moment...");
            System.out.println("Aborting election process...");
            this.coordinator = -1;
            return;
        }
        // Subtract 1 from the ID to match the index of the process in the process_state array.
        id = id - 1;
        int currentCoordinator = id; // assuming the initiator is the current coordinator candidate.
        int token = (id + 1) % n; //  next process to receive the election message.
        System.out.println("\nElection initiator : " + (id + 1));
        //	Election algorithm
        while (token != id) {
            System.out.println("Token at process " + (token + 1));
            if (this.processState[token]) {
                //  If the token process has a higher ID,
                //  it updates currentCoordinator to the ID of the token process.
                if (token > currentCoordinator) {
                    currentCoordinator = token;
                }
            }
            token = (token + 1) % this.n;
        }
        System.out.println("Elected coordinator : " + (currentCoordinator + 1));
        this.coordinator = currentCoordinator;
    }

    // sends a message from a process to the coordinator in the ring network
    public void pingCoordinator(int id) {
        if (!this.processState[id - 1]) {
            System.out.println("Process with this id is inactive...");
            System.out.println("Aborting...");
            return;
        }
        if (id == coordinator) {
            if (this.processState[id - 1]) {
                System.out.println("Coordinator is active");
            } else {
                System.out.println("Coordinator is inactive!\nInitiate election from other process");
            }
        }
        System.out.println("Sending message from process " + id + " to " + (this.coordinator + 1));
        if (!this.processState[this.coordinator]) {
            System.out.println("Coordinator process not responding");
            System.out.println("Conducting election...");
            this.election(id);
        } else {
            System.out.println("Coordinator is alive");
        }
    }

    public void setCoordinator(int id) {
        this.coordinator = id;
    }

    public static void main(String[] args) {
        int choice = 0, id;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of processes: ");
        int n = sc.nextInt();
        Ring ring = new Ring(n);

        while (choice < 5) {
            System.out.println("***********Menu***********");
            System.out.println("1. Deactivate a process");
            System.out.println("2. Ping coordinator");
            System.out.println("3. View Ring");
            System.out.println("4. Election");
            System.out.println("5. Set Coordinator");
            System.out.println("6. Exit");
            System.out.println("**************************");
            System.out.println("Enter Choice : ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter process ID : ");
                    id = sc.nextInt();
                    ring.deactivateProcess(id);
                    break;
                case 2:
                    System.out.println("Enter process ID for sender");
                    id = sc.nextInt();
                    ring.pingCoordinator(id);
                    break;
                case 3:
                    ring.viewRing();
                    break;
                case 4:
                    System.out.println("Enter process ID for election initiator");
                    id = sc.nextInt();
                    ring.election(id);
                    break;
                case 5:
                    System.out.println("Enter process ID to set as coordinator");
                    id = sc.nextInt();
                    ring.setCoordinator(id);
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Please make another choice");
                    break;
            }
            System.out.println();
        }
        System.out.println("Program terminated..");
        sc.close();
    }
}
