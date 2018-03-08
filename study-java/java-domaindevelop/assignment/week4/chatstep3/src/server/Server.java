package server;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashMap;

public class Server {

	public static void main(String[] args) {
		try {
			int portNumber = 7777;
			ServerSocket serverSocket = new ServerSocket(portNumber);
			System.out.println("Waiting client...");
			HashMap<String, PrintWriter> clients = new HashMap<String, PrintWriter>();
			boolean listening = true;
			while(listening) {
				ServerThread serverThread = new ServerThread(serverSocket.accept(), clients);
				serverThread.start();
				if(!serverThread.isAlive())
					serverSocket.close();
			}			
		}catch(Exception e) {
			System.err.println(e);
			
		}
	}

}
