package business.services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import business.interfaces.IHistoryService;
import dal.HistoryDal;
import models.Account;
import models.History;



public class HistoryService extends UnicastRemoteObject implements IHistoryService{
     public HistoryService() throws RemoteException {

        super();
    }

    @Override
    public void createHistory(Account from,Account to,String op,String amount)throws RemoteException{
    
        History newHistory = new History(from.getIdAccount(),from.getIdUser(),amount,op, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")).toString(),from.getAccountNumber(),to.getAccountNumber());
        HistoryDal.create(newHistory);
    }

    @Override
    public Vector<History> getHistory(Account account)throws RemoteException{
        return HistoryDal.getHistory(account);
    }

    

}
