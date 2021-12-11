package by.mc.task.pack.process;

import java.io.IOException;

import by.mc.task.client.Client;
import by.mc.task.client.io.Output;
import by.mc.task.client.message.ProfileMessage;
import by.mc.task.profile.Profile;
import by.mc.task.pack.Package;
import by.mc.task.pack.PackageType;

public class ShowProfilePackageProcess implements ProcessablePackage {

	@Override
	public Object invokeOutput(Client client, Object obj) {
		Profile profile = (Profile)obj;
		
		Output.print("Name: " + profile.getName() + "\n");
		Output.print("Surname: " + profile.getSurname() + "\n");
		Output.print("Age: " + profile.getAge() + "\n");
		Output.print("Id: " + profile.getId() + "\n");
		Output.print("Group: " + profile.getGroup() + "\n");
		
		return obj;
	}

	@Override
	public Object invokeInput(Client client, Object obj) throws IOException {
		String[] args = (String[])obj;
		if (args.length < 2) {
			throw new IOException("Not enought arguments");
		}
		
		ProfileMessage message = new ProfileMessage(Integer.valueOf(args[1]), null, null);
		Package pack = new Package(PackageType.SHOW_PROFILE, message);
		
		return pack;
	}
	
}
