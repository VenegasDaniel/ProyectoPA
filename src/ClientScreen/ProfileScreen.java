package ClientScreen;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import ConnectionDataBase.ConnectionDB;
import Dominio.Person;
import GUI.Login;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;

public class ProfileScreen extends JFrame {

	private JPanel contentPane;
	private static Person p;
	private static ConnectionDB connect;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfileScreen frame = new ProfileScreen(p,connect);
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
	public ProfileScreen(Person p,ConnectionDB connect) {
		ProfileScreen.p = p;
		ProfileScreen.connect = connect;
		
		setTitle("Perfil Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Perfil Usuario");
		lblNewLabel_1.setBounds(89, 0, 271, 116);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 45));
		contentPane.add(lblNewLabel_1);
		
		JButton viewPurchasesButton = new JButton("Historial Compras");
		viewPurchasesButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		viewPurchasesButton.setBounds(25, 228, 247, 124);
		viewPurchasesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PurchaseScreen ps = new PurchaseScreen(p,connect);
				ps.setVisible(true);
				ProfileScreen.this.dispose();
			}
		});
		contentPane.add(viewPurchasesButton);
		
		JButton editDataButton = new JButton("Editar Datos");
		editDataButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		editDataButton.setBackground(Color.WHITE);
		editDataButton.setBounds(25, 100, 247, 116);
		editDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditDataScreen eds = new EditDataScreen(p,connect);
				eds.setVisible(true);
				ProfileScreen.this.dispose();
			}
		});
		contentPane.add(editDataButton);
		
		JLabel ImageLabel = new JLabel("");
		ImageLabel.setBounds(282, 100, 379, 321);
		ImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(ImageLabel);
		
		
		
		if(p.getImage() != null) {
			Image imagen = new ImageIcon(p.getImage()).getImage();
			ImageIcon iImagen = new ImageIcon(imagen.getScaledInstance(ImageLabel.getWidth(), ImageLabel.getHeight(), Image.SCALE_SMOOTH));
			ImageLabel.setIcon(iImagen);
		}
		
		JButton uploadPhotoButton = new JButton("Subir Foto");
		uploadPhotoButton.setBounds(395, 431, 201, 59);
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
				String nombre = jf.getSelectedFile().getName();
				String newPath = "Media/Persona/";
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
					p.setImage(destinationFile.getAbsolutePath());
					String query = String.format("update person set image = '%s' where rut = '%s' ",p.getImage(),p.getRut());
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
		
		JButton exitButton = new JButton("Salir");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientScreen cs = new ClientScreen(p,connect);
				cs.setVisible(true);
				ProfileScreen.this.dispose();
			}
		});
		exitButton.setBounds(544, 21, 117, 59);
		contentPane.add(exitButton);
	}
	
}
