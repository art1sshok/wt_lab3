package by.mc.task.profile;

import java.io.Serializable;

public class Profile implements Serializable {
	private static final long serialVersionUID = -1609637065217367563L;
	
	private int id;
	private int age;
	
	private String group;
	private String name;
	private String surname;
	
	public Profile(int id, String name, String surname, String group, int age) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.group = group;
		this.age = age;
	}
	
	public int getId() {
		return id;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getGroup() {
		return group;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
}
