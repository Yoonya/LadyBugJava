package LadyBug;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Character extends JPanel implements KeyListener { 
	//캐릭터 이미지
	private Image character = new ImageIcon(Main.class.getResource("../images/character.png"))
			.getImage();
	
	public void setCharacter(Image character) {
		this.character = character;
	}

	//캐릭터 위치, 스피드
	private int characterX = 215;
	private int characterY = Main.SCREEN_HEIGHT - 100;
	private int characterSpeed = 6;
	//동시입력을 구현하기 위함, 반복 입력을 구현하기 위함
	private boolean keyUpPressed;
	private boolean keyDownPressed;
	private boolean keyLeftPressed;
	private boolean keyRightPressed;
	private boolean pause = false;
	
	public Character() {
		//포커스를 맞춰주고 키리스너를 추가
		this.setFocusable(true);
	    this.requestFocus();
		addKeyListener(this);
	}

	//boolean으로 키 입력 확인
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			keyUpPressed = true;
		}
		//나머지 키도 위와 같다.
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			keyDownPressed = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			keyLeftPressed = true;		
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keyRightPressed = true;	
		}
	}	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			keyUpPressed = false;		
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			keyDownPressed = false;	
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			keyLeftPressed = false;		
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keyRightPressed = false;	
		}
	}
	public void keyTyped(KeyEvent e) {
	}
	
	public void draw(Graphics g) {
		if (!pause) {//일시정지가 아닐 때,
			// 키보드 알고리즘 - 창을 벗어나면 안되고, 동시 입력 가능
			if (keyUpPressed) { //상키 누르고 있을 때
				if (characterY >= 0 && characterY <= 814) { // 창 범위
					characterY -= characterSpeed; // 이동
				} else { // 창 범위 벗어났을 때, 위치 강제 이동
					if (characterY < 0)
						characterY = 0;
					if (characterY > 814)
						characterY = 814;
				}
			}

			if (keyDownPressed) {//하키 누르고 있을 때
				if (characterY >= 0 && characterY <= 814) {
					characterY += characterSpeed;
				} else {
					if (characterY < 0)
						characterY = 0;
					if (characterY > 814)
						characterY = 814;
				}
			}
			if (keyLeftPressed) {//왼쪽 키 누르고 있을 때
				if (characterX >= 0 && characterX <= 414) {
					characterX -= characterSpeed;
				} else {
					if (characterX < 0)
						characterX = 0;
					if (characterX > 414)
						characterX = 414;
				}
			}
			if (keyRightPressed) {//오른쪽 키 누르고 있을 때
				if (characterX >= 0 && characterX <= 414) {
					characterX += characterSpeed;
				} else {
					if (characterX < 0)
						characterX = 0;
					if (characterX > 414)
						characterX = 414;
				}
			}
		}
		//캐릭터가 계속해서 여백이 그려지던 문제를 BufferedImage를 통해서 해결하였다.
		BufferedImage characterImage = new BufferedImage(character.getWidth(null), character.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D characterGraphics = (Graphics2D)characterImage.getGraphics();
		
		//rotate 정확히는 모르겠지만 1/2를 해주지 않으면 좌표가 중앙으로 잡혀지지 않는다. toRadians은 당연히 원형 모양 각도
		//누를 때마다 그 방향으로 각도가 바뀜
		if(keyUpPressed && keyLeftPressed) characterGraphics.rotate(Math.toRadians(45), characterImage.getWidth()/2, characterImage.getHeight()/2);
		if(keyUpPressed && keyRightPressed) characterGraphics.rotate(Math.toRadians(315), characterImage.getWidth()/2, characterImage.getHeight()/2);
		if(keyDownPressed && keyLeftPressed) characterGraphics.rotate(Math.toRadians(135), characterImage.getWidth()/2, characterImage.getHeight()/2);
		if(keyDownPressed && keyRightPressed) characterGraphics.rotate(Math.toRadians(225), characterImage.getWidth()/2, characterImage.getHeight()/2);
		if(keyUpPressed) characterGraphics.rotate(Math.toRadians(0), characterImage.getWidth()/2, characterImage.getHeight()/2);
		if(keyDownPressed) characterGraphics.rotate(Math.toRadians(180), characterImage.getWidth()/2, characterImage.getHeight()/2);
		if(keyLeftPressed) characterGraphics.rotate(Math.toRadians(270), characterImage.getWidth()/2, characterImage.getHeight()/2);
		if(keyRightPressed) characterGraphics.rotate(Math.toRadians(90), characterImage.getWidth()/2, characterImage.getHeight()/2);
		//translate rotate와 서로 협력해서 쓰는 메소드인데, 정확한 사용법을 몰라서 제외해도 상관없길래 일단은 제외해둔다.  
		//characterGraphics.translate((characterImage.getWidth() - character.getWidth(null)) / 2, (characterImage.getHeight() - character.getHeight(null)) / 2);
		
		characterGraphics.drawImage(character, 0 , 0, null);
		g.drawImage(characterImage, characterX , characterY, null);
	}
	
	//getter and setter
	public Image getCharacter() {
		return character;
	}
	
	public int getCharacterX() {
		return characterX;
	}

	public void setCharacterX(int characterX) {
		this.characterX = characterX;
	}

	public int getCharacterY() {
		return characterY;
	}

	public void setCharacterY(int characterY) {
		this.characterY = characterY;
	}

	public int getCharacterSpeed() {
		return characterSpeed;
	}

	public void setCharacterSpeed(int characterSpeed) {
		this.characterSpeed = characterSpeed;
	}
	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}
}
