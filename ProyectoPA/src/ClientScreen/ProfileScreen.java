package ClientScreen;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.Login;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;

public class ProfileScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfileScreen frame = new ProfileScreen();
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
	public ProfileScreen() {
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
		
		JButton viewPurchasesButton = new JButton("Ver Compras");
		viewPurchasesButton.setBounds(25, 226, 193, 124);
		viewPurchasesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(viewPurchasesButton);
		
		JButton editDataButton = new JButton("Editar Datos");
		editDataButton.setBackground(Color.WHITE);
		editDataButton.setBounds(25, 100, 193, 116);
		editDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(editDataButton);
		
		JLabel ImageLabel = new JLabel("");
		ImageLabel.setBounds(282, 100, 379, 321);
		ImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(ImageLabel);
		
		JButton uploadPhotoButton = new JButton("Subir Foto");
		uploadPhotoButton.setBounds(395, 431, 201, 59);
		uploadPhotoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String imagePath = "";
				JFileChooser jf = new JFileChooser();
				FileNameExtensionFilter filterSearch = new FileNameExtensionFilter("JGP, PNG & GIF","jgp","png","gif");
				jf.setFileFilter(filterSearch);
				
				int respuesta = jf.showOpenDialog(uploadPhotoButton);
				if(respuesta == JFileChooser.APPROVE_OPTION) {
					imagePath = jf.getSelectedFile().getPath();
					Image imagen = new ImageIcon(imagePath).getImage();
					ImageIcon iImagen = new ImageIcon(imagen.getScaledInstance(ImageLabel.getWidth(), ImageLabel.getHeight(), Image.SCALE_SMOOTH));
					ImageLabel.setIcon(iImagen);
				}
				
			}
		});
		contentPane.add(uploadPhotoButton);
		
		JButton exitButton = new JButton("Salir");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientScreen cs = new ClientScreen();
				cs.setVisible(true);
				ProfileScreen.this.dispose();
			}
		});
		exitButton.setBounds(544, 21, 117, 59);
		contentPane.add(exitButton);
	}
}
