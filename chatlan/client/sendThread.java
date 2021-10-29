package chatlan.client;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Base64;

public class sendThread extends Thread {
	public sendThread() {
	}

	public void sendData(Socket socket, String receiver, String data) {
		try {
//			create output stream for Socket
			DataOutputStream signalToServer = new DataOutputStream(socket.getOutputStream());

//			send signal to server
			String request = "TO:" + receiver + "|" + "MSG:" + Base64.getEncoder().encodeToString(data.getBytes());
			signalToServer.writeBytes(request + "\n");
			signalToServer.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
