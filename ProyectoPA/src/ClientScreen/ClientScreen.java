package ClientScreen;
import Dominio.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ConnectionDataBase.ConnectionDB;
import GUI.Login;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class ClientScreen extends JFrame {

	private JPanel contentPane;
	private static ConnectionDB connect;
	private static Person p;
	private JTable table;
	private ArrayList<String> l = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientScreen frame = new ClientScreen(p,connect);
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
	public ClientScreen(Person p,ConnectionDB connect) {
		ClientScreen.connect = connect;
		ClientScreen.p = p;
		setTitle("Ventana Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 938, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton profileButton = new JButton("Perfil Usuario");
		profileButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		profileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfileScreen ps = new ProfileScreen(p,connect);
				ps.setVisible(true);
				ClientScreen.this.dispose();
			}
		});
		profileButton.setBounds(10, 58, 237, 45);
		contentPane.add(profileButton);
		
		JButton exitButton = new JButton("Salir");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l = new Login(connect);
				l.setVisible(true);
				ClientScreen.this.dispose();
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		exitButton.setBounds(754, 26, 142, 37);
		contentPane.add(exitButton);
		
		String query = String.format("select * from product");
		try {
			l = connect.findProduct(query);
			if(l.isEmpty()) {
				JOptionPane.showMessageDialog(null,"No existen Productos");
				String [][] s = new String[(l.size()/3)][3];
				table = new JTable();
				table.setBounds(270, 151, 626, 355);
				table.setModel(new DefaultTableModel(s,
					new String[] {
						"Producto", "Precio", "Quedan"
					}
				));
			}
			else {
				String [][] s = new String[(l.size()/3)][3];
				rellenarTabla(l,s);
				table = new JTable();
				table.setBounds(270, 151, 626, 355);
				table.setModel(new DefaultTableModel(s,
					new String[] {
							"Producto", "Precio", "Quedan"
					}
				));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPane.add(table);
		
		JButton buttonViewPhoto = new JButton("Mostrar Producto");
		buttonViewPhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row >= 0) {
					String nameProduct = (String) table.getValueAt(row, 0);
					String query = String.format("select * from product where nameProduct= '%s'",nameProduct);
					String imagePath;
					try {
						imagePath = connect.findImageProduct(query);
						ShowProduct sp = new ShowProduct(p,connect,imagePath);
						sp.setVisible(true);
						ClientScreen.this.dispose();
						
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"Debe seleccionar la casilla que desea ver");
				}
				
			}
		});
		buttonViewPhoto.setFont(new Font("Tahoma", Font.PLAIN, 25));
		buttonViewPhoto.setBounds(10, 151, 237, 45);
		contentPane.add(buttonViewPhoto);
		
		JLabel lblNewLabel = new JLabel("Producto");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(390, 117, 142, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrecio.setBounds(579, 117, 142, 25);
		contentPane.add(lblPrecio);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStock.setBounds(754, 117, 142, 25);
		contentPane.add(lblStock);
		
		JButton buttonAddCart = new JButton("Agregar Al Carrito");
		buttonAddCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row >= 0) {
					String nameProduct = (String) table.getValueAt(row, 0);
					
					String query = String.format("select * from product where nameProduct = '%s'",nameProduct);
					try {
						int price = 0;
						price = connect.getPriceProduct(query);
						if(price > 0) {
							String cant = JOptionPane.showInputDialog("Ingrese La cantidad que desea comprar");
							if(cant != null) {
								int priceFinal = Integer.parseInt(cant)*price;
								query = String.format("insert into shoppingCart values('%s','%s','%s','%s')",nameProduct,p.getRut(),Integer.parseInt(cant),priceFinal);
								try {
									connect.insertData(query);
									JOptionPane.showMessageDialog(null,"Agregado Correctamente al Carrito");
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							
						}
						else {
							JOptionPane.showMessageDialog(null,"Producto con error de precio");
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null,"Debe seleccionar la casilla que desea ver");
				}
				
			}
		});
		buttonAddCart.setFont(new Font("Tahoma", Font.PLAIN, 25));
		buttonAddCart.setBounds(10, 240, 237, 45);
		contentPane.add(buttonAddCart);
		
		JButton buttonViewCart = new JButton("Mostrar Carrito");
		buttonViewCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewCart vc = new ViewCart(p,connect);
				vc.setVisible(true);
				ClientScreen.this.dispose();
			}
		});
		buttonViewCart.setFont(new Font("Tahoma", Font.PLAIN, 25));
		buttonViewCart.setBounds(10, 313, 237, 45);
		contentPane.add(buttonViewCart);
	}
	
	private void rellenarTabla(ArrayList<String> l2, String[][] s) {
		int k= 0;
		for(int i=0;i<(l2.size()/3);i++) {
			for(int j=0;j<3;j++) {
				if(l2.get(j) != null) {
					s[i][j] = l2.get(k);
					k++;
				}
				
			}
		}
		
	}
}
