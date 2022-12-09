package AdminScreen;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ConnectionDataBase.ConnectionDB;

import javax.swing.JButton;
import javax.swing.JTable;

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
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(592, 31, 127, 84);
		contentPane.add(btnNewButton);
		
		table = new JTable();
		table.setBounds(82, 437, 396, -295);
		contentPane.add(table);
		
		String query = String.format("select nameProduct,quantityPurchased,finalPrice from purchasePersonProduct where rutPerson = '%s'");
		try {
			l = connect.findHistory(query);
			if(l.isEmpty()) {
				JOptionPane.showMessageDialog(null,"No Has realizado Compras");
				String [][] s = new String[(l.size()/3)][3];
				table = new JTable();
				table.setBounds(69, 154, 444, 270);
				table.setModel(new DefaultTableModel(s,
					new String[] {
						"Producto", "Cantidad Comprado", "Precio Pagado"
					}
				));
			}
			else {
				String [][] s = new String[(l.size()/3)][3];
				rellenarTabla(l,s);
				table = new JTable();
				table.setBounds(69, 154, 444, 270);
				table.setModel(new DefaultTableModel(s,
					new String[] {
						"Producto", "Cantidad Comprado", "Precio Pagado"
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
