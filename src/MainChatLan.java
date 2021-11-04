import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
public class MainChatLan extends Application{
    public static void main(String [] args) {
        launch(args);
    }

    @Override
    public void start(Stage guiChatLan) throws Exception {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Gui/Log/sign.fxml"));
            parent.setId("parent");
            Scene guiSign = new Scene(parent);
            guiChatLan.setScene(guiSign);
            guiChatLan.getIcons().add(new Image("/image/icon_scr.png"));
            //------------------
            guiChatLan.setTitle("ChatLan");
            //guiChatLan.setResizable(false);
            guiChatLan.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
  }
}
