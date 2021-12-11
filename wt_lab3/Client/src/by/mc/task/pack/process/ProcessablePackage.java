package by.mc.task.pack.process;

import java.io.IOException;

import by.mc.task.client.Client;

public interface ProcessablePackage {
	Object invokeInput(Client client, Object obj) throws IOException;
	Object invokeOutput(Client client, Object obj);
}
