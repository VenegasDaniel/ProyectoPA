package Logica;

import GUI.Login;

import java.io.IOException;
import java.sql.SQLException;

import Dominio.ConnectionDB;

public class App {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		ConnectionDB connect = new ConnectionDB();
		connect.getConnection();
		Login l = new Login(connect);
		l.setVisible(true);
		
	}

}
