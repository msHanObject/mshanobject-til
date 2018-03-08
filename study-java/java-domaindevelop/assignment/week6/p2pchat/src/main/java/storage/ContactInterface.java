package storage;

import java.util.LinkedHashMap;

public interface ContactInterface {
	
	public void loadContact();
	public void addContact(String clientId, LinkedHashMap<String, String> address);
	public String getClientId();
	public LinkedHashMap<String, LinkedHashMap<String, String>> getContact();
}
