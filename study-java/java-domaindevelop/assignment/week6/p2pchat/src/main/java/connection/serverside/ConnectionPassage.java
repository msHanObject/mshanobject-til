package connection.serverside;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class ConnectionPassage extends Thread {

	private Socket socket;
	private LinkedHashMap<String, PrintWriter> clients;
	private String clientId;
	private PrintWriter out;
	private BufferedReader in;
	
	public ConnectionPassage(Socket acceptedSocket, LinkedHashMap<String, PrintWriter> accessClients) {
		socket = acceptedSocket;
		this.clients = accessClients;
	}
	
	public void run() {
		try {			
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			clientId = in.readLine();
			broadcast(clientId + " is connected.");
			
			synchronized(clients) {
				clients.put(clientId, out);
			}
			
			String inputLine = null;
			while((inputLine = in.readLine()) != null) {
				if(inputLine.equalsIgnoreCase("/quit"))
					break;
				else
					broadcast(inputLine);			
			}
		}catch(IOException e) {			
			e.printStackTrace();
		}finally {
			synchronized(clients) {
				clients.remove(clientId);
			}			
			broadcast(clientId + " client has been disconnected.");
			try {
				if(socket != null)
					socket.close();
				if(in != null)
					in.close();
				if(out != null)
					out.close();
			}catch(Exception e) {}
		}
		
	}
	
	public void broadcast(String msg) {
		synchronized(clients){
			Collection<PrintWriter> collection = clients.values();
			Iterator<PrintWriter> iterator = collection.iterator();
			while(iterator.hasNext()){
				PrintWriter printWriter = (PrintWriter)iterator.next();
				printWriter.println("[All] [" + clientId + "] : " + msg);
				printWriter.flush();
			}
		}
	}

}
