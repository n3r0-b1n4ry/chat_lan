package Controller.Chat;

import java.util.Enumeration;
import java.util.Hashtable;
import Connect.chatClient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
	@FXML
	private Button logOut;

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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private class Recv extends Thread {

		chatClient session;

		public Recv(chatClient session) {
			this.session = session;
		}

		public void run() {
			while (true) {
				try {
					Hashtable<String, String> msg = this.session.recvMsg();
					if (msg.size() > 0) {
						Enumeration<String> e = msg.keys();

						while (e.hasMoreElements()) {

							String key = e.nextElement();

							Label textClient = new Label();
							// textClient.setText("msg recv");
							textClient.setText(key + ": " + msg.get(key));
							System.out.println(key + ": " + msg.get(key));

							textClient.setTextFill(Color.WHITE);
							textClient.setFont(Font.font("tnr", 14));
							textClient.setPrefWidth(150);
							textClient.setWrapText(true);

							// ----add text client to vbox chat----

							vboxChat.getChildren().addAll(textClient);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void runRecv() {
		// Runnable task = () -> {
		// 	Platform.runLater(() -> {
		// 		while (true) {
		// 			try {
		// 				Hashtable<String, String> msg = this.chat.recvMsg();
		// 				if (msg.size() > 0) {
		// 					Enumeration<String> e = msg.keys();
	
		// 					while (e.hasMoreElements()) {
	
		// 						String key = e.nextElement();
	
		// 						Label textClient = new Label();
		// 						// textClient.setText("msg recv");
		// 						textClient.setText(key + ": " + msg.get(key));
		// 						System.out.println(key + ": " + msg.get(key));
	
		// 						textClient.setTextFill(Color.WHITE);
		// 						textClient.setFont(Font.font("tnr", 14));
		// 						textClient.setPrefWidth(150);
		// 						textClient.setWrapText(true);
	
		// 						// ----add text client to vbox chat----
	
		// 						vboxChat.getChildren().addAll(textClient);
		// 					}
		// 				}
		// 			} catch (Exception e) {
		// 				e.printStackTrace();
		// 			}
		// 		}
		// 	});
		// };

		// Thread thread = new Thread(task);
		Recv thread = new Recv(this.chat);
		thread.setDaemon(true);
		thread.start();
	}

	@FXML
	private void clickLogOut(ActionEvent event) {
		chat.logout();
		Platform.exit();
		System.exit(0);
	}
}
