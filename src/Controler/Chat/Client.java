package Controler.Chat;

import java.util.Enumeration;
import java.util.Hashtable;

import Connect.chatClient;
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
	private chatClient chat;

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
	String textUser;

	public void setUerName(String name) {
		userNameLabel.setText(name);
	}

	public void setSession(chatClient session) {
		this.chat = session;
	}

	@FXML
	public void sendSmg(MouseEvent send) {

		textSmgUser = textAreaMsg.getText().toString();

		if (textSmgUser.isEmpty() == false) {
			try {
				this.chat.sendMsg(textSmgUser);

				Label textUser = new Label(textSmgUser);
				textUser.setTextFill(Color.WHITE);
				textUser.setFont(Font.font("tnr", 14));
				textUser.setWrapText(true);
				textUser.setPrefWidth(200);
				textUser.setTranslateX(295);
				textUser.setAlignment(Pos.TOP_RIGHT);

				// ----add label to vbox chat-----
				vboxChat.getChildren().addAll(textUser);

				// ---test chat client----
				// msgClient();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	// ----- create a label containing text: client------
	public void outMsg() {
		
			Hashtable<String, String> msg = this.chat.recvMsg();
			if (msg.size() > 0) {
				Enumeration<String> e = msg.keys();

				while (e.hasMoreElements()) {
					
					String key = e.nextElement();
					
					Label textClient = new Label();
					textClient.setText(key + ": " + msg.get(key));
					
					textClient.setTextFill(Color.WHITE);
					textClient.setFont(Font.font("tnr", 14));
					textClient.setPrefWidth(150);
					textClient.setWrapText(true);
					
					// ----add text client to vbox chat----
					
					vboxChat.getChildren().addAll(textClient);
					
				}
			}
		}
		
	

}
