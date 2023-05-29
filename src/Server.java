import java.rmi.*;
import java.util.Scanner;

import Assignment2.ReverseImpl;
import org.omg.COBRA.*;
import org.omg.COBRA.CosNaming.*;
import org.omg.COBRA.CosNaming.NamingContextPackage.*;

public class ReverseServer {
    public static void main(String[] args) {
        try {
            System.out.println("step 1");
            org.omg.COBRA.ORB orb = org.omg.COBRA.ORB.init(args, null);

            System.out.println("step 2");
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("rootPOA"));
            rootPOA.the_POAManager().activate();

            System.out.println("step3");
            ReverseImpl reverseImpl = new ReverseImpl();

            System.out.println("step 4");
            org.omg.COBRA.Object servantRef = rootPOA.servant_to_reference(reverseImpl);

            System.out.println("step5");
            Reverse reverseHelper = ReverseHelper.narrow(servantRef);

            System.out.println("step6");
            org.omg.COBRA.Object objRef = orb.resolve_initial_references("NamingService");

            System.out.println("Step7");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            System.out.println("Step 8");
            String name = "Reverse";
            ncRef.rebind(name, reverseHelper);

            System.out.println("Reverse Server reading and waiting....");
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}