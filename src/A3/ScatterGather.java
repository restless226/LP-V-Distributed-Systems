//package A3;
//
//import mpi.MPI;
//
//public class ScatterGather {
//    public static void main(String[] args) {
//        // It initializes execution environment MPI and sets up the necessary communication structures.
//        MPI.Init(args);
//
//        // rank represents the rank (process identifier) of the current process
//        int rank = MPI.COMM_WORLD.Rank();
//        // size variable represents the total number of processes in the communicator
//        int size = MPI.COMM_WORLD.Size();
//
//        int unitSize = 5; // Number of elements each process will receive
//        int root = 0; // The rank of the root process (process 0)
//
//        int[] sendBuffer = new int[unitSize * size]; // Buffer for sending data
//        int[] receiveBuffer = new int[unitSize]; // Buffer for receiving data
//        int[] gatherBuffer = new int[size]; // Buffer for gathering data
//
//        //  Set data for distribution
//        if (rank == root) {
//            int totalElements = unitSize * size;
//            System.out.println("Enter " + totalElements + " elements: ");
//            for (int i = 0; i < totalElements; i++) {
//                System.out.println("Element " + i + "\t = " + i);
//                sendBuffer[i] = i;
//            }
//        }
//
//        // Scatter data to processes
//        // The Scatter operation divides the data from the root process and sends a portion to each process.
//        // Here, the send buffer (send_buffer) is divided into equal parts and scattered to each process.
//        // Each process receives a portion into its own receive buffer
//        MPI.COMM_WORLD.Scatter(
//                sendBuffer,
//                0,
//                unitSize,
//                MPI.INT,
//                receiveBuffer,
//                0,
//                unitSize,
//                MPI.INT,
//                root
//        );
//
//        // Each non-root process calculates the sum of the received elements
//        // and stores it in the first index of its receive buffer.
//        // It then displays the intermediate sum for each process.
//        for (int i = 1; i < unitSize; i++) {
//            receiveBuffer[0] += receiveBuffer[i];
//        }
//        System.out.println("Intermediate sum at process " + rank + " is " + receiveBuffer[0]);
//
//        // The Gather operation collects the data from all processes and gathers it at the root process.
//        // Here, each process contributes its sum to the gather operation.
//        // The gathered data is stored in the gatherBuffer
//        MPI.COMM_WORLD.Gather(
//                receiveBuffer,
//                0,
//                1,
//                MPI.INT,
//                gatherBuffer,
//                0,
//                1,
//                MPI.INT,
//                root
//        );
//
//        if (rank == root) {
//            int totalSum = 0;
//            for (int i = 0; i < size; i++) {
//                totalSum += gatherBuffer[i];
//            }
//            System.out.println("Final sum : " + totalSum);
//        }
//        // It cleans up all state related to MPI.
//        // The application must ensure that all pending communications are completed or canceled before calling MPI_Finalize
//        MPI.Finalize();
//    }
//}
