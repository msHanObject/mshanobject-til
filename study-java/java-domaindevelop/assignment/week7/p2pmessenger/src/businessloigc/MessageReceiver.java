package businessloigc;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import entity.Client;

public class MessageReceiver extends Thread{

	private Socket socket;
	
	public MessageReceiver(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			//System.out.println("MessageReceiver run()");
			ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			Client client;
			while((client = (Client) objectInputStream.readObject()) != null) {
				//System.out.println("MessageReceiver while()");
				//TODO: MessageFormat invoke
				System.out.println("From. " +client.getLocalClientId() + ") : " + client.getMessage() + " (To. " + client.getRemoteClientId() +")");
				Thread.sleep(30);
			}
			objectInputStream.close();
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
