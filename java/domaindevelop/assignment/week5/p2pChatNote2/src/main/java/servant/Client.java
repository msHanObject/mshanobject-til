package servant;

import java.io.Serializable;

public class Client implements Serializable{

	private String localClientId;
	private String remoteClientId;
	private String MsgToSend;
	//private String ipAddress;
	//private String portNumber;
	
	public void setId(String id) {
		localClientId = id;
	}
	
	public void setRemoteId(String remoteId) {
		remoteClientId = remoteId;
	}
	
	public void setMsgToSend(String msg) {
		MsgToSend = msg;
	}
	
	public String getClientId() {
		return localClientId;
	}
	
	public String getRemoteId() {
		return remoteClientId;
	}
	public String getMsgToSend() {
		return MsgToSend;
	}
}
