package entity;

import java.io.Serializable;

public class ClientInformation implements Serializable{

	private static final long serialVersionUID = 1L;
	private String localClientId;
	private String remoteClientId;
	private String messageToSend;
	
	public void setLocalId(String id) {
		localClientId = id;
	}
	
	public void setRemoteId(String remoteId) {
		remoteClientId = remoteId;
	}
	
	public void setMessageToSend(String message) {
		messageToSend = message;
	}
	
	public String getLocalId() {
		return localClientId;
	}
	
	public String getRemoteId() {
		return remoteClientId;
	}
	
	public String getMessageToSend() {
		return messageToSend;
	}
}
