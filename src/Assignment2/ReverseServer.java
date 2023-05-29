package Assignment2;

import ReverseModule.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.PortableServer.*;

class ReverseServer {
    public static void main(String[] args) {
        try {
            // initialize the ORB
            System.out.println("Step1");
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

            // initialize the BOA/POA
            System.out.println("Step2");
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();

            // creating the calculator object
            System.out.println("Step3");
            ReverseImpl reverseImpl = new ReverseImpl();

            // get the object reference from the servant class
            System.out.println("Step4");
            org.omg.CORBA.Object servantRef = rootPOA.servant_to_reference(reverseImpl);

            System.out.println("Step5");
            Reverse reverseHelper = ReverseHelper.narrow(servantRef);

            System.out.println("Step6");
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

            System.out.println("Step7");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            System.out.println("Step8");
            String name = "Reverse";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, reverseHelper);

            System.out.println("Reverse Server reading and waiting....");
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
