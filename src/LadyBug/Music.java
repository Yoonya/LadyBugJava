package LadyBug;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player; //�ܺ� ���̺귯��, mp3������ ����

public class Music extends Thread{ //�ܺ� ���̺귯���� ������ ������.

	private Player player;
	private boolean isLoop; //���� �ݺ��Ǵ��� �ѹ��ϰ� ��������
	private boolean running = false; // ����Ǵ� ������
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop) { //�������� �̸��� �ݺ�üũ�� �޾ƿ�
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/" + name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis); //�ְ�ְ� �־ ���
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getTime() { //����Ǵ� ������ ��� ��ġ���� ����Ǵ��� 0.001�� ������ ��ȯ
		if(player == null)
			return 0;
		return player.getPosition(); //�̰� �װ�
	}
	public void close() { // ���� ����
		isLoop = false; 
		running = false;
		player.close(); 
		this.interrupt();//������ �ߴ�
	}
	@Override
	public void run() {
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			}while(isLoop); //true�϶� ���� �ݺ��ϵ���
			
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
