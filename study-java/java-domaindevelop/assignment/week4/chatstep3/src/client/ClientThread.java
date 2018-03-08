package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread{

	private Socket socket;
	private String clientId;
	private PrintWriter out;
	private BufferedReader in, keyboardIn;
	
	public ClientThread(Socket socket, String clientId) {
		this.socket = socket;
		this.clientId = clientId;
	}
	
	public void run() {
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			keyboardIn = new BufferedReader(new InputStreamReader(System.in));
			//client id push to server
			out.println(clientId);
			
			while(true) {
				try {
					//read others message
					String message = null;
					if((message = in.readLine()) != null) {
						System.out.println(message);
					}
					//read & write client message
					String clientInput = null;
					if((clientInput = keyboardIn.readLine()) != null) {
						out.println(clientInput);
						if(clientInput.equalsIgnoreCase("/quit"))
							break;
					}
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			
		}catch(Exception e) {
			System.err.println(e);
		}finally {
			try {
				if(out != null)
					out.close();				
			}catch(Exception e) {}
			
			try {
				if(socket != null)
					socket.close();
			}catch(Exception e) {}
		}
					
	}

	
}
