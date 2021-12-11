package by.mc.task.pack.process;

import java.io.IOException;

import by.mc.task.client.Client;
import by.mc.task.client.message.ProfileMessage;
import by.mc.task.pack.Package;
import by.mc.task.pack.PackageType;

public class ChangeProfilePackageProcess implements ProcessablePackage {

	@Override
	public Object invokeOutput(Client client, Object obj) {
		return null;
	}

	@Override
	public Object invokeInput(Client client, Object obj) throws IOException {
		String[] args = (String[])obj;
		if (args.length < 4) {
			throw new IOException("Not enought arguments");
		}
		
		ProfileMessage message = new ProfileMessage(Integer.valueOf(args[1]), args[2], args[3]);
		Package pack = new Package(PackageType.CHANGE_PROFILE, message);
		
		return pack;
	}

}
