package by.mc.task.pack.process;

import by.mc.task.dao.ProfileDAO;
import by.mc.task.profile.Profile;
import by.mc.task.client.session.Client;
import by.mc.task.client.message.ProfileMessage;
import by.mc.task.pack.Package;
import by.mc.task.pack.PackageType;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class ShowProfilePackageProcess implements ProcessablePackage {

	@Override
	public Object invokeOutput(Client client, Object obj) {
		if (!client.checkAccessLevel(0)) {
			return new Package(PackageType.FAILED, "Permission denied.");
		}
		
		ProfileMessage message = (ProfileMessage)obj;
		ProfileDAO dao = new ProfileDAO();
		
		try {
			Profile profile = dao.findProfileById(message.getId());
			if (profile != null) {
				return new Package(PackageType.SHOW_PROFILE, profile);
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			return new Package(PackageType.FAILED, "The error occurred on the server side.");
		}
		return new Package(PackageType.FAILED, "Wrong profile id.");
	}

	@Override
	public Object invokeInput(Client client, Object obj) {
		return null;
	}
	
}
