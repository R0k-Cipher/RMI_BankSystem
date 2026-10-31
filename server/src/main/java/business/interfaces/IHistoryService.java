package business.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import models.Account;
import models.History;


public interface IHistoryService extends Remote{
    public void createHistory(Account from,Account to,String op,String amount)throws RemoteException;
    public Vector<History> getHistory(Account account)throws RemoteException;
    
}
