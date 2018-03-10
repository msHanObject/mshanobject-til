/* Category ArrayList Version Failed
 
import java.util.ArrayList;
import java.util.Scanner;

class Category {
	private ArrayList<String> categoryList;
	private Processor processor;

	public Category(){
		categoryList = new ArrayList<String>();
		processor = new Processor();
	}
	
	public void showCategoryList(ArrayList<Book> bookList){
		int count = 1;
		
		for(String categoryName : this.categoryList){
			System.out.println(count + ". " + categoryName);
			count++;
		}
	}

	public void selectCategory(BookStorage bookStorage){
		System.out.println("Please Select Category.");
		Scanner scanner = new Scanner(System.in);
		System.out.print("<Select Category> : ");
		int categoryIndex = scanner.nextInt();
		scanner.nextLine();
		String categoryName = this.categoryList.get(categoryIndex);
		processor.windowFormat();
		ArrayList<Book> list = new ArrayList<Book>();
		list = bookStorage.getBookList();
		for(Book book : list){
			if(book.getCategoryName().equals(categoryName)){
				processor.dataFormatting(book);
			}
		}
	}

	public void setCategory(ArrayList<Book> bookList){
		
		for(Book book: bookList){
			if(!this.categoryList.equals(book.getCategoryName())){
				this.categoryList.add(book.getCategoryName());
			}
		}
	}
	
	public ArrayList<String> getGategory() {
		return this.categoryList;
	}


}
*/