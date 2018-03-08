import java.io.*;

public class DataInputStreamTest{
	public static void main(String args[])throws IOException{
		//writing string to a file encoded as modified UTF-8
		DataOutputStream dataOut = new DataOutputStream(new FileOutputStream("C:/Users/nulgraces/workspace/til/file.txt"));
		dataOut.writeUTF("hello");

		//Reading data from the same file
		DataInputStream dataIn = new DataInputStream(new FileInputStream("C:/Users/nulgraces/workspace/til/file.txt"));

		while(dataIn.available()>0){

			String k = dataIn.readUTF();
			System.out.print(k+" ");
		}
	}
}
