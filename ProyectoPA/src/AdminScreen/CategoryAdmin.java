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
		setBounds(100, 100, 500, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		
		String query = String.format("select * from category");
		try {
			l = connect.findProduct(query);
			if(l.isEmpty()) {
				JOptionPane.showMessageDialog(null,"No existen Categorias");
				String [][] s = new String[(l.size())][1];
				table = new JTable();
				table.setBounds(23, 124, 191, 295);
				table.setModel(new DefaultTableModel(s,
					new String[] {
						"category"
					}
				));
			}
			else {
				String [][] s = new String[(l.size())][1];
				rellenarTabla(l,s);
				table = new JTable();
				table.setBounds(23, 124, 191, 295);
				table.setModel(new DefaultTableModel(s,
					new String[] {
							"category"
					}
				));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPane.add(table);
		contentPane.setLayout(null);
		
		
		
		
		JLabel lblNewLabel = new JLabel("Categorias");
		lblNewLabel.setBounds(52, 63, 125, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		table = new JTable();
		table.setBounds(23, 124, 191, 295);
		contentPane.add(table);
		
		JButton buttonEditProduct = new JButton("Editar categoria");
		buttonEditProduct.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonEditProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row >= 0) {
					String nameCategory = (String) table.getValueAt(row, 0);
					String query = String.format("select * from category where category= '%s'",nameCategory);
					ArrayList<String> l;
					try {
						l = connect.findProductAll(query);
						String nameNew = JOptionPane.showInputDialog("Ingrese el nuevo nombre");
						
						//update 
						query = String.format("update product set category = '%s' where category = '%s'", nameNew, nameCategory);
						CategoryAdmin.this.dispose();
                        JOptionPane.showMessageDialog(null,"Categoria cambiada,Se refrescara la pestañana");
                        CategoryAdmin ca = new CategoryAdmin(connect);
                        ca.setVisible(true);
						
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
		
		JButton buttonAddCategory = new JButton("Añadir Categoria");
		buttonAddCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonAddCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row >= 0) {
					String nameCategory = JOptionPane.showInputDialog("Ingrese la nueva categoria");
					String response;
					String query = String.format("select * from category where category= '%s'",nameCategory);
					ArrayList<String> l;
					try {
						
						response = connect.getNameCategory(query);
						if(response == null) {																		
							query = String.format("insert into category values('%s')",nameCategory);
									
							try {
								connect.insertData(query);
							} catch (Exception e1) {
										// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							CategoryAdmin.this.dispose();
	                        JOptionPane.showMessageDialog(null,"Categoria Añadida,Se refrescara la pestañana");
	                        CategoryAdmin ca = new CategoryAdmin(connect);
	                        ca.setVisible(true);
															
						}
						else {
							JOptionPane.showMessageDialog(null,"Categoria ya existente");
						}
						
						
						
						
						
						
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
		buttonAddCategory.setBounds(681, 244, 157, 45);
		contentPane.add(buttonAddCategory);
		
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
