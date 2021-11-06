package Connect;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Base64;

import org.json.simple.JSONObject;

/*
 * from: {username of sender} | to: {username of receiver}
	 * content: {message}
 */

public class sendThread extends Thread {
	public sendThread() {
	}

	@SuppressWarnings("unchecked")
	public boolean sendData(Socket socket, String username, String type, String data) {
		try {
//			create output stream for Socket
			DataOutputStream signalToServer = new DataOutputStream(socket.getOutputStream());
			BufferedReader signalFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));

//			send signal to server
			JSONObject request = new JSONObject();
			request.put("type", type);
			switch (type) {
			case "login":
				request.put("username", username);
				break;
			case "logout":
				request.put("username", username);
				break;
			case "message":
				request.put("username", username);
				request.put("content", Base64.getEncoder().encodeToString(data.getBytes()));
				break;
			}

			String content = request.toJSONString();
			signalToServer.writeBytes(content + "\n");
			signalToServer.flush();
			String respond = signalFromClient.readLine();
			if (respond == "true") {
				return true;
			}
			return false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

}
