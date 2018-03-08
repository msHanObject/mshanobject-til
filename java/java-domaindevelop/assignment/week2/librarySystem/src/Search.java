import java.util.ArrayList;
import java.util.Scanner;

class Search {

	private Scanner scanner;
	private Processor processor;
	

	public Search(){
		super();
		//TODO Auto-generated constructor stub
		scanner = new Scanner(System.in);
		processor = new Processor();		
	}

	public Book bookNumber(ArrayList<Book> bookList){
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("<Search by bookNumber>");
		System.out.print("<bookNumber> : ");
		String bookNumber = scanner.nextLine();
		processor.windowFormat();
		
		for(Book book : bookList){
			if(book.getBookNumber().equalsIgnoreCase(bookNumber)){
				processor.dataFormatting(book);				
				return book;
			}else{
				System.out.println(bookNumber+" undetected");
				return null;
			}
		}
		// I'm not sure this part
		return null;
	}

	public void bookCategory(ArrayList<Book> bookList) {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("<Search by bookCategory>");
		System.out.print("<Category Index> : ");
		String index = scanner.nextLine();
		processor.windowFormat();
		try {
			for(EnumCategory category : EnumCategory.values()) {
				int categoryIndex = category.ordinal() +1;
				String categoryName = category.name();
				if(categoryIndex == Integer.parseInt(index)) {
					
					for(Book book : bookList){
						if(book.getCategoryName().equalsIgnoreCase(categoryName)) {
							processor.dataFormatting(book);
						}
					}
				}
			}
		}catch(Exception e) {
			System.out.println("Check again Category Index");
		}
		
	}
		
	public void bookName(ArrayList<Book> bookList){
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("<Search by bookName>");
		System.out.print("<bookName> : ");
		String bookName = scanner.nextLine();
		processor.windowFormat();

		for(Book book : bookList){
			if(book.getBookName().contains(bookName)){
				processor.dataFormatting(book);
			}
		}
		
	}

	public Book numberOfBorrowed(ArrayList<Book> bookList){
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("<Search by number of borrowed>");
		int maxValue=0;
	
		for(Book book : bookList){
			if(book.getNumberOfBorrowed() > maxValue){
				maxValue = book.getNumberOfBorrowed();
			}	
		}
		Book popularBook = new Book();
		for(Book book : bookList){
			if(book.getNumberOfBorrowed() == maxValue){
				popularBook = book;
			}
		}
		return popularBook;
	}
}
