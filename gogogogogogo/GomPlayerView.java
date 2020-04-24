package gogogogogogo;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javazoom.jl.player.Player;


public class GomPlayerView extends JFrame implements ActionListener,ItemListener
{
	JSlider slider;
	JLabel Label;
	private JPanel contentPane;
	private JButton btn_upload, btn_open;
	private JPanel panel_1;
	private JTextField textField;
	private JButton btnNewButton_2;
	private Player play;
	private File songFile;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_3;
	private JToggleButton btn_play;
	private Thread thread = new Thread();
	private JTable table;
	private DefaultTableModel model;
	private JButton btn_test;
	private JList<File> list;
	private int length=0;
	static int pre;
	static String name; 
	final static int STATE_INIT =0;
	final static int stateCode = STATE_INIT;
	final static int STATE_STOPPED =3;
	
	
	public GomPlayerView() {
		setTitle("MP3Player");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,467,443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0,0,0,0);
		contentPane.add(panel);
	panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBounds(12, 10, 431, 65);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(12, 5, 251, 23);
		panel_1.add(textField);
		textField.setColumns(10);
		
		btnNewButton_2 = new JButton("◁");
		btnNewButton_2.setBounds(12, 36, 45, 23);
		panel_1.add(btnNewButton_2);
		
		btn_play = new JToggleButton();
		btn_play.setText("PLAY / STOP");
//		btn_play.addActionListener(this); 
		btn_play.addItemListener(this);
		btn_play.setIcon(null);
		btn_play.setBounds(66, 36, 140, 23);
		panel_1.add(btn_play);
		
		btnNewButton = new JButton("▷");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(218, 36, 45, 23);
		panel_1.add(btnNewButton);
		
		btn_upload = new JButton("UpLoad");
		btn_upload.setBounds(332, 37, 91, 21);
		btn_upload.addActionListener(this);
		panel_1.add(btn_upload);
		
		btnNewButton_1 = new JButton("∞");
		btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 15));
		btnNewButton_1.setBounds(275, 36, 45, 23);
		panel_1.add(btnNewButton_1);
		
		btn_open = new JButton("Open");
		btn_open.addActionListener(this);
		btn_open.setBounds(275, 5, 68, 23);
		panel_1.add(btn_open);
		
		JButton btnNewButton_4 = new JButton("Delete");
		btnNewButton_4.setBounds(355, 5, 68, 23);
		panel_1.add(btnNewButton_4);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 85, 431, 304);
		contentPane.add(scrollPane);
		
		table = new JTable();
		String columnName[]= {"Music","Time"};
		
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
