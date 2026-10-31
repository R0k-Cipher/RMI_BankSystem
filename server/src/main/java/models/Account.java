package models;
import java.io.Serializable;
public class Account implements Serializable{
    public int idAccount;
    private String accountNumber;
    private double balance=0.0;
    private int idUser;

    public Account(String accountNumber, int idUser){
        this.accountNumber=accountNumber;
        this.idUser=idUser;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public static String generateAccNumber(int idUser){

        return ""+String.format("%02d", idUser)+"";
    }
}
