package servant;

import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

import storage.Contact;
import storage.ContactInterface;

/**
 * @author nulgrace
 * Client have a list of clients.
 * First, request for client id.
 * Second, read a list of clients which randomly registered and had other client's IP address.
 * The list of clients is made by Hash Map.
 * Third, select the client to connect to.
 * After selection, it creates a server socket based on the port number of the selected client.
 * When you successfully create a server socket, it creates and starts a server thread, ready to receive sockets for clients.
 * The server thread has a socket that is connected as a parameter.
 * And then get the IP address of connected client.
 * It then creates a socket with the IP address you chose to connect as parameters. 
 * Next, we create and start an object of the read-thread and write-thread classes.
 *  
 */
public class Servant {
		
	public static void main(String[] args) {
		//login process
		Scanner inputId = new Scanner(System.in);
		System.out.println("please input your client id.");
		String localClientId;
		localClientId = inputId.nextLine();
		
		//contact setting
		ContactInterface contacts= new Contact();
		contacts.setContact();
		
		//show ui(UserList + logout command)
		System.out.println("If you want logout, please entered '/logout'");
		
		//select remote client id
		System.out.println("please select a client to connect");
		String remoteClientId;
		remoteClientId = inputId.nextLine();
		
		if(remoteClientId.equalsIgnoreCase("/logout"))
			System.exit(0);

		Socket socket = null;
		//find and get remote client's ip address & port number
		if(contacts.getContact().containsKey(remoteClientId)) {
			HashMap<String, String> address = contacts.getContact().get(remoteClientId);
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
		// ReadMsgFileThread
		MessageFileReader readMsgFile = new MessageFileReader(remoteClientId);
		readMsgFile.start();			
		//show ui ( Ui to convert conversation file with selected user to chat window )
		
		// WriteMsgThread
		MessageSender writeMsg = new MessageSender(socket, localClientId, remoteClientId);
		writeMsg.start();
		//show ui(UserList + logout command)
		
		//MessageReader
		Socket localServerSocket = null;
		//get local client server's ip, port number 
		if(contacts.getContact().containsKey(localClientId)) {
			HashMap<String, String> address = contacts.getContact().get(localClientId);
			String ipAddress = null;
			for(String ip : address.keySet()) {
				ipAddress = ip;
			}
			int portNumber = Integer.parseInt(address.get(ipAddress));
			try {
				//connect socket to localClientId's serverSocket
				localServerSocket = new Socket(ipAddress, portNumber);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		MessageReader messageReader = new MessageReader(localServerSocket, localClientId);
		messageReader.start();
	}
}