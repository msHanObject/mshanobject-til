package connection.serverside;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.LinkedHashMap;

public class Passage {

	private ServerSocket serverSocket;
	private LinkedHashMap<String, PrintWriter> accessClients;
	
	public void setServerSocket(ServerSocket serverSocket) {
			this.serverSocket = serverSocket;		
	}
	public void open() {
				
		accessClients = new LinkedHashMap<String, PrintWriter>();
		while(true) {
			ConnectionPassage connectionPassage;
			try {
				connectionPassage = new ConnectionPassage(serverSocket.accept(), accessClients);
				connectionPassage.start();
				if(!connectionPassage.isAlive()) {
					try {
						serverSocket.close();
					} catch (IOException e) {
						System.out.println("couldn't close server socket or couldn't find the server socket");
					}
				}
			} catch (IOException e1) {
				System.err.println("connection passage was closed");
			}
			
		}
		
	}

}
