package Assignment2;

import ReverseModule.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*; // Common Object Services (COS) - Provides a naming service for Java IDL.
import org.omg.CosNaming.NamingContextPackage.*; // This package contains Exception classes for the org.omg.CosNaming package.
import java.io.*;
import java.util.Scanner;

class ReverseClient {
    public static void main(String[] args) {
        try {
            //  This line initializes the ORB (Object Request Broker)
            //  by passing properties object (null in this case).
            System.out.println("Step1");
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

            // This line obtains a reference to the NameService
            // A CORBA naming service holds CORBA object references
            System.out.println("Step2");
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

            System.out.println("Step3");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // This line resolves the remote object reference
            // by calling the resolve_str method of the NamingContextExt interface
            // and passing the name of the remote object.
            System.out.println("Step4");
            String name = "Reverse";
            Reverse reverseImpl = ReverseHelper.narrow(ncRef.resolve_str(name));

            System.out.println("Enter String: ");
            Scanner scanner = new Scanner(System.in);
            String str = scanner.readLine();

            String reversedString = reverseImpl.reverse_string(str);
            System.out.println(reversedString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
