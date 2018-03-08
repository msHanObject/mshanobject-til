package connection.clientside;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashMap;

import businesslogic.ClientChecker;
import connection.serverside.Passage;
import entity.ClientInformation;
import messagemanager.MessageReceiver;
import messagemanager.MessageSender;
import storage.Contact;
import storage.ContactInterface;

public class Connector {

	private boolean connectState;
	private ServerSocket serverSocket;
	private Socket socket;
	private ClientInformation clientInfo;
	private String remoteClientId;
	private Passage passage;
	private ContactInterface contacts;
	private ClientChecker checker;
	
	public Connector() {
		connectState = false;
		contacts = new Contact();
		contacts.loadContact();
		clientInfo = new ClientInformation();
	}
	public boolean isConnectState() {
		return connectState;
	}

	public void setConnectState(boolean state) {
		connectState = state;
	}
	
	public void setServerSocket(ContactInterface contacts, String id) {
		//find and get remote client's port number for server socket
		if(contacts.getContact().containsKey(id)) {
			LinkedHashMap<String, String> address =  contacts.getContact().get(id);
			String ipAddress = null;
			for(String ip : address.keySet()) {
				ipAddress = ip;
			}
			int portNumber = Integer.parseInt(address.get(ipAddress));
			try {
				//connect socket to remoteClientId's serverSocket
				serverSocket = new ServerSocket(portNumber);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}
	
	public void setSocket(ContactInterface contacts, String id) {
		if(contacts.getContact().containsKey(id)) {
			LinkedHashMap<String, String> address =  contacts.getContact().get(id);
			String ipAddress = null;
			for(String ip : address.keySet()) {
				ipAddress = ip;
			}
			int portNumber = Integer.parseInt(address.get(ipAddress));
			try {
				//connect socket to remoteClientId's serverSocket
				socket = new Socket(ipAddress, portNumber);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public Socket getSocket() {
		return socket;
	}
	public void login() {
		//login
		checker = new ClientChecker();

		System.out.println("#Please enter your local ID");	
		String localId = checker.inputId();
				
		if(checker.query(localId, contacts.getContact())) {
			
			System.out.println("#Login("+localId+") success");
			clientInfo.setLocalId(localId);
			//connect
			connect();
		}else {
			System.out.println("#Login("+localId+") fail");				
		}
	}
	public void connect() {
		
		//show current access user list
		
		//if remote client already access local client, auto connecting the remote client
		
		setSocket(contacts, clientInfo.getLocalId());
		Socket selfSocket = new Socket();
		selfSocket = getSocket();
		
		if(selfSocket.isConnected()) {
			setConnectState(true);
			System.out.println(clientInfo.getLocalId()+"'s server socket already exists");
			MessageReceiver messageReceiver = new MessageReceiver(selfSocket);
			MessageSender messageSender = new MessageSender(selfSocket, clientInfo);	
			messageReceiver.start();				
			messageSender.start();
			
		}else {
			//select remote client id to connect			
			remoteClientId = checker.inputId();
			if(checker.query(remoteClientId, contacts.getContact())) {
				System.out.println(remoteClientId +" exists in the contact list");
				clientInfo.setRemoteId(remoteClientId);
				//show current access user list
				
			}else {
				System.out.println(remoteClientId +" doesn't exist in the contact list");				
			}
			//connect remote client
			
			setServerSocket(contacts, remoteClientId);			
			passage.setServerSocket(getServerSocket());
			passage.open();
		}		
	}
}
