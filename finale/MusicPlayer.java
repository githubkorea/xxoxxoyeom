package finale;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MusicPlayer extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnFile;
	private JButton btnPlay,btnPause,btnStop,btnPre,btnNext;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MusicPlayer frame = new MusicPlayer();
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
	public MusicPlayer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		getContentPane().setLayout(new FlowLayout());
		
		
		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnFile = new JButton("파일 선택");
		contentPane.add(btnFile);
		
		btnPlay = new JButton(new imageIcon("C:\\Users\\user\\Desktop\\icon\\play.png"));
		contentPane.add(btnPlay);
		setVisible(true);
		btnPlay.setBorderPainted(false); 
		btnPlay.setFocusPainted(false); 
		btnPlay.setContentAreaFilled(false);

		
		
		btnPause = new JButton(new imageIcon("C:\\Users\\user\\Desktop\\icon\\pause.png"));
		contentPane.add(btnPause);
		setVisible(true);
		btnPause.setBorderPainted(false); 
		btnPause.setFocusPainted(false); 
		btnPause.setContentAreaFilled(false);
		
		btnStop = new JButton(new imageIcon("C:\\Users\\user\\Desktop\\icon\\stop.png"));
		contentPane.add(btnStop);
		setVisible(true);
		btnStop.setBorderPainted(false); 
		btnStop.setFocusPainted(false); 
		btnStop.setContentAreaFilled(false);
		
		btnPre = new JButton(new ImageIcon("C:\\Users\\user\\Desktop\\icon\\pre.png"));
		contentPane.add(btnPre);
		setVisible(true);
		btnPre.setBorderPainted(false); 
		btnPre.setFocusPainted(false); 
		btnPre.setContentAreaFilled(false);
		
		btnNext = new JButton(new imageIcon("C:\\Users\\user\\Desktop\\icon\\next.png"));
		contentPane.add(btnNext);
		setVisible(true);
		btnNext.setBorderPainted(false); 
		btnNext.setFocusPainted(false); 
		btnNext.setContentAreaFilled(false);
		
		//player 초기화
		btnFile.addActionListener(this);
		btnPlay.addActionListener(this);
		btnPause.addActionListener(this);
		btnStop.addActionListener(this);
		
		


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if(btn==btnPlay) {
			player.stop();
			
			player.open(musicName);
			player..stat();
			
			MusicPlayer.stateCode = MusicPlayer.STATE_INIT;
			
		}else if (btn==btnStop) { // 정지
			player.stop();
			
		}else if (btn==btnFile) {
			String file=getFile();
			musicName = file;
			textField.setText(file);
			
		}else if(btn==btnPause) { //일시정지
			
			// 일시정지 후 재시작 코드
			MusicPlayer.stateCode = MusicPlayer.STATE_SUSPENDED;
			player.suspend();
			
		}else if (btn==btnpre)
		
	}

}
