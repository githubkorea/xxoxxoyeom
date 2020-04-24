package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChattingServer {
	ExecutorService executorService;
	ServerSocket serverSocket = null;
	List<Client> connections = new Vector<Client>();
	
	void startServer() {
		// 서버 프로그램이 실행되는 컴퓨터의 cpu 코어 수 만큼 스레드를 갖는 스레드 풀 생성
		executorService = Executors.newFixedThreadPool(
			Runtime.getRuntime().availableProcessors()
		);
		try {
			// ServerSocket을 생성하고, 5001 번 포트로 바인딩
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("172.31.36.213", 5001));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 연결 요청을 수락하는 작업을 생성. 이 작업은 하나의 스레드에서 무한반복 됩니다.
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						System.out.println("[연결 수락 대기]");
						Socket socket = serverSocket.accept();
						InetSocketAddress socketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
						System.out.println("[연결 수락 : " + socketAddress.getHostName() +
								"Thread: " + Thread.currentThread().getName() + "]");
						Client client = new Client(socket, socketAddress);
						connections.add(client);
						System.out.println("[연결된 클라이언트 개수 : " + connections.size() + "]");
					} catch (IOException e) {
						if(!serverSocket.isClosed()) {
							stopServer();
						}
						break;
					}
				}
			}
		};
		executorService.submit(runnable);
	}
	
	void stopServer() {
		Iterator<Client> iterator = connections.iterator();
		try {
			while(iterator.hasNext()) {
				Client client = iterator.next();
				client.socket.close();
				iterator.remove();
			}
			if(serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
			if(executorService != null && !executorService.isShutdown()) {
				executorService.shutdown();
			}
			System.out.println("[서버 멈춤]");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class Client {
		Socket socket;
		InetSocketAddress socketAddress;
		Client(Socket socket, InetSocketAddress socketAddress) {
			this.socket = socket;
			this.socketAddress = socketAddress;
			receive();
		}
		
		void receive() {
			// read() 메소드가 블로킹 되기 때문에 별도의 쓰레드에서 실행되도록 작업 생성
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						while(true) {
							byte[] arr = new byte[100];
							InputStream inputStream = socket.getInputStream();
							
							int readByteCnt = inputStream.read(arr);
							
							if (readByteCnt == -1) { throw new IOException(); }
							
							String message = new String(arr, 0, readByteCnt, "UTF-8");
							// 메시지를 보낸 클라이언트를 제외한 모든 클라이언트에게 메시지를 전송한다.
							for (Client client : connections) {
								if (client.socketAddress.getHostName().equals(socketAddress.getHostName())) {
									client.send(message);
								}
							}
							System.out.println("[요청 처리 완료] : " + socketAddress.getHostName() );
						}
					} catch(Exception e) {
						try {
							connections.remove(Client.this);
							String message = "[클리이언트 통신 안됨]" + socketAddress;
							System.out.println(message);
							socket.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			};
			executorService.submit(runnable);
		}
		
		void send(String message) {
			// write() 메소드가 블로킹 되기 때문에 별도의 쓰레드에서 실행되도록 작업 생성
			Runnable runnable = new Runnable() {

				@Override
				public void run() {
					try {
						byte[] arr = message.getBytes("UTF-8");
						OutputStream os = socket.getOutputStream();
						os.write(arr);
						os.flush();
					} catch(Exception e) {
						try {
							connections.remove(Client.this);
							String message = "[클리이언트 통신 안됨]" + socketAddress;
							System.out.println(message);
							socket.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				
			};
			executorService.submit(runnable);
		}
	}
	
	
	public static void main(String[] args) {
		ChattingServer chattingServer = new ChattingServer();
		chattingServer.startServer();
		Scanner sc = new Scanner(System.in);
		String cmd = sc.nextLine();
		if(cmd.equals("stop")) {
			chattingServer.stopServer();
		}
	}
}