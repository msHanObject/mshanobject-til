package entity;

import connection.clientside.Connector;

public class Messenger {

	public static void main(String[] args) {
		Connector connector = new Connector();
		connector.login();
	}
}
