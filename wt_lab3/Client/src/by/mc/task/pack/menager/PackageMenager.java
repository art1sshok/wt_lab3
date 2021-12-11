package by.mc.task.pack.menager;

import java.io.IOException;
import java.util.HashMap;

import by.mc.task.client.Client;
import by.mc.task.pack.Package;
import by.mc.task.pack.PackageType;
import by.mc.task.pack.process.ProcessablePackage;

public class PackageMenager {
	private HashMap<PackageType, ProcessablePackage> processes = new HashMap<PackageType, ProcessablePackage>();
	
	public void add(PackageType type, ProcessablePackage process) {
		processes.put(type, process);
	}
	
	public Object handlePackage(Client client, String[] args) throws IOException {
		PackageType type;
		try {
			type = PackageType.valueOf(args[0].toUpperCase());
		} catch(IllegalArgumentException e) {
			return null;
		}
	
		return processes.get(type).invokeInput(client, args);
	}
	
	public Object handleUnpackage(Client client, Package pack) {
		if (processes.containsKey(pack.getType())) {
			return processes.get(pack.getType()).invokeOutput(client, pack.getMessage().unpack());
		}
		return null;
	}
}
