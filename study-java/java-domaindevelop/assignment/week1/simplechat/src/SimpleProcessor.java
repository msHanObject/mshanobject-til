import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Vector;

public class SimpleProcessor {

	Vector<PrintWriter> clientList = new Vector<PrintWriter>();
	
	void add(PrintWriter pw) {
		clientList.addElement(pw);
	}
	
	void send(String theInput) {
		Enumeration en = clientList.elements();
		
		while(en.hasMoreElements()) {
			try {
				PrintWriter out = (PrintWriter) en.nextElement();
				out.write(theInput);
			}catch(Exception e) {
				System.out.println("Server has down!");
				break;
			}
		}
	}	
}
