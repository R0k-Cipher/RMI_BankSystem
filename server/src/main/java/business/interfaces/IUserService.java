package business.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import models.User;

public interface IUserService extends Remote{
    User createUser(User user)throws RemoteException;
    User login(String email,String password)throws RemoteException;
    
}
