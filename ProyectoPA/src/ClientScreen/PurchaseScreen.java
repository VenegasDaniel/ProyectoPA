package ClientScreen;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import ConnectionDataBase.ConnectionDB;
import Dominio.Person;
import Dominio.Product;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PurchaseScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	private static Person p;
	private static ConnectionDB connect;
	private JTable table;
	private ArrayList<String> l = null;
	
	DefaultTableModel model = new DefaultTableModel();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PurchaseScreen frame = new PurchaseScreen(p,connect);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public PurchaseScreen(Person p,ConnectionDB connect){
		PurchaseScreen.p = p;
		PurchaseScreen.connect = connect;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Historial");
		lblNewLabel.setBounds(41, 29, 45, 13);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Historial");
		lblNewLabel_1.setBounds(116, 22, 142, 68);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		contentPane.add(lblNewLabel_1);
		
		table = new JTable();
		table.setBounds(69, 154, 444, 270);
		
		String query = String.format("select nameProduct,quantityPurchased,finalPrice from purchasePersonProduct where rutPerson = '%s'",p.getRut());
		try {
			l = connect.findHistory(query);
			if(l.isEmpty()) {
				JOptionPane.showMessageDialog(null,"No Has realizado Compras");
				String [][] s = new String[(l.size()/3)][3];
				table.setModel(new DefaultTableModel(s,
					new String[] {
						"Producto", "Cantidad Comprado", "Precio Pagado"
					}
				));
			}
			else {
				String [][] s = new String[(l.size()/3)][3];
				fillerTable(l,s);
				DefaultTableModel model =new DefaultTableModel(s,new String[] {"Producto", "Cantidad Comprado", "Precio Final"}) {
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
		
		JLabel lblNewLabel_2 = new JLabel("Producto");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(95, 128, 83, 25);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Cantidad Comprado");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(215, 128, 152, 25);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Precio Pagado");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_2.setBounds(377, 128, 142, 25);
		contentPane.add(lblNewLabel_2_2);
		
		JLabel ImageLabel = new JLabel("");
		ImageLabel.setBounds(590, 98, 336, 317);
		ImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(ImageLabel);
		
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfileScreen ps = new ProfileScreen(p,connect);
				ps.setVisible(true);
				PurchaseScreen.this.dispose();	
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton.setBounds(748, 17, 158, 36);
		contentPane.add(btnNewButton);
		
		JButton btnMostrarFotoProducto = new JButton("Mostrar Foto Producto");
		btnMostrarFotoProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row >= 0) {
					String nameProduct = (String) table.getValueAt(row, 0);
					String query = String.format("select * from product where nameProduct= '%s'",nameProduct);
					String imagePath;
					try {
						imagePath = connect.findImageProduct(query);
						Image image = new ImageIcon(imagePath).getImage();
						ImageIcon iImagen = new ImageIcon(image.getScaledInstance(ImageLabel.getWidth(), ImageLabel.getHeight(), Image.SCALE_SMOOTH));
						ImageLabel.setIcon(iImagen);
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
		btnMostrarFotoProducto.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMostrarFotoProducto.setBounds(627, 440, 279, 36);
		contentPane.add(btnMostrarFotoProducto);
		
		
		
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
