package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ClientScreen.ClientScreen;
import Dominio.ConnectionDB;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class SignIn extends JFrame {

	private JPanel contentPane;
	private JTextField textField_5;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private ConnectionDB connect;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn frame = new SignIn();
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
	public SignIn() {
		setTitle("Registrarse");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(47, 70, 86, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblApellido.setBounds(47, 119, 86, 22);
		contentPane.add(lblApellido);
		
		JLabel lblNewLabel_1 = new JLabel("Correo Electronico");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(44, 164, 198, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContrasea.setBounds(47, 217, 147, 23);
		contentPane.add(lblContrasea);
		
		JLabel lblNewLabel_2 = new JLabel("Numero de Telefono");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(47, 264, 195, 22);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Direccion");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(47, 308, 147, 22);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//connect.insertData(rut,name,lastName,passwordPerson,mail,direction,phone);
				Login l = new Login(connect);
				l.setVisible(true);
				SignIn.this.dispose();
			}
		});
		btnNewButton.setBounds(233, 361, 205, 57);
		contentPane.add(btnNewButton);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(261, 72, 299, 29);
		contentPane.add(textField_5);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(261, 120, 299, 29);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(261, 164, 299, 29);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(261, 211, 299, 29);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(261, 257, 299, 29);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(261, 309, 299, 29);
		contentPane.add(textField_4);
		
		JButton exitButton = new JButton("Salir");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l = new Login(connect);
				l.setVisible(true);
				SignIn.this.dispose();
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		exitButton.setBounds(561, 10, 103, 41);
		contentPane.add(exitButton);
	}
}
