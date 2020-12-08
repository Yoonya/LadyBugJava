package LadyBug;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player; //외부 라이브러리, mp3실행을 위함

public class Music extends Thread{ //외부 라이브러리의 사용법을 따랐다.

	private Player player;
	private boolean isLoop; //곡이 반복되는지 한번하고 꺼지는지
	private boolean running = false; // 실행되는 중인지
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop) { //음악파일 이름과 반복체크를 받아옴
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/" + name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis); //넣고넣고 넣어서 사용
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getTime() { //실행되는 음악이 어느 위치에서 실행되는지 0.001초 단위로 반환
		if(player == null)
			return 0;
		return player.getPosition(); //이게 그거
	}
	public void close() { // 음악 종료
		isLoop = false; 
		running = false;
		player.close(); 
		this.interrupt();//쓰레드 중단
	}
	@Override
	public void run() {
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			}while(isLoop); //true일때 무한 반복하도록
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public boolean isRunning() {
		return running;
	}
	public void setRunning(boolean b) {
		running = b;
	}
}
