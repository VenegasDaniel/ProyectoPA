package AdminScreen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ClientScreen.EditDataScreen;
import ClientScreen.ProfileScreen;
import ConnectionDataBase.ConnectionDB;
import Dominio.Product;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditProductAdmin extends JFrame {

	private JPanel contentPane;
	private static ConnectionDB connect;
	private JTextField enterName;
	private JTextField enterCategory;
	private JTextField enterDescription;
	private JTextField enterPrice;
	private JTextField enterStock;
	public static Product p;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditProductAdmin frame = new EditProductAdmin(connect,p);
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
	public EditProductAdmin(ConnectionDB connect,Product p) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombre.setBounds(63, 78, 85, 37);
		contentPane.add(lblNombre);
		
		enterName = new JTextField();
		enterName.setColumns(10);
		enterName.setBounds(175, 85, 346, 32);
		contentPane.add(enterName);
		
		JLabel lblNombre_1 = new JLabel("Categoria");
		lblNombre_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombre_1.setBounds(63, 125, 85, 37);
		contentPane.add(lblNombre_1);
		
		enterCategory = new JTextField();
		enterCategory.setColumns(10);
		enterCategory.setBounds(175, 132, 346, 32);
		contentPane.add(enterCategory);
		
		JLabel lblNombre_2 = new JLabel("Descripcion");
		lblNombre_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombre_2.setBounds(63, 172, 85, 37);
		contentPane.add(lblNombre_2);
		
		enterDescription = new JTextField();
		enterDescription.setColumns(10);
		enterDescription.setBounds(175, 179, 346, 32);
		contentPane.add(enterDescription);
		
		JLabel lblNombre_3 = new JLabel("Precio");
		lblNombre_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombre_3.setBounds(63, 219, 85, 37);
		contentPane.add(lblNombre_3);
		
		enterPrice = new JTextField();
		enterPrice.setColumns(10);
		enterPrice.setBounds(175, 226, 346, 32);
		contentPane.add(enterPrice);
		
		JLabel lblNombre_4 = new JLabel("Stock");
		lblNombre_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombre_4.setBounds(63, 266, 85, 37);
		contentPane.add(lblNombre_4);
		
		enterStock = new JTextField();
		enterStock.setColumns(10);
		enterStock.setBounds(175, 273, 346, 32);
		contentPane.add(enterStock);
		
		
		fillData();
		blockData();
		
		JButton buttonFinishEdit = new JButton("TerminarEdicion");
		buttonFinishEdit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonFinishEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}	
		});
		buttonFinishEdit.setBounds(326, 346, 195, 37);
		contentPane.add(buttonFinishEdit);
		
		JButton buttonUnlock = new JButton("HabilitarEdicion");
		buttonUnlock.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonUnlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unlockData();
			}

		});
		buttonUnlock.setBounds(63, 346, 203, 37);
		contentPane.add(buttonUnlock);
		
		JLabel lblNewLabel_1 = new JLabel("Editar Datos");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel_1.setBounds(157, 21, 276, 37);
		contentPane.add(lblNewLabel_1);
	}
	private void blockData() {
		enterName.setEnabled(false);
		enterCategory.setEnabled(false);
		enterDescription.setEnabled(false);
		enterPrice.setEnabled(false);
		enterStock.setEnabled(false);
	}

	private void fillData() {;
		enterName.setText(p.getNameProduct());
		enterCategory.setText(p.getCategory());;
		enterDescription.setText(p.getDescription());;
		enterPrice.setText(String.valueOf(p.getPrice()));
		enterStock.setText(String.valueOf(p.getStock()));;
	};
	
	private void unlockData() {
		enterName.setEnabled(true);
		enterCategory.setEnabled(true);
		enterDescription.setEnabled(true);
		enterPrice.setEnabled(true);
		enterStock.setEnabled(true);	
		
	}
}
