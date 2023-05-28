//package Practice;
//
//import mpi.*;
//
//public class A3 {
//    public static void main(String[] args) {
//        MPI.Init(args);
//
//        int rank = MPI.COMM_WORLD.Rank();
//        int size = MPI.COMM_WORLD.Size();
//
//        int unitSize = 5;
//        int root = 0;
//
//        int[] sendBuffer = new int[unitSize * size];
//        int[] receiveBuffer = new int[unitSize];
//        int[] gatherBuffer = new int[size];
//
//        if (rank == root) {
//            int totalElements = unitSize * size;
//            System.out.println("Enter " + totalElements + " elements: ");
//            for (int i = 0; i < totalElements; i++) {
//                sendBuffer[i] = i;
//                System.out.println("Element " + "i = " + i);
//            }
//        }
//        // scatter
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
//        for (int i = 1; i < unitSize; i++) {
//            receiveBuffer[0] += receiveBuffer[i];
//        }
//
//        System.out.println("Intermediate sum at process " + rank + " is " + receiveBuffer[0]);
//
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
//            System.out.println("Final sum: " + totalSum);
//        }
//
//        MPI.Finalize();
//    }
//}