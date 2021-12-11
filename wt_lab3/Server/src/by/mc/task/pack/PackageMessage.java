package by.mc.task.pack;

import java.io.Serializable;

public class PackageMessage implements Serializable {
	
	private static final long serialVersionUID = 2269294173422453168L;
	private Object object;
	
	public PackageMessage(Object obj) {
		object = obj;
	}
	
	public Object unpack() {
		return object;
	}
}
