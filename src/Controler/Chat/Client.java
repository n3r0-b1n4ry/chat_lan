package Controler.Chat;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Client {

    @FXML
    private ImageView sendImg;
    @FXML
    private TextArea textAreaMsg;
    @FXML
    private VBox vboxChat;
    @FXML
    public Label userNameLabel;

    String textSmgUser;
    String textSmgClient;// main lay text nay add vao cho may client

    @FXML
    public void sendSmg(MouseEvent send) {
        textSmgUser = textAreaMsg.getText();

        // ------create a label containing text: user-------

        Label textUser = new Label(textSmgUser);
        textUser.setTextFill(Color.WHITE);
        textUser.setFont(Font.font("tnr", 14));
        textUser.setWrapText(true);
        textUser.setPrefWidth(150);
        textUser.setTranslateX(160);
        textUser.setAlignment(Pos.TOP_RIGHT);

        // ----add label to vbox chat-----
        vboxChat.getChildren().addAll(textUser);

        // ---test chat client----
        msgClient();
    }

    // ----- create a label containing text: client------
    public void msgClient() {
        textSmgClient = "hello";// test
        Label textClient = new Label(textSmgClient);
        textClient.setTextFill(Color.WHITE);
        textClient.setFont(Font.font("tnr", 14));
        textClient.setPrefWidth(150);
        textClient.setWrapText(true);
        // ----add text client to vbox chat----
        vboxChat.getChildren().addAll(textClient);
    }

    public void setUerName(String name) {
        userNameLabel.setText(name);
    }

}
