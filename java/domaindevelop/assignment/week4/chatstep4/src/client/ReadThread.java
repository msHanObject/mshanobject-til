package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread {

	private Socket socket;
	private BufferedReader in;
	
	public ReadThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
