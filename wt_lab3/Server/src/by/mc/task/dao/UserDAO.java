package by.mc.task.dao;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class UserDAO {
	private static final String filename = "res\\users.xml";
	
	public int findUser(String login, String password) throws ParserConfigurationException, SAXException, IOException {
		NodeList nodes = parseDocument(filename);
		for (int i = 0; i < nodes.getLength(); i++) {
			if (nodes.item(i).getNodeName().equals("User")) {
				Element user = (Element)nodes.item(i);
				if (user.getAttribute("login").equals(login) && user.getAttribute("password").equals(password)) {
					return Integer.parseInt(user.getAttribute("status"));
				}
			}
		}
		return -1;
	}
	
	private NodeList parseDocument(String filename) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(filename);
		
		return document.getDocumentElement().getChildNodes();
	}
}
