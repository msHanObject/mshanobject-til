package storage;

import java.util.HashMap;

public interface ContactInterface {

	public void setContact();
	public void addContact(String clientId, HashMap<String, String> address);
	public String getClientId();
	public HashMap<String, HashMap<String, String>> getContact();
	//public void isExist();
}
