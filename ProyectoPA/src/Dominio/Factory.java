package Dominio;

public class Factory {
	public static Person factory(String rut,String namePerson,String lastName,String passwordPerson,String mail,
			String statePerson,String direction,String image,String phone,String rutCompany,String passwordCompany) {
				
		if(rutCompany == null) {
			return new Client(rut,namePerson,lastName,passwordPerson,mail,image,direction,Boolean.parseBoolean(statePerson),Integer.parseInt(phone));
		}
		else {
			return new Admin(rut,namePerson,lastName,passwordPerson,mail,image,direction,Boolean.parseBoolean(statePerson),Integer.parseInt(phone),rutCompany,passwordCompany);
		}
		
	}
}
