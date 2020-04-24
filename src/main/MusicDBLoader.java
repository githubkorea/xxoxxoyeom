package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;

public class MusicDBLoader extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JButton btn_DBsearch;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MusicDBLoader DBLoader = new MusicDBLoader();
					DBLoader.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public MusicDBLoader() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 424, 252);
		contentPane.add(scrollPane);
		
		JList musicList = new JList();
		scrollPane.setViewportView(musicList);
		
		btn_DBsearch = new JButton("탐색하기");
		btn_DBsearch.addActionListener(this);
		btn_DBsearch.setBounds(435, 3, 97, 23);
		contentPane.add(btn_DBsearch);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn_DBsearch) {
			MusicDAO dao = new MusicDAO();
			Vector<MusicVO> vecList = dao.getMusicList();
			System.out.println(vecList);
			
			
			JList<String> list = new JList<>();
			scrollPane.setViewportView(list);
		}
		
		
	}
}
