package AdminScreen;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class AdminScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminScreen frame = new AdminScreen();
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
	public AdminScreen() {
		setTitle("Pantalla Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 791, 529);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Agregar Producto");
		btnNewButton.setBounds(267, 216, 218, 62);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Eliminar Producto");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(267, 288, 218, 62);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("EditarProducto");
		btnNewButton_2.setBounds(267, 360, 218, 62);
		contentPane.add(btnNewButton_2);
		
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
		
		JButton btnAgregarCategoria = new JButton("Agregar Categoria");
		btnAgregarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAgregarCategoria.setBounds(10, 216, 218, 62);
		contentPane.add(btnAgregarCategoria);
		
		JButton btnEliminarCategoria = new JButton("Eliminar Categoria");
		btnEliminarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminarCategoria.setBounds(10, 288, 218, 62);
		contentPane.add(btnEliminarCategoria);
		
		JButton btnEditarProducto = new JButton("Editar Categoria Producto");
		btnEditarProducto.setBounds(10, 360, 218, 62);
		contentPane.add(btnEditarProducto);
		
		JButton btnAgregarUsuario = new JButton("Agregar Usuario");
		btnAgregarUsuario.setBounds(536, 216, 218, 62);
		contentPane.add(btnAgregarUsuario);
		
		JButton btnEliminarUsuario = new JButton("Eliminar Usuario");
		btnEliminarUsuario.setBounds(536, 288, 216, 62);
		contentPane.add(btnEliminarUsuario);
		
		JButton btnEditarUsuario = new JButton("Editar Usuario");
		btnEditarUsuario.setBounds(536, 360, 216, 62);
		contentPane.add(btnEditarUsuario);
		
		JLabel lblNewLabel_3 = new JLabel("ADMIN");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(267, 16, 248, 132);
		contentPane.add(lblNewLabel_3);
	}

}
