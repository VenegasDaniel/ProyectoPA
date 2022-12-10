package AdminScreen;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import ClientScreen.EditDataScreen;
import ConnectionDataBase.ConnectionDB;
import Dominio.Factory;
import Dominio.Person;
import Dominio.Product;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UsersAdmin extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static ConnectionDB connect;
	private ArrayList<String> l = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsersAdmin frame = new UsersAdmin(connect);
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
	public UsersAdmin(ConnectionDB connect) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 791, 529);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Rut");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(82, 90, 142, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuarios");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(236, 31, 83, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblPrecio = new JLabel("Nombre");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrecio.setBounds(269, 90, 142, 25);
		contentPane.add(lblPrecio);
		
		JButton btnNewButton = new JButton("Eliminar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
                if(row >= 0) {
                    String rut = (String) table.getValueAt(row, 0);
                    try {
                    	String query = String.format("delete from shoppingCart where rutPerson= '%s'",rut);
                        connect.DeleteData(query);
                        query = String.format("delete from reviews where rutPerson= '%s'",rut);
                        connect.DeleteData(query);
                        query = String.format("delete from purchasePersonProduct where rutPerson= '%s'",rut);
                        connect.DeleteData(query);
                        query = String.format("delete from person where rut= '%s'",rut);
                        connect.DeleteData(query);
                        UsersAdmin.this.dispose();
                        JOptionPane.showMessageDialog(null,"Persona Eliminada,Se refrescara la pestañana");
                        UsersAdmin ua = new UsersAdmin(connect);
                        ua.setVisible(true);
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
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(576, 133, 127, 45);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton1 = new JButton("Editar");
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row >= 0) {
					String nameUser = (String) table.getValueAt(row, 0);
					String query = String.format("select * from usuario where nameProduct= '%s'",nameUser);
					ArrayList<String> l;
					try {
						l = connect.findUser(query);
						Person p = Factory.factory( l.get(0),l.get(1),l.get(2),l.get(3), l.get(4),  l.get(5),  l.get(6),  l.get(7),  l.get(8),  l.get(9),  l.get(10));
						EditDataScreen eds = new EditDataScreen(p,connect);
						eds.setVisible(true);
						UsersAdmin.this.dispose();
						
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
		btnNewButton1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton1.setBounds(576, 198, 127, 45);
		contentPane.add(btnNewButton1);
		
		
		
		String query = String.format("select rut,namePerson from person");
		try {
			l = connect.findHistory(query);
			if(l.isEmpty()) {
				JOptionPane.showMessageDialog(null,"No Has realizado Compras");
				String [][] s = new String[(l.size()/2)][2];
				table = new JTable();
				table.setBounds(82, 437, 396, -295);
				table.setModel(new DefaultTableModel(s,
					new String[] {
						"rut", "nombre"
					}
				));
			}
			else {
				String [][] s = new String[(l.size()/2)][2];
				rellenarTabla(l,s);
				table = new JTable();
				table.setBounds(82, 437, 396, -295);
				table.setModel(new DefaultTableModel(s,
					new String[] {
						"rut", "nombre"
					}
				));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		contentPane.add(table);
		
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
