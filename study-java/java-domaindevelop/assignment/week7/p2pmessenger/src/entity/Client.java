package entity;

import java.io.Serializable;

public class Client implements Serializable{

	private static final long serialVersionUID = 1L;
	private String localClientId;
	private String remoteClientId;
	private String message;

	public String getLocalClientId() {
		return localClientId;
	}

	public void setLocalClientId(String localClientId) {
		this.localClientId = localClientId;
	}
	
	public void setRemoteClientId(String remoteClientId) {
		this.remoteClientId = remoteClientId;
	}
	
	public String getRemoteClientId() {
		return remoteClientId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
