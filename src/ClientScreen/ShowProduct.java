package ClientScreen;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ConnectionDataBase.ConnectionDB;
import Dominio.Person;

import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowProduct extends JFrame {

	private JPanel contentPane;
	private static ConnectionDB connect;
	private static String imagePath;
	private JTextField Description;
	private JTable table;
	private JButton btnNewButton;
	private static Person p;
	private ArrayList<String> l = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowProduct frame = new ShowProduct(p,connect,imagePath);
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
	public ShowProduct(Person p,ConnectionDB connect,String imagePath) {
		ShowProduct.connect = connect;
		ShowProduct.imagePath = imagePath;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 951, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel ImageLabel = new JLabel("");
		ImageLabel.setBounds(55, 50, 417, 276);
		ImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(ImageLabel);
		
		Image image = new ImageIcon(imagePath).getImage();
		ImageIcon iImagen = new ImageIcon(image.getScaledInstance(ImageLabel.getWidth(), ImageLabel.getHeight(), Image.SCALE_SMOOTH));
		ImageLabel.setIcon(iImagen);
	
		Description = new JTextField();
		Description.setEnabled(true);
		Description.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Description.setBounds(55, 348, 417, 160);
		
		String query = String.format("select * from product where image = '%s'",imagePath);
		try {
			String description = connect.getDescriptionProduct(query);
			Description.setText(description);
			Description.setEnabled(false);
			contentPane.add(Description);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		
		btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientScreen cs =  new ClientScreen(p,connect);
				cs.setVisible(true);
				ShowProduct.this.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton.setBounds(723, 26, 186, 40);
		contentPane.add(btnNewButton);
		
		query = String.format("select * from product where image = '%s'",imagePath);
		try {
			String nameProduct = connect.getNameProduct(query);
			try {
				query = String.format("select * from reviews where nameProduct = '%s'",nameProduct);
				l = connect.findReviews(query);
				if(l.isEmpty()) {
					JOptionPane.showMessageDialog(null,"No existen Productos");
					String [][] s = new String[(l.size()/2)][2];
					table = new JTable();
					table.setBounds(555, 110, 372, 398);
					table.setModel(new DefaultTableModel(s,
						new String[] {
							"rutPerson", "Review"
						}
					));
				}
				else {
					String [][] s = new String[(l.size()/2)][2];
					rellenarTabla(l,s);
					table = new JTable();
					table.setBounds(555, 110, 372, 398);
					table.setModel(new DefaultTableModel(s,
						new String[] {
								"rutPerson", "Review"
						}
					));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		contentPane.add(table);
		
		JLabel lblNewLabel = new JLabel("Rut Person");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(640, 93, 93, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblReview = new JLabel("Review");
		lblReview.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblReview.setBounds(813, 93, 93, 13);
		contentPane.add(lblReview);
		
	}
	
	private void rellenarTabla(ArrayList<String> l2, String[][] s) {
		int k= 0;
		for(int i=0;i<(l2.size()/2);i++) {
			for(int j=0;j<2;j++) {
				if(l2.get(j) != null) {
					s[i][j] = l2.get(k);
					k++;
				}
				
			}
		}
		
	}
}
