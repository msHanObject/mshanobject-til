import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class SimpleClientThread extends Thread {

	private Socket socket;
	
	public SimpleClientThread(Socket socket) {
		super();
		// TODO Auto-generated constructor stub
		this.socket = socket;		
	}
	
	public void run() {
		
		String line= null;
		
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			
			while(true) {
				line= in.readLine();
				System.out.println(line);
			}
		}catch(Exception e) {
			System.out.println("client thread closed...");
		}
		
	}
	
}
