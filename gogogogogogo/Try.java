package gogogogogogo;

import java.io.FileInputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import jaco.mp3.player.MP3Player;
import javazoom.jl.player.Player;

public class Try extends JFrame {

	MP3Player player;
	private JButton playBtn;
	private JPanel mainPanel;
	
	private void initComponents() {
		mainPanel = new JPanel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,600);
		setVisible(true);
	}
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("C:\\Users\\Public\\Music\\Sample Music\\lostStars.MP3");
			Player playMP3 =new Player(fis);
			playMP3.play();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}

		
	}
	
	private void playBtnMouseClicked() {
		player.play();
	}

}
