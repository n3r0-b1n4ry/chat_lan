package Controller.Chat;

import java.util.Enumeration;
import java.util.Hashtable;
import Connect.chatClient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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
	String textUser;

	public void setUerName(String name) {
		userNameLabel.setText(name);
	}

	public void setSession(chatClient session) {
		this.chat = session;
	}

	@FXML
	public void sendSmg(MouseEvent send) {

		send();
	}

	public void enter() {
		textAreaMsg.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent ev) {
				if (ev.getCode() == KeyCode.ENTER) {
					textAreaMsg.setText(textAreaMsg.getText().trim());
					System.out.println(textAreaMsg.getText());
					send();
				}
			}
		});
		{
		}
	}

	public void send() {
		textSmgUser = textAreaMsg.getText().toString();
		if (textSmgUser.isEmpty() == false) {
			try {
				this.chat.sendMsg(textSmgUser);

				FlowPane paneSend = new FlowPane();
				paneSend.setTranslateX(5);
				// paneSend.setAlignment(Pos.TOP_RIGHT);
				Label keyUser = new Label();
				keyUser.setText(userNameLabel.getText().toString() + ": ");
				keyUser.setFont(Font.font("Arial", FontWeight.BOLD, 14));
				keyUser.setTextFill(Color.WHITE);
				paneSend.getChildren().add(keyUser);
				
				Label textUser = new Label(textSmgUser);
				textUser.setTextFill(Color.WHITE);
				textUser.setFont(Font.font("Arial", 14));
				textUser.setWrapText(true);
				textUser.setMaxWidth(400);
				textUser.setAlignment(Pos.TOP_RIGHT);
				textUser.setPadding(new Insets(5));
				textUser.setStyle("-fx-background-color: #696969; -fx-border-radius: 10;-fx-border-color: burlywood;");

				paneSend.getChildren().add(textUser);

				// ----add label to vbox chat-----
				vboxChat.getChildren().add(paneSend);

				// ----clear textarea----
				textAreaMsg.clear();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// ------create a class Recv: update msg to UI--------
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
							if (key.equals(userNameLabel.getText())) {
								break;
							} else {
								Pane paneMsg = new Pane();
								paneMsg.setTranslateX(5);

								FlowPane flowPaneMsg = new FlowPane();
								flowPaneMsg.setHgap(10);

								Label keyName = new Label();
								keyName.setText(key + ": ");
								keyName.setFont(Font.font("Arial", FontWeight.BOLD, 14));
								keyName.setTextFill(Color.WHITE);
								flowPaneMsg.getChildren().add(keyName);

								Label textClient = new Label(msg.get(key));
								textClient.setTextFill(Color.WHITE);
								textClient.setFont(Font.font("Arial", 14));
								textClient.setMaxWidth(400);
								textClient.setWrapText(true);
								textClient.setPadding(new Insets(5));
								textClient.setStyle(
										"-fx-background-color: #696969; -fx-border-radius: 10;-fx-border-color: burlywood;");
								flowPaneMsg.getChildren().add(textClient);
								paneMsg.getChildren().add(flowPaneMsg);

								// ----add text client to vbox chat---
								Platform.runLater(() -> {
									vboxChat.getChildren().add(paneMsg);
								});
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ----create Thread (chatClient)--------
	public void runRecv() {
		Recv thread = new Recv(this.chat);
		thread.setDaemon(true);
		thread.start();
	}

	// -----logout-----------
	@FXML
	private void clickLogOut(ActionEvent event) {
		chat.logout();
		Platform.exit();
		System.exit(0);
	}
}
