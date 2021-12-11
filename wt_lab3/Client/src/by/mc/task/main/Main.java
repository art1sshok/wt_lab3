package by.mc.task.main;

import java.io.IOException;

import by.mc.task.client.Client;

public class Main {

	private static final int DEFAULT_CLIENT_PORT = 42;
	
	public static void main(String[] args) {
		Client client = new Client("127.0.0.1", DEFAULT_CLIENT_PORT);
		try {
			client.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
