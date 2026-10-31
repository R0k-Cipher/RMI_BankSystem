package estm.dsic.jee;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import business.interfaces.IAccountService;
import business.interfaces.IHistoryService;
import business.interfaces.IUserService;
import business.services.AccountService;
import business.services.HistoryService;
import business.services.UserService;
public class Main {
    public static void main(String[] args) {
        try {
            
            System.out.println("banking services listening in 9090");
            Registry rmiRegistry = LocateRegistry.createRegistry(9090);
            IUserService userService = new UserService();
            rmiRegistry.bind("userService", userService);
            IAccountService accountService = new AccountService();
            rmiRegistry.bind("accountService", accountService);
            IHistoryService historyService = new HistoryService();
            rmiRegistry.bind("historyService", historyService);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

