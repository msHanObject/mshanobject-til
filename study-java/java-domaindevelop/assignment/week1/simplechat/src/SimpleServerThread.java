

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleServerThread extends Thread{
	
	private Socket socket = null;

	public SimpleServerThread(Socket socket) {
		super();
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}
	
	public void run() {
		
		try (				
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(
						new InputStreamReader(
								socket.getInputStream()));
			){
			System.out.println("New client has come");
			SimpleProcessor sp = new SimpleProcessor();
			sp.add(out);
			String inputLine;			
						
			while((inputLine = in.readLine()) != null) {
				System.out.println("<Received> "+inputLine);
				sp.send(inputLine);				
			}
			socket.close();			
		}catch(IOException e) {			
			e.printStackTrace();
		}		
	}
}
