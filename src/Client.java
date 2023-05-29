import java.rmi.*;
import java.util.Scanner;

import org.omg.COBRA.*;
import org.omg.COBRA.CosNaming.*;
import org.omg.COBRA.CosNaming.NamingContextPackage.*;

public class ReverseCLient {
    public static void main(String[] args) {
        try {
            System.out.println("step 1");
            org.omg.COBRA.ORB orb = org.omg.COBRA.ORB.init(args, null);

            System.out.println("step2");
            org.omg.COBRA.Object objRef = orb.resolve_initial_references("NamingService");

            System.out.println("Step3");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            System.out.println("Step 4");
            String name = "Reverse";
            Reverse reverseImpl = new ReverseHelper.narrow(ncRef.resolve_str(name));

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a string:");
            String str = scanner.nextLine();

            String reversedString = reverseImpl.reverse_string();
            System.out.println(reversedString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}