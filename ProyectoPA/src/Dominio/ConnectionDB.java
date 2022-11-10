package Dominio;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionDB {

    private static Connection connection;
    private static final String host = "localhost";
    private static final String port = "5432";
    private static final String db_name = "ProyectoPA";
    private static final String username = "postgres";
    private static final String pass = "1234";

    public ConnectionDB() throws ClassNotFoundException, SQLException, IOException {
        connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://"+host+":"+port+"/"+db_name+"", ""+username+"", ""+pass+"");
            if (connection != null) {
                System.out.println("Connection OK");
            } else {
                System.out.println("Connection Failed");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.exit(0);
        }

    }

    public Connection getConnection() {
        return connection;
    }
    
    public ArrayList<String> findUser(String query,String rut,String pass) throws SQLException{
    	try {
    		PreparedStatement  statement = connection.prepareStatement(query);
        	ResultSet result = statement.executeQuery();
        	ArrayList<String> array = new ArrayList<>();
        	while(result.next()) {
        		if(result.getString("rut").equals(rut) && result.getString("passwordPerson").equals(pass)){
        			array.add(result.getString("rut"));
        			array.add(result.getString("namePerson"));
        			array.add(result.getString("lastName"));
        			array.add(result.getString("passwordPerson"));
        			array.add(result.getString("mail"));
        			array.add(result.getString("statePerson"));
        			array.add(result.getString("direction"));
        			array.add(result.getString("image"));
        			array.add(result.getString("phone"));
        			array.add(result.getString("ruthCompany"));
        			array.add(result.getString("passwordCompany"));
        		}
        		if(result.getString("ruthCompany") != null) {
        			if(result.getString("ruthCompany").equals(rut) && result.getString("passwordCompany").equals(pass)){
            			array.add(result.getString("rut"));
            			array.add(result.getString("namePerson"));
            			array.add(result.getString("lastName"));
            			array.add(result.getString("passwordPerson"));
            			array.add(result.getString("mail"));
            			array.add(result.getString("statePerson"));
            			array.add(result.getString("direction"));
            			array.add(result.getString("image"));
            			array.add(result.getString("phone"));
            			array.add(result.getString("ruthCompany"));
            			array.add(result.getString("passwordCompany"));
            		}
        		}
        		
        		
        	}
        	return array;
    	}catch(Exception e) {System.out.println(e);}
		return null;
    	
    	
    }
    
    public static void insertData(String rut,String name,String lastName,String passwordPerson,String mail,String direction,String phone) throws Exception{
        try{
            PreparedStatement posted = connection.prepareStatement("INSERT INTO Person (rut,name,lastName,passwordPerson,mail,statePerson,direction,phone) VALUES ('"+rut+"', '"+name+"','"+lastName+"','"+passwordPerson+"','"+mail+"','"+direction+"','"+phone+"')");
            posted.executeUpdate();
        } catch(Exception e){System.out.println(e);}
        finally {
            System.out.println("Insert Completed.");
        }
    }
    
    public void close() {
        connection = null;
        if (connection == null) {
            System.out.println("Conexión terminada");
        }
    }
    
    

}
