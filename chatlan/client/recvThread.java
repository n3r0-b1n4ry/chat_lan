package chatlan.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

public class recvThread extends Thread {
	public recvThread() {
	}

	public ArrayList<String> recvData(Socket socket) {
		ArrayList<String> result = new ArrayList<String>();
		String msg = "";
		String sender = "";
		String[] data;

		try {
//			create input stream for Socket
			BufferedReader signalFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			data = signalFromServer.readLine().split("\\|");
			sender = data[0].split(":")[1];
			msg = new String(Base64.getDecoder().decode(data[1].split(":")[1]), StandardCharsets.UTF_8);
			if (msg != "") {
				System.out.println("Recv data from server successfully");
			}

			result.add(sender);
			result.add(msg);
		} catch (Exception e) {
			System.out.println(e);
		}

		return result;
	}
}
