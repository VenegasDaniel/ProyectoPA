package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ClientScreen.ClientScreen;
import ConnectionDataBase.ConnectionDB;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
 * @author mauri
 *
 */
public class SignIn extends JFrame {

	private JPanel contentPane;
	private JTextField enterDirection;
	private static ConnectionDB connect;
	private JTextField enterMail;
	private JTextField enterPassword;
	private JTextField enterLastName;
	private JTextField enterName;
	private JTextField enterRut;
	private JTextField enterPhone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn frame = new SignIn(connect);
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
	public SignIn(ConnectionDB connect) {
		SignIn.connect = connect;
		setTitle("Registrarse");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 677);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("Rut");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(26, 123, 162, 37);
		contentPane.add(lblNewLabel);

		JLabel lblApellido = new JLabel("Nombre");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblApellido.setBounds(25, 185, 126, 36);
		contentPane.add(lblApellido);

		JLabel lblNewLabel_1 = new JLabel("Apellido");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(25, 243, 231, 37);
		contentPane.add(lblNewLabel_1);

		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblContrasea.setBounds(26, 308, 216, 36);
		contentPane.add(lblContrasea);

		JLabel lblNewLabel_2 = new JLabel("Mail");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(32, 370, 210, 36);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Direccion");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_3.setBounds(26, 435, 162, 36);
		contentPane.add(lblNewLabel_3);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(enterRut.getText().length() == 0 || enterName.getText().length() == 0 || enterLastName.getText().length() == 0 || 
						enterPassword.getText().length() == 0 || enterMail.getText().length() == 0 || enterDirection.getText().length() == 0 ||
						enterPhone.getText().length() == 0) {
					JOptionPane.showMessageDialog(null,"Recuerde Rellenar todos los campos primero");
				}
				else {
					String rut = enterRut.getText();
					rut = validateRut(rut);
					if(rut != null) {
						String query =  String.format("select * from person where rut = '%s'", rut);
						try {
							rut = connect.getRut(query);
							if(rut == null) {
								query = String.format("select * from person where mail = '%s'", enterMail.getText());
								try {
									String mail = connect.findEmail(query);
									if(mail != null) {
										JOptionPane.showMessageDialog(null,"El email ingresado ya existe");
									}
									else {
										query = String.format("insert into person values('%s','%s','%s','%s','%s',%s,'%s',%s,'%s')",enterRut.getText(),enterName.getText(),
												enterLastName.getText(),enterPassword.getText(),enterMail.getText(),false,enterDirection.getText(),null,enterPhone.getText());
										
										try {
											connect.insertData(query);
										} catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										JOptionPane.showMessageDialog(null,"Registrado Correctamente");
										Login l = new Login(connect);
										l.setVisible(true);
										SignIn.this.dispose();
									}
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
							}
							else {
								JOptionPane.showMessageDialog(null,"Ingrese un rut Valido");
							}
						} catch (SQLException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
						
						
					}
					else {
						JOptionPane.showMessageDialog(null,"Ingrese un rut Valido");
					}
	
				}
				
			}
		});
		btnNewButton.setBounds(319, 573, 205, 57);
		contentPane.add(btnNewButton);

		enterDirection = new JTextField();
		enterDirection.setFont(new Font("Tahoma", Font.PLAIN, 20));
		enterDirection.setColumns(10);
		enterDirection.setBounds(333, 430, 421, 41);
		contentPane.add(enterDirection);

		JButton exitButton = new JButton("Salir");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l = new Login(connect);
				l.setVisible(true);
				SignIn.this.dispose();
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		exitButton.setBounds(711, 31, 103, 41);
		contentPane.add(exitButton);
		
		enterMail = new JTextField();
		enterMail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		enterMail.setColumns(10);
		enterMail.setBounds(333, 365, 421, 41);
		contentPane.add(enterMail);
		
		enterPassword = new JTextField();
		enterPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		enterPassword.setColumns(10);
		enterPassword.setBounds(333, 303, 421, 41);
		contentPane.add(enterPassword);
		
		enterLastName = new JTextField();
		enterLastName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		enterLastName.setColumns(10);
		enterLastName.setBounds(333, 245, 421, 41);
		contentPane.add(enterLastName);
		
		enterName = new JTextField();
		enterName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		enterName.setColumns(10);
		enterName.setBounds(333, 180, 421, 41);
		contentPane.add(enterName);
		
		enterRut = new JTextField();
		enterRut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		enterRut.setColumns(10);
		enterRut.setBounds(333, 125, 421, 41);
		contentPane.add(enterRut);
		
		enterPhone = new JTextField();
		enterPhone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		enterPhone.setColumns(10);
		enterPhone.setBounds(333, 493, 421, 41);
		contentPane.add(enterPhone);
		
		JLabel lblNewLabel_3_1 = new JLabel("Telefono");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_3_1.setBounds(26, 498, 162, 36);
		contentPane.add(lblNewLabel_3_1);
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
