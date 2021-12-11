package by.mc.task.main;

import by.mc.task.server.Server;

import java.io.IOException;

public class Main {

	private static final int DEFAULT_SERVER_PORT = 42;
	
	public static void main(String[] args) {
		Server server = new Server(DEFAULT_SERVER_PORT);
		try {
			server.start();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
