package messagemanager;

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

import entity.ClientInformation;

public class MessageSender extends Thread{

	private Socket socket;
	private ClientInformation clientInfo;
	private ObjectOutputStream objectOut;
	private BufferedReader br;
	private File file;
	private String sentTime;
	
	public MessageSender(Socket socket, ClientInformation clientInfo) {
		this.socket = socket;
		this.clientInfo = clientInfo;
	}
	
	public void run() {
		try {
			objectOut = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(System.in));
			
			//Set the time the message was sent
			Date date = Calendar.getInstance().getTime();
			String dateFormat = "yyyyMMdd";
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			String theDate = sdf.format(date);
			
			//time format
			String timeFormat = "HH:mm:ss";
			sdf = new SimpleDateFormat(timeFormat);
			sentTime = sdf.format(date);
			
			//Set file name to save
			String fileName = theDate + ".dat";
			String remoteClientId = clientInfo.getRemoteId(); 
			String filePath = "./src/main/resources/conversationList/" + remoteClientId + "/";
			file = new File(filePath + fileName);
			if(!file.exists()) {
	           	file.getParentFile().mkdirs();
				file.createNewFile();			
	        }
			
			//set object to send
			clientInfo.setMessageToSend(br.readLine());
			String message = clientInfo.getMessageToSend(); 
			while( message != null){
				if(message.equalsIgnoreCase("/quit")) {
					break;
				}
				else {
					//transmission object
					objectOut.writeObject(clientInfo);
					
					//save message in local message file
					saveMessage(message);
					
					//refresh localClient object
					clientInfo = new ClientInformation();
					
					Thread.sleep(30);
					clientInfo.setMessageToSend(br.readLine());
				}
			}
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}		
	}
	
	public void saveMessage(String msg) {
		try {			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

			//append message
			String localClientId = clientInfo.getLocalId();
			writer.write("\t\t\t\t\t\t\t"+localClientId);
			writer.newLine();
			writer.write("\t\t\t\t<" + msg + ">" + sentTime);
			writer.newLine();
			writer.flush();
			writer.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
