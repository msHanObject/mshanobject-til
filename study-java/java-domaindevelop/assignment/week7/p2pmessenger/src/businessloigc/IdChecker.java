package businessloigc;

import entity.Contact;

public class IdChecker {

	private Contact contact;
	
	public IdChecker(Contact contact) {
		//System.out.println("IdChecker created");
		this.contact = contact;
	}
	
	public boolean check(String id) {
		//System.out.println("IdChecker.check() invoked");
		
		if(contact.getContacts().containsKey(id))
			return true;
		else
			return false;
	}
}
