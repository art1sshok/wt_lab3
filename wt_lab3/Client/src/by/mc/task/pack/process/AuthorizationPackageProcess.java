package by.mc.task.pack.process;

import java.io.IOException;

import by.mc.task.client.Client;
import by.mc.task.client.message.AuthorizationMessage;
import by.mc.task.pack.Package;
import by.mc.task.pack.PackageType;

public class AuthorizationPackageProcess implements ProcessablePackage {
	
	@Override
	public Object invokeOutput(Client client, Object obj) {
		return null;
	}

	@Override
	public Object invokeInput(Client client, Object obj) throws IOException {
		String[] args = (String[])obj;
		if (args.length < 3) {
			throw new IOException("Not enought arguments");
		}
		
		AuthorizationMessage message = new AuthorizationMessage(args[1], args[2]);
		Package pack = new Package(PackageType.AUTHORIZATION, message);
		
		return pack;
	}
}
