/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataBaseStuff;

import java.sql.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Cefeg
 */
public class DBconnections {
    
    Connection connection = null;
    
    String user = "postgres";
    String contra = "1234";
    String dbName = "Proyecto-E-C";
    String ip = "localhost";
    String puerto = "5432";
    
    String cadena = "jdbc:postgresql://"+ip+":"+puerto+"/"+dbName;
    
    public Connection connect(){
        try {
            Class.forName("org.postgresql.Driver");
            connection=DriverManager.getConnection(cadena,user,contra);
            JOptionPane.showMessageDialog(null,"Se conecto correctamente");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error en conectar a la base de datos, error:"+e.toString());
        }
        return connection;
    }
    
    public ArrayList<String> findUser(String query,String rut,String pass) throws SQLException{
        String sql = "";
        sql = query;
    	try {
    		Statement st = connection.createStatement();
        	ResultSet result = st.executeQuery(sql);
        	ArrayList<String> array = new ArrayList<>();
        	while(result.next()) {
        		if(result.getString("rut").equals(rut) && result.getString("passwordPerson").equals(pass)){
        			array.add(result.getString(1));
        			array.add(result.getString(2));
        			array.add(result.getString(3));
        			array.add(result.getString(4));
                                array.add(result.getString(5));
                                array.add(result.getString(6));
        			array.add(result.getString(7));
                                array.add(result.getString(8));
        			array.add(result.getString(9));
        			array.add(result.getString(10));
                                array.add(result.getString(11));
        		}
       	}
        	return array;
    	}catch(Exception e) {System.out.println(e);}
		return null;	
    }
    
    public void getProducts(JTable paramTable) throws SQLException{
        
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("Nombre");
        model.addColumn("Categoria");
        model.addColumn("Precio");
        model.addColumn("Stock");
        
        paramTable.setModel(model);
        
        String sql = "";
        sql = "select * from product";
        String [] datos = new String[4];
    	try {
    	Statement st = connection.createStatement();
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
        		
            datos[0] = result.getString(1);
            datos[1] = result.getString(2);
            datos[2] = result.getString(4);
            datos[3] = result.getString(5);
                    
            model.addRow(datos);
                    
            }
        paramTable.setModel(model);                
       	}catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Error con los productos, error:"+e.toString());
        }				
    }
    
    public void insertPerson(String rut, String name, String lastname, String mail, 
            String phone, String password, String city, String commune, String direction){  
        
        
        
        String consulta = "insert into person "
                + "(rut,namePerson,lastName,mail,phone,passwordPerson,city,commune,direction,image,statePerson) values "
                + "(?,?,?,?,?,?,?,?,?,?,'false');";
        
        try {
            
            CallableStatement cs = connection.prepareCall(consulta);
            cs.setString(1, rut);
            cs.setString(2, name);
            cs.setString(3, lastname);
            cs.setString(4, mail);
            cs.setString(5, phone);
            cs.setString(6, password);
            cs.setString(7, city);
            cs.setString(8, commune);
            cs.setString(9, direction);
            cs.setString(10, null);
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"Se insertó correctamente");
            
            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"Error:"+ e.toString());
        }
        
    }       
}
