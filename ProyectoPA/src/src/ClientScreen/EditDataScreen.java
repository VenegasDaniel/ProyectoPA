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
		setBounds(100, 100, 604, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombre.setBounds(57, 92, 85, 37);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblApellido.setBounds(57, 139, 85, 37);
		contentPane.add(lblApellido);
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMail.setBounds(57, 233, 85, 37);
		contentPane.add(lblMail);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContrasea.setBounds(57, 186, 102, 37);
		contentPane.add(lblContrasea);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDireccion.setBounds(57, 280, 85, 37);
		contentPane.add(lblDireccion);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTelefono.setBounds(57, 327, 102, 37);
		contentPane.add(lblTelefono);
		
		enterName = new JTextField();
		enterName.setColumns(10);
		enterName.setBounds(169, 99, 346, 32);
		contentPane.add(enterName);
		
		enterLastName = new JTextField();
		enterLastName.setColumns(10);
		enterLastName.setBounds(169, 146, 346, 32);
		contentPane.add(enterLastName);
		
		enterPassword = new JTextField();
		enterPassword.setColumns(10);
		enterPassword.setBounds(169, 193, 346, 32);
		contentPane.add(enterPassword);
		
		enterMail = new JTextField();
		enterMail.setColumns(10);
		enterMail.setBounds(169, 240, 346, 32);
		contentPane.add(enterMail);
		
		enterDirection = new JTextField();
		enterDirection.setColumns(10);
		enterDirection.setBounds(169, 287, 346, 32);
		contentPane.add(enterDirection);
		
		enterPhone = new JTextField();
		enterPhone.setColumns(10);
		enterPhone.setBounds(169, 334, 346, 32);
		contentPane.add(enterPhone);
		
		
		fillData();
		blockData();
		
		JLabel lblNewLabel_1 = new JLabel("Editar Datos");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel_1.setBounds(181, 23, 276, 37);
		contentPane.add(lblNewLabel_1);
		
		JButton buttonFinishEdit = new JButton("TerminarEdicion");
		buttonFinishEdit.setFont(new Font("Tahoma", Font.PLAIN, 20));
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
		buttonFinishEdit.setBounds(327, 402, 195, 37);
		contentPane.add(buttonFinishEdit);
		
		JButton buttonUnlock = new JButton("HabilitarEdicion");
		buttonUnlock.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonUnlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unlockData();
			}

		});
		buttonUnlock.setBounds(57, 402, 203, 37);
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
