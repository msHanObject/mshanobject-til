package userinterface;


import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;

import connection.Connector;
import entity.Client;
import entity.Contact;

public class ChatInterface {

	private Contact contact;
	private Client client;
	private Scanner scanner;
	private Connector connector;
	
	public ChatInterface(Contact contact) {
		//System.out.println("ChatInterface() invoked");
		this.contact = contact;
		client = new Client();
	}
	
	public void excute(String id) {
		showContacts();
		client.setLocalClientId(id);
		selectRemoteClient();
		connector = new Connector(client, contact);
		invokeConnector();
	}
	
	public void showContacts() {
		Iterator<String> contactIterator = contact.getContacts().keySet().iterator();
		while(contactIterator.hasNext()) {
			
			String clientId = contactIterator.next();
			LinkedHashMap<String, String> address = contact.getContacts().get(clientId);
			Iterator<String> addressIterator = address.keySet().iterator(); 
			
			while(addressIterator.hasNext()) {
				String ipAddress = addressIterator.next();			
				String portNumber = address.get(ipAddress);
				System.out.println("#Client Id: "+ clientId+" #Ip Address: "+ ipAddress+" #Port Number: " + portNumber);				
			}	
		}
	}
	
	public void selectRemoteClient() {
		System.err.println("Please enter remote client id");
		scanner = new Scanner(System.in);
		String remoteClientId = scanner.nextLine();
		if(!remoteClientId.equalsIgnoreCase(client.getLocalClientId())) {
			client.setRemoteClientId(remoteClientId);			
		}
		else {
			System.err.println("Can't connect to self");
			System.exit(1);			
		}
			
	}
	
	public void invokeConnector() {
		//Connector invoke
		connector.connect();
	}
}
