package Dominio;

import javax.swing.ImageIcon;

public class Admin extends Person {
	private String ruthCompany;
	private String passwordCompany;

	public Admin(String name, String lastName, String ruth, String password, String mail, ImageIcon image, int phone,boolean state,
			String ruthCompany, String passwordCompany) {
		super(name, lastName, ruth, password, mail,state);
		this.ruthCompany = ruthCompany;
		this.passwordCompany = passwordCompany;
	}

	public String getRuthCompany() {
		return ruthCompany;
	}

	public void setRuthCompany(String ruthCompany) {
		this.ruthCompany = ruthCompany;
	}

	public String getPasswordCompany() {
		return passwordCompany;
	}

	public void setPasswordCompany(String passwordCompany) {
		this.passwordCompany = passwordCompany;
	}
	
	
	
}
