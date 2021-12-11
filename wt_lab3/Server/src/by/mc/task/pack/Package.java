package by.mc.task.pack;

import java.io.Serializable;

public class Package implements Serializable {
	private static final long serialVersionUID = 3888414734575089224L;
	
	private PackageType type;
	private PackageMessage message;
	
	public Package(PackageType type, Object obj) {
		this.type = type;
		this.message = new PackageMessage(obj);
	}
	
	public PackageType getType() {
		return type;
	}
	
	public PackageMessage getMessage() {
		return message;
	}
}
