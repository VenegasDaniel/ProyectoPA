package AdminScreen;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConnectionDataBase.ConnectionDB;
import GUI.Login;
import GUI.SignIn;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminScreen extends JFrame {

	private JPanel contentPane;
	private static ConnectionDB connect;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminScreen frame = new AdminScreen(connect);
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
	public AdminScreen(ConnectionDB connect) {
		setTitle("Pantalla Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 791, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JButton btnNewButton = new JButton("Manejar Productos");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductsAdmin pa = new ProductsAdmin(connect);
				pa.setVisible(true);
				AdminScreen.this.dispose();
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnNewButton.setBounds(267, 216, 218, 62);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Productos");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(327, 158, 109, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Categoria");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(58, 158, 126, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Usuarios");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(594, 162, 98, 21);
		contentPane.add(lblNewLabel_2);
		
		JButton btnAgregarCategoria = new JButton("Manejar Categorias");
		btnAgregarCategoria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAgregarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CategoryAdmin ca = new CategoryAdmin(connect);
				ca.setVisible(true);
				AdminScreen.this.dispose();
			}
		});
		btnAgregarCategoria.setBounds(10, 216, 218, 62);
		contentPane.add(btnAgregarCategoria);
		
		JButton btnEliminarUsuario = new JButton("Manejar Usuarios");
		btnEliminarUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEliminarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserAdmin ua = new UserAdmin(connect);
				ua.setVisible(true);
				AdminScreen.this.dispose();
			}
		});
		btnEliminarUsuario.setBounds(536, 216, 216, 62);
		contentPane.add(btnEliminarUsuario);
		
		JLabel lblNewLabel_3 = new JLabel("ADMIN");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(267, 16, 248, 132);
		contentPane.add(lblNewLabel_3);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l = new Login(connect);
				l.setVisible(true);
				AdminScreen.this.dispose();
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnSalir.setBounds(616, 43, 119, 34);
		contentPane.add(btnSalir);
	}
	

}