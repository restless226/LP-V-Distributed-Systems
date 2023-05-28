package A1;

import java.rmi.*;

public class AddServer {
    public static void main(String[] args) {
        try {
            AddServerImpl addServerImpl = new AddServerImpl();
            // adding addServerImpl remote object in rmi registry
            Naming.bind("AddServer", addServerImpl);
        } catch (Exception e) {
            System.out.println("Exception is: " + e);
        }
    }
}
