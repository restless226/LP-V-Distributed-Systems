//package Assignment2;
//
//import ReverseModule.*;
//import org.omg.CosNaming.*;
//import org.omg.CosNaming.NamingContextPackage.*;
//import org.omg.CORBA.*;
//
//import java.io.*;
//import java.util.Scanner;
//
//class ReverseClient {
//    public static void main(String args[]) {
//        try {
//            // initialize the ORB
//            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
//
//            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
//            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
//
//            String name = "Reverse";
//            Reverse reverseImpl = ReverseHelper.narrow(ncRef.resolve_str(name));
//
//            System.out.println("Enter String: ");
//            Scanner scanner = new Scanner(System.in);
//            String str = scanner.readLine();
//
//            String reversedString = reverseImpl.reverse_string(str);
//
//            System.out.println(reversedString);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
