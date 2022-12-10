package AdminScreen;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ConnectionDataBase.ConnectionDB;
import GUI.Login;
import GUI.SignIn;


public class AddProduct extends JFrame {

	private JPanel contentPane;
	private JTextField enterName;
	private JTextField enterCategory;
	private JTextField enterDescription;
	private JTextField enterPrice;
	private JTextField enterStock;
	private static ConnectionDB connect;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProduct frame = new AddProduct(connect);
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
	public AddProduct(ConnectionDB connect) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblName = new JLabel("Nombre");
		lblName.setBounds(62, 99, 85, 37);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblName);
		
		enterName = new JTextField();
		enterName.setBounds(174, 106, 346, 32);
		enterName.setColumns(10);
		contentPane.add(enterName);
		
		JLabel lblCategory = new JLabel("Categoria");
		lblCategory.setBounds(62, 146, 85, 37);
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblCategory);
		
		enterCategory = new JTextField();
		enterCategory.setBounds(174, 153, 346, 32);
		enterCategory.setColumns(10);
		contentPane.add(enterCategory);
		
		JLabel lblDescription = new JLabel("Descripcion");
		lblDescription.setBounds(62, 193, 85, 37);
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblDescription);
		
		enterDescription = new JTextField();
		enterDescription.setBounds(174, 200, 346, 32);
		enterDescription.setColumns(10);
		contentPane.add(enterDescription);
		
		JLabel lblPrice = new JLabel("Precio");
		lblPrice.setBounds(62, 240, 85, 37);
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblPrice);
		
		enterPrice = new JTextField();
		enterPrice.setBounds(174, 247, 346, 32);
		enterPrice.setColumns(10);
		contentPane.add(enterPrice);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(62, 287, 85, 37);
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblStock);
		
		enterStock = new JTextField();
		enterStock.setBounds(174, 294, 346, 32);
		enterStock.setColumns(10);
		contentPane.add(enterStock);
		
		JLabel lblAdd = new JLabel("Añadir un producto");
		lblAdd.setBounds(210, 34, 239, 32);
		lblAdd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblAdd);
		
		JButton buttonAddProduct = new JButton("Añadir");
		buttonAddProduct.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(enterName.getText().isEmpty() || enterCategory.getText().isEmpty() || enterDescription.getText().isEmpty() || 
						enterPrice.getText().isEmpty() || enterStock.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Recuerde Rellenar todos los campos primero");
				}
				else {
					String nameProduct = enterName.getText();
					String query =  String.format("select * from product where nameProduct = '%s'", nameProduct);
					try {
						nameProduct = connect.getRut(query);
						if(nameProduct == null) {
							
							
								String mail = connect.findEmail(query);
								if(mail != null) {
									JOptionPane.showMessageDialog(null,"El email ingresado ya existe");
								}
								else {
									query = String.format("insert into product values('%s','%s','%s',%s,%s,'%s')",enterName.getText(),enterCategory.getText(),
											enterDescription.getText(),Integer.parseInt(enterPrice.getText()) ,Integer.parseInt(enterStock.getText()),false);
									
									try {
										connect.insertData(query);
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									JOptionPane.showMessageDialog(null,"Añadido Correctamente");
									ProductsAdmin pa = new ProductsAdmin(connect);
									pa.setVisible(true);
									AddProduct.this.dispose();
								}							
						}
						else {
							JOptionPane.showMessageDialog(null,"Producto ya existente");
						}
					} catch (SQLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
				}
			}

		});
		buttonAddProduct.setBounds(189, 379, 203, 37);
		contentPane.add(buttonAddProduct);
		
		
		JButton exitButton = new JButton("Salir");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductsAdmin pa = new ProductsAdmin(connect);
				pa.setVisible(true);
				AddProduct.this.dispose();
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		exitButton.setBounds(477, 30, 103, 41);
		contentPane.add(exitButton);
		
	}
	

}
