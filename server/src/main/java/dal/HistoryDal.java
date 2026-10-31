package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import models.Account;
import models.History;


public class HistoryDal {
    private static Connection conn= Database.getConnection();;
    private static History history;

     public static void create(History history){
        
          try  {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO history(idAccount,idUser,operation, amount,date,`from`,`to`) VALUES (?,?,?,?,?,?,?)");
            stmt.setInt(1, history.getIdAccount());
            stmt.setInt(2, history.getIdUser());
            stmt.setString(3, history.getOperation());
            stmt.setString(4, history.getAmount());
            stmt.setString(5, history.getDate());
            stmt.setString(6, history.getFrom());
            stmt.setString(7, history.getTo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static Vector<History> getHistory(Account account) {
        Vector<History> h = new Vector<>();
        
        try  {
            
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM history WHERE `from` = ? OR `to` = ?");
            stmt.setString(1, account.getAccountNumber());
            stmt.setString(2, account.getAccountNumber());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                     history=new History(rs.getInt("idAccount"), rs.getInt("idUser"), rs.getString("amount"), rs.getString("operation"), rs.getString("date"),rs.getString("from"),rs.getString("to"));
                     history.setIdHistory(rs.getInt("id"));
                    h.add(history);
                }
            }
        } catch (SQLException e) {
            // Handle or log the exception appropriately
            e.printStackTrace();
        }
        return h;
    }
    

}
