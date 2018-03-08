package p2pOne;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageSender extends Thread{
	private Socket socket;
	private BufferedReader keyboardIn;
	private PrintWriter out;
	
	public MessageSender(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			keyboardIn = new BufferedReader(new InputStreamReader(System.in));
			out.println("clientA");
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
			e.printStackTrace();
		}
		
		try {
			out.close();
			keyboardIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}