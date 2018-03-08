package entity;

import service.GuestService;
import ui.GuestInterface;

public class Guest extends GuestService implements GuestInterface{

	@Override
	public void exe() {
		// TODO Auto-generated method stub
		System.out.println("\nRunning Guest Mode...\n");
		this.excuteMenu();
	}

}
