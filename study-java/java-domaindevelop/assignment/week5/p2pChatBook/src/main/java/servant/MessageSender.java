package servant;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MessageSender extends Thread{

	private String userId;
	private Socket socket;
	private BufferedReader keyboardIn;
	private ObjectOutputStream keyboardOut;
	private Client localClient;
	
	public MessageSender(Socket socket, String id) {
		this.socket = socket;
		userId = id;
		localClient = new Client();
	}
	
	public void run() {
		try {
		localClient.setId(userId);	
		keyboardOut = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		keyboardIn = new BufferedReader(new InputStreamReader(System.in));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		try {
			localClient.setMsgToSend(keyboardIn.readLine());
			while(localClient.getMsgToSend() != null){
				if(localClient.getMsgToSend().equalsIgnoreCase("/quit")) {
					break;
				}
				else {
					keyboardOut.writeObject(localClient);
					localClient = new Client();//I don't know why should I do this line
					//Messages entered by A are also saved to the file
					Thread.sleep(30);
					localClient.setMsgToSend(keyboardIn.readLine());
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {			
				keyboardOut.close();
				keyboardIn.close();
			}catch(IOException e) {
				e.printStackTrace();
			}			
		}	
	}
}
