package server.test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.junit.Test;

import servant.MessageFileReader;

public class MsgReceiveThreadTest {

	@Test
	public void run() {
		String remoteClientId = "testdesk1";
		String msg = "Test1";
		File file = new File("./src/main/resources/conversationList/"+remoteClientId+"/170906.dat");
		if(!file.exists()) {
            try {
            	file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
         }
		String message = null;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			/*writer.write(remoteClientId);
			writer.newLine();*/
			writer.write("<" + msg + ">");
			writer.newLine();
			writer.flush();
			writer.close();
			//
			MessageFileReader readMsgFile = new MessageFileReader("testdesk1");
			Iterator<File> iterator =  readMsgFile.sortOut().values().iterator();
			File rfile = null;
			while(iterator.hasNext()) {
				rfile = iterator.next();
				//assertEquals(file.getPath(), rfile.getPath());
				//test result: file path was same
				BufferedReader in = new BufferedReader(new FileReader(rfile));
				message = in.readLine();
				assertEquals("<Test1>", message);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}


}
