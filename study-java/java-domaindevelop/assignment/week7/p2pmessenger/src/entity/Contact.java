package entity;

import java.util.LinkedHashMap;

public class Contact {

	private LinkedHashMap<String, LinkedHashMap<String, String>> contacts;
	
	public Contact(LinkedHashMap<String, LinkedHashMap<String, String>> contacts) {
		setContacts(contacts);
	}
	
	public void setContacts(LinkedHashMap<String, LinkedHashMap<String, String>> contacts) {
		this.contacts = contacts;
	}
	
	public LinkedHashMap<String, LinkedHashMap<String, String>> getContacts() {
		return contacts;
	}
}
