package Dominio;

public class Person {
	private String rut;
	private String name;
	private String lastName;
	private String password;
	private String mail;
	private String image;
	private String direction;
	private boolean state;
	private int phone;
	
	

	public Person(String rut,String name, String lastName, String password, String mail, String image,
			String direction, boolean state, int phone) {
		this.rut = rut;
		this.name = name;
		this.lastName = lastName;
		this.password = password;
		this.mail = mail;
		this.image = image;
		this.direction = direction;
		this.state = state;
		this.phone = phone;
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

	public String getRut() {
		return rut;
	}

	public void setRut(String ruth) {
		this.rut = ruth;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
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
