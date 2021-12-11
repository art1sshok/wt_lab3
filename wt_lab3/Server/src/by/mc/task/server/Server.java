package by.mc.task.server;

import java.io.IOException;
import java.net.ServerSocket;

import by.mc.task.client.session.Client;

public class Server {
	
	private ServerSocket server;
	private int port;
	
	public Server(int port) {
		this.port = port;
	}
	
	public void start() throws IOException, InterruptedException {
		server = new ServerSocket(port);
		
		while (true) {
			Thread session = new Thread(new Client(server.accept()));
			session.start();
		}
	}
	
	public void stop() throws IOException {
		server.close();
	}
}
