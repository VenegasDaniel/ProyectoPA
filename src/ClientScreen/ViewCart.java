package ClientScreen;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ConnectionDataBase.ConnectionDB;
import Dominio.Person;

import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class ViewCart extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static ConnectionDB connect;
	private static Person p;
	private ArrayList<String> l = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCart frame = new ViewCart(p,connect);
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
	public ViewCart(Person p,ConnectionDB connect) {
		ViewCart.p = p;
		ViewCart.connect = connect;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 513, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		String query = String.format("select * from shoppingCart where rutPerson = '%s'",p.getRut());
		try {
			l = connect.findProductShoppingCart(query);
			if(l.isEmpty()) {
				JOptionPane.showMessageDialog(null,"No existen Productos");
				String [][] s = new String[(l.size()/3)][3];
				table = new JTable();
				table.setBounds(28, 70, 415, 321);
				table.setModel(new DefaultTableModel(s,
					new String[] {
						"Producto", "Cantidad Comprado", "Precio Final"
					}
				));
			}
			else {
				String [][] s = new String[(l.size()/3)][3];
				rellenarTabla(l,s);
				table = new JTable();
				table.setBounds(28, 70, 415, 321);
				table.setModel(new DefaultTableModel(s,
					new String[] {
							"Producto", "Cantidad Comprado", "Precio Final"
					}
				));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPane.add(table);
		
		JLabel lblNewLabel = new JLabel("Producto");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(61, 43, 101, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblCantidadAComprar = new JLabel("Cantidad A Comprar");
		lblCantidadAComprar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCantidadAComprar.setBounds(172, 43, 141, 17);
		contentPane.add(lblCantidadAComprar);
		
		JLabel lblPreciofinal = new JLabel("PrecioFinal");
		lblPreciofinal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPreciofinal.setBounds(323, 43, 101, 17);
		contentPane.add(lblPreciofinal);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton.setBounds(323, 425, 120, 43);
		contentPane.add(btnNewButton);
		
		JButton btnPagarcarrito = new JButton("PagarCarrito");
		btnPagarcarrito.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnPagarcarrito.setBounds(33, 425, 213, 43);
		contentPane.add(btnPagarcarrito);
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
