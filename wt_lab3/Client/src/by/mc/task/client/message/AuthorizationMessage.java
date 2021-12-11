package by.mc.task.client.message;

import java.io.Serializable;

public class AuthorizationMessage implements Serializable {
	private static final long serialVersionUID = -5446129887889394468L;
	
	private String login;
	private String password;
	
	public AuthorizationMessage(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
}
