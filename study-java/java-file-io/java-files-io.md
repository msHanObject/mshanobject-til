Stream
===
A stream can be defined as a sequence of data. There are two kinds of Streams-
* *InputStream* - The InputStream is used to read data from a source.
* *OutputSTream* - Ther OutputStream is used for writing data to a destination.

Byte Streams
---
Java byte streams are used to perform input and output of 8-bit bytes.
*FileInputStream* and *FileOutputStream* are used to frequently as byte streams
```java
import java.io.*;
public class CopyFile{
	public static void main(String args[]) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;

		try{
			in = new FileInputStream("input.txt");
			out = new FileOutputStream("output.txt");

			int c;
			while(( c= in.read()) != -1) {
				out.write(c);
			}
		}finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}
}
```

Character Streams
---
Java Byte Streams are used to perform input and output of 8-bit bytes, whereas Java *Character* streams are used to perform input and output for 16-bit unicode. Though there are many classes related to character streams but the most frequently used classes are, *FileReader* and *FileWriter*. Though internally FileReader uses FileInputStream and FileWriter uses FileOutputStream but here the major difference is that FileReader reads two bytes at a time and FileWriter wirtes two bytes at a time.
```java
import java.io.*;
public class CopyFile {

	public static void main(String args[]) throws IOException {
		FileReader in = null;
		FileWriter out = null;

		try{
			in = new FileReader("input.txt");
			out = new FileWriter("output.txt");

			int c;
			while((c = in.read()) != -1) {
				out.write(c);
			}
		}finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}
}
```
Standard Streams
---
Java provieds the following three standard streams-
* *Standard Input* - This is usedto feed the data to user's program and usually a keyborad is used as standard input stream and represented as *System.in.*
* *Standard Output* - This is used to output the data produced by the user's program and usually a computer screen is used for standard output stream and represented as *System.out*
* *Standard Error* - This is used to output the error data produced by the user's program and usually a computer screen is used for standard error stream and represented as *System.err.*

Reading and writing Files
===
![Hierarchy of classes to deal with Input, Output streams](https://www.tutorialspoint.com/java/images/file_io.jpg)


FileInputStream
---
This stream is used for reading data from the files.
Following constructor takes a file name as a string to create an input stream object to read the file-
```
InputStream f = new FileInputStream("C:/java/hello");
```
Following constructor takes a file object to create an input stream object to read the file.
First we create a file object using File() method as follows -
```
File f = new File("C:/java/hello");
InputStream f = new FileInputStream(f);
```
Once you have InputStream object in hand, then there is a list of helper methods which can be used to read to stream or to do other operations on the stream.

FileOutputStream
---
FileOutputStream is used to create a file and write data into it. The stream would create a file, if it doesn't already exist, before opening it for output.

Following construcutor takes a file object to create an output stream object to write the file. First, we create a file object using File() method as follows -
```java
File f = new File("C://java/hello");
OutputStream f = new FileOutputStream(f);
```

Following is the example to demonstrate InputStream and OutputStream =
```java
import java.io.*;
public class fileStreamTest{
	public static void main(String args[]){
		try{
			byte bWrite [] = {11,21,3,40,5};
			OutputStream os = new FileOutputStream("test.txt");
			for(int x= 0; x < bWrite.length; x++){
				os.write(bWrite[x]);	//writes the bytes
			}
			os.close();
			InputStream is = new FileInputStream("test.txt");
			int size = is.available();
			for(int i=0; i < size; i++){
				System.out.print((char)is.read() + "  ");
				};
			}
			is.close();
		}catch(IOException e){
			System.out.print("Exception");
		}
	}
}
```

ByteArrayInputStream
---
The ByteArrayInputStream class allows a buffer in the memory to be used as an InputStream. The Input source is a byte array.

ByteArrayInputStream class provides the following constructors.

Sr.No|Constructor and Description
---|---
1|**ByteArrayInputStream(byte [] a)**<br/>This constructor accepts a byte array as a parameter.
2|**ByteArrayInputStream(byte [] a, int off, int len)**<br />This constructor takes an array of bytes, and two integer values, where off is the first byte to be read and **len** is the number of bytes to be read.

There is a list of herlper methods which can be used to read the stream or to do other opertions on the stream.

Sr.<br/>No.|Methods with Description
1|**public int read()**<br /> This method reads the next byte of data from the InputStream. Returns an int as the next byte of data. If it is the end of the file, then it returns -1.
2|**public int read(byte[] r, int off, int len)**<br/>This method reads upto **len** number of bytes starting from **off** from the input stream into an array. Returns the total number of bytes read. If it is the end of the file, -1 will be returned.
3|**public int available()**<br/>Gives the nubmer of bytes that can be read from this file input stream. Returns an int that gives the nubmer of bytes to be read.
4|**public void mark(int read)**<br/>This sets the current marked position in the stream. The parameter gives the maximum limit of bytes can be read before the markes position becomes invaild.
5|**public long skip(long n)**<br/>Skips 'n' nubmer of bytes from the stream. This returns the actual number of bytes skipped.

**Example**
Following is the example to demonstrate ByteArrayInputStream and ByteArrayOutputStream.
```java
import java.io.*;

public class ByteStreamTest {
	public static void main(String args[]) throws IOException {
		ByteArrayOutputStream bOutput = new ByteARrayOutputStream(12);

		while(bOutput.size() != 10) {
			//Gets the inputs from the user
			bOutput.write(Sytem.in.read());
		}

		byte b [] = bOutput.toByteArray();
		System.out.println("Print the content");
		for (int x=0; x<b.length; x++){
			//printing the characters
			System.out.print((char)b[x] + "\t");
		}
		System.out.println("\t");

		int c;

		ByteArrayInputStream bInput = new ByteArrayInputStream(b);

		System.out.println("Converting characters to Upper case ");
		for(int y =0; y <1; y++){
			while(( c= bIput.read()) != -1){
				System.out.println(Character.toUpperCase((char)c));
			}
			bInput.reset();
		}
	}
}
```

DataInputStream
---
The DataInputStream is used in the context of DataOutputStream and can be used to read primitives.<br/>Following is the constructor to create an InputStream:
> InputStream in = DataInputStream(InputStream in);
Once you have *DataInputStream* object in hand, then  ther is list of helper methods, which can be used to read the stream or to do other operations on the stream.

Sr.No.|Methods with Description
1|**public final int read(byte[] r, int off, int len)throws IOException**<br/>Reads up to len bytes of data from the input stream into an array of bytes. Returns the total number of bytes read into the buffer otherwise -1 if it is end of file.
2|**Public final int read(byte [] b)throws IOException**<br/>Reads some bytes the inputstream an stores in to the byte array. Returns the total number of bytes read into the buffer otherwise -1 if it is end of file.
3|**(a) public final Boolean readBoolean()throws IOException**<br/>**(b) public final byte readByte()throws IOException**<br/>**(c)public final short readShort()throws IOException**<br/>**(d) public final Int readInt()throws IOException**<br/><br/>These methods will read the bytes from the contained InputStream. Returns the next two bytes of the InputStream as the specific primitive type.
4|**public String readLine() throws IOException**<br/>Reads the next line of text from the input stream. It reads successive bytes, converting each byte separately into a character, until it encounters a line terminator or end of file; the characters read are then returned as a String.

**Example**
Following is an example to demonstrate DataInputStream and DataOutputStream. This example reads 5 lines given in a file test.txt and conerts those lines into capital letters and finally copies them into another file test1.txt.
```java
import java.io.*;

public class DataInputStreamTest{
	public static void main(String args[])throws IOException{
		//writing string to a file encoded as modified UTF-8
		DataOutputStream dataOut = new DataOutputStream(new FileOutputStream("C:\Users\nulgraces\workspace\file.txt"));
		dataOut.writeUTF("hello");

		//Reading data from the same file
		DataInputStream dataIn = new DataInputStream(new FileInputStream("C:\Users\nulgraces\workspace\file.txt"));

		while(dataIn.available()>0){

			String k = dataIn.readUTF();
			System.out.print(k+" ");
		}
	}
}
```

FileOutputStream
---
FileOutputStream is used to create a file and write data into it. The stream would create a file, if it doesn't already exist, before opening it for output.

Here are two constructors which can be used to crreate a FileOutputStream object.

Following constructor takes a file object to create an output stream object to write the file. First, we create a file object using File() method aas follows:
```
File f = new File("C:/java/hello");
OutputStream f = new FileOutputStream(f);
```

Sr.No.| Methods with Description
---|---
1|**public void close() throws IOException{}**<br/>This method closes the file output stream. Releases any system resources associated with the file. Throws an IOException.
2|**protected void finalize()throws IOException{}**<br/>This method cleans up the connection to the file. Ensure that the close method of this file output stream is called when there are no more references to this stream. Throws an IOException.
3|**public void write(int w)throws IOException{}**<br/>This methods writes the specified byte to the output stream.
4|**public void write(byte[] w)**<br/>Writes w.length bytes from the mentioned byte array to the OutputStream.

ByteArrayOutputStream
---
The ByteArrayOutputStream class stream creates a buffer in memory and all the data sent to the stream is stored in the buffer.

...

File Navigation and I/O
===

Directories in Java
---

You use File object to create directories, to list down files available in a directory. 

Createing Directories
---
There are two useful *File* utility mehotds, which can be used to create directories-
* The *mkidr()* method creates a directory, returning true on success and false on failure, Failure indicates that the path specified in the File object already exists, or that the directory cannot be created because the entire path does not exist yet.
* The *mkdirs()* method creates both a directory and all the parents of the directory.
Following example creates"/tmp/user/java/bin" directory -
**Example**
```java
import java.io.File;
public class CreateDir {
	public static void main(String args[]){
		String dirname = "tmp/user/java/bin";
		File d = new File(dirname);

		//Create directory now;
		d.mkdirs();
	}
}
```

Listing Directories
---
You can use **list()** mehtod provided by **File** object to list down all the files and directories available in a directory as follows -
**Example**
```java
import java.io.File;
public class ReadDir {
	public static void main(String[] args){
		File file = null;
		Stirng[] paths;

		tyr{
			//create new file object
			file = new File("/tmp");

			//array of files and directory
			paths = file.list();

			//for each name in the path array
			for(String path : paths) {
				//prints filename and directory name
				System.out.println(path);
			}
		}catch(Exception e) {
			//if any error occurs
			e.printStackTrac();
		}
	}
}
```


