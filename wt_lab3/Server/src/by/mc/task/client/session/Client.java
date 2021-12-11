package by.mc.task.client.session;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import by.mc.task.pack.Package;
import by.mc.task.pack.PackageType;
import by.mc.task.pack.menager.PackageMenager;

import by.mc.task.pack.process.AuthorizationPackageProcess;
import by.mc.task.pack.process.CreateProfilePackageProcess;
import by.mc.task.pack.process.ChangeProfilePackageProcess;
import by.mc.task.pack.process.ShowProfilePackageProcess;

public class Client extends Thread {
	
	private int accessLevel;
	
	private PackageMenager packageMenager;
	private ObjectInputStream reader;
	private ObjectOutputStream writer;
	private Socket client;
	
	public Client(Socket socket) throws IOException {
		accessLevel = -1;
		client = socket;
		reader = new ObjectInputStream(client.getInputStream());
		writer = new ObjectOutputStream(client.getOutputStream());
		packageMenager = new PackageMenager();
	}
	
	@Override
	public void run() {
		initializeSession();
		runSession();
	}
	
	private void initializeSession() {
		packageMenager.add(PackageType.AUTHORIZATION, new AuthorizationPackageProcess());
		packageMenager.add(PackageType.CREATE_PROFILE, new CreateProfilePackageProcess());
		packageMenager.add(PackageType.CHANGE_PROFILE, new ChangeProfilePackageProcess());
		packageMenager.add(PackageType.SHOW_PROFILE, new ShowProfilePackageProcess());
	}
	
	private void runSession() {
		InetSocketAddress clientAddress = (InetSocketAddress)client.getRemoteSocketAddress();
		System.out.format("Client connected (%s)\n", clientAddress.getAddress().getHostAddress());
		try {
			try {
				try {
					Object obj;
					while ((obj = reader.readObject()) != null) {
						obj = packageMenager.handleUnpackage(this, (Package)obj);
						if (obj != null) {
							writer.writeObject(obj);
						}
					}
				} finally {
					reader.close();
					client.close();
				}
			} finally {
				System.out.format("Client disconnected (%s)\n", clientAddress.getAddress().getHostAddress());
			}
		} catch (IOException | ClassNotFoundException e) {
			System.err.println(e);
		}
	}
	
	public void changeAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}
	
	public boolean checkAccessLevel(int minAccessLevel) {
		return this.accessLevel >= minAccessLevel;
	}
}
