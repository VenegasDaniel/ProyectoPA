package ClientScreen;
import Dominio.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.Login;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ClientScreen extends JFrame {

	private JPanel contentPane;
	private ConnectionDB connect;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientScreen frame = new ClientScreen();
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
	public ClientScreen() {
		setTitle("Ventana Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton profileButton = new JButton("Perfil");
		profileButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		profileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfileScreen ps = new ProfileScreen();
				ps.setVisible(true);
				ClientScreen.this.dispose();
			}
		});
		profileButton.setBounds(10, 24, 140, 45);
		contentPane.add(profileButton);
		
		JButton exitButton = new JButton("Salir");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l = new Login(connect);
				l.setVisible(true);
				ClientScreen.this.dispose();
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		exitButton.setBounds(304, 10, 122, 33);
		contentPane.add(exitButton);
	}
}
