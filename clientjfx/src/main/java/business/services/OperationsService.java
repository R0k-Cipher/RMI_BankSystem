package business.services;

import java.rmi.RemoteException;
import business.interfaces.IAccountService;
import estm.dsic.jee.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


public class OperationsService {
    
    private IAccountService accountService=App.accountService;
    private BankService bankService;
    public void setBS(BankService bs){
        bankService=bs;
    }
    @FXML
    private TextField amount_deposit;

    @FXML
    private Button deposit;

    @FXML
    private TextField amount_withdraw;

    @FXML
    private Button withdraw;

    @FXML
    private TextField account_transfer;

    @FXML
    private TextField amount_transfer;

    @FXML
    private Button transfer;

    @FXML
    void transfer(ActionEvent event) {
        try {
            if(App.account.getBalance()<=0 || App.account.getBalance()-Double.parseDouble(amount_transfer.getText())<=0){
                Alert alert=new Alert(AlertType.ERROR,"Error");
                alert.setContentText("Insuffisant Balance");
                alert.showAndWait();
            }else{
                if(accountService.getAccountByAccountNumber(account_transfer.getText())==null){
                    Alert alert=new Alert(AlertType.ERROR,"Error");
                    alert.setContentText("Invalid Account Number");
                        alert.showAndWait();
                        account_transfer.setText("");
                        amount_transfer.setText("");
                    
                }
                else{
                accountService.transfer(App.account, account_transfer.getText(), Double.parseDouble(amount_transfer.getText()));
                App.account=accountService.getAccount(App.user);
                bankService.history();
                bankService.setBalance();
                Stage currentStage = (Stage) transfer.getScene().getWindow();
                currentStage.close();
                Alert alert=new Alert(AlertType.INFORMATION,"SUCCESS");
                        alert.setContentText("Transfer Complete");
                        alert.showAndWait();
                }
            }
        } catch (NumberFormatException | RemoteException e) {
            
            e.printStackTrace();
        }
    }

    @FXML
    void withdraw(ActionEvent event) {
        try {
            if(App.account.getBalance()<=0 || App.account.getBalance()-Double.parseDouble(amount_withdraw.getText())<=0){
                Alert alert=new Alert(AlertType.ERROR,"Error");
                alert.setContentText("Insuffisant Balance");
                alert.showAndWait();

            }else{
            accountService.withdrawal(App.account,Double.parseDouble(amount_withdraw.getText()));
            App.account=accountService.getAccount(App.user);
            bankService.history();
            bankService.setBalance();
            Stage currentStage = (Stage) withdraw.getScene().getWindow();
            currentStage.close();
            Alert alert=new Alert(AlertType.INFORMATION,"SUCCESS");
                    alert.setContentText("Withrawel Complete");
                    alert.showAndWait();
            }
        } catch (NumberFormatException | RemoteException e) {
            
            e.printStackTrace();
        }
    
    }

    @FXML
    void deposit(ActionEvent event) {
        try {
            accountService.deposit(App.account,Double.parseDouble(amount_deposit.getText()));
            App.account=accountService.getAccount(App.user);
            bankService.history();
            bankService.setBalance();
            Stage currentStage = (Stage) deposit.getScene().getWindow();
            currentStage.close();
            Alert alert=new Alert(AlertType.INFORMATION,"SUCCESS");
                    alert.setContentText("Deposit Complete");
                    alert.showAndWait();
        } catch (NumberFormatException | RemoteException e) {
            e.printStackTrace();
        }
        
        
    }

   

    

    

   
}
