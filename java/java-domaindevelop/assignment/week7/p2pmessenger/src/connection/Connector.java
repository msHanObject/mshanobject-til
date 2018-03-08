package connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashMap;

import businessloigc.MessageReceiver;
import businessloigc.MessageSender;
import entity.Client;
import entity.Contact;
import entity.Server;

public class Connector {

	private ServerSocket serverSocket;
	private Socket socket;
	private Client client;
	private Contact contact;
	private String localIpAddress;
	private int localPortNumber;
	private String remoteIpAddress;
	private int remotePortNumber;
	private Server server;
	private LinkedHashMap<Integer, ObjectOutputStream> connectedClient;
	
	public Connector(Client client, Contact contact) {
		this.client = client;
		this.contact = contact;	
	}
	
	public void connect() {
		checkSelf();
		createRemoteServer();
	}
	public void checkSelf() {
		try {
			String localClientId = client.getLocalClientId();
			localIpAddress = contact.getContacts().get(localClientId).keySet().iterator().next();
			localPortNumber = Integer.parseInt(contact.getContacts().get(localClientId).get(localIpAddress));
			
			socket = new Socket(localIpAddress, localPortNumber);
			
			if(socket.isConnected()) {
				System.err.println("self server socket is exist");
                MessageReceiver messageReceiver = new MessageReceiver(socket);
                MessageSender messageSender = new MessageSender(socket, client);
                messageReceiver.start();
                messageSender.start();
			}else {
				socket.close();
			}
		} catch (IOException e) {
			System.err.println("Connection refused(This socket does not exist)");
		} 
	}
	
	public void createRemoteServer() {
		
		try {
			
			String remoteClientId = client.getRemoteClientId();	
			remoteIpAddress = contact.getContacts().get(remoteClientId).keySet().iterator().next();
			remotePortNumber = Integer.parseInt(contact.getContacts().get(remoteClientId).get(remoteIpAddress));
			
			serverSocket = new ServerSocket(remotePortNumber);
			connectedClient = new LinkedHashMap<Integer, ObjectOutputStream>();
			
			server = new Server(serverSocket.accept(), connectedClient);
			server.start();
			
			if(serverSocket.isBound())
				connectToRemoteServer();
			
			if(!server.isAlive())
				serverSocket.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void connectToRemoteServer() {
		//System.out.println("connectToRemoteServer() invoked");
		
		try {
			
			socket = new Socket(remoteIpAddress, remotePortNumber);
			
			if(socket.isConnected()) {
				System.out.println("connecting to remote socket is success");
				MessageSender messageSender = new MessageSender(socket, client);
                MessageReceiver messageReceiver = new MessageReceiver(socket);
                messageSender.start();
                messageReceiver.start();
                if(!messageReceiver.isAlive())
                		socket.close();
                if(!messageSender.isAlive())
                		socket.close();
			}
			else
				socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
