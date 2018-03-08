package client;

import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		String hostName = "localhost";
		int portNumber =  7777;
		System.out.println("Please input your Id.");
		Scanner inputId = new Scanner(System.in);
		String clientId = inputId.nextLine();
		try {
			Socket socket = new Socket(hostName, portNumber);
			ReadThread readThread = new ReadThread(socket);
			WriteThread writeThread = new WriteThread(socket, clientId);
			readThread.start();
			writeThread.start();
			if(!readThread.isAlive()) {				
				socket.close();				
			}
			if(!writeThread.isAlive()) {				
				socket.close();				
			}
			/*
			ClientThread clientThread = new ClientThread(socket, clientId);
			clientThread.start();
			if(!clientThread.isAlive()) {				
				socket.close();				
			}
			*/
		}catch(Exception e) {
			System.err.println(e);
		}
	}

}
