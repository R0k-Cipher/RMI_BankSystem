package models;
import java.io.Serializable;
public class History implements Serializable{
    private int idHistory;
    private int idAccount;
    private int idUser;
    private String amount;
    private String operation;
    private String date;
    private String from;
    private String to;


    public History(int idAccount, int idUser, String amount, String operation, String date, String from,String to){
        this.idAccount=idAccount;
        this.idUser=idUser;
        this.amount=amount;
        this.operation=operation;
        this.date=date;
        this.from=from;
        this.to=to;
   }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
    
    public int getIdAccount() {
        return idAccount;
    }
    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }
    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getOperation() {
        return operation;
    }
    public void setOperation(String operation) {
        this.operation = operation;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public int getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(int idHistory) {
        this.idHistory = idHistory;
    }


    

}
