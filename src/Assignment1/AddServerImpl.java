package Assignment1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AddServerImpl extends UnicastRemoteObject implements AddServerInterface {

    public AddServerImpl() throws RemoteException {

    }

    @Override
    public double add(double d1, double d2) throws RemoteException {
        return (d1 + d2);
    }

    @Override
    public double subtract(double d1, double d2) throws RemoteException {
        return (d1 - d2);
    }

    @Override
    public double multiply(double d1, double d2) throws RemoteException {
        return (d1 * d2);
    }

    @Override
    public double divide(double d1, double d2) throws RemoteException {
        return (d1 / d2);
    }
}
