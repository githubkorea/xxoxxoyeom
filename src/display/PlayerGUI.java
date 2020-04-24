package display;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;

public class PlayerGUI {

	private JFrame frmMpPlayer;
	private JTextField Pathfiled;
	private JButton startbtn,Openbtn;
	private File songfile;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // 메소드는 현재 자바 스윙 컴포넌트가 실행되고 있는 환경에 따라 다른 클래스 이름을 리턴한다.
					PlayerGUI window = new PlayerGUI();
					window.frmMpPlayer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public PlayerGUI() {
		initialize();
	}

	private void initialize() {
		frmMpPlayer = new JFrame();
		frmMpPlayer.setTitle("mp3 player");
		frmMpPlayer.setBounds(100, 100, 329, 137);
		frmMpPlayer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMpPlayer.setLocationRelativeTo(null);
		frmMpPlayer.getContentPane().setLayout(null);
		
		startbtn = new JButton("start");
		startbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Play Area
					try {
						Player p = new Player(new FileInputStream(songfile));
						p.play();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (JavaLayerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		startbtn.setToolTipText("start");
		startbtn.setBounds(10, 34, 291, 55);
		frmMpPlayer.getContentPane().add(startbtn);
		
		Pathfiled = new JTextField();
		Pathfiled.setForeground(Color.DARK_GRAY);
		Pathfiled.setEditable(false);
		Pathfiled.setText("Song Path");
		Pathfiled.setToolTipText("");
		Pathfiled.setBounds(10, 4, 192, 23);
		frmMpPlayer.getContentPane().add(Pathfiled);
		Pathfiled.setColumns(10);
		
		Openbtn = new JButton("Open");
		Openbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				open();
			}
		});
		Openbtn.setBounds(204, 4, 97, 23);
		frmMpPlayer.getContentPane().add(Openbtn);
	}

private void open() {
	try {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Choose Song To Load...");
		chooser.showOpenDialog(null);
		songfile = chooser.getSelectedFile();
		Pathfiled.setText(songfile.getAbsolutePath());
		
//		if(songfile.getName().endsWith(".mp3")) {
//			JOptionPane.showMessageDialog(null,"파일을 찾지 못함","Error",JOptionPane.ERROR_MESSAGE);
//			open();
//		}
		
	} catch (Exception e2) {
		e2.printStackTrace();
	}
}

}




















