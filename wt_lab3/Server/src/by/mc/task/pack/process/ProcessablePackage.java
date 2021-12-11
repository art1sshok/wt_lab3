package by.mc.task.pack.process;

import by.mc.task.client.session.Client;

public interface ProcessablePackage {
	Object invokeInput(Client client, Object obj);
	Object invokeOutput(Client client, Object obj);
}
