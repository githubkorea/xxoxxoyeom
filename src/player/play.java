package player;

import java.io.FileInputStream;
import javazoom.jl.player.Player;


public class play extends Thread {
	
	public static void main(String[] args) {
		 
		try { FileInputStream fis = new FileInputStream("C:\\Users\\admin\\Desktop\\Khalid-05-Talk.mp3");
				Player playMp3 = new Player(fis); 
				playMp3.play(); 
				playMp3.close();
		}catch (Exception e) {
			System.out.println(e); 
			} 
		} 
	}


