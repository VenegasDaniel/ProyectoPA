package AdminScreen;

import java.awt.BorderLayout;
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
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ClientScreen.EditDataScreen;
import ConnectionDataBase.ConnectionDB;
import Dominio.Factory;
import Dominio.Person;

public class UserAdmin extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static ConnectionDB connect;
	private ArrayList<String> l = null;
	private String [][] s;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserAdmin frame = new UserAdmin(connect);
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
	public UserAdmin(ConnectionDB connect) {
		UserAdmin.connect = connect;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setBounds(48, 150, 396, 295);
		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		String query = String.format("select * from person");
		try {
			l = connect.findRutName(query);
			if(l.isEmpty()) {
				JOptionPane.showMessageDialog(null,"No Has realizado Compras");
				s = new String[(l.size()/2)][2];
				table.setModel(new DefaultTableModel(s,
					new String[] {
							"rut", "nombre"
					}
				));
			}
			else {
				s = new String[(l.size()/2)][2];
				fillerTable(l,s);
				DefaultTableModel model =new DefaultTableModel(s,new String[] {"rut", "nombre"}) {
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
		contentPane.setLayout(null);
		contentPane.add(table);
		
		
		JLabel lblNewLabel = new JLabel("Rut");
		lblNewLabel.setBounds(82, 90, 142, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuarios");
		lblNewLabel_1.setBounds(495, 151, 83, 25);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblPrecio = new JLabel("Nombre");
		lblPrecio.setBounds(269, 90, 142, 25);
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblPrecio);
		
		JButton btnNewButton = new JButton("Eliminar");
		btnNewButton.setBounds(470, 313, 145, 45);
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
                        UserAdmin.this.dispose();
                        JOptionPane.showMessageDialog(null,"Persona Eliminada,Se refrescara la pestaña");
                        UserAdmin ua = new UserAdmin(connect);
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
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnNewButton);
		
		JButton btnNewButton1 = new JButton("Editar");
		btnNewButton1.setBounds(470, 385, 145, 45);
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row >= 0) {
					String rut = (String) table.getValueAt(row, 0);
					String query = String.format("select * from person where rut = '%s'",rut);
					ArrayList<String> l;
					try {
						l = connect.findUser(query);
						Person p = Factory.factory( l.get(0),l.get(1),l.get(2),l.get(3), l.get(4),  l.get(5),  l.get(6),  l.get(7),  l.get(8),  l.get(9),  l.get(10));
						EditDataPersonScreen edps = new EditDataPersonScreen(p,connect);
						edps.setVisible(true);
						UserAdmin.this.dispose();
						
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
		btnNewButton1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnNewButton1);
		
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminScreen as = new AdminScreen(connect);
				as.setVisible(true);
				UserAdmin.this.dispose();
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnSalir.setBounds(484, 36, 119, 37);
		contentPane.add(btnSalir);
		
		JButton ButtonBlock = new JButton("Bloquear");
		ButtonBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
                if(row >= 0) {
                    String rut = (String) table.getValueAt(row, 0);
                    try {
                    	String query = String.format("update person set statePerson = true where rut = '%s'",rut);
                        connect.UpdateData(query);;
                        UserAdmin.this.dispose();
                        JOptionPane.showMessageDialog(null,"Persona Bloqueada con existo,Se refrescara la pestaña");
                        UserAdmin ua = new UserAdmin(connect);
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
		ButtonBlock.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ButtonBlock.setBounds(470, 186, 145, 45);
		contentPane.add(ButtonBlock);
		
		JButton buttonUnlock = new JButton("Desbloquear");
		buttonUnlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
                if(row >= 0) {
                    String rut = (String) table.getValueAt(row, 0);
                    try {
                    	String query = String.format("update person set statePerson = false where rut = '%s'",rut);
                        connect.UpdateData(query);;
                        UserAdmin.this.dispose();
                        JOptionPane.showMessageDialog(null,"Persona Desbloqueada con existo,Se refrescara la pestaña");
                        UserAdmin ua = new UserAdmin(connect);
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
		buttonUnlock.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonUnlock.setBounds(470, 247, 145, 45);
		contentPane.add(buttonUnlock);
		
	}
	
	private void fillerTable(ArrayList<String> l2, String[][] s) {
		int k= 0;
		for(int i=0;i<(l2.size()/2);i++) {
			for(int j=0;j<2;j++) {
				s[i][j] = l2.get(k);
				k++;
			}
		}
		
	}
}
