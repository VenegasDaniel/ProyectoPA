package ClientScreen;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConnectionDataBase.ConnectionDB;
import Dominio.Factory;
import Dominio.Person;
import GUI.Login;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class EditDataScreen extends JFrame {

	private JPanel contentPane;
	private static Person p;
	private static ConnectionDB connect;
	private JTextField enterName;
	private JTextField enterLastName;
	private JTextField enterPassword;
	private JTextField enterMail;
	private JTextField enterDirection;
	private JTextField enterPhone;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditDataScreen frame = new EditDataScreen(p,connect);
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
	public EditDataScreen(Person p,ConnectionDB connect) {
		EditDataScreen.p = p;
		EditDataScreen.connect = connect;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 701, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNombre.setBounds(57, 92, 185, 37);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblApellido.setBounds(57, 146, 143, 37);
		contentPane.add(lblApellido);
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblMail.setBounds(57, 250, 85, 37);
		contentPane.add(lblMail);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblContrasea.setBounds(57, 203, 168, 37);
		contentPane.add(lblContrasea);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblDireccion.setBounds(57, 303, 185, 37);
		contentPane.add(lblDireccion);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTelefono.setBounds(57, 363, 168, 37);
		contentPane.add(lblTelefono);
		
		enterName = new JTextField();
		enterName.setColumns(10);
		enterName.setBounds(271, 92, 357, 43);
		contentPane.add(enterName);
		
		enterLastName = new JTextField();
		enterLastName.setColumns(10);
		enterLastName.setBounds(271, 146, 357, 37);
		contentPane.add(enterLastName);
		
		enterPassword = new JTextField();
		enterPassword.setColumns(10);
		enterPassword.setBounds(271, 193, 357, 43);
		contentPane.add(enterPassword);
		
		enterMail = new JTextField();
		enterMail.setColumns(10);
		enterMail.setBounds(271, 251, 357, 43);
		contentPane.add(enterMail);
		
		enterDirection = new JTextField();
		enterDirection.setColumns(10);
		enterDirection.setBounds(271, 304, 357, 43);
		contentPane.add(enterDirection);
		
		enterPhone = new JTextField();
		enterPhone.setColumns(10);
		enterPhone.setBounds(271, 357, 357, 43);
		contentPane.add(enterPhone);
		
		
		fillData();
		blockData();
		
		JLabel lblNewLabel_1 = new JLabel("Editar Datos");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel_1.setBounds(234, 21, 276, 37);
		contentPane.add(lblNewLabel_1);
		
		JButton buttonFinishEdit = new JButton("TerminarEdicion");
		buttonFinishEdit.setFont(new Font("Tahoma", Font.PLAIN, 30));
		buttonFinishEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blockData();
				String query;
				//update phone
				query = String.format("update person set phone = %s where rut = '%s'",enterPhone.getText(),p.getRut());
				try {
					connect.UpdateData(query);
					p.setPhone(Integer.parseInt(enterPhone.getText()));
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				//update direction
				query = String.format("update person set direction = '%s' where rut = '%s'",enterDirection.getText(),p.getRut());
				try {
					connect.UpdateData(query);
					p.setDirection(enterDirection.getText());
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				//update mail
				query = String.format("update person set mail = '%s' where rut = '%s'",enterMail.getText(),p.getRut());
				try {
					connect.UpdateData(query);
					p.setMail(enterMail.getText());
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				//update password
				query = String.format("update person set passwordPerson = '%s' where rut = '%s'",enterPassword.getText(),p.getRut());
				try {
					connect.UpdateData(query);
					p.setPassword(enterPassword.getText());
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				//update lastName
				query = String.format("update person set lastName = '%s' where rut = '%s'",enterLastName.getText(),p.getRut());
				try {
					connect.UpdateData(query);
					p.setLastName(enterLastName.getText());
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				//update name
				query = String.format("update person set namePerson = '%s' where rut = '%s'",enterName.getText(),p.getRut());
				try {
					connect.UpdateData(query);
					p.setName(enterName.getText());
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				ProfileScreen ps = new ProfileScreen(p,connect);
				ps.setVisible(true);
				EditDataScreen.this.dispose();	
			
			}	
		});
		buttonFinishEdit.setBounds(362, 424, 276, 48);
		contentPane.add(buttonFinishEdit);
		
		JButton buttonUnlock = new JButton("HabilitarEdicion");
		buttonUnlock.setFont(new Font("Tahoma", Font.PLAIN, 30));
		buttonUnlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unlockData();
			}

		});
		buttonUnlock.setBounds(57, 425, 268, 47);
		contentPane.add(buttonUnlock);
	}
	private void blockData() {
		enterName.setEnabled(false);
		enterLastName.setEnabled(false);
		enterPassword.setEnabled(false);
		enterMail.setEnabled(false);
		enterDirection.setEnabled(false);
		enterPhone.setEnabled(false);	
	}

	private void fillData() {;
		enterName.setText(p.getName());
		enterLastName.setText(p.getLastName());;
		enterPassword.setText(p.getPassword());;
		enterMail.setText(p.getMail());
		enterDirection.setText(p.getDirection());;
		enterPhone.setText(String.valueOf(p.getPhone()));
	};
	
	private void unlockData() {
		enterName.setEnabled(true);
		enterLastName.setEnabled(true);
		enterPassword.setEnabled(true);
		enterMail.setEnabled(true);
		enterDirection.setEnabled(true);
		enterPhone.setEnabled(true);
	}
	
}
