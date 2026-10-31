package estm.dsic.jee;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Account;
import models.User;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import business.interfaces.IAccountService;
import business.interfaces.IHistoryService;
import business.interfaces.IUserService;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static User user;
    public static Account account;
    public static IUserService userService ;
    public static IAccountService accountService;
    public static IHistoryService historyService;
    @Override
    public void start(Stage stage) throws IOException, NotBoundException {
       
        scene = new Scene(loadFXML("Login"), 240, 240);
        stage.setScene(scene);
        stage.setTitle("LOGIN");
        stage.setResizable(false);
        stage.show();
        
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        userService = (IUserService)Naming.lookup("rmi://localhost:9090/userService");
        accountService = (IAccountService)Naming.lookup("rmi://localhost:9090/accountService");
        historyService = (IHistoryService)Naming.lookup("rmi://localhost:9090/historyService");
        launch();
    }


    public static void openStage(String stageName,String title,int W,int H) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(stageName+".fxml"));
        Parent root;
        try {
            root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root,W,H);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        
    }

}