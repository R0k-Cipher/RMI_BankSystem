package business.services;

import java.rmi.RemoteException;
import business.interfaces.IAccountService;
import business.interfaces.IUserService;
import estm.dsic.jee.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.User;


public class LoginService {
    private IUserService userService=App.userService; 
    private IAccountService accountService=App.accountService;
    
    @FXML
    private Hyperlink create_account;

    @FXML
    private TextField email;

    @FXML
    private Button login;

    @FXML
    private TextField password;

   

    @FXML
    void login(ActionEvent event) throws RemoteException {
        if(email.getText().isEmpty() || password.getText().isEmpty()){
            Alert alert=new Alert(AlertType.ERROR,"Error");
                    alert.setContentText("Email or Password Field is Empty");
                    alert.showAndWait();
        }else{
        User user=userService.login(email.getText(),password.getText());        
        if(user!=null) 
                 {
                    App.user=user;
                    App.account=accountService.getAccount(App.user);
                    email.setText("");
                    password.setText("");
                    closeStage();
                    App.openStage("Main","RMI BANKING SYSTEM",610,400);
                    
                 }else
                 {
                    Alert alert=new Alert(AlertType.ERROR,"Error");
                    alert.setContentText("Email or Password  is incorrect");
                    alert.showAndWait();
                 }
        }
    }

    @FXML
    void create_account(ActionEvent event){
        closeStage();
        App.openStage("Signup","SIGN UP",300,400);
    }

    private void closeStage(){
        Stage currentStage = (Stage) login.getScene().getWindow();
        currentStage.close();
    }

}