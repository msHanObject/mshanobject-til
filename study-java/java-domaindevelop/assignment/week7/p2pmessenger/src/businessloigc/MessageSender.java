package businessloigc;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

import entity.Client;

public class MessageSender extends Thread{

	private Socket socket;
	private Client client;
	
	public MessageSender(Socket socket, Client client) {
		this.socket = socket;
		this.client = client;
	}
	
	public void run() {
	
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));
			String clientInput = null;
			while((clientInput = keyboardIn.readLine()) != null) {
				
				if(clientInput.equalsIgnoreCase("/quit"))
					System.exit(0);

				client.setMessage(clientInput);
				objectOutputStream.writeObject(client);
				objectOutputStream.flush();
			    Thread.sleep(30);
			    client = new Client();
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
