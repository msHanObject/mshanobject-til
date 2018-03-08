import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BookStorage{

	private ArrayList<Book> bookList;
	private String dataFileLocation;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private File file;	

	public BookStorage(){
		bookList = new ArrayList<Book>();
		dataFileLocation = "";		
	}

	public void setBookList(ArrayList<Book> bookList) {
		this.bookList = bookList;
	}

	public ArrayList<Book> getBookList() {
		return bookList;
	}
	
	public void loadBookData(){
		//load .dat	
		try{
				bufferedReader = new BufferedReader(new FileReader(file));
				String fileData ="";
								
				while((fileData = bufferedReader.readLine()) != null){
					
					String[] bookData = fileData.split(";");
					Book book = new Book();
					book.setBookNumber(bookData[0]);
					book.setBookName(bookData[1]);
					book.setAuthor(bookData[2]);
					book.setCategoryName(bookData[3]);
					book.setRentPossible(Boolean.parseBoolean(bookData[4]));
					book.setNumberOfBorrowed(Integer.parseInt(bookData[5]));
					bookList.add(book);
					
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void saveBookData(){
		//save .dat
		try{
				bufferedWriter = new BufferedWriter(new FileWriter(file));
				//bufferedWriter.write('\0');
				for(Book book : bookList){
					String bookFileData = book.getBookNumber() + ";" + book.getBookName() + ";" + book.getAuthor() + ";" + book.getCategoryName() + ";" + book.isRentPossible() + ";" + book.getNumberOfBorrowed();
					bufferedWriter.write(bookFileData);
					bufferedWriter.newLine();
				}
				bufferedWriter.flush();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void setDataFileLocation(){
		//set datafile location
		System.out.print("<System> : Set your 'book.dat' file's location.");
		System.out.println("\tex) ./src/namoo_library.dat");
		Scanner scanner = new Scanner(System.in);
		dataFileLocation = scanner.nextLine();
		file = new File(dataFileLocation);
	}
}
