package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WriteThread extends Thread {

	private String clientId;
	private Socket socket;
	private BufferedReader keyboardIn;
	private PrintWriter out;
	
	public WriteThread(Socket socket, String clientId) {
		this.socket = socket;
		this.clientId = clientId;
	}
	
	public void run() {
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			keyboardIn = new BufferedReader(new InputStreamReader(System.in));
			out.println(clientId);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String clientInput = null;
		try {
			while((clientInput = keyboardIn.readLine()) != null) {
				out.println(clientInput);
				Thread.sleep(50);
				if(clientInput.equalsIgnoreCase("/quit"))
				System.exit(0);;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			out.close();
			keyboardIn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
