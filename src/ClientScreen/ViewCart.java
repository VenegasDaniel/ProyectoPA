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
import Dominio.Mail;
import Dominio.Person;

import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.mail.MessagingException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ListSelectionModel;

public class ViewCart extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static ConnectionDB connect;
	private static Person p;
	private ArrayList<String> l = null;
	private String [][] s;
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
		setBounds(100, 100, 588, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		table = new JTable();
		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(28, 70, 415, 321);
		
		
		String query = String.format("select * from shoppingCart where rutPerson = '%s'",p.getRut());
		try {
			l = connect.findProductShoppingCart(query);
			if(l.isEmpty()) {
				JOptionPane.showMessageDialog(null,"No existen Productos para mostrar");
				s = new String[(l.size()/3)][3];
				table.setModel(new DefaultTableModel(s,
					new String[] {
						"Producto", "Cantidad Comprado", "Precio Final"
					}
				));
			}
			else {
				s = new String[(l.size()/3)][3];
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
		contentPane.setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("Producto");
		lblNewLabel.setBounds(61, 43, 101, 17);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel);
		
		JLabel lblCantidadAComprar = new JLabel("Cantidad A Comprar");
		lblCantidadAComprar.setBounds(172, 43, 141, 17);
		lblCantidadAComprar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblCantidadAComprar);
		
		JLabel lblPreciofinal = new JLabel("PrecioFinal");
		lblPreciofinal.setBounds(323, 43, 101, 17);
		lblPreciofinal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblPreciofinal);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientScreen cs = new ClientScreen(p,connect);
				cs.setVisible(true);
				ViewCart.this.dispose();
			}
		});
		btnNewButton.setBounds(444, 24, 120, 43);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		contentPane.add(btnNewButton);
		
		JButton btnPagarcarrito = new JButton("PagarCarrito");
		btnPagarcarrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int finalPrice = finalPrice(s);
				String text = JOptionPane.showInputDialog("El valor Final a pagar por el carrito es de "+ finalPrice +" \n [1]Si [2]No");
				if(text.equals("1")) {
					Boolean condition = deleteStock(s);
					if(condition == true) {
						JOptionPane.showMessageDialog(null,"Se ha realizado la compra");
						String query = String.format("delete from shoppingCart where rutPerson = '%s'",p.getRut());
						try {
							connect.DeleteData(query);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						String message = messageClient(s);
						try {
							Mail.sendMail(p.getMail(),message);
							JOptionPane.showMessageDialog(null,"Factura Enviada con exito a su correo");
						} catch (MessagingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						enterHistoryData(s);
						ClientScreen cs = new ClientScreen(p,connect);
						cs.setVisible(true);
						ViewCart.this.dispose();
					}
					else {
						JOptionPane.showMessageDialog(null,"Disminuya la cantidad de productos que desea comprar ya que \n "
								+ "la cantidad que intenta comprar sobrepasa el stock");
					}
					
				}
				else if(text.equals("2")) {
					JOptionPane.showMessageDialog(null,"No se ha realizado ninguna transaccion");
				}
				else {
					JOptionPane.showMessageDialog(null,"Debe Ingresar correctamente [1] si es si o [2] si es no");
				}
			}

		});
		btnPagarcarrito.setBounds(310, 435, 213, 43);
		btnPagarcarrito.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnPagarcarrito);
		
		JButton btnEliminarDeCarrito = new JButton("Eliminar De Carrito");
		btnEliminarDeCarrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row >= 0) {
					String nameProduct = (String) table.getValueAt(row, 0);
					try {
						String query = String.format("delete from shoppingCart where nameProduct= '%s'",nameProduct);
						connect.DeleteData(query);
						ViewCart.this.dispose();
						JOptionPane.showMessageDialog(null,"Producto Eliminado,Se refrescara la pestañana");
						ViewCart vc = new ViewCart(p,connect);
						vc.setVisible(true);
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
		btnEliminarDeCarrito.setBounds(43, 435, 229, 43);
		btnEliminarDeCarrito.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnEliminarDeCarrito);
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
	
	private int finalPrice(String[][] s) {
		int i;
		int sum = 0;
		for(i=0;i<(s.length);i++) {
			sum += Integer.parseInt(s[i][2]);
		}
		return sum;
	}
	
	private Boolean deleteStock(String[][] s) {
		for(int i=0;i<(s.length);i++) {
			String nameProduct =s[i][0];
			int cantBuyProduct = Integer.parseInt(s[i][1]);
			String query = String.format("select * from product where nameProduct = '%s'",nameProduct);
			try {
				int cantProduct = connect.getStock(query);
				if(cantProduct >= cantBuyProduct) {
					int newCant = cantProduct - cantBuyProduct;
					query = String.format("update product set stock = %s where nameProduct = '%s'",newCant,nameProduct);
					try {
						connect.UpdateData(query);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null,"El stock del producto es menor al que usted desea comprar");
					return false;
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
		
		
	}
	
	private void enterHistoryData(String[][] s) {
		for(int i=0;i<(s.length);i++) {
			String nameProduct =s[i][0];
			int cantBuyProduct = Integer.parseInt(s[i][1]);
			int finalPrice = Integer.parseInt(s[i][2]);
			String query = String.format("insert into purchasePersonProduct values('%s','%s','%s','%s')",p.getRut(),nameProduct,cantBuyProduct,finalPrice);
			try {
				connect.insertData(query);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private String messageClient(String[][] s) {
		String message = "Items \n";
		message += "------------------------------------------------------------------- \n";
		for(int i=0;i<(s.length);i++) {
			String nameProduct =s[i][0];
			int cantBuyProduct = Integer.parseInt(s[i][1]);
			int finalPrice = Integer.parseInt(s[i][2]);
			message += "Producto Comprado = "+nameProduct+"\n";
			message += "Cantidad Comprado ="+cantBuyProduct+"\n";
			message += "Precio Producto Comprado"+finalPrice;
			message += "\n";
			message += "------------------------------------------------------------------- \n";
		}
		int price = finalPrice(s);
		message += "------------------------------------------------------------------- \n";
		message += "El precio final pagado fue de = "+String.valueOf(price);
		return message;
	}
}
