package chatlan.client;

import java.util.Enumeration;
import java.util.Hashtable;

public class client {
	public static void main(String[] args) {
//		chatClient client = new chatClient("192.168.40.25", 1234, "user1");
		chatClient client = new chatClient("192.168.40.25", 1234, "user2");

		if (client.createConnection()) {
			client.login();
			while (true) {
				Hashtable<String, String> msg = client.recvMsg();
				if (msg.size() > 0) {
					Enumeration<String> e = msg.keys();

					while (e.hasMoreElements()) {
						String key = e.nextElement();
						System.out.println(key + ": " + msg.get(key));
					}
				}
			}
//			client.sendMsg("hello xyz");
//			client.sendMsg("hello 456");
		}
		client.logout();
	}
}
