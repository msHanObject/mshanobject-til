import java.util.ArrayList;

class Processor{

	public void dataFormatting(Book book){
		String rent;
		if(book.isRentPossible()) {
			rent = "대여가능";
		}else {
			rent = "대여불가능";
		}
		String category ="";
		if(!book.getCategoryName().equalsIgnoreCase("에세이")) {
			category = book.getCategoryName()+"   ";
		}else
		{
			category = book.getCategoryName();
		}
		System.out.println(rent + "               " + book.getBookNumber() + "            " + category + "                "+ book.getBookName());
	}

	public void windowFormat(){
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("<Rent>    <BookNumber>    <BookCategory>        <BookName>");
	}
	
	public void showCategoryList() {		
		for(EnumCategory category : EnumCategory.values() ) {
			int index = category.ordinal()+1;
			System.out.print(index+"\t");
			System.out.println(category);
		}		
	}
	
	public void rentBook(Book book){
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("[ Book rental ]");
		try{
			if(book.isRentPossible()){
				book.setRentPossible(false);
				int count =book.getNumberOfBorrowed();
				book.setNumberOfBorrowed(count++);
				System.out.println(book.getBookNumber() + " Rental is complete.");
				System.out.println("--------------------------------------------------------------------------------");
			}else{
				System.out.println(book.getBookNumber() + " is already rented.");
				System.out.println("--------------------------------------------------------------------------------");
			}
		}catch(Exception e){
			System.out.println("Something is wrong! please check again!");
			System.out.println("--------------------------------------------------------------------------------");
		}			
	}

	public void returnBook(Book book){
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("[ Return books ]");
		try{
			if(!book.isRentPossible()){
				book.setRentPossible(true);
				System.out.println(book.getBookNumber() + " Return is complete.");
				System.out.println("--------------------------------------------------------------------------------");
			}else{
				System.out.println(book.getBookNumber() + " is already returned.");
				System.out.println("--------------------------------------------------------------------------------");
			}
		}catch(Exception e){
			System.out.println("Something is wrong! please check again!");
			System.out.println("--------------------------------------------------------------------------------");
		}
	}

	public void showMostPopularBook(Book book){
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("[ Most popular book ]");
		System.out.println("Book number" + "\t|\t" + book.getBookNumber());
		System.out.println("Book category" + "\t|\t" + book.getCategoryName());
		System.out.println("Book name" + "\t|\t" + book.getBookName());
		System.out.println("Book author" + "\t|\t" + book.getAuthor());
		if(book.isRentPossible()){
			System.out.println("* Rental available.");
		}else{
			System.out.println("* This book is currently being rented.");
		}
	}

	public void exitProgram(){
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Thank you for using." + "\t" + "- The End - ");	
		System.exit(0);
	}

}
