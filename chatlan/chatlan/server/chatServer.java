package chatlan.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class chatServer {
	private ServerSocket server_conn;
	private int port_binding = 1234;

	private Hashtable<String, String> userlist = new Hashtable<>();

	/*
	 * data fields: type: {login, logout, message}
	 * 
	 * if type == [login, logout]: username: {username of client}
	 * 
	 * if type == message: username: {username of sender} | content: {message}
	 */

	public chatServer() {
	}

	public chatServer(int port_binding) {
		this.port_binding = port_binding;
	}

	public void startServer() {
		try {
			this.server_conn = new ServerSocket(this.port_binding);
			System.out.println("Server open port listening at " + this.port_binding);
			this.startProcess();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void startProcess() {
		String msg, ip, username;
		Object obj;
		JSONParser parser = new JSONParser();
		JSONObject data, respond = new JSONObject();

		DataOutputStream signalToClient;

		while (true) {
			try {
				Socket connectionSocket = this.server_conn.accept();

//				create input stream for Socket
				BufferedReader signalFromClient = new BufferedReader(
						new InputStreamReader(connectionSocket.getInputStream()));
//				create output stream for Socket
				signalToClient = new DataOutputStream(connectionSocket.getOutputStream());

//				read signal from socket
				String content = signalFromClient.readLine();
				obj = parser.parse(content);
				data = (JSONObject) obj;
//				System.out.println(content);

				switch (data.get("type").toString()) {
				case "login":
					username = (data.get("username")).toString();
					ip = (connectionSocket.getRemoteSocketAddress().toString().replace("/", "")).split(":")[0];

					if (userlist.containsKey(username) == false) {
						userlist.put(username, ip);
						System.out.println(ip + " connect with nickname: " + username);
						signalToClient.writeBytes("true");
						signalToClient.flush();
					} else {
						signalToClient.writeBytes("false");
						signalToClient.flush();
					}

//					close connection to sender
					connectionSocket.close();

					break;
				case "logout":
					username = (data.get("username")).toString();
					System.out.println(username + " disconnected");

					if (userlist.containsKey(username) == true) {
						userlist.remove(username);
					}
					connectionSocket.close();

					break;
				case "message":
					username = (data.get("username")).toString();
					msg = (data.get("content")).toString();

					signalToClient.writeBytes("true");
					signalToClient.flush();
					connectionSocket.close();

//					parse data to JSON
					respond.put("from", username);
					respond.put("content", msg);
					content = respond.toJSONString();
//					System.out.println(content);

					Enumeration<String> e = userlist.keys();
					while (e.hasMoreElements()) {
						String key = e.nextElement();

						if (key != username) {
							try {
//								send data to client
								connectionSocket = new Socket(this.userlist.get(key), 4567);
								signalToClient = new DataOutputStream(connectionSocket.getOutputStream());

								signalToClient.writeBytes(content);
								signalToClient.flush();

//								close connection to sender
								connectionSocket.close();
							} catch (Exception e1) {
								e1.printStackTrace();
								this.userlist.remove(key);
							}
						}
					}
					break;
				default:
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int getPort_binding() {
		return port_binding;
	}

	public void setPort_binding(int port_binding) {
		this.port_binding = port_binding;
	}

}