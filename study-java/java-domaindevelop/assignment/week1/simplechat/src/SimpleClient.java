

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient {
	public static void main(String[] args) throws IOException{
		if(args.length != 3) {
			System.err.println("Usage: java SimpleClient <host name> <port number> <user name>");
			System.exit(1);
		}
		
		String hostName = args[0];
		int portNumber = Integer.parseInt(args[1]);
		
		try(
				Socket simpleSocket = new Socket(hostName, portNumber);
				PrintWriter out = new PrintWriter(simpleSocket.getOutputStream(), true);
				
			){
				SimpleClientThread simpleClientThread = new SimpleClientThread(simpleSocket);
				simpleClientThread.start();
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
				String Isay;
				String userName = args[2];
				
				while(simpleClientThread.isAlive()) {
															
					Isay = stdIn.readLine();
					if( Isay != null) {
						Isay = "<" + userName +"> : " + Isay;
						out.println(Isay);
					}else if( Isay.equalsIgnoreCase("Bye.")) {
						break;
					}
				}
			}catch(UnknownHostException e) {
				System.err.println("Don't know about host " + hostName);
				System.exit(1);
			}catch(IOException e) {
				System.err.println("Couldn't get I/O for the connection to " + hostName);
				System.exit(1);
			}
	}
}
