package Assignment1;

import java.rmi.Naming;

public class AddClient {
    public static void main(String[] args) {
        try {
            String addServerUrl = "rmi://" + args[0] + "/AddServer";
            System.out.println("addServerUrl = " + addServerUrl);

            // creating reference to remote object
            AddServerInterface addServerInterface = (AddServerInterface) Naming.lookup(addServerUrl);
            System.out.println("The first number is: " + args[1]);
            System.out.println("The second number is: " + args[2]);
            double d1 = Double.parseDouble(args[1]);
            double d2 = Double.parseDouble(args[2]);
            System.out.println("The sum is: " + addServerInterface.add(d1, d2));
        } catch (Exception e) {
            System.out.println("Exception is: " + e);
        }
    }
}
