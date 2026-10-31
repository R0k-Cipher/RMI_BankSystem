package dal;

public interface Config {
    static final String  DBDriver="com.mysql.cj.jdbc.Driver";
    static final String  DBName="bank";
    static final String  User="root";
    static final String  Password="";
    static final String URl="jdbc:mysql://localhost:3306/"+DBName;
}
