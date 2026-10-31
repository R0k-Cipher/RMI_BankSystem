package business.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import models.Account;
import models.User;

public interface IAccountService extends Remote{
    public Account createAccount(User user)throws RemoteException;
    public void deposit(Account account, double amount)throws RemoteException;
    public void withdrawal(Account account,double amount)throws RemoteException;
    public Account getAccount(User user)throws RemoteException;
    public void transfer(Account account,String accountNumber,double amount)throws RemoteException;
    public Account getAccountByAccountNumber(String accountNumber)throws RemoteException;
}
