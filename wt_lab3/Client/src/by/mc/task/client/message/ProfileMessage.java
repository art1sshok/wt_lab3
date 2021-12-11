package by.mc.task.client.message;

import java.io.Serializable;

public class ProfileMessage implements Serializable {
	private static final long serialVersionUID = 7632041194151165326L;
	
	private int id;
	private String daoField;
	private String daoFieldValue;
	
	public ProfileMessage(int id, String daoFiled, String daoFieldValue) {
		this.id = id;
		this.daoField = daoFiled;
		this.daoFieldValue = daoFieldValue;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDAOField() {
		return daoField;
	}
	
	public String getDAOFieldValue() {
		return daoFieldValue;
	}
}
