package AdminScreen;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ConnectionDataBase.ConnectionDB;
import javax.swing.JTextField;
import java.awt.Font;

public class AddProduct extends JFrame {

	private JPanel contentPane;
	private static ConnectionDB connect;
	private static String nameProduct;
	private JTextField enterCategory;
	private JTextField enterDescription;
	private JTextField enterPrice;
	private JTextField enterStock;
	private JTextField enterNameProduct;
	private String path;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProduct frame = new AddProduct(connect,nameProduct);
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
	public AddProduct(ConnectionDB connect,String nameProduct) {
		AddProduct.connect = connect;
		AddProduct.nameProduct = nameProduct;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 854, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel ImageLabel = new JLabel("");
		ImageLabel.setBounds(451, 150, 379, 321);
		ImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(ImageLabel);
		
		JButton uploadPhotoButton = new JButton("Subir Foto");
		uploadPhotoButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		uploadPhotoButton.setBounds(543, 494, 201, 59);
		uploadPhotoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf = new JFileChooser();
				jf.showOpenDialog(null);
				File f = jf.getSelectedFile();
				if(f != null) {
					String filename = f.getAbsolutePath();
					String nombre = jf.getSelectedFile().getName();
					String newPath = "Media/Producto/";
					File directory = new File(newPath);
					if(!directory.exists()) {
						directory.mkdir();
					}
					File sourceFile = null;
					File destinationFile = null;
					sourceFile = new File(filename);
					destinationFile = new File(newPath+nombre);
					try {
						Files.copy(sourceFile.toPath(),destinationFile.toPath());
						Image imagen = new ImageIcon(filename).getImage();
						ImageIcon iImagen = new ImageIcon(imagen.getScaledInstance(ImageLabel.getWidth(), ImageLabel.getHeight(), Image.SCALE_SMOOTH));
						ImageLabel.setIcon(iImagen);
						path = destinationFile.getAbsolutePath();
						String query = String.format("update product set image = '%s' where nameProduct= '%s' ",destinationFile.getAbsolutePath(),nameProduct);
						try {
							connect.UpdateData(query);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null,"Imagen ya existente con ese nombre,porfavor cambielo");
						
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"No ha seleccionado ningun elemento");
				}
				
			}
			
		});
		contentPane.add(uploadPhotoButton);
		
		enterCategory = new JTextField();
		enterCategory.setFont(new Font("Tahoma", Font.PLAIN, 20));
		enterCategory.setBounds(60, 80, 325, 45);
		contentPane.add(enterCategory);
		enterCategory.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Categoria");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(138, 35, 168, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descripcion");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(138, 135, 168, 38);
		contentPane.add(lblNewLabel_1);
		
		enterDescription = new JTextField();
		enterDescription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		enterDescription.setColumns(10);
		enterDescription.setBounds(60, 183, 325, 98);
		contentPane.add(enterDescription);
		
		enterPrice = new JTextField();
		enterPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		enterPrice.setColumns(10);
		enterPrice.setBounds(60, 339, 325, 45);
		contentPane.add(enterPrice);
		
		JLabel lblNewLabel_1_1 = new JLabel("Precio");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1_1.setBounds(168, 291, 168, 38);
		contentPane.add(lblNewLabel_1_1);
		
		enterStock = new JTextField();
		enterStock.setFont(new Font("Tahoma", Font.PLAIN, 20));
		enterStock.setColumns(10);
		enterStock.setBounds(60, 427, 325, 44);
		contentPane.add(enterStock);
		
		JLabel lblNewLabel_1_2 = new JLabel("Stock");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1_2.setBounds(168, 382, 168, 38);
		contentPane.add(lblNewLabel_1_2);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProduct.this.dispose();
				JOptionPane.showMessageDialog(null,"Saliendo... sin guardar los cambios");
				ProductsAdmin pa = new ProductsAdmin(connect);
				pa.setVisible(true);
				
				
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnSalir.setBounds(629, 10, 201, 59);
		contentPane.add(btnSalir);
		
		JButton btnAgregarproducto = new JButton("AgregarProducto");
		btnAgregarproducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(enterCategory.getText() == null) {
					JOptionPane.showMessageDialog(null,"Campo Vacio en Categoria porfavor Completar");
				}
				else if(enterDescription.getText() == null) {
					JOptionPane.showMessageDialog(null,"Campo Vacio en descripcion porfavor Completar");
				}
				else if(enterPrice.getText() == null) {
					JOptionPane.showMessageDialog(null,"Campo Vacio en precio porfavor Completar");
				}
				else if(enterStock.getText() == null) {
					JOptionPane.showMessageDialog(null,"Campo Vacio en stock porfavor Completar");
				}
				else {
					String categoryAux = enterCategory.getText();
					categoryAux = categoryAux.toLowerCase();
					String query = String.format("select * from categoryProduct where category = '%s'",categoryAux);
					try {
						String category = connect.getCategory(query);
						if(category != null) {
							query = String.format("insert into product values('%s','%s','%s',%s,%s,'%s')",nameProduct,categoryAux,
									enterDescription.getText(),Integer.parseInt(enterPrice.getText()),Integer.parseInt(enterStock.getText()),path);
							try {
								connect.insertData(query);
								JOptionPane.showMessageDialog(null,"Ingresado Correctamente");
								AddProduct.this.dispose();
								ProductsAdmin pa = new ProductsAdmin(connect);
								pa.setVisible(true);
								
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"La categoria ingresada no existe");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
			
		});
		btnAgregarproducto.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnAgregarproducto.setBounds(77, 494, 288, 59);
		contentPane.add(btnAgregarproducto);
		
		enterNameProduct = new JTextField();
		enterNameProduct.setFont(new Font("Tahoma", Font.PLAIN, 30));
		enterNameProduct.setEditable(false);
		enterNameProduct.setColumns(10);
		enterNameProduct.setBounds(489, 107, 325, 33);
		enterNameProduct.setText(nameProduct);
		contentPane.add(enterNameProduct);
	}
}
