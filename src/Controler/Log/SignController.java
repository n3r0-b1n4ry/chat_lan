package Controler.Log;

import java.io.IOException;
import javafx.scene.Node;
import Connect.chatClient;
import Controler.Chat.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignController {
	private Stage guiChat;
	private Parent parent;
	private Scene scene;

	@FXML
	private Button moveclient;
	@FXML
	public TextField userNameLog;

	public void UserLog() {

	}

	@FXML
	public void clickLog(ActionEvent event) throws IOException {
		if (userNameLog.getText().isEmpty()) {

			// ----user name is null-----

			Alert warning = new Alert(AlertType.WARNING);
			warning.setHeaderText("Please enter user name");
			warning.setTitle("Warning");
			warning.show();
		} else {

			// -------user name is'n null----

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/Gui/Chat/Client.fxml"));
			parent = loader.load();
			parent.setId("backgroundSign");
			guiChat = (Stage) ((Node) (event.getSource())).getScene().getWindow();
			scene = new Scene(parent);
			scene.getStylesheets().add(getClass().getResource("/style/Chat/client.css").toExternalForm());
			guiChat.setScene(scene);

			// ------- change user name from text field to user name of client---

			Client client = loader.getController();
			client.setUerName(userNameLog.getText().toString());

			// -----------log----------

			chatClient userLog = new chatClient("127.0.0.1", 1234, userNameLog.getText().toString());
			if (userLog.createConnection()) {
				userLog.login();
			}
		}

	}
}
