package business.services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import business.interfaces.IUserService;
import dal.UserDal;
import models.User;

public class UserService extends UnicastRemoteObject implements IUserService{
    User user;
       public UserService() throws RemoteException {

        super();
    }
     
    @Override
    public User createUser(User user)throws RemoteException{
        UserDal.create(user);
        user.setIdUser(UserDal.getLastUserId());
        return user;
    }
    @Override
    public User login(String email,String password)throws RemoteException{
        user=UserDal.getUser(email, password);
        return user;  
    }
}
