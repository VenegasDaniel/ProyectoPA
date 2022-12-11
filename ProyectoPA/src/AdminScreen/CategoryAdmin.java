package AdminScreen;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ConnectionDataBase.ConnectionDB;


import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class CategoryAdmin extends JFrame {

	private JPanel contentPane;
	private static ConnectionDB connect;
	private JTable table;
	private ArrayList<String> l;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategoryAdmin frame = new CategoryAdmin(connect);
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
	public CategoryAdmin(ConnectionDB connect) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 497, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		table = new JTable();
		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(21, 39, 191, 295);
		
		String query = String.format("select * from categoryProduct");
		try {
			l = connect.findCategory(query);
			if(l.isEmpty()) {
				JOptionPane.showMessageDialog(null,"No existen Categorias");
				String [][] s = new String[(l.size())][1];
				
				table.setModel(new DefaultTableModel(s,
					new String[] {
						"category"
					}
				));
			}
			else {
				String [][] s = new String[(l.size())][1];
				rellenarTabla(l,s);
				DefaultTableModel model =new DefaultTableModel(s,new String[] {"category"}) {
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
		
		
		
		
		JLabel lblNewLabel = new JLabel("Categorias");
		lblNewLabel.setBounds(235, 40, 125, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		JButton buttonEditProduct = new JButton("Editar categoria");
		buttonEditProduct.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonEditProduct.setBounds(219, 93, 157, 45);
		buttonEditProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row >= 0) {
					String nameCategory = (String) table.getValueAt(row, 0);
					String newCategory = JOptionPane.showInputDialog("Ingrese el nuevo nombre para la categoria "+nameCategory);
					if(newCategory != null) {
						newCategory = newCategory.toLowerCase();
						String query = String.format("select * from product where category= '%s' limit 1",nameCategory);
						try {
							String category = connect.getCategory(query);
							query = String.format("select * from categoryProduct where category= '%s'",newCategory);
							String category2 = connect.getCategory(query);
							if(category2 == null) {
								if(category != null) {
									query = String.format("insert into categoryProduct values ('%s')",newCategory);
									try {
										connect.insertData(query);
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									query = String.format("update product set category = '%s' where category = '%s'",newCategory,nameCategory);
									try {
										connect.UpdateData(query);
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									query = String.format("delete from categoryProduct where category = '%s'",nameCategory);
									try {
										connect.DeleteData(query);;
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									CategoryAdmin.this.dispose();
			                        JOptionPane.showMessageDialog(null,"Categoria cambiada,Se refrescara la pestaña");
			                        CategoryAdmin ca = new CategoryAdmin(connect);
			                        ca.setVisible(true);
								}
								else {
									query = String.format("update categoryProduct set category = '%s' where category = '%s'", newCategory,nameCategory);
									try {
										connect.UpdateData(query);
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									CategoryAdmin.this.dispose();
			                        JOptionPane.showMessageDialog(null,"Categoria cambiada,Se refrescara la pestaña");
			                        CategoryAdmin ca = new CategoryAdmin(connect);
			                        ca.setVisible(true);
								}
							}
							else {
								JOptionPane.showMessageDialog(null,"La categoria ingresada ya existe");
							}
					
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"La categoria a ingresar debe ser distinta de null");
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null,"Debe seleccionar la casilla que desea ver");
				}
			}
		});
		contentPane.add(buttonEditProduct);
		
		JButton buttonAddCategory = new JButton("Añadir Categoria");
		buttonAddCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonAddCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newCategory = JOptionPane.showInputDialog("Ingrese el nuevo nombre para la categoria");
				newCategory = newCategory.toLowerCase();
				if(newCategory != null) {
					String query = String.format("select * from categoryProduct where category= '%s'",newCategory);
					try {
						String validacion = connect.getCategory(query);
						if(validacion == null) {
							query = String.format("insert into categoryProduct values ('%s')",newCategory);
							try {
								connect.insertData(query);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							CategoryAdmin.this.dispose();
				            JOptionPane.showMessageDialog(null,"Categoria Añadida,Se refrescara la pestaña");
				            CategoryAdmin ca = new CategoryAdmin(connect);
				            ca.setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog(null,"La categoria ya existe");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"Debe Ingresar algo distinto de vacio");
				}

			}
		});
		buttonAddCategory.setBounds(219, 169, 157, 45);
		contentPane.add(buttonAddCategory);
		
		JButton buttonAddCategory_1 = new JButton("Eliminar Categoria");
		buttonAddCategory_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row >= 0) {
					String nameCategory = (String) table.getValueAt(row, 0);
					String query = String.format("select * from product where category= '%s' limit 1",nameCategory);
					try {
						String validacion = connect.getCategory(query);
						if(validacion == null) {
							query = String.format("delete from categoryProduct where category = '%s'",nameCategory);
							try {
								connect.DeleteData(query);;
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							CategoryAdmin.this.dispose();
				            JOptionPane.showMessageDialog(null,"Categoria eliminada con exito,Se refrescara la pestaña");
				            CategoryAdmin ca = new CategoryAdmin(connect);
				            ca.setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog(null,"Es imposible eliminar la categoria ya que hay productos que estan utilizando esta categoria \n"
									+ "porfavor cambie la categoria de los poductos donde la categoria sea igual a la que desea borrar");
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
		buttonAddCategory_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonAddCategory_1.setBounds(220, 245, 157, 45);
		contentPane.add(buttonAddCategory_1);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminScreen as = new AdminScreen(connect);
				as.setVisible(true);
				CategoryAdmin.this.dispose();
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalir.setBounds(364, 10, 119, 34);
		contentPane.add(btnSalir);
		
	}
	
	private void rellenarTabla(ArrayList<String> l2, String[][] s) {
		int k= 0;
		for(int i=0;i<(l2.size());i++) {
			for(int j=0;j<1;j++) {
				if(l2.get(j) != null) {
					s[i][j] = l2.get(k);
					k++;
				}				
			}
		}		
	}
}
