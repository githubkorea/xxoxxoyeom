package player;

import java.io.FileInputStream;

import javazoom.jl.player.advanced.*;

public class Test// 메인 클래스에 해당합니다.
{
    public static void main(String[] args)
    {
        SoundJLayer soundToPlay = new SoundJLayer("C:\\Users\\admin\\Desktop\\Khalid-05-Talk.mp3");
        /* SoundJLayer을 생성함과 동시에 mp3파일의 경로를 입력해 줍니다.
         *
         * */

        soundToPlay.play();//실제로 mp3를 실행해 봅니다.
    }
}
//쓰레드를 상속받고 PlaybackListener이라는 클래스를 상속받았습니다.
class SoundJLayer extends PlaybackListener implements Runnable
{
    private String filePath;
    private AdvancedPlayer player;
    private Thread playerThread;    

    public SoundJLayer(String filePath)//파일의 경로를 filePath에 입력합니다.
    {
        this.filePath = filePath;//filePath의 값을 mp3의 경로 값으로 초기화.
    }

    public void play()
    {
        try
        {
            String urlAsString = this.filePath;//
            System.out.println("제대로 실행이 되었습니다.");
            this.player = new AdvancedPlayer(new FileInputStream(urlAsString));

            this.player.setPlayBackListener(this);

            this.playerThread = new Thread(this, "AudioPlayerThread");

            this.playerThread.start();
            System.out.println("제대로 실행이 되었습니다.");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    // PlaybackListener members

    public void playbackStarted(PlaybackEvent playbackEvent)
    {
        System.out.println("playbackStarted");
    }

    public void playbackFinished(PlaybackEvent playbackEvent)
    {
        System.out.println("playbackEnded");
    }    

    // Runnable members

    public void run()
    {
        try
        {
            this.player.play();
        }
        catch (javazoom.jl.decoder.JavaLayerException ex)
        {
            ex.printStackTrace();
        }

    }
}

