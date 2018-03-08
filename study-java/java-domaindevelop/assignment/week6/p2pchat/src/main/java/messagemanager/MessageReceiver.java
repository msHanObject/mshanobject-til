package messagemanager;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import entity.ClientInformation;

public class MessageReceiver extends Thread{
	
	private Socket socket;
	private ClientInformation clientInfo;
	private ObjectInputStream objectIn;
	private File file;
	private String senderId;
	private String arrivedTime;
	
	public MessageReceiver(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			objectIn = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			clientInfo = (ClientInformation) objectIn.readObject();
			
			String message = null;
			message = clientInfo.getMessageToSend();
			
			//Set the time the message was received
			Date date = Calendar.getInstance().getTime();
			String dateFormat = "yyyyMMdd";
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			String theDate = sdf.format(date);
			
			//time format
			String timeFormat = "HH:mm:ss";
			sdf = new SimpleDateFormat(timeFormat);
			arrivedTime = sdf.format(date);
			
			//set remote client id
			senderId = clientInfo.getLocalId();
			//String receiverId = clientInfo.getRemoteId();
			
			//Set file name to save
			String fileName = theDate + ".dat";
			String filePath = "./src/main/resources/conversationList/" + senderId + "/";
			file = new File(filePath + fileName);
			if(!file.exists()) {
	            try {
	            	file.getParentFile().mkdirs();
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
			
			if(message != null) {
				System.out.println(message);
				//save received message
				
				saveMessage(message);
			}
			
			//if new clientInfo exist, save message here
			while((clientInfo = (ClientInformation) objectIn.readObject()) != null) {
				System.out.println(message);
				//save the message to that file
				saveMessage(message);
			}
			Thread.sleep(30);
		}catch(Exception e) {
			System.err.println("couldn't read message");
		}finally {
			try {
				objectIn.close();
			}catch(IOException e) {
				System.err.println("couldn't close ObjectInputStream");
			}
		}
	}
	
	public void saveMessage(String msg) {
		try {			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			
			//append message
			writer.write(senderId);
			writer.newLine();
			writer.write("<" + msg + ">" + arrivedTime );
			writer.newLine();
			writer.flush();
			writer.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
}
