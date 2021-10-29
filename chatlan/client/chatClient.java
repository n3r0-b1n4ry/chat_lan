package chatlan.client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class chatClient {
	protected String server_name;
	protected int server_port;
	protected ServerSocket sess_recv;
	private sendThread send;
	private recvThread recv;

	public chatClient(String servername, int serverport) {
		this.server_name = servername;
		this.server_port = serverport;
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
	public void sendMsg(String receiver, String message) {
//		params:
//		String receiver is IP address of receiver
//		String message is the content string to send
		Socket socket;
		try {
			socket = new Socket(this.server_name, this.server_port);
			this.send.sendData(socket, receiver, message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<String> recvMsg() {
//		result return:
//		index 0 is IP address of sender
//		index 1 is message
		try {
			Socket socket = this.sess_recv.accept();
			return this.recv.recvData(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<String>();
	}

}
