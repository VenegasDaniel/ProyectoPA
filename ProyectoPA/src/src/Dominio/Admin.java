package Dominio;


public class Admin extends Person {
	private String rutCompany;
	private String passwordCompany;

	public Admin( String rut,String name, String lastName, String password, String mail, String image,
			String direction, boolean state, int phone, String rutCompany, String passwordCompany) {
		super(rut,name, lastName, password, mail, image, direction, state, phone);
		this.rutCompany = rutCompany;
		this.passwordCompany = passwordCompany;
	}

	public String getRutCompany() {
		return rutCompany;
	}

	public void setRuthCompany(String rutCompany) {
		this.rutCompany = rutCompany;
	}

	public String getPasswordCompany() {
		return passwordCompany;
	}

	public void setPasswordCompany(String passwordCompany) {
		this.passwordCompany = passwordCompany;
	}
	
	
	
}
