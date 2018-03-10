package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

	public static void main(String[] args) {

		int portNum = 6666;	
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(portNum);
			System.out.println("Waiting remote client's messages...");
			while(true) {
				MessageReceiver msgReceive = new MessageReceiver(serverSocket.accept());
				msgReceive.start();
				if(!msgReceive.isAlive())
					serverSocket.close();				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
