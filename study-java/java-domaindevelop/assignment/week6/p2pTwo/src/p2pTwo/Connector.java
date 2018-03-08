package p2pTwo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

public class Connector {
	
	private Socket clientSocketB;
	private MessageReceiver messageReceiver;
	private MessageSender messageSender;
	public static boolean connectState;
	
	public Connector() {
		connectState = false;
	}
	
	public void connect() {
		
		try {
			clientSocketB = new Socket("localhost", 8888);
			if(clientSocketB.isConnected()) {
				connectState = true;
				System.out.println("server socket B is exist");
				messageReceiver = new MessageReceiver(clientSocketB);
				messageSender = new MessageSender(clientSocketB);	
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
				System.out.println("server socket B is not exist");
				ServerSocket serverSocketA = new ServerSocket(9999);
				HashMap<String, PrintWriter> clients = new HashMap<String, PrintWriter>();
				while(true) {
					ServerReceiver serverReceiver = new ServerReceiver(serverSocketA.accept(), clients);
					serverReceiver.start();
					if(!serverReceiver.isAlive())
						serverSocketA.close();
					
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
