package entity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedHashMap;

public class Server extends Thread {

	private Socket socket;
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;
	private LinkedHashMap<Integer, ObjectOutputStream> connectedClient;
	private Client clientPacket;
	private int numberOfClient;
	
	public Server(Socket acceptedSocket, LinkedHashMap<Integer, ObjectOutputStream> connectedClient) {
		
		socket = acceptedSocket;
		this.connectedClient = connectedClient;
		System.err.println("server accepted socket");
		
	}
	
	public void run() {
		try {
			objectInputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			
			//System.out.println("server run()");
			
			synchronized(connectedClient) {
				numberOfClient++;
				//System.out.println(numberOfClient);
				connectedClient.put(numberOfClient, objectOutputStream);
			}
			while((clientPacket = (Client) objectInputStream.readObject()) != null) {
				
				//System.out.println(clientPacket.getMessage());
				broadcast();
				//System.out.println("from server client packet transmission");
			}
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void broadcast() {
		//System.out.println("broadcast() invoked");
				
		if(connectedClient.values().iterator().hasNext()) {
			try {
				//System.out.println(clientPacket.getMessage());
				connectedClient.values().iterator().next().writeObject(clientPacket);
				connectedClient.values().iterator().next().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}			
	}
	
}
