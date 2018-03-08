package server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import servant.Client;

public class MessageReceiver extends Thread{

	private Socket socket;
	private File file;
	private String senderId;
	private String receiverId;
	private Date date;
	private SimpleDateFormat sdf;
	private String dateFormat;
	private String fileName;
	private String filePath;
	private Client sender;
	private ObjectInputStream reader;
	private HashMap<String, PrintWriter> clients;
	private String timeFormat;
	private String theTime;
	
	public MessageReceiver(Socket receivedSocket, HashMap<String, PrintWriter> clients) {
		this.socket = receivedSocket;
		this.clients = clients;
	}
	
	public void run() {
		try {
			//get client's PrintWriter
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
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
			
			//time format
			timeFormat = "HH:mm:ss";
			sdf = new SimpleDateFormat(timeFormat);
			theTime = sdf.format(date);
			
			//set remote client id
			senderId = sender.getClientId();
			receiverId = sender.getRemoteId();
			
			//Set file name to save
			fileName = theDate + ".dat";
			filePath = "./src/main/resources/conversationList/" + senderId + "/";
			file = new File(filePath + fileName);
			if(!file.exists()) {
	            try {
	            	file.getParentFile().mkdirs();
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
			
			//synchronized all clients accepted
			synchronized(clients) {
				clients.put(senderId, out);
			}
			if(message != null) {
				//save message here
				saveMessage(message);
				sendMessageToMe(message);
			}
			
			//if new sender exist, save message here
			while((sender = (Client)reader.readObject()) != null) {
				//save the message to that file
				message = sender.getMsgToSend();				
			
				//save message here
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
			
			//append message
			writer.write(senderId);
			writer.newLine();
			writer.write("<" + msg + ">" + theTime);
			writer.newLine();
			writer.flush();
			writer.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void sendMessageToMe(String msg) {
		Object obj = clients.get(receiverId);
		if(obj != null) {			
			PrintWriter localWriter = (PrintWriter) obj;
			localWriter.println(senderId + "\n<"+msg+">"+ theTime);
			localWriter.flush();
		}
	}

}
