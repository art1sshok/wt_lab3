package by.mc.task.pack.process;

import by.mc.task.client.Client;
import by.mc.task.client.io.Input;
import by.mc.task.client.io.Output;
import by.mc.task.pack.Package;
import by.mc.task.pack.PackageType;
import by.mc.task.profile.Profile;

public class CreateProfilePackageProcess implements ProcessablePackage {

	@Override
	public Object invokeOutput(Client client, Object obj) {
		return null;
	}

	@Override
	public Object invokeInput(Client client, Object obj) {		
		Output.print("Name: ");
		String name = Input.scanLine();
		Output.print("Surname: ");
		String surname = Input.scanLine();
		Output.print("Age: ");
		String age = Input.scanLine();
		Output.print("Group: ");
		String group = Input.scanLine();
		
		Profile newProfile = new Profile(0, name, surname, group, Integer.valueOf(age));
		Package pack = new Package(PackageType.CREATE_PROFILE, newProfile);
		
		return pack;
	}

}
