package p2pOne;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionRetry {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connector connector = new Connector();
		if(!connector.getConnectState()) {
			try {
				Socket clientSocketA = new Socket("localhost", 8888);
				if(clientSocketA.isConnected()) {
					System.out.println("clientSocketA is connected...");
					MessageReceiver messageReceiver = new MessageReceiver(clientSocketA);
					MessageSender messageSender = new MessageSender(clientSocketA);	
					messageReceiver.start();				
					messageSender.start();
					if(!messageReceiver.isAlive())
						clientSocketA.close();
					if(!messageSender.isAlive())
						clientSocketA.close();
				}
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
