package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import models.User;

public class UserDal {
    private static Connection conn= Database.getConnection();
    private static User user;
    public static void create(User user){
          try  {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (firstname,lastname,email, password) VALUES (?, ?,?,?)");
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User getUser(String email,String password){
        
         try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
             stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery() ;
            if(rs.next()){
            user = new User(rs.getString("firstname"),rs.getString("lastname"), rs.getString("email"), rs.getString("password"));
            user.setIdUser(rs.getInt("id"));
            }else{
                user=null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User getUserById(int idUser){
        
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            stmt.setInt(1, idUser);
            ResultSet rs = stmt.executeQuery();
           rs.next();
           user = new User(rs.getString("firstname"),rs.getString("lastname"), rs.getString("email"), rs.getString("password"));
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return user;
   }

   public static int getLastUserId(){
    int id = 0;
    
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT MAX(id) AS last_user_id FROM users");
            ResultSet rs = stmt.executeQuery();
           rs.next();
            id = rs.getInt("last_user_id");
       } catch (SQLException e) {
           e.printStackTrace();
       }
        return id;
   }

   
}
