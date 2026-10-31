package business.services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import business.interfaces.IAccountService;
import dal.AccountDal;
import models.Account;
import models.User;



public class AccountService extends UnicastRemoteObject implements IAccountService{
    private HistoryService hs=new HistoryService();
    public AccountService() throws RemoteException {
        super();
    }
    @Override
    public Account createAccount(User user)throws RemoteException{
        Account newAccount=new Account(Account.generateAccNumber(user.getIdUser()), user.getIdUser());
        AccountDal.create(newAccount);
        newAccount.setIdAccount(AccountDal.getLastAccountId());
        return newAccount;
    }


    public void deposit(Account account,double amount)throws RemoteException{
       account.setBalance(account.getBalance()+amount);
       AccountDal.update(account);
       
       hs.createHistory(account,account,"Deposit",String.valueOf(amount));
    }

    public void withdrawal(Account account,double amount)throws RemoteException{
        account.setBalance(account.getBalance()-amount);
        AccountDal.update(account);
        hs.createHistory(account,account,"Withdrawal",String.valueOf(amount));
    }

    public Account getAccount(User user)throws RemoteException{
        return AccountDal.getAccountByIdUser(user.getIdUser());
    }

    public void transfer(Account from,String accountNumber,double amount)throws RemoteException{

        Account to=AccountDal.getAccountByAccountNumber(accountNumber);
        from.setBalance(from.getBalance()-amount);
        to.setBalance(to.getBalance()+amount);
        AccountDal.updateTransfer(from , to, amount);
        hs.createHistory(from,to,"Transfer",String.valueOf(amount) );
    }
    @Override
    public Account getAccountByAccountNumber(String accountNumber) throws RemoteException {
       return AccountDal.getAccountByAccountNumber(accountNumber);
    }
}

