package Connect;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Hashtable;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/*
 * from: {username of sender} | to: {username of receiver}
	 * content: {message}
 */

public class recvThread extends Thread {
	public recvThread() {
	}

	public Hashtable<String, String> recvData(Socket socket) {
		Hashtable<String, String> result = new Hashtable<>();
		String msg = "", sender = "";
		Object obj;
		JSONParser parser = new JSONParser();
		JSONObject data;

		try {
//			create input stream for Socket
			BufferedReader signalFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String content = signalFromServer.readLine();
			obj = parser.parse(content);
			data = (JSONObject) obj;
			sender = data.get("from").toString();
			msg = new String(Base64.getDecoder().decode(data.get("content").toString()), StandardCharsets.UTF_8);
			if (msg != "") {
				System.out.println("Recv data from server successfully");
			}

			result.put(sender, msg);
		} catch (Exception e) {
			System.out.println(e);
		}

		return result;
	}
}
