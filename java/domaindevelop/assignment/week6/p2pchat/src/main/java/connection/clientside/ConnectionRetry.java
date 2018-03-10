package connection.clientside;

import java.net.Socket;

import entity.ClientInformation;
import messagemanager.MessageReceiver;
import messagemanager.MessageSender;
import storage.Contact;
import storage.ContactInterface;

public class ConnectionRetry {

	public static void main(String[] args) {
		//retry connect to remote client
		ContactInterface contacts = new Contact();
		ClientInformation clientInfo = new ClientInformation();
		Connector connector = new Connector();
		connector.setSocket(contacts, clientInfo.getRemoteId());
		Socket socket = connector.getSocket();
		
		if(socket.isConnected()) {
			connector.setConnectState(true);
			System.out.println(clientInfo.getRemoteId()+"'s server socket already exists");
			MessageReceiver messageReceiver = new MessageReceiver(socket);
			MessageSender messageSender = new MessageSender(socket, clientInfo);	
			messageReceiver.start();				
			messageSender.start();
		}

	}

}
