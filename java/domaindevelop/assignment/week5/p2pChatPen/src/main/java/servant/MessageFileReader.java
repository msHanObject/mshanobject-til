package servant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class MessageFileReader extends Thread{

	private BufferedReader in;
	private String clientId;
	//private String fileName;
	private Client client;
	private File dir;
	private File file;
	
	public MessageFileReader(String remoteClientId) {
		//remoteClientId
		this.clientId = remoteClientId;
		dir = new File("./src/main/resources/conversationList/" + clientId);		 
	}
	
	public void run() {
		try {
			//Use a hash map for later partial file invocations
			Iterator<File> iterator =  sortOut().values().iterator();
			while(iterator.hasNext()) {
				file = iterator.next();
				in = new BufferedReader(new FileReader(file));
				String message = null;
				while((message = in.readLine()) != null) {
					System.out.println(message);
				}
				in.close();
			}
			Thread.sleep(30);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public LinkedHashMap<String, File> sortOut() {
		LinkedHashMap<String, File> allMessages = new LinkedHashMap<String, File>();
		if(dir.isDirectory()) {
			//synchronized File[] items? or allMessages?
			synchronized(allMessages) {
				File[] items = dir.listFiles();
				for(File item : items) {
					if(item.getName().endsWith(".dat")) {
						allMessages.put(item.getName(), item);
					}
				}
			}
		}
		return allMessages;
	}
}
