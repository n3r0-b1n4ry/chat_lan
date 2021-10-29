package chatlan.client;

public class client {
	public static void main(String[] args) {
		chatClient client = new chatClient("192.168.40.32", 1234);
//		chatClient client = new chatClient("127.0.0.1", 1234);
		if (client.createConnection()) {
			while (true) {
				System.out.println(client.recvMsg());
			}
//			client.sendMsg("192.168.40.10", "hello xyz");
//			client.sendMsg("192.168.40.10", "hello 456");
		}

	}
}
