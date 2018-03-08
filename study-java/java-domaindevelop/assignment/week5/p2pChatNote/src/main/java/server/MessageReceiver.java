package server;

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
import servant.Client;

public class MessageReceiver extends Thread{

	private Socket socket;
	private File file;
	private String remoteClientId;
	private Date date;
	private SimpleDateFormat sdf;
	private String dateFormat;
	private String fileName;
	private String filePath;
	private Client sender;
	private ObjectInputStream reader;
	
	public MessageReceiver(Socket receivedSocket) {
		this.socket = receivedSocket;
	}
	
	public void run() {
		try {
			//set sender(=remoteClient)
			reader =  new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			sender = (Client) reader.readObject();
			
			//get message
			String message = null;
			message = sender.getMsgToSend();				
			
			//Set the time the message was received
			date = Calendar.getInstance().getTime();
			dateFormat = "yyyyMMdd";
			sdf = new SimpleDateFormat(dateFormat);
			String theDate = sdf.format(date);
			
			//Set file name to save
			remoteClientId = sender.getClientId();
			fileName = theDate + ".dat";
			filePath = "./src/main/resources/conversationList/" + remoteClientId + "/";
			file = new File(filePath + fileName);
			if(!file.exists()) {
	            try {
	            	file.getParentFile().mkdirs();
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
			
			//save message here
			saveMessage(message);
			
			//if new sender exist, save message here
			while((sender = (Client)reader.readObject()) != null) {
				//save the message to that file
				message = sender.getMsgToSend();				
				saveMessage(message);
			}
			Thread.sleep(30);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				reader.close();
			} catch (IOException e) {
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
			writer.write(remoteClientId);
			writer.newLine();
			writer.write("<" + msg + ">");
			writer.newLine();
			writer.flush();
			writer.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

}
