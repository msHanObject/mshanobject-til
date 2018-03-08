package p2pOne;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MessageReceiver extends Thread{
	
	private Socket socket;
	private BufferedReader in;
	
	public MessageReceiver(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String message = null;
		try {
			while((message = in.readLine()) != null) {
				System.out.println(message);
			}
			Thread.sleep(50);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}