package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.Graphics;


public class Register extends JFrame implements ActionListener{
	
	private JScrollPane scrollPane;
	private ImageIcon icon;
	private LoginDAO ldao;
	private JPanel panel;
	private JTextField txt_regid;
	private JTextField txt_regpw;
	private JTextField txt_regname;
	private JTextField txt_regmail;
	private JButton btn_regcheck;
	private JButton btn_regcancel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Register() {
		setTitle("회원가입");
		
		setBounds(100, 100, 450, 300);
		
		ldao = new LoginDAO();
		
		icon = new ImageIcon(Register.class.getResource("intro_register.jpg"));
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponents(g);
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
				setVisible(true);
			}
		};
		
		scrollPane = new JScrollPane(background);
		background.setLayout(null);
		
		JLabel lb_title = new JLabel("회원가입");
		lb_title.setFont(new Font("굴림", Font.BOLD, 20));
		lb_title.setBounds(182, 17, 88, 26);
		background.add(lb_title);
		
		JLabel lb_regid = new JLabel("Username");
		lb_regid.setBounds(132, 61, 64, 15);
		background.add(lb_regid);
		
		txt_regid = new JTextField();
		txt_regid.setBounds(215, 57, 116, 21);
		background.add(txt_regid);
		txt_regid.setColumns(10);
		
		JLabel lb_regpw = new JLabel("Password");
		lb_regpw.setBounds(133, 95, 67, 15);
		background.add(lb_regpw);
		
		txt_regpw = new JTextField();
		txt_regpw.setBounds(215, 90, 116, 21);
		background.add(txt_regpw);
		txt_regpw.setColumns(10);
		
		JLabel lb_regname = new JLabel("이름");
		lb_regname.setBounds(166, 129, 30, 15);
		background.add(lb_regname);
		
		txt_regname = new JTextField();
		txt_regname.setBounds(215, 124, 116, 21);
		background.add(txt_regname);
		txt_regname.setColumns(10);
		
		JLabel lb_regmail = new JLabel("이메일");
		lb_regmail.setBounds(154, 164, 41, 15);
		background.add(lb_regmail);
		
		txt_regmail = new JTextField();
		txt_regmail.setBounds(215, 158, 116, 21);
		background.add(txt_regmail);
		txt_regmail.setColumns(10);
		
		JButton btn_regcheck = new JButton("등록");
		btn_regcheck.setForeground(new Color(255, 255, 255));
		btn_regcheck.setBackground(new Color(0, 51, 102));
		btn_regcheck.setFont(new Font("굴림", Font.BOLD, 15));
		btn_regcheck.setBounds(147, 211, 70, 26);
		background.add(btn_regcheck);
		
		JButton btn_regcancel = new JButton("취소");
		btn_regcancel.setForeground(new Color(255, 255, 255));
		btn_regcancel.setBackground(new Color(0, 51, 120));
		btn_regcancel.setFont(new Font("굴림", Font.BOLD, 15));
		btn_regcancel.setBounds(229, 211, 70, 26);
		background.add(btn_regcancel);
		
	
		setContentPane(scrollPane);	
		setVisible(true);
		
		btn_regcheck.addActionListener(this);
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_regcheck) {
			LoginVO vo = new LoginVO();
		
			vo.setId(txt_regid.getText());
			vo.setPasswd(txt_regpw.getText());
			vo.setName(txt_regname.getText());
			vo.setEmail(txt_regmail.getText());
					
			//db에 입력하기
			int result = ldao.reg(vo);
			
			if(result > 0) {
				System.out.println();
				this.dispose();
				JOptionPane.showMessageDialog(this, "성공");				
			}else {
				JOptionPane.showMessageDialog(this, "실패");
				this.dispose();
			}
		}
	}
}