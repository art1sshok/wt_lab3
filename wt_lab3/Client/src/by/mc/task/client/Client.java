package by.mc.task.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import by.mc.task.client.io.Input;
import by.mc.task.client.io.Output;

import by.mc.task.pack.Package;
import by.mc.task.pack.PackageType;
import by.mc.task.pack.menager.PackageMenager;

import by.mc.task.pack.process.AuthorizationPackageProcess;
import by.mc.task.pack.process.ChangeProfilePackageProcess;
import by.mc.task.pack.process.CreateProfilePackageProcess;
import by.mc.task.pack.process.FailedPackageProcess;
import by.mc.task.pack.process.OkPackageProcess;
import by.mc.task.pack.process.ShowProfilePackageProcess;

public class Client {
	
	private Socket client;
	
	private String ip;
	private int port;
	
	private boolean isTerminated = false;
	
	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public void start() throws UnknownHostException, IOException {
		client = new Socket(ip, port);
		
		PackageMenager packageMenager = new PackageMenager();
		
		initializeSession(packageMenager);
		runSession(packageMenager);
	}
	
	public void stop() throws IOException {
		client.close();
	}
	
	public void terminate() {
		isTerminated = true;
	}
	
	private void initializeSession(PackageMenager packageMenager) {
		packageMenager.add(PackageType.AUTHORIZATION, new AuthorizationPackageProcess());
		packageMenager.add(PackageType.CREATE_PROFILE, new CreateProfilePackageProcess());
		packageMenager.add(PackageType.CHANGE_PROFILE, new ChangeProfilePackageProcess());
		packageMenager.add(PackageType.SHOW_PROFILE, new ShowProfilePackageProcess());
		packageMenager.add(PackageType.FAILED, new FailedPackageProcess());
		packageMenager.add(PackageType.OK, new OkPackageProcess());
	}
	
	private void runSession(PackageMenager packageMenager) throws IOException {
		ObjectOutputStream writer = new ObjectOutputStream(client.getOutputStream());
		ObjectInputStream reader = new ObjectInputStream(client.getInputStream());
		
		while (!isTerminated) {
			String[] args = Input.scanCommand();
			Package pack = (Package)packageMenager.handlePackage(this, args);
			
			if (pack != null) {
				try {
					writer.writeObject(pack);
					pack = (Package)reader.readObject();
				} catch (ClassNotFoundException | IOException e) {
					Output.printError(e.getMessage());
				}
				packageMenager.handleUnpackage(this, pack);
			} else {
				Output.print("Command not found.\n");
			}
		}
	}
}
