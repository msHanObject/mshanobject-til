import java.util.ArrayList;

class LibrarySystem {

	private GuestMenu guestMenu;
	private Processor processor;
	private Search search;	
	private BookStorage bookStorage;

	public LibrarySystem(){
		guestMenu = new GuestMenu();
		processor = new Processor();
		search = new Search();		
		bookStorage = new BookStorage();
	}

	public static void main(String[] args){
		
		LibrarySystem librarySystem = new LibrarySystem();

		System.out.println("================================================================");
		System.out.println("			Namoo Sori			");
		System.out.println("			 Librarry			");
		System.out.println("================================================================");
		librarySystem.bookStorage.setDataFileLocation();
		
		/*
		  data file location example
		  ./src/namoo_library.dat
		
		*/
		try {	
			librarySystem.bookStorage.loadBookData();
			//librarySystem.bookStorage.arrangeCategory();
				while(true){
					librarySystem.bookStorage.saveBookData();
					librarySystem.guestMenu.showMenu();
					char select = librarySystem.guestMenu.selectMenu();
					switch(select){
						case '1':
							librarySystem.search.bookName(librarySystem.bookStorage.getBookList());
							break;
						case '2':
							librarySystem.processor.showCategoryList();
							librarySystem.search.bookCategory(librarySystem.bookStorage.getBookList());
							break;
						case '3':
							librarySystem.processor.rentBook(librarySystem.search.bookNumber(librarySystem.bookStorage.getBookList()));
							break;
						case '4':
							librarySystem.processor.returnBook(librarySystem.search.bookNumber(librarySystem.bookStorage.getBookList()));
							break;
						case '5':
							librarySystem.processor.showMostPopularBook(librarySystem.search.numberOfBorrowed(librarySystem.bookStorage.getBookList()));
							break;
						case 'e':
							librarySystem.processor.exitProgram();
							break;					
					}
					if(select=='e') {
						break;
					}
				}				
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}
}
