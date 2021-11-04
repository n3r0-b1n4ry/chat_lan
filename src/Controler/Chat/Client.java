package Controler.Chat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application{

    @Override
    public void start(Stage client) throws Exception {
    try {
        Parent rootClient = FXMLLoader.load(getClass().getResource("/Gui/Chat/Client.fxml"));
       
    } catch (Exception e) {
        //TODO: handle exception
    }        
    }
    
}
