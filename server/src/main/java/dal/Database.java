package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database implements Config{
    private static Connection connexion;
    
    public static Connection getConnection(){
        if(connexion == null) {
            try {
                Class.forName(DBDriver);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }  
            try {
                connexion = DriverManager.getConnection(URl, User,Password );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connexion;
    }

    
}
