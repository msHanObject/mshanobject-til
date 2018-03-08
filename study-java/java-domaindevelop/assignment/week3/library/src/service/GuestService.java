package service;

import ui.BookDataFormat;

public class GuestService implements LibraryService{

	private String selectedMenu;
	private SearchService search;
	private RentalService rental;
	private BookDataFormat bdf;
	
	
	public GuestService() {
		super();
		// TODO Auto-generated constructor stub
		search = new SearchService();
		rental = new RentalService();
		bdf = new BookDataFormat();
	}

	@Override
	public void showMenu() {
		// TODO Auto-generated method stub
		System.out.println("\n\t<Guest Menu>");
		System.out.println("1. Search by Book Name.");
		System.out.println("2. Search by Book Category.");
		System.out.println("3. Rent Books.");
		System.out.println("4. Return Books.");
		System.out.println("5. Most Popular Book.");
		System.out.println("0. Exit.");		
	}

	@Override
	public void selectMenu() {
		// TODO Auto-generated method stub
		System.out.print("\nPlease enter menu number.\t");
		System.out.println("ex) <Guest> : 1");
		System.out.print("<Guest> : ");
		selectedMenu = keybordIn();
		System.out.println();
	}

	@Override
	public void excuteMenu() {
		// TODO Auto-generated method stub
		
	while(true) {
		this.showMenu();
		this.selectMenu();
		switch(Integer.parseInt(selectedMenu)) {
		case 1:
			System.out.println("Please enter book name");
			//SearchService.bookName
			search.bookName();
			break;
		case 2:
			System.out.println("Please enter book category");
			//SearchService.bookCategory
			search.bookCategory();
			break;
		case 3:
			System.out.println("Please enter book Id for rent");
			//RentalService.rent
			rental.rentBook();
			break;
		case 4:
			System.out.println("Please enter book Id for return");
			//RentalService.return
			rental.returnBook();
			break;
		case 5:
			System.out.println("Now Most Popular Book is...");
			//SearchService.mostPopular
			bdf.showMostPopularBook(search.numberOfBorrowed());
			break;
		case 0:
			//exit
			break;		
		}
		if(Integer.parseInt(selectedMenu)==0)
			break;			
		}
	}
}
