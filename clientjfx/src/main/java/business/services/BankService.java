package business.services;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import business.interfaces.IHistoryService;
import estm.dsic.jee.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.History;

public class BankService implements Initializable{
    private IHistoryService historyService=App.historyService;



    @FXML
    private TableColumn<History, String> amount;

    @FXML
    private Label balance;

    @FXML
    private  TableColumn<History, String> date;

    @FXML
    private VBox deposit;

    @FXML
    private  TableColumn<History, String> from;

    @FXML
    private TableView<History> history;

    @FXML
    private Button logout;

    @FXML
    private  TableColumn<History, String> operation;

    @FXML
    private  TableColumn<History, String> to;

    @FXML
    private VBox transfer;

    @FXML
    private Label welcome;

    @FXML
    private VBox withdarwal;
    @FXML
    private Label account_number;

    @FXML
    void deposit(MouseEvent event) {
        openStage("deposit","DEPOSIT", 240, 180);
    }

    @FXML
    void logout(ActionEvent event) {
        App.user=null;
        App.account=null;
        closeStage();
        App.openStage("Login","LOGIN",240,240);
    }

    @FXML
    void transfer(MouseEvent event) {
        openStage("transfer","TRANSFER", 240, 260);
    }

    @FXML
    void withdarwal(MouseEvent event) {
        openStage("withdrawal","WITHDRAWL", 240, 180);
    }
    
    private  void openStage(String stageName,String title,int W,int H) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(stageName+".fxml"));
        Parent root;
        try {
            
            root = loader.load();
            Stage stage = new Stage();
            stage.initOwner((Stage) logout.getScene().getWindow()); 
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root,W,H);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(title);
            OperationsService operationsService= loader.getController();
            operationsService.setBS(this);
            stage.show();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        
    }

    private void closeStage(){
        Stage currentStage = (Stage) logout.getScene().getWindow();
        currentStage.close();
    }
    private ObservableList<History> historyListData = FXCollections.observableArrayList();
    public void history(){
        
        try {
            historyListData.clear();
            history.setItems(historyListData);
            for(History h:historyService.getHistory(App.account)){
                if (h.getOperation().equals("Deposit")) {
                    h.setAmount("+" + h.getAmount());
                } else if (h.getOperation().equals("Withdrawal")) {
                    h.setAmount("-" + h.getAmount());
                } else if (h.getOperation().equals("Transfer")) {
                    if (h.getFrom().equals(App.account.getAccountNumber())) {
                        h.setAmount("-" + h.getAmount());
                    } else if (h.getTo().equals(App.account.getAccountNumber())) {
                        h.setAmount("+" + h.getAmount());
                    }
                }
                historyListData.add(h);
            }
            
            from.setCellValueFactory(new PropertyValueFactory<>("from"));
            to.setCellValueFactory(new PropertyValueFactory<>("to"));
            operation.setCellValueFactory(new PropertyValueFactory<>("operation"));
            amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
    
        
            history.setItems(historyListData);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        
        
       
    }
     public void setBalance(){
        balance.setText(String.valueOf(App.account.getBalance())+"DH");
     }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        history();
        setBalance();
        welcome.setText("Welcome "+App.user.getLastName()+" "+App.user.getFirstName()+"!");
        account_number.setText(String.valueOf(App.account.getAccountNumber()));
    }
}
