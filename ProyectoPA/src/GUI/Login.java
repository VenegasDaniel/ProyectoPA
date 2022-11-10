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
import Dominio.ConnectionDB;

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
				String passwordPerson = textField_1.getText();
				try {
					l= connect.findUser("Select * from Person",rut,passwordPerson);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(l.isEmpty()) {
					JOptionPane.showMessageDialog(null,"Usuario no existente o datos erroneos");
				}
				else {
					if(l.get(5).equals("t")) {
						JOptionPane.showMessageDialog(null,"Usuario Bloqueado");
					}
					else if(l.get(5).equals("f") && l.get(9) == null) {
						ClientScreen cs = new ClientScreen();
						cs.setVisible(true);
						Login.this.dispose();
					}
					else {
						AdminScreen as = new AdminScreen();
						as.setVisible(true);
						Login.this.dispose();
						
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
				SignIn si = new SignIn();
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
				Login.this.dispose();
			}
		});
		closeProgram.setFont(new Font("Tahoma", Font.PLAIN, 15));
		closeProgram.setBounds(441, 10, 73, 29);
		contentPane.add(closeProgram);
	}

	
}
