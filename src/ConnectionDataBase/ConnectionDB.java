package ConnectionDataBase;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

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
                System.out.println("Connection completed successfully");
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
    
    public ArrayList<String> findUser(String query) throws SQLException{
    	ArrayList<String> array = new ArrayList<>();
    	PreparedStatement  statement = connection.prepareStatement(query);
    	ResultSet result = statement.executeQuery();
    	while(result.next()) {
        	array.add(result.getString("rut"));
        	array.add(result.getString("namePerson"));
        	array.add(result.getString("lastName"));
        	array.add(result.getString("passwordPerson"));
        	array.add(result.getString("mail"));
        	array.add(String.valueOf(result.getString("statePerson")));
        	array.add(result.getString("direction"));
        	array.add(result.getString("image"));
        	array.add(String.valueOf(result.getString("phone")));
        	array.add(result.getString("rutCompany"));
        	array.add(result.getString("passwordCompany"));
        }
        return array;
    }
    
    public void insertData(String query) throws Exception{
        try{
            PreparedStatement posted = connection.prepareStatement(query);
            posted.executeUpdate();
        } catch(Exception e){System.out.println(e);}
        finally {
            System.out.println("Insert Completed.");
        }
    }
    
    public void UpdateData(String query) throws Exception{
        try{
            PreparedStatement posted = connection.prepareStatement(query);
            posted.executeUpdate();
        } catch(Exception e){System.out.println(e);}
        finally {
            System.out.println("Update Completed.");
        }
    }
    
    public void DeleteData(String query) throws Exception{
        try{
            PreparedStatement posted = connection.prepareStatement(query);
            posted.executeUpdate();
        } catch(Exception e){System.out.println(e);}
        finally {
            System.out.println("Delete Completed.");
        }
    }
    
    public ArrayList<String> findHistory(String query) throws SQLException{
    	ArrayList<String> array = new ArrayList<>();
    	PreparedStatement  statement = connection.prepareStatement(query);
    	ResultSet result = statement.executeQuery();
    	while(result.next()) {
        	array.add(result.getString("nameProduct"));
        	array.add(String.valueOf(result.getString("quantityPurchased")));
        	array.add(String.valueOf(result.getString("finalPrice")));
        }
        return array;
    }
  
    public String findRut(String query) throws SQLException{
    	PreparedStatement  statement = connection.prepareStatement(query);
    	ResultSet result = statement.executeQuery();
    	while(result.next()) {
    		String rut = result.getString("rut");
    		 return rut;
        }
		return null;	
    }
    
    public String findEmail(String query) throws SQLException{
    	PreparedStatement  statement = connection.prepareStatement(query);
    	ResultSet result = statement.executeQuery();
    	while(result.next()) {
    		String email = result.getString("mail");
    		 return email;
        }
		return null;	
    }
    
    public String findImageProduct(String query) throws SQLException{
    	PreparedStatement  statement = connection.prepareStatement(query);
    	ResultSet result = statement.executeQuery();
    	while(result.next()) {
    		String path = result.getString("image");
    		 return path;
        }
		return null;	
    }
    
    public ArrayList<String> findProduct(String query) throws SQLException{
    	ArrayList<String> array = new ArrayList<>();
    	PreparedStatement  statement = connection.prepareStatement(query);
    	ResultSet result = statement.executeQuery();
    	while(result.next()) {
        	array.add(result.getString("nameProduct"));
        	array.add(String.valueOf(result.getString("price")));
        	array.add(String.valueOf(result.getString("stock")));
        }
        return array;
    }
    
    
    public String getDescriptionProduct(String query) throws SQLException{
    	PreparedStatement  statement = connection.prepareStatement(query);
    	ResultSet result = statement.executeQuery();
    	while(result.next()) {
    		String description = result.getString("description");
    		 return description;
        }
		return null;	
    }
    
    public String getNameProduct(String query) throws SQLException{
    	PreparedStatement  statement = connection.prepareStatement(query);
    	ResultSet result = statement.executeQuery();
    	while(result.next()) {
    		String nameProduct = result.getString("nameProduct");
    		 return nameProduct;
        }
		return null;	
    }
    
    public int getPriceProduct(String query) throws SQLException{
    	PreparedStatement  statement = connection.prepareStatement(query);
    	ResultSet result = statement.executeQuery();
    	while(result.next()) {
    		 int price = Integer.parseInt(result.getString("price"));
    		 return price;
        }
		return 0;	
    }
    
    public ArrayList<String> findReviews(String query) throws SQLException{
    	ArrayList<String> array = new ArrayList<>();
    	PreparedStatement  statement = connection.prepareStatement(query);
    	ResultSet result = statement.executeQuery();
    	while(result.next()) {
        	array.add(result.getString("rutPerson"));
        	array.add(result.getString("review"));
        }
        return array;
    }
    
    public ArrayList<String> findProductShoppingCart (String query) throws SQLException{
    	ArrayList<String> array = new ArrayList<>();
    	PreparedStatement  statement = connection.prepareStatement(query);
    	ResultSet result = statement.executeQuery();
    	while(result.next()) {
        	array.add(result.getString("nameProduct"));
        	array.add(String.valueOf(result.getString("quantityPurchased")));
        	array.add(String.valueOf(result.getString("finalPrice")));
        }
        return array;
    }
    
    public String getRut(String query) throws SQLException{
    	PreparedStatement  statement = connection.prepareStatement(query);
    	ResultSet result = statement.executeQuery();
    	while(result.next()) {
    		String rut = result.getString("rut");
    		 return rut;
        }
		return null;	
    }
    
    public int getCant(String query) throws SQLException{
    	PreparedStatement  statement = connection.prepareStatement(query);
    	ResultSet result = statement.executeQuery();
    	while(result.next()) {
    		 int cant= Integer.parseInt( result.getString("quantityPurchased"));
    		 return cant;
        }
		return 0;	
    }
    
    public int getStock(String query) throws SQLException{
    	PreparedStatement  statement = connection.prepareStatement(query);
    	ResultSet result = statement.executeQuery();
    	while(result.next()) {
    		 int stock = Integer.parseInt( result.getString("stock"));
    		 return stock;
        }
		return 0;	
    }
    
    public String getCategory(String query) throws SQLException{
    	PreparedStatement  statement = connection.prepareStatement(query);
    	ResultSet result = statement.executeQuery();
    	while(result.next()) {
    		 String category = result.getString("category");
    		 return category;
        }
		return null;	
    }
    
    public ArrayList<String> findCategory(String query) throws SQLException{
    	ArrayList<String> array = new ArrayList<>();
    	PreparedStatement  statement = connection.prepareStatement(query);
    	ResultSet result = statement.executeQuery();
    	while(result.next()) {
        	array.add(result.getString("category"));
        }
        return array;
    }
    
    public ArrayList<String> findRutName (String query) throws SQLException{
    	ArrayList<String> array = new ArrayList<>();
    	PreparedStatement  statement = connection.prepareStatement(query);
    	ResultSet result = statement.executeQuery();
    	while(result.next()) {
        	array.add(result.getString("rut"));
        	array.add(result.getString("namePerson"));
        }
        return array;
    }
    
    public ArrayList<String> findProductAll(String query) throws SQLException{
    	ArrayList<String> array = new ArrayList<>();
    	PreparedStatement  statement = connection.prepareStatement(query);
    	ResultSet result = statement.executeQuery();
    	while(result.next()) {
        	array.add(result.getString("nameProduct"));
        	array.add(String.valueOf(result.getString("category")));
        	array.add(String.valueOf(result.getString("description")));
        	array.add(String.valueOf(result.getString("price")));
        	array.add(String.valueOf(result.getString("stock")));
        	array.add(String.valueOf(result.getString("image")));
        }
        return array;
    }
    
    public void close() {
        connection = null;
        if (connection == null) {
            System.out.println("Connection terminated successfully");
        }
    }
    

}
