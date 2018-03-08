package servant;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MessageSender extends Thread{

	private String localClientId;
	private Socket socket;
	private BufferedReader keyboardIn;
	private ObjectOutputStream keyboardOut;
	private Client localClient;
	private Date date;
	private SimpleDateFormat sdf;
	private String dateFormat;
	private File file;
	private String fileName;
	private String filePath;
	private String remoteClientId;
	
	public MessageSender(Socket socket, String localClientId, String remoteClientId) {
		this.socket = socket;
		this.localClientId = localClientId;
		this.remoteClientId = remoteClientId;
		localClient = new Client();
	}
	
	public void run() {
		try {
		localClient.setId(localClientId);
		localClient.setRemoteId(remoteClientId);
		keyboardOut = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		keyboardIn = new BufferedReader(new InputStreamReader(System.in));
				
		//Set the time the message was received
		date = Calendar.getInstance().getTime();
		dateFormat = "yyyyMMdd";
		sdf = new SimpleDateFormat(dateFormat);
		String theDate = sdf.format(date);
		
		//Set file name to save
		fileName = theDate + ".dat";
		filePath = "./src/main/resources/conversationList/" + remoteClientId + "/";
		file = new File(filePath + fileName);
		if(!file.exists()) {
           	file.getParentFile().mkdirs();
			file.createNewFile();			
        }
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			localClient.setMsgToSend(keyboardIn.readLine());
			while(localClient.getMsgToSend() != null){
				if(localClient.getMsgToSend().equalsIgnoreCase("/quit")) {
					break;
				}
				else {
					//transmission object
					keyboardOut.writeObject(localClient);
					
					//save message in local message file
					saveMessage(localClient.getMsgToSend());
					
					//refresh localClient object
					localClient = new Client();//I don't know why should I do this line
					
					Thread.sleep(30);
					localClient.setMsgToSend(keyboardIn.readLine());
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {			
				keyboardOut.close();
				keyboardIn.close();
			}catch(IOException e) {
				e.printStackTrace();
			}			
		}		
	}
	public void saveMessage(String msg) {
		try {			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			//time format
			String timeFormat = "HH:mm:ss";
			sdf = new SimpleDateFormat(timeFormat);
			String theTime = sdf.format(date);
			//append message
			writer.write("\t\t\t\t\t\t\t"+localClientId);
			writer.newLine();
			writer.write("\t\t\t\t<" + msg + ">" + theTime);
			writer.newLine();
			writer.flush();
			writer.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
}
