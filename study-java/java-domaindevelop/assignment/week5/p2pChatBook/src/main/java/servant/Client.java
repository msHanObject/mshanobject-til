package servant;

import java.io.Serializable;

public class Client implements Serializable{

	private String clientId;
	private String MsgToSend;
	//private String ipAddress;
	//private String portNumber;
	
	public void setId(String id) {
		clientId = id;
	}
	
	public void setMsgToSend(String msg) {
		MsgToSend = msg;
	}
	
	public String getClientId() {
		return clientId;
	}
	
	public String getMsgToSend() {
		return MsgToSend;
	}
}
