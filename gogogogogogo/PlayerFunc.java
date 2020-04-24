package gogogogogogo;

import java.io.Closeable;
import java.io.FileInputStream;
import java.util.ArrayList;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.SampleBuffer;
import javazoom.jl.player.AudioDevice;


public class PlayerFunc implements Runnable{
	GomPlayerDB db = new GomPlayerDB();
	
	static class Sample {
		private short[] buffer;
		private int length;
		
		public Sample(short[] buf,int s) {
			buffer = buf.clone();
			length=s;
		}
		
		public short[] GetBuffer() {
			return buffer;
		}
		
		public int GetLength() {
			return length;
		}
		
		public static final int BUFFER_SIZE = 10000;
		
		private Decoder decoder;
		private AudioDevice out;
		private ArrayList<Sample> playes;
		
		public boolean IsInvalid() {
			return (decoder==null || out==null || playes==null || !out.isOpen());
			
		}
		
		protected boolean Getplayes(String path) {
			if(IsInvalid())
				return false;
			
			try {
				javazoom.jl.decoder.Header header;
				SampleBuffer buffer;
				FileInputStream fis = new FileInputStream(path);
				Bitstream bit = new Bitstream(fis);
				if((header=bit.readFrame())==null)
					return false;
				while(length < BUFFER_SIZE && header != null) {
					buffer=(SampleBuffer)decoder.decodeFrame(header,bit);
					playes.add(new Sample(buffer.getBuffer(),buffer.getBufferLength()));
					length++;
					bit.closeFrame();
					header = bit.readFrame();
				}
			} catch (Exception e) {
				return false;
			}
			return true;
		}
		
		private Thread thisThread;
		
		final static int STATE_INIT = 0;
		final static int STATE_STARTED = 1;
		final static int STATE_SUSPENDED = 2;
		final static int STATE_STOPPED = 3;
		
		static int stateCode = STATE_INIT;
		
		public void start() {
			synchronized ( this ) {
				thisThread = new Thread((Runnable) this);
				thisThread.start();
				stateCode = STATE_STARTED;
			}
		}
		
		public void run() {
			while (true) {
				if(stateCode == STATE_STOPPED) {
					break;
				}
			play();
			}
		}
		
		@SuppressWarnings("static-access")
		public void stop() {
			synchronized (this) {
				this.stateCode = STATE_STOPPED;
				try {
					thisThread.sleep(100);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("정지");
			}
		}
		
		@SuppressWarnings("static-access")
		public void play() {
			if(IsInvalid()) {
				return;
			}
			System.out.println(db.Name() + ": 실행중");
			try {
				for(int i=0;i<length;i++) {
					out.write(playes.get(i).GetBuffer(),0,playes.get(i).GetLength());
					if(stateCode == STATE_SUSPENDED) {
						Close();
					}
					if (stateCode == STATE_SUSPENDED) {
						System.out.println("일시정지");
						while(true) {
							try {
								thisThread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							if( stateCode == STATE_STARTED || stateCode == STATE_STOPPED) {
								break;
							}
						}
					}
				}
			} catch (InterruptedException e) {
				Close();
			}
		}
		
		public void Close() {
			if((out != null) && !out.isOpen()) {
				out.close();
			}
			length = 0;
			playes = null;
			out = null;
			decoder = null;
		}
		public void suspend() {
			if ( stateCode == STATE_SUSPENDED) {
				return;
			}else if ( stateCode == STATE_INIT) {
				System.out.println("실행 중이 아닙니다.");
			}else{
				System.out.println("일시정지");
				stateCode = STATE_SUSPENDED;
			}
		}
		
		public void resume() {
			if (stateCode == STATE_STARTED || stateCode == STATE_INIT) {
				System.out.println("실행 중이 아닙니다.");
				return;
			}
			if( stateCode == STATE_STOPPED)
				System.out.println("정지상태");
			stateCode = STATE_STARTED;
			System.out.println("되돌리기");
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}






















