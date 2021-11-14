package Controller.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.scene.Node;
import Connect.chatClient;
import Controller.Chat.Client;
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

	@FXML
	public void clickLog(ActionEvent event) throws IOException {

		String ipServer;

		// -----------read ip server----------
		File ipServerFile = new File("c:\\windows\\temp\\ipServer.txt");
		BufferedReader readIpServer = new BufferedReader(new FileReader(ipServerFile));
		ipServer = readIpServer.readLine();

		if (ipServer == null) {

			// -----if: ip server is null-----
			Alert error = new Alert(AlertType.ERROR);
			String notification = "Error: IP Server is null" + "\n"
					+ "Please enter the IP Server in the file 'ipServer.txt' according to the path 'c:\\windows\\temp'";
			error.setHeaderText(notification);
			error.setTitle("Error");
			error.show();
		} else {

			if (userNameLog.getText().isEmpty()) {

				// ----if: user name is null-----
				Alert warning = new Alert(AlertType.WARNING);
				warning.setHeaderText("Please enter user name");
				warning.setTitle("Warning");
				warning.show();
			} else {

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

				// ---------log-----------
				chatClient userLog = new chatClient(ipServer, 1234, userNameLog.getText().toString());

				if (userLog.createConnection()) {

					userLog.login();
					readIpServer.close();
				}
				try {
					client.setSession(userLog);
					client.runRecv();
					client.enter();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}