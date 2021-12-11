package by.mc.task.pack.menager;

import java.util.HashMap;

import by.mc.task.client.session.Client;
import by.mc.task.pack.Package;
import by.mc.task.pack.PackageType;
import by.mc.task.pack.process.ProcessablePackage;

public class PackageMenager {
	private HashMap<PackageType, ProcessablePackage> processes = new HashMap<PackageType, ProcessablePackage>();
	
	public void add(PackageType type, ProcessablePackage process) {
		processes.put(type, process);
	}
	
	public Object handlePckage(Client client, String[] args) {
		if (processes.containsKey(PackageType.valueOf(args[0].toUpperCase()))) {
			return processes.get(PackageType.valueOf(args[0].toUpperCase())).invokeInput(client, args);
		}
		return null;
	}
	
	public Object handleUnpackage(Client client, Package pack) {
		if (processes.containsKey(pack.getType())) {
			return processes.get(pack.getType()).invokeOutput(client, pack.getMessage().unpack());
		}
		return null;
	}
}
