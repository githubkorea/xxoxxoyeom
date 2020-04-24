package login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import board.Board;
import main.*;
import main.FirstPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

public class Login extends JFrame implements ActionListener {
	
	private JScrollPane scrollPane;
	private ImageIcon icon;	
	private JButton btn_reg = new JButton("Sign Up");
	private JButton btn_login = new JButton("Sign In");
	private JLabel lb_id = new JLabel("Username");
	private JTextField txt_id = new JTextField();	
	private JLabel lb_pw = new JLabel("Password");	

	private JTextField txt_pw = new JPasswordField();	
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setSize(450, 300);
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
	public Login() {
		setTitle("Music Player");	

		icon = new ImageIcon(Login.class.getResource("intro.jpg"));
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponents(g);
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
//				setVisible(true);
			}
		};
		background.setLayout(null);
			
		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);	
		
	
				
		lb_id.setBounds(129, 100, 63, 15);
		background.add(lb_id);
		
		lb_pw.setBounds(129, 136, 61, 15);
		background.add(lb_pw);
		
		txt_id = new JTextField();
		txt_id.setBounds(202, 97, 118, 21);
		background.add(txt_id);
		txt_id.setColumns(10);
		
		txt_pw = new JPasswordField();
		txt_pw.setBounds(202, 133, 118, 21);
		background.add(txt_pw);
		setContentPane(scrollPane);	
		
		btn_reg.setForeground(new Color(255, 255, 255));
		btn_reg.setBackground(new Color(0, 51, 102));
		btn_reg.setFont(new Font("굴림", Font.BOLD, 12));
		btn_reg.setBounds(126, 202, 85, 23);
		background.add(btn_reg);
		
		btn_login.setForeground(new Color(255, 255, 255));
		btn_login.setBackground(new Color(0, 51, 102));
		btn_login.setFont(new Font("굴림", Font.BOLD, 12));
		btn_login.setBounds(223, 202, 85, 23);
		background.add(btn_login);

		
		setVisible(true);
		btn_login.addActionListener(this);
		btn_reg.addActionListener(this);			
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_login) {
			LoginDAO dao = new LoginDAO();
			LoginVO vo = new LoginVO();
			vo = dao.login(txt_id.getText(), txt_pw.getText());
			System.out.println(vo);
			if(vo.getName()!=null) {
				String a = txt_id.getText();
				Board board = new Board();
				board.getid(a);
				board.show();
				dispose();
			}else {
				JOptionPane.showMessageDialog(this, "실패");
			}			
		
			}else if(e.getSource()==btn_reg) {		
					Register register = new Register();
		}	
	}
}
