package Controler.Log;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SignController {
    private Stage guiChat;
    private Parent parent;
    private Scene scene;
    
    @FXML
    private Button moveclient;
    
    @FXML
    public void clickClient(ActionEvent event) throws IOException{
    parent = FXMLLoader.load(getClass().getResource("/Gui/Chat/Client.fxml"));
    parent.setId("backgrclient");
    guiChat = (Stage) ((Node) (event.getSource())).getScene().getWindow();
    scene = new Scene(parent);
    scene.getStylesheets().add(getClass().getResource("/style/Chat/client.css").toExternalForm());
    guiChat.setScene(scene);
    guiChat.show();
    }
    
}
