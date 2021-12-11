package by.mc.task.pack.process;

import java.io.IOException;

import by.mc.task.client.Client;
import by.mc.task.client.io.Output;

public class OkPackageProcess implements ProcessablePackage {

	@Override
	public Object invokeInput(Client client, Object obj) throws IOException {
		return null;
	}

	@Override
	public Object invokeOutput(Client client, Object obj) {
		Output.print(obj + "\n");		
		return obj;
	}

}
