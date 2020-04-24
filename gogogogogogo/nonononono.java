package gogogogogogo;

import javax.print.attribute.standard.Media;
import javax.swing.JFrame;


import com.sun.media.MediaPlayer;

public class nonononono {

	public static void main(String[] args) {
		JFXPanel panel = new JFXPanel();
		
		JFrame f = new JFrame("메인윈도우");
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setSize(200,200);
		f.setVisible(true);
		
		Media m = new Media("C:\\Users\\Public\\Music\\Sample Music\\Kalimba");
		MediaPlayer p = new MediaPlayer();
		p.play();
	}

}
