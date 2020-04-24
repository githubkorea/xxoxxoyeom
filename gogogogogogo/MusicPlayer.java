package gogogogogogo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class MusicPlayer {
	private String fileName="C:\\Users\\Public\\Music\\Sample Music\\Kalimba";
	private Player player;
	
	public MusicPlayer(String fileName) {
		this.fileName=fileName;
	}
	public void close() {
		if(player!=null) 
			player.close();
	}
	public void play() {
		try {
			FileInputStream fis = new FileInputStream(fileName);
			BufferedInputStream bis = new BufferedInputStream(fis);
			player=new Player(bis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new Thread() {
				public void run() {
					try {player.play();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}.start();
	}
}
