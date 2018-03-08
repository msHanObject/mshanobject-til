Java-String Buffer & String Builder Classes
===
The **StringBuffer** and **StringBuilder** classes are used when there is a necessity to make a lot of modifications to Strings of characters.
Unlike Strings, objects of type StringBuffer and String builder can be modified over and over again without leaving behind a lot of new unused objects.
The main difference between the StringBuffer and StringBuilder is that StringBuilders methods are not thread safe (not synchronised).
It is recommended to use StringBuilder whenever possible because it is faster than StringBuffer. However, if the thread safety is necessary, the best option is StringBuffer objects.

**Example**
```java
public class Test {
	public static void main(String arss[]){
		StringBuffer sBuffer = new StringBuffer("test");
		sBuffer.append("String Buffer");
		System.out.pritln(sBuffer);
	}
}
```
This will produce the following result:
```
test String Buffer
```
StringBuffer Methods
---
Here is the list of important methods by StringBuffer class:

Sr.No|Methods with Description
---|---
1|**public StringBuffer append(String s)**<br/>Updates the values of the object that invoked the mothod. The mothod takes boolean, char, int, long, Strings, etc.
2|**public StringBuffer reverse()**<br/>The method reverses the value of the StringBuffer object that invoked the method.
3|**public delete(int start, int end)**<br/>Deletes the string starting from the start index until the end index.

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

Sr.<br/>No.|Methods with Description
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

