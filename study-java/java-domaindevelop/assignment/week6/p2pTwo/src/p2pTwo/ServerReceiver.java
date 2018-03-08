package p2pTwo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class ServerReceiver extends Thread{

	private Socket socket;
	private HashMap<String, PrintWriter> clients;
	private String clientId;
	
	public ServerReceiver(Socket acceptedSocket, HashMap<String, PrintWriter> clients) {
		socket = acceptedSocket;
		this.clients = clients;
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
				if(inputLine.indexOf("/to") == 0) {
					sendWhisper(inputLine);
				}else {
					broadcast(inputLine);
				}				
			}
			in.close();
			out.close();
			socket.close();
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
	
	public void sendWhisper(String msg) {
		int idStart = msg.indexOf(" ") +1;
		int idEnd = msg.indexOf(" ", idStart);
		
		if(idEnd != -1) {
			String to = msg.substring(idStart, idEnd);
			String msgContent = msg.substring(idEnd + 1);
			Object obj = clients.get(to);
			if( obj != null) {
				PrintWriter printWriter = (PrintWriter) obj;
				printWriter.println("[Whisper] [" + clientId + "'s whisper] : " + msgContent);
				printWriter.flush();
			}
		}
	}
}
