package gogogogogogo;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MusicUI extends JFrame implements ActionListener{
	
	private JPanel contentPane;
	private JButton btnPlay, btnPause, btnStop;
	private JTextField textField;
	private JButton btnFile;
	
	//  현재 재생할 곡
	private String musicName;
	private JButton btnRestart;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new runnable() {
			public void run() {
				try {
					MusicUI frame = new MusicUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MusicUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,452,300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnFile = new JButton("파일선택");
		contentPane.add(btnFile);
		
		btnPlay = new JButton("play");
		contentPane.add(btnPlay);
		
		btnPause = new JButton("일시정지");
		contentPane.add(btnPause);
		
		btnStop = new JButton("정지");
		contentPane.add(btnStop);
		
		btnRestart = new JButton("재시작");
		contentPane.add(btnRestart);
		
		//player 초기화
		player = new player();
		
		btnFile.addActionListener(this);
		btnPlay.addActionListener(this);
		btnPause.addActionListener(this);
		btnStop.addActionListener(this);
		btnRestart.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton btn = (JButton) e.getsource();
		if(btn==btnPlay) {  // 플레이
			//일시중지 후 다시 재생버튼을 누를 때 기존의 스레드 중지
			player.stop();
			
			player.open(musicName);
			player.start();
			//일시정지 후 재시작 버튼이 아니라 플레이 버틀을 누르는 경우
			
		}
		
		
		
	}

}
