package servant;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MessageReader extends Thread{

	private Socket socket;
	private Client localClient;
	private ObjectOutputStream localIdOut;
	private String localClientId;
	public MessageReader(Socket socket, String localClientId) {
		this.socket = socket;
		this.localClientId = localClientId;
		localClient = new Client();
	}
	public void run() {
		try {
			localIdOut = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			localClient.setId(localClientId);
			localClient.setMsgToSend(null);
			localIdOut.writeObject(localClient);
			BufferedReader ReaderFromSelfServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String message = null;
			while((message = ReaderFromSelfServer.readLine()) != null) {
				System.out.println(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
