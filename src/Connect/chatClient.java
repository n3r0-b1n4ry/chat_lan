package Connect;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class chatClient {
	protected String username;
	protected String server_name;
	protected int server_port;
	protected ServerSocket sess_recv;
	private sendThread send;
	private recvThread recv;

	public chatClient(String servername, int serverport, String username) {
		this.server_name = servername;
		this.server_port = serverport;
		this.username = username;
	}

	public boolean createConnection() {
		try {
//			open port to recv messages from server 
			this.sess_recv = new ServerSocket(4567);

//			start thread send and recv
			this.send = new sendThread();
			this.recv = new recvThread();
			this.send.start();
			this.recv.start();

			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
//		this is username of client
		this.username = username;
	}

	public String getServername() {
		return server_name;
	}

	public void setServername(String servername) {
//		this is IP address of server
		this.server_name = servername;
	}

	public int getServerport() {
		return server_port;
	}

	public void setServerport(int serverport) {
//		this is port of server
		this.server_port = serverport;
	}

//	feature function
	public boolean login() {
		Socket socket;
		try {
			socket = new Socket(this.server_name, this.server_port);
			return this.send.sendData(socket, this.username, "login", "");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean logout() {
		Socket socket;
		try {
			socket = new Socket(this.server_name, this.server_port);
			this.send.sendData(socket, this.username, "logout", "");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean sendMsg(String message) {
//		params:
//		String message is the content string to send
		Socket socket;
		try {
			socket = new Socket(this.server_name, this.server_port);
			this.send.sendData(socket, this.username, "message", message);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Hashtable<String, String> recvMsg() {
//		result return:
//		key is IP address of sender
//		value is message
		try {
			Socket socket = this.sess_recv.accept();
			return this.recv.recvData(socket);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Hashtable<>();
	}

}
