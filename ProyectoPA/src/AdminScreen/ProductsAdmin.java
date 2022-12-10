package AdminScreen;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ClientScreen.ClientScreen;
import ClientScreen.ShowProduct;
import ConnectionDataBase.ConnectionDB;
import Dominio.Factory;
import Dominio.Person;
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
		
		JButton btnNewButton = new JButton("Editar producto");
		btnNewButton.addActionListener(new ActionListener() {
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
		btnNewButton.setBounds(717, 94, 121, 45);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(717, 168, 121, 45);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(717, 244, 121, 45);
		contentPane.add(btnNewButton_2);
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
