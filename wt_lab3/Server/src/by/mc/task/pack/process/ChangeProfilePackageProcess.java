package by.mc.task.pack.process;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import by.mc.task.client.message.ProfileMessage;
import by.mc.task.client.session.Client;
import by.mc.task.dao.ProfileDAO;
import by.mc.task.pack.Package;
import by.mc.task.pack.PackageType;

public class ChangeProfilePackageProcess implements ProcessablePackage {

	@Override
	public Object invokeOutput(Client client, Object obj) {
		if (!client.checkAccessLevel(1)) {
			return new Package(PackageType.FAILED, "Permission denied.");
		}
		
		ProfileMessage message = (ProfileMessage)obj;
		ProfileDAO dao = new ProfileDAO();
		
		try {
			boolean status = dao.changeProfileFieldById(message.getId(), message.getDAOField(), message.getDAOFieldValue());
			if (status) {
				return new Package(PackageType.OK, "Profile changed.");
			}
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			return new Package(PackageType.FAILED, "The error occurred on the server side.");
		}
		return new Package(PackageType.FAILED, "Wrong profile id.");
	}

	@Override
	public Object invokeInput(Client client, Object obj) {
		return null;
	}

}
