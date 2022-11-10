package Dominio;

import javax.swing.ImageIcon;

public class Person {
	private String name;
	private String lastName;
	private String ruth;
	private String password;
	private String mail;
	private ImageIcon image;
	private String direction;
	private boolean state;
	private int phone;
	
	public Person(String name, String lastName, String ruth, String password, String mail,boolean state) {
		this.name = name;
		this.lastName = lastName;
		this.ruth = ruth;
		this.password = password;
		this.mail = mail;
		this.state = state;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRuth() {
		return ruth;
	}

	public void setRuth(String ruth) {
		this.ruth = ruth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	
}
