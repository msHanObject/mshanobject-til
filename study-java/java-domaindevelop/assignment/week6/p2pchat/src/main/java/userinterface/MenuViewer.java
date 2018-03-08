/*package userinterface;

import java.net.Socket;
import java.util.Scanner;

import businesslogic.ClientChecker;
import connection.clientside.Connector;
import connection.serverside.Passage;
import entity.ClientInformation;
import messagemanager.MessageReceiver;
import messagemanager.MessageSender;
import storage.ContactInterface;

public class MenuViewer {

	private Scanner s;
	
	private boolean selectListener;
	
	public MenuViewer(ContactInterface contacts, ClientInformation ClientInfo, ClientChecker checker) {
		s = new Scanner(System.in);
		this.contacts = contacts;
		this.clientInfo = ClientInfo;
		this.checker = checker;
		connector = new Connector();
		passage = new Passage();
		selectListener = true;
	}
	
	public void showMenu() {
		//menus show
		System.out.println("***Messenger Menu***");
		System.out.println("0. Check for existing sockets and connect them to their sockets");
		System.out.println("1. Reload & Show contact");
		System.out.println("2. Select remote client id to connect");
		System.out.println("3. Connect to remote client's server socket");
		System.out.println("4. Retry connect to remote client");
		//System.out.println(". Add contact");
				
	}
	public void selectMenu() {
		while(s.hasNextLine()) {

			
			
			while(selectListener) {
				
				
			}
			//s.close();
		}
	}
}
*/