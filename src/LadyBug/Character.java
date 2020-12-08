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
	//ĳ���� �̹���
	private Image character = new ImageIcon(Main.class.getResource("../images/character.png"))
			.getImage();
	
	public void setCharacter(Image character) {
		this.character = character;
	}

	//ĳ���� ��ġ, ���ǵ�
	private int characterX = 215;
	private int characterY = Main.SCREEN_HEIGHT - 100;
	private int characterSpeed = 6;
	//�����Է��� �����ϱ� ����, �ݺ� �Է��� �����ϱ� ����
	private boolean keyUpPressed;
	private boolean keyDownPressed;
	private boolean keyLeftPressed;
	private boolean keyRightPressed;
	private boolean pause = false;
	
	public Character() {
		//��Ŀ���� �����ְ� Ű�����ʸ� �߰�
		this.setFocusable(true);
	    this.requestFocus();
		addKeyListener(this);
	}

	//boolean���� Ű �Է� Ȯ��
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			keyUpPressed = true;
		}
		//������ Ű�� ���� ����.
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
		if (!pause) {//�Ͻ������� �ƴ� ��,
			// Ű���� �˰��� - â�� ����� �ȵǰ�, ���� �Է� ����
			if (keyUpPressed) { //��Ű ������ ���� ��
				if (characterY >= 0 && characterY <= 814) { // â ����
					characterY -= characterSpeed; // �̵�
				} else { // â ���� ����� ��, ��ġ ���� �̵�
					if (characterY < 0)
						characterY = 0;
					if (characterY > 814)
						characterY = 814;
				}
			}

			if (keyDownPressed) {//��Ű ������ ���� ��
				if (characterY >= 0 && characterY <= 814) {
					characterY += characterSpeed;
				} else {
					if (characterY < 0)
						characterY = 0;
					if (characterY > 814)
						characterY = 814;
				}
			}
			if (keyLeftPressed) {//���� Ű ������ ���� ��
				if (characterX >= 0 && characterX <= 414) {
					characterX -= characterSpeed;
				} else {
					if (characterX < 0)
						characterX = 0;
					if (characterX > 414)
						characterX = 414;
				}
			}
			if (keyRightPressed) {//������ Ű ������ ���� ��
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
		//ĳ���Ͱ� ����ؼ� ������ �׷����� ������ BufferedImage�� ���ؼ� �ذ��Ͽ���.
		BufferedImage characterImage = new BufferedImage(character.getWidth(null), character.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D characterGraphics = (Graphics2D)characterImage.getGraphics();
		
		//rotate ��Ȯ���� �𸣰����� 1/2�� ������ ������ ��ǥ�� �߾����� �������� �ʴ´�. toRadians�� �翬�� ���� ��� ����
		//���� ������ �� �������� ������ �ٲ�
		if(keyUpPressed && keyLeftPressed) characterGraphics.rotate(Math.toRadians(45), characterImage.getWidth()/2, characterImage.getHeight()/2);
		if(keyUpPressed && keyRightPressed) characterGraphics.rotate(Math.toRadians(315), characterImage.getWidth()/2, characterImage.getHeight()/2);
		if(keyDownPressed && keyLeftPressed) characterGraphics.rotate(Math.toRadians(135), characterImage.getWidth()/2, characterImage.getHeight()/2);
		if(keyDownPressed && keyRightPressed) characterGraphics.rotate(Math.toRadians(225), characterImage.getWidth()/2, characterImage.getHeight()/2);
		if(keyUpPressed) characterGraphics.rotate(Math.toRadians(0), characterImage.getWidth()/2, characterImage.getHeight()/2);
		if(keyDownPressed) characterGraphics.rotate(Math.toRadians(180), characterImage.getWidth()/2, characterImage.getHeight()/2);
		if(keyLeftPressed) characterGraphics.rotate(Math.toRadians(270), characterImage.getWidth()/2, characterImage.getHeight()/2);
		if(keyRightPressed) characterGraphics.rotate(Math.toRadians(90), characterImage.getWidth()/2, characterImage.getHeight()/2);
		//translate rotate�� ���� �����ؼ� ���� �޼ҵ��ε�, ��Ȯ�� ������ ���� �����ص� ������淡 �ϴ��� �����صд�.  
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
