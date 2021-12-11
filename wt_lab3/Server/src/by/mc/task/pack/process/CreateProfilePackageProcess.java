package by.mc.task.pack.process;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import by.mc.task.client.session.Client;
import by.mc.task.dao.ProfileDAO;
import by.mc.task.pack.Package;
import by.mc.task.pack.PackageType;
import by.mc.task.profile.Profile;

public class CreateProfilePackageProcess implements ProcessablePackage {

	@Override
	public Object invokeOutput(Client client, Object obj) {
		if (!client.checkAccessLevel(1)) {
			return new Package(PackageType.FAILED, "Permission denied.");
		}
		
		Profile profile = (Profile)obj;
		ProfileDAO dao = new ProfileDAO();
		
		try {
			dao.createProfile(profile);
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			return new Package(PackageType.FAILED, "The error occurred on the server side.");
		}
		return new Package(PackageType.OK, "Profile created.");
	}

	@Override
	public Object invokeInput(Client client, Object obj) {
		return null;
	}

}
