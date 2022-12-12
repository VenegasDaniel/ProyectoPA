package AdminScreen;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
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
		ProductsAdmin.connect = connect;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(69, 143, 461, 323);
		
		String query = String.format("select * from product");
		try {
			l = connect.findProduct(query);
			if(l.isEmpty()) {
				JOptionPane.showMessageDialog(null,"No existen Productos");
				String [][] s = new String[(l.size()/3)][3];
				table.setModel(new DefaultTableModel(s,
					new String[] {
						"Producto", "Precio", "Stock"
					}
				));
			}
			else {
				String [][] s = new String[(l.size()/3)][3];
				fillerTable(l,s);
				
				DefaultTableModel model =new DefaultTableModel(s,new String[] {"Product", "Precio","Stock"}) {
					@Override
					public boolean isCellEditable(int rowIndex,int rowColumn) {
						return false;
					}
				};
				table.setModel(model);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPane.add(table);
		contentPane.setLayout(null);
		
		
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
		buttonEditProduct.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonEditProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row >= 0) {
					String nameProduct = (String) table.getValueAt(row, 0);
					String query = String.format("select * from product where nameProduct= '%s'",nameProduct);
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
		buttonEditProduct.setBounds(571, 159, 203, 45);
		contentPane.add(buttonEditProduct);
		
		JButton buttonDelete = new JButton("Eliminar Producto");
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
                if(row >= 0) {
                    String nameProduct = (String) table.getValueAt(row, 0);
                    try {
                    	String query = String.format("delete from shoppingCart where nameProduct= '%s'",nameProduct);
                        connect.DeleteData(query);
                        query = String.format("delete from reviews where nameProduct= '%s'",nameProduct);
                        connect.DeleteData(query);
                        query = String.format("delete from purchasePersonProduct where nameProduct= '%s'",nameProduct);
                        connect.DeleteData(query);
                        query = String.format("delete from product where nameProduct= '%s'",nameProduct);
                        connect.DeleteData(query);
                        ProductsAdmin.this.dispose();
                        JOptionPane.showMessageDialog(null,"Producto Eliminado,Se refrescara la pestaña");
                        ProductsAdmin pa = new ProductsAdmin(connect);
                        pa.setVisible(true);
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"Debe seleccionar la casilla que desea ver");
                }
			}
		});
		buttonDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonDelete.setBounds(571, 264, 203, 45);
		contentPane.add(buttonDelete);
		
		JButton buttonAddProduct = new JButton("Añadir Producto");
		buttonAddProduct.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameProduct = JOptionPane.showInputDialog("Ingrese el nombre del nuevo producto");
				nameProduct = nameProduct.toLowerCase();
				String query = String.format("select * from product where nameProduct = '%s'",nameProduct);
				try {
					String existe = connect.getNameProduct(query);
					if(existe == null) {
						AddProduct ap = new AddProduct(connect,nameProduct);
						ap.setVisible(true);
						ProductsAdmin.this.dispose();
					}
					else {
						JOptionPane.showMessageDialog(null,"No se pudo agregar \n [RAZON] Este producto ya existe");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		buttonAddProduct.setBounds(571, 377, 203, 45);
		contentPane.add(buttonAddProduct);
		
		JButton exitButton = new JButton("Salir");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminScreen as = new AdminScreen(connect);
				as.setVisible(true);
				ProductsAdmin.this.dispose();
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		exitButton.setBounds(637, 32, 119, 50);
		contentPane.add(exitButton);
		
	}
	
	
	
	private void fillerTable(ArrayList<String> l2, String[][] s) {
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
