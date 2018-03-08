package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
			e1.printStackTrace();
		}
		String message = null;
		try {
			while((message = in.readLine()) != null) {
				System.out.println(message);
				saveConversation(message);
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
	
	public void saveConversation(String msg) {
		try {
			DataOutputStream dataOut = new DataOutputStream(new FileOutputStream("../allConversation.txt", true));
			try {
				dataOut.writeUTF(msg+"\n");
				dataOut.flush();
				dataOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
	}
}
