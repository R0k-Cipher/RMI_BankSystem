package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Account;

public class AccountDal {
    public static Account account;
    private static Connection conn= Database.getConnection();;
    public static void create(Account account){
        
          try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO accounts (accountNumber,balance,idUser) VALUES (?, ?,?)");
            stmt.setString(1, account.getAccountNumber());
            stmt.setDouble(2, account.getBalance());
            stmt.setInt(3, account.getIdUser());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(Account account){
       
        try{
        PreparedStatement ps = conn.prepareStatement("update accounts set balance=? where idAccount =?");
        ps.setDouble(1, account.getBalance());
        ps.setInt(2, account.getIdAccount());
        ps.execute();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Account getAccountById(int idAccount){
        
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM accounts WHERE idUser =?");
            stmt.setInt(1, idAccount);
            ResultSet rs = stmt.executeQuery( ); 
            while(rs.next()) {
                account = new Account(rs.getString("accountNumber"), rs.getInt("idUser"));
                account.setBalance(rs.getDouble("balance"));
                account.setIdAccount(idAccount);
            }
           
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return account;
   }


   public static Account getAccountByIdUser(int idUser){
    
    try {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM accounts WHERE idAccount =?");
        stmt.setInt(1, idUser);
        ResultSet rs = stmt.executeQuery( ); 
        while(rs.next()) {
            account = new Account(rs.getString("accountNumber"), rs.getInt("idUser"));
            account.setBalance(rs.getDouble("balance"));
            account.setIdAccount(rs.getInt("idAccount"));
        }
       
   } catch (SQLException e) {
       e.printStackTrace();
   }
   return account;
}

public static Account getAccountByAccountNumber(String accountNumber){
    
    try {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM accounts WHERE accountNumber =?");
        stmt.setString(1, accountNumber);
        ResultSet rs = stmt.executeQuery(); 
        if(rs.next()) {
            account = new Account(rs.getString("accountNumber"), rs.getInt("idUser"));
            account.setBalance(rs.getDouble("balance"));
            account.setIdAccount(rs.getInt("idAccount"));
        }else{
            account=null;
        }
       
   } catch (SQLException e) {
       e.printStackTrace();
   }
   return account;
}
   public static int getLastAccountId(){
    int id = 0;
    
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT MAX(idAccount) AS last_account_id FROM accounts");
            ResultSet rs = stmt.executeQuery();
           rs.next();
            id = rs.getInt("last_account_id");
       } catch (SQLException e) {
           e.printStackTrace();
       }
        return id;
   }

   public static void updateTransfer(Account from,Account to,double amount){
    
    try{
    PreparedStatement ps = conn.prepareStatement("update accounts set balance=? where idAccount =?");
    ps.setDouble(1, from.getBalance());
    ps.setInt(2, from.getIdAccount());
    ps.execute();
    PreparedStatement ps2 = conn.prepareStatement("update accounts set balance=? where idAccount =?");
    ps2.setDouble(1, to.getBalance());
    ps2.setInt(2,to.getIdAccount());
    ps2.execute();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }

   }
}
