package businesslogic;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class ClientChecker {

	public String inputId() {
		Scanner s = new Scanner(System.in);
		String id = s.nextLine();
		s.close();
		return id;
	}
	
	public boolean query(String id, LinkedHashMap<String, LinkedHashMap<String, String>> contacts) {
		if(contacts.containsKey(id))
			return true;
		else
			return false;
	}
}
