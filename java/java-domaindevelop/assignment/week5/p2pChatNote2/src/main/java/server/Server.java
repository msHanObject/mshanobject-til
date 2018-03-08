package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashMap;

public class Server {

	public static void main(String[] args) {

		int portNum = 8888;	
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(portNum);
			System.out.println("Waiting remote client's messages...");
			HashMap<String, PrintWriter> clients = new HashMap<String, PrintWriter>();
			while(true) {
				MessageReceiver msgReceive = new MessageReceiver(serverSocket.accept(), clients);
				msgReceive.start();
				if(!msgReceive.isAlive())
					serverSocket.close();				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
