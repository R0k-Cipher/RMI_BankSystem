package business.services;

import java.io.IOException;
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

public class SignupService {
    private IUserService userService=App.userService; 
    private IAccountService accountService=App.accountService;
    @FXML
    private Button create_account;

    @FXML
    private TextField email;

    @FXML
    private TextField first_name;

    @FXML
    private TextField last_name;

    @FXML
    private Hyperlink login;

    @FXML
    private TextField password;

    @FXML
    private TextField verify_password;

    @FXML
    void create_account(ActionEvent event) {
        if(password.getText().equals(verify_password.getText())){
                try {
                    App.user=userService.createUser(new User(first_name.getText(),last_name.getText(),email.getText(),password.getText()));
                    App.account=accountService.createAccount(App.user);
                    closeStage();
                    App.openStage("Main","RMI BANKING SYSTEM",610,400);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }         
            }
            else
            {
                    Alert alert=new Alert(AlertType.ERROR,"Error");
                    alert.setContentText("Passwords do not match");
                    alert.showAndWait();
            }

    }

    @FXML
    void login(ActionEvent event) throws IOException {
        closeStage();
        App.openStage("Login","LOGIN",240,240);
    }

    private void closeStage(){
        Stage currentStage = (Stage) login.getScene().getWindow();
        currentStage.close();
    }
}
