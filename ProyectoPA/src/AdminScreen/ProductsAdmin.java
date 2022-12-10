package AdminScreen;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import ConnectionDataBase.ConnectionDB;
import Dominio.Product;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.SQLException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductsAdmin extends JFrame {

	private JPanel contentPane;
	
	private static ConnectionDB connect;
	private ArrayList<String> l = null;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductsAdmin frame = new ProductsAdmin(connect);
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
	public ProductsAdmin(ConnectionDB connect) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 938, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String query = String.format("select * from product");
		try {
			l = connect.findProduct(query);
			if(l.isEmpty()) {
				JOptionPane.showMessageDialog(null,"No existen Productos");
				String [][] s = new String[(l.size()/3)][3];
				table = new JTable();
				table.setBounds(83, 443, 461, -323);
				table.setModel(new DefaultTableModel(s,
					new String[] {
						"Producto", "Precio", "Stock"
					}
				));
			}
			else {
				String [][] s = new String[(l.size()/3)][3];
				rellenarTabla(l,s);
				table = new JTable();
				table.setBounds(83, 443, 461, -323);
				table.setModel(new DefaultTableModel(s,
					new String[] {
							"Producto", "Precio", "Stock"
					}
				));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPane.add(table);
		
		
		
		JLabel lblNewLabel = new JLabel("Producto");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(83, 77, 142, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrecio.setBounds(272, 77, 142, 25);
		contentPane.add(lblPrecio);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStock.setBounds(447, 77, 142, 25);
		contentPane.add(lblStock);
		
		JButton buttonEditProduct = new JButton("Editar producto");
		buttonEditProduct.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonEditProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row >= 0) {
					String nameProduct = (String) table.getValueAt(row, 0);
					String query = String.format("select * from product where nameProduct= '%s'",nameProduct);
					ArrayList<String> l;
					try {
						l = connect.findProductAll(query);
						Product p = new Product(l.get(0),l.get(1),l.get(2),Integer.parseInt(l.get(3)),Integer.parseInt(l.get(4)),l.get(5));						
						EditProductAdmin eda = new EditProductAdmin(connect,p);
						eda.setVisible(true);
						ProductsAdmin.this.dispose();
						
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
		buttonEditProduct.setBounds(681, 94, 157, 45);
		contentPane.add(buttonEditProduct);
		
		JButton btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(681, 168, 157, 45);
		contentPane.add(btnNewButton_1);
		
		JButton buttonAddProduct = new JButton("A�adir Producto");
		buttonAddProduct.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProduct pa = new AddProduct(connect);
				pa.setVisible(true);
				ProductsAdmin.this.dispose();
			}
		});
		buttonAddProduct.setBounds(681, 244, 157, 45);
		contentPane.add(buttonAddProduct);
		
		JButton exitButton = new JButton("Salir");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsersAdmin ua = new UsersAdmin(connect);
				ua.setVisible(true);
				ProductsAdmin.this.dispose();
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		exitButton.setBounds(477, 30, 103, 41);
		contentPane.add(exitButton);
		
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
