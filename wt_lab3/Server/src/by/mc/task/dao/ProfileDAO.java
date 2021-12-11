package by.mc.task.dao;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;

import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import by.mc.task.profile.Profile;

public class ProfileDAO {
	private static final String filename = "res\\profiles.xml";
	
	public Profile findProfileById(int id) throws ParserConfigurationException, SAXException, IOException {
		NodeList nodes = openDocument(filename).getDocumentElement().getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			if (nodes.item(i).getNodeName().equals("Profile")) {
				Element profile = (Element)nodes.item(i);
				if (profile.getAttribute("id").equals(String.valueOf(id))) {
					return new Profile(id, 
							profile.getAttribute("name"), 
							profile.getAttribute("surname"), 
							profile.getAttribute("group"),
							Integer.parseInt(profile.getAttribute("age"))
							);
				}
			}
		}
		return null;
	}
	
	public boolean changeProfileFieldById(int id, String field, String value) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		Document document = openDocument(filename);
		NodeList nodes = document.getDocumentElement().getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			if (nodes.item(i).getNodeName().equals("Profile")) {
				Element profile = (Element)nodes.item(i);
				if (profile.getAttribute("id").equals(String.valueOf(id))) {
					profile.setAttribute(field, value);
					saveDocument(document, filename);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean createProfile(Profile profile) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		Document document = openDocument(filename);
		
		Element profileElement = document.createElement("Profile");
		
		NodeList profileElements = document.getElementsByTagName("Profile");
		int profileElementsLength = profileElements.getLength();
		int profileElementsLastId = Integer.parseInt(((Element)profileElements.item(profileElementsLength - 1)).getAttribute("id"));
		String profileId = String.valueOf(profileElementsLastId + 1);
		
		profileElement.setAttribute("id", profileId);
		profileElement.setAttribute("group", profile.getGroup());
		profileElement.setAttribute("name", profile.getName());
		profileElement.setAttribute("surname", profile.getSurname());
		profileElement.setAttribute("age", String.valueOf(profile.getAge()));
		
		document.getElementsByTagName("Profiles").item(0).appendChild(profileElement);
		saveDocument(document, filename);
		
		return true;
	}
	
	private Document openDocument(String filename) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
		return documentBuilder.parse(filename);
	}
	
	private void saveDocument(Document document, String filename) throws TransformerException {
		  TransformerFactory transformerFactory = TransformerFactory.newInstance();
		  Transformer transformer = transformerFactory.newTransformer();
		  StreamResult streamResult =  new StreamResult(new File(filename));
		  DOMSource source = new DOMSource(document);
		  
		  transformer.transform(source, streamResult);
	}
}
