package p2pTwo;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionRetry {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connector connector = new Connector();
		if(!connector.getConnectState()) {
			try {
				Socket clientSocketB = new Socket("localhost", 9999);
				if(clientSocketB.isConnected()) {
					System.out.println("clientSocketB is connected...");
					MessageReceiver messageReceiver = new MessageReceiver(clientSocketB);
					MessageSender messageSender = new MessageSender(clientSocketB);	
					messageReceiver.start();				
					messageSender.start();
					if(!messageReceiver.isAlive())
						clientSocketB.close();
					if(!messageSender.isAlive())
						clientSocketB.close();
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
