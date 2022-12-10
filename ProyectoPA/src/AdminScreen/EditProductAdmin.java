package AdminScreen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ClientScreen.EditDataScreen;
import ClientScreen.ProfileScreen;
import ConnectionDataBase.ConnectionDB;
import Dominio.Product;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class EditProductAdmin extends JFrame {

	private JPanel contentPane;
	private static ConnectionDB connect;
	private JTextField enterName;
	private JTextField enterCategory;
	private JTextField enterDescription;
	private JTextField enterPrice;
	private JTextField enterStock;
	public static Product p;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditProductAdmin frame = new EditProductAdmin(connect,p);
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
	public EditProductAdmin(ConnectionDB connect,Product p) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Nombre");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(63, 78, 85, 37);
		contentPane.add(lblName);
		
		enterName = new JTextField();
		enterName.setColumns(10);
		enterName.setBounds(175, 85, 346, 32);
		contentPane.add(enterName);
		
		JLabel lblCategory = new JLabel("Categoria");
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCategory.setBounds(63, 125, 85, 37);
		contentPane.add(lblCategory);
		
		enterCategory = new JTextField();
		enterCategory.setColumns(10);
		enterCategory.setBounds(175, 132, 346, 32);
		contentPane.add(enterCategory);
		
		JLabel lblDescription = new JLabel("Descripcion");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDescription.setBounds(63, 172, 85, 37);
		contentPane.add(lblDescription);
		
		enterDescription = new JTextField();
		enterDescription.setColumns(10);
		enterDescription.setBounds(175, 179, 346, 32);
		contentPane.add(enterDescription);
		
		JLabel lblPrice = new JLabel("Precio");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrice.setBounds(63, 219, 85, 37);
		contentPane.add(lblPrice);
		
		enterPrice = new JTextField();
		enterPrice.setColumns(10);
		enterPrice.setBounds(175, 226, 346, 32);
		contentPane.add(enterPrice);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStock.setBounds(63, 266, 85, 37);
		contentPane.add(lblStock);
		
		enterStock = new JTextField();
		enterStock.setColumns(10);
		enterStock.setBounds(175, 273, 346, 32);
		contentPane.add(enterStock);
		
		
		fillData();
		blockData();
		
		JButton buttonFinishEdit = new JButton("TerminarEdicion");
		buttonFinishEdit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonFinishEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blockData();
				String query;
				//update 
				query = String.format("update product set nameProduct = '%s', category = '%s', description = '%s', price = %s, stock = %s where nameProduct = '%s'",
						enterName.getText(),enterCategory.getText(),enterDescription.getText(),Integer.parseInt(enterPrice.getText()),
						Integer.parseInt(enterStock.getText()),p.getNameProduct());
				try {
					connect.UpdateData(query);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				ProductsAdmin pa = new ProductsAdmin(connect);
				pa.setVisible(true);
				EditProductAdmin.this.dispose();	
			}	
		});
		buttonFinishEdit.setBounds(326, 346, 195, 37);
		contentPane.add(buttonFinishEdit);
		
		JButton buttonUnlock = new JButton("HabilitarEdicion");
		buttonUnlock.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonUnlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unlockData();
			}

		});
		buttonUnlock.setBounds(63, 346, 203, 37);
		contentPane.add(buttonUnlock);
		
		JLabel lblNewLabel_1 = new JLabel("Editar Datos");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel_1.setBounds(157, 21, 276, 37);
		contentPane.add(lblNewLabel_1);
		
		JButton exitButton = new JButton("Salir");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductsAdmin pa = new ProductsAdmin(connect);
				pa.setVisible(true);
				EditProductAdmin.this.dispose();
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		exitButton.setBounds(477, 21, 103, 41);
		contentPane.add(exitButton);
		
		JLabel ImageLabel = new JLabel("");
		ImageLabel.setBounds(547, 85, 239, 182);
		ImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(ImageLabel);
						
		if(p.getImage() != null) {
			Image imagen = new ImageIcon(p.getImage()).getImage();
			ImageIcon iImagen = new ImageIcon(imagen.getScaledInstance(ImageLabel.getWidth(), ImageLabel.getHeight(), Image.SCALE_SMOOTH));
			ImageLabel.setIcon(iImagen);
		}
		
		JButton uploadPhotoButton = new JButton("Subir Foto");
		uploadPhotoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		uploadPhotoButton.setBounds(559, 348, 201, 35);
		uploadPhotoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				String imagePath = "";
				JFileChooser jf = new JFileChooser();
				FileNameExtensionFilter filterSearch = new FileNameExtensionFilter("JGP, PNG & GIF","jgp","png","gif");
				jf.setFileFilter(filterSearch);
				int respuesta = jf.showOpenDialog(uploadPhotoButton);
				
				if(respuesta == JFileChooser.APPROVE_OPTION) {
					System.out.println(jf.getSelectedFile().getName());
					imagePath = jf.getSelectedFile().getPath();
					if(p.getImage() == null || !p.getImage().equals(imagePath)) {
						Image imagen = new ImageIcon(imagePath).getImage();
						ImageIcon iImagen = new ImageIcon(imagen.getScaledInstance(ImageLabel.getWidth(), ImageLabel.getHeight(), Image.SCALE_SMOOTH));
						ImageLabel.setIcon(iImagen);
						p.setImage(imagePath);
						String query = String.format("update person set image = '%s' where rut = '%s' ",p.getImage(),p.getRut());
						try {
							connect.UpdateData(query);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				}
				*/
				JFileChooser jf = new JFileChooser();
				jf.showOpenDialog(null);
				File f = jf.getSelectedFile();
				String filename = f.getAbsolutePath();
				String name = jf.getSelectedFile().getName();
				String newPath = "Media/Producto/";
				File directory = new File(newPath);
				if(!directory.exists()) {
					directory.mkdir();
				}
				File sourceFile = null;
				File destinationFile = null;
				sourceFile = new File(filename);
				destinationFile = new File(newPath+name);
				try {
					Files.copy(sourceFile.toPath(),destinationFile.toPath());
					Image imagen = new ImageIcon(filename).getImage();
					ImageIcon iImagen = new ImageIcon(imagen.getScaledInstance(ImageLabel.getWidth(), ImageLabel.getHeight(), Image.SCALE_SMOOTH));
					ImageLabel.setIcon(iImagen);
					p.setImage(destinationFile.getAbsolutePath());
					String query = String.format("update product set image = '%s' where rut = '%s' ",p.getImage(),p.getNameProduct());
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
			
		});
		contentPane.add(uploadPhotoButton);
		
	}
	
	
	private void blockData() {
		enterName.setEnabled(false);
		enterCategory.setEnabled(false);
		enterDescription.setEnabled(false);
		enterPrice.setEnabled(false);
		enterStock.setEnabled(false);
	}

	private void fillData() {;
		enterName.setText(p.getNameProduct());
		enterCategory.setText(p.getCategory());;
		enterDescription.setText(p.getDescription());;
		enterPrice.setText(String.valueOf(p.getPrice()));
		enterStock.setText(String.valueOf(p.getStock()));;
	};
	
	private void unlockData() {
		enterName.setEnabled(true);
		enterCategory.setEnabled(true);
		enterDescription.setEnabled(true);
		enterPrice.setEnabled(true);
		enterStock.setEnabled(true);	
		
	}	
}
