package userinterface;

import java.util.Scanner;

import businessloigc.IdChecker;
import entity.Contact;
import storage.ContactStorage;

public class LoginInterface {

	private String loginId;
	private ContactStorage contactStorage;
	private Contact contact;
	private Scanner scanner;
	
	public LoginInterface() {
		//System.out.println("LoginInterface() invoked");			
	}
	
	public void login() {
		//System.out.println("login() invoked");
		showLoginUI();
		setLoginId();
		
		contactStorage = new ContactStorage();
		contact = new Contact(contactStorage.getContacts());
		IdChecker idChecker = new IdChecker(contact);
		
		if(idChecker.check(getLoginId())) {
			System.err.println("check Id: success");
			
			executeChat(getLoginId());
			
		}else {
			System.err.println("check Id: fail");
		}
	}
	
	public void setLoginId() {
		scanner = new Scanner(System.in);
		String id = scanner.nextLine();
		loginId = id;
	}
	
	public String getLoginId() {
		return loginId;
	}
	
	public void showLoginUI() {
		System.err.println("\t\tLogin");
		System.err.print("Id: ");
	}
	
	public void executeChat(String id) {
		ChatInterface chatInterface = new ChatInterface(contact);
		chatInterface.excute(id);
	}
}
