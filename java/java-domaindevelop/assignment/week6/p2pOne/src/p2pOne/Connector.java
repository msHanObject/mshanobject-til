package p2pOne;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

public class Connector {
	
	private Socket clientSocketA;
	private MessageReceiver messageReceiver;
	private MessageSender messageSender;
	public static boolean connectState;
	
	public Connector() {
		connectState = false;
	}
	
	public void connect() {
		
		try {
			clientSocketA = new Socket("localhost", 9999);
			if(clientSocketA.isConnected()) {
				connectState = true;
				System.out.println("server socket A is exist");
				messageReceiver = new MessageReceiver(clientSocketA);
				messageSender = new MessageSender(clientSocketA);	
				messageReceiver.start();				
				messageSender.start();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			try {
				System.out.println("server socket A is not exist");
				ServerSocket serverSocketB = new ServerSocket(8888);
				HashMap<String, PrintWriter> clients = new HashMap<String, PrintWriter>();
				while(true) {
					ServerReceiver serverReceiver = new ServerReceiver(serverSocketB.accept(), clients);
					serverReceiver.start();
					if(!serverReceiver.isAlive())
						serverSocketB.close();
					
				}
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}		
	}
	
	public boolean getConnectState() {
		return connectState;
	}
}
