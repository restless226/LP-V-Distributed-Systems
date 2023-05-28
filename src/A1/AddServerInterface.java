package A1;

import java.rmi.*;

public interface AddServerInterface extends Remote {
    public double add(double d1, double d2) throws RemoteException;

    public double subtract(double d1, double d2) throws RemoteException;

    public double multiply(double d1, double d2) throws RemoteException;

    public double divide(double d1, double d2) throws RemoteException;
}