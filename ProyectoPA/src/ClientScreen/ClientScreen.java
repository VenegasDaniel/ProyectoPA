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
import java.nio.file.DirectoryStream.Filter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class ClientScreen extends JFrame {

	private JPanel contentPane;
	private static ConnectionDB connect;
	private static Person p;
	private JTable table;
	private ArrayList<String> l = null;
	private JTextField enterFilterName;
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
		
		table = new JTable();
		table.setBounds(270, 151, 626, 355);
		
		String query = String.format("select * from product");
		try {
			l = connect.findProduct(query);
			if(l.isEmpty()) {
				JOptionPane.showMessageDialog(null,"No existen Productos");
				String [][] s = new String[(l.size()/3)][3];
				table.setModel(new DefaultTableModel(s,
					new String[] {
						"Producto", "Precio", "Quedan"
					}
				));
			}
			else {
				String [][] s = new String[(l.size()/3)][3];
				fillerTable(l,s);
				DefaultTableModel model =new DefaultTableModel(s,new String[] {"Producto", "precio", "Quedan"}) {
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
		
		JButton buttonViewPhoto = new JButton("Mostrar Producto");
		buttonViewPhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row >= 0) {
					String nameProduct = (String) table.getValueAt(row, 0);
					if(nameProduct != null) {
						String query = String.format("select * from product where nameProduct= '%s'",nameProduct);
						String imagePath;
						try {
							imagePath = connect.findImageProduct(query);
							ShowProduct sp = new ShowProduct(p,connect,imagePath,nameProduct);
							sp.setVisible(true);
							ClientScreen.this.dispose();
							
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"Seleccione una linea que si contenga caracteres");
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
					if(nameProduct != null) {
						String query = String.format("select * from product where nameProduct = '%s'",nameProduct);
						try {
							int price = 0;
							price = connect.getPriceProduct(query);
							if(price > 0) {
								String cant = JOptionPane.showInputDialog("Ingrese La cantidad que desea comprar");
								if(cant != null) {
									query = String.format("select * from shoppingCart where nameProduct = '%s'",nameProduct);
									String auxNameProduct = connect.getNameProduct(query);
									if(auxNameProduct == null) {
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
									else {
										 query = String.format("select * from shoppingCart where nameProduct = '%s'",nameProduct);
										 int auxCant = connect.getCant(query);
										 auxCant += Integer.parseInt(cant);
										 int priceFinal = auxCant*price;
										 query = String.format("update shoppingCart set quantityPurchased = %s where nameProduct = '%s'",auxCant,nameProduct);
										 try {
											connect.UpdateData(query);
										 } catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										 }
										 query = String.format("update shoppingCart set finalPrice = %s where nameProduct = '%s'",priceFinal,nameProduct);
										 try {
											connect.UpdateData(query);
										} catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
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
						JOptionPane.showMessageDialog(null,"Seleccione una linea que si contenga caracteres");
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
		
		JComboBox Filter = new JComboBox();
		Filter.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Filter.setBounds(10, 398, 237, 45);
		fillCategories(Filter);
		contentPane.add(Filter);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String category = (String) Filter.getSelectedItem();
				if(!category.equals("ALL")) {
					String query = String.format("select * from product where category = '%s'",category);
					try {
						l = connect.findProduct(query);
						if(l.isEmpty()) {
							JOptionPane.showMessageDialog(null,"No Existen productos de esa la categoria = "+category);
							String [][] s = new String[(l.size()/3)][3];
							table.setModel(new DefaultTableModel(s,
								new String[] {
									"Producto", "Precio", "Quedan"
								}
							));
						}
						else {
							String [][] s = new String[(l.size()/3)][3];
							fillerTable(l,s);
							DefaultTableModel model =new DefaultTableModel(s,new String[] {"Producto", "precio", "Quedan"}) {
								@Override
								public boolean isCellEditable(int rowIndex,int rowColumn) {
									return false;
								}
							};
							table.setModel(model);
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					contentPane.add(table);
				}
				else {
					String query = String.format("select * from product");
					try {
						l = connect.findProduct(query);
						if(l.isEmpty()) {
							JOptionPane.showMessageDialog(null,"No existen Productos");
							String [][] s = new String[(l.size()/3)][3];
							table.setModel(new DefaultTableModel(s,
								new String[] {
									"Producto", "Precio", "Quedan"
								}
							));
						}
						else {
							String [][] s = new String[(l.size()/3)][3];
							fillerTable(l,s);
							DefaultTableModel model =new DefaultTableModel(s,new String[] {"Producto", "precio", "Quedan"}) {
								@Override
								public boolean isCellEditable(int rowIndex,int rowColumn) {
									return false;
								}
							};
							table.setModel(model);
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					contentPane.add(table);
				}
				
			}
		});
		btnFiltrar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnFiltrar.setBounds(10, 461, 237, 45);
		contentPane.add(btnFiltrar);
		
		JLabel lblNewLabel_1 = new JLabel("Buscar Por Nombre");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(320, 11, 200, 37);
		contentPane.add(lblNewLabel_1);
		
		enterFilterName = new JTextField();
		enterFilterName.setBounds(307, 58, 225, 38);
		contentPane.add(enterFilterName);
		enterFilterName.setColumns(10);
		
		JButton buttonSearch = new JButton("Buscar");
		buttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameProduct = enterFilterName.getText();
				if(nameProduct != null) {
					nameProduct = nameProduct.toLowerCase();
					String query = String.format("select * from product where nameProduct = '%s'",nameProduct);
					try {
						String existe = connect.getNameProduct(query);
						if(existe != null) {
							query = String.format("select * from product where nameProduct = '%s'",nameProduct);
							l = connect.findProduct(query);
							String [][] s = new String[(l.size()/3)][3];
							fillerTable(l,s);
							DefaultTableModel model =new DefaultTableModel(s,new String[] {"Producto", "precio", "Quedan"}) {
								@Override
								public boolean isCellEditable(int rowIndex,int rowColumn) {
									return false;
								}
							};
							table.setModel(model);
						}
						else {
							JOptionPane.showMessageDialog(null,"Ese producto no existe");
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"buscqueda en vacio porfavor ingrese el nombre del producto a buscar");
				}
			}
		});
		buttonSearch.setFont(new Font("Tahoma", Font.PLAIN, 25));
		buttonSearch.setBounds(557, 58, 127, 38);
		contentPane.add(buttonSearch);
	}
	
	private void fillCategories(JComboBox Filter) {
		ArrayList<String> l2 = new ArrayList<>();
		String query = String.format("select * from categoryProduct");
		Filter.addItem("ALL");
		try {
			l2 = connect.findCategory(query);
			if(l2.isEmpty()) {
				JOptionPane.showMessageDialog(null,"No hay categorias disponibles");
			}
			else {
				for(int i=0;i<l2.size();i++) {
					Filter.addItem(l2.get(i));
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void fillerTable(ArrayList<String> l2, String[][] s) {
		int k= 0;
		int cant = 0;
		for(int i=0;i<(l2.size()/3);i++) {
			for(int j=0;j<3;j++) {
				if(Integer.parseInt(l2.get((i*3)+2)) != 0) {
					s[cant][j] = l2.get(k);
					k++;
				}
				else {
					k += 3;
					cant --;
					break;
						
				}				
				
				
			}
			cant++;
		}
		
	}
}
