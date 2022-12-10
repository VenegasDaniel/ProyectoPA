package GUI;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import AdminScreen.AdminScreen;
import ClientScreen.ClientScreen;
import ConnectionDataBase.ConnectionDB;
import Dominio.Factory;
import Dominio.Person;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private static ConnectionDB connect;
	/**
	 * Launch the application.
	 * 
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login(connect);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public Login(ConnectionDB connect) {
		setTitle("Login");
		setBounds(100, 100, 538, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(114, 60, 164, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel icon_User = new JLabel("Usuario");
		icon_User.setHorizontalAlignment(SwingConstants.CENTER);
		icon_User.setFont(new Font("Tahoma", Font.PLAIN, 25));
		icon_User.setBounds(114, 25, 139, 25);
		contentPane.add(icon_User);
		
		JLabel lblNewLabel_1_1 = new JLabel("Contrasena");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(114, 99, 164, 29);
		contentPane.add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(114, 138, 164, 29);
		contentPane.add(textField_1);
		//login button creation and function
		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> l = null;
				String rut = textField.getText();
				String rutAdmin = textField.getText();
				String passwordPerson = textField_1.getText();
				rut = validateRut(rut);
				if(rut != null) {
					String query = String.format("Select * from Person where rut = '%s' ",rut);
					try {
						l= connect.findUser(query);
						if(l.isEmpty()) {
							JOptionPane.showMessageDialog(null,"Usuario no existente");
							
						}else {
							if(l.get(0).equals(rut)) {
								if(l.get(5).equals("f") && l.get(3).equals(passwordPerson) ) {
									Person p = Factory.factory( l.get(0),l.get(1),l.get(2),l.get(3), l.get(4),  l.get(5),  l.get(6),  l.get(7),  l.get(8),  l.get(9),  l.get(10));
									ClientScreen cs = new ClientScreen(p,connect);
									cs.setVisible(true);
									Login.this.dispose();
								}
								else if(!l.get(3).equals(passwordPerson)){
									JOptionPane.showMessageDialog(null,"Contraseña y/o rut incorrectos");
								}
								else {
									JOptionPane.showMessageDialog(null,"Usuario Bloqueado");
								}
							}
						}
						
					} catch (SQLException e1) {
						System.out.println(e1);
					}
				}
				else {
					String query = String.format("Select * from Person where rutCompany = '%s' ",rutAdmin);
					try {
						l= connect.findUser(query);
						if(!l.isEmpty()) {
							if(l.get(9).equals(rutAdmin) && l.get(10).equals(passwordPerson)) {
								AdminScreen as = new AdminScreen(connect);
								as.setVisible(true);
								Login.this.dispose();
							}
							else {
								JOptionPane.showMessageDialog(null,"Contraseña y/o rut incorrectos");
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"Ingrese Rut Valido");
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}

			}
		});
		login.setBounds(114, 188, 164, 38);
		contentPane.add(login);
		
		
		
		JLabel lblNewLabel = new JLabel("No tienes una cuenta?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(115, 246, 219, 46);
		contentPane.add(lblNewLabel);
		
		JButton signUp = new JButton("Registrate");
		signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignIn si = new SignIn(connect);
				si.setVisible(true);
				Login.this.dispose();
			}
		});
		signUp.setBounds(318, 254, 139, 38);
		contentPane.add(signUp);
		
		JButton closeProgram = new JButton("");
		closeProgram.setBounds(441, 10, 100,32);
		ImageIcon image = new ImageIcon("Media/Exit3.png");
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(closeProgram.getWidth(), closeProgram.getHeight(), Image.SCALE_DEFAULT));
		closeProgram.setIcon(icon);
		closeProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect.close();
				Login.this.dispose();
			}
		});
		closeProgram.setFont(new Font("Tahoma", Font.PLAIN, 15));
		closeProgram.setBounds(441, 10, 73, 29);
		contentPane.add(closeProgram);
	}
	private String validateRut(String rut) {
		try {
			rut =  rut.toUpperCase();
			rut = rut.replace(".", "");
			rut = rut.replace("-", "");
			int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));
			char dv = rut.charAt(rut.length() - 1);
			int m = 0, s = 1;
			for (; rutAux != 0; rutAux /= 10) {
				s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
			}
			if (dv == (char) (s != 0 ? s + 47 : 75)) {
				return rut;
			}
		}
		catch (java.lang.NumberFormatException e) {
		} catch (Exception e) {
		}
		return null;
		
	}
	
}
