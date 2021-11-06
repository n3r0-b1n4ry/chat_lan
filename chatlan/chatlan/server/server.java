package chatlan.server;

public class server {
	public static void main(String[] args) {
//		server open port 1234 by default
		chatServer server = new chatServer();
		server.startServer();
	}
}
