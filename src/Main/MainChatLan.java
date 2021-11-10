package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainChatLan extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage guiChatLan) throws Exception {
        try {

            // -----set scene Sign----------

            Parent signParent = FXMLLoader.load(getClass().getResource("/Gui/Log/sign.fxml"));
            signParent.setId("signParent");
            Scene guiSign = new Scene(signParent);
            guiSign.getStylesheets().add(getClass().getResource("/style/Log/Sign.css").toExternalForm());
            guiChatLan.setScene(guiSign);
            guiChatLan.getIcons().add(new Image("/image/logo_app.png"));
            guiChatLan.setTitle("ChatLan");
            guiChatLan.setResizable(false);
            guiChatLan.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
