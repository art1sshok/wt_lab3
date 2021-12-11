package by.mc.task.pack.process;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import by.mc.task.client.session.Client;
import by.mc.task.client.message.AuthorizationMessage;
import by.mc.task.pack.Package;
import by.mc.task.pack.PackageType;
import by.mc.task.dao.UserDAO;

public class AuthorizationPackageProcess implements ProcessablePackage {
	
	@Override
	public Object invokeOutput(Client client, Object obj) {
		AuthorizationMessage message = (AuthorizationMessage)obj;
		UserDAO dao = new UserDAO();
		
		try {
			int clientStatus = dao.findUser(message.getLogin(), message.getPassword());
			if (clientStatus != -1) {
				client.changeAccessLevel(clientStatus);
				return new Package(PackageType.OK, "Authorization was successful.");
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			return new Package(PackageType.FAILED, "The error occurred on the server side.");
		}
		return new Package(PackageType.FAILED, "Wrong login or password.");
	}

	@Override
	public Object invokeInput(Client client, Object obj) {
		return null;
	}
}
