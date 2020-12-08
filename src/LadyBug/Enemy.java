package LadyBug;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Enemy extends JPanel {

	private Image characterImage;
	//�� �̹���
	private Image enemy = new ImageIcon(Main.class.getResource("../images/enemy.png"))
			.getImage();
	
	//ĳ���� ��ġ, ���ǵ�
	private int enemyX;
	private int enemyY = -50; //�� ���� ���� ����
	private int enemySpeed = 3; //�� ���ǵ� - 60������ 1�̾����� x�� ������ �ȵǾ� 30�����Ӵ� 2�� ����
	private int characterX; //ĳ���Ͳ� �޾ƿ�
	private int characterY;
	private int angle = 180; // rotate angle
	private int transition = 0; //�ﰢ�Լ� ����(�����ж���� drawImage int�� ���ж����)�� ������ �ֱ�����
	private int delay = 0; // ���� �̹����� ��鸮�� ������ drawimage�� int�� ������ �̷������� �ӵ� ����
	private boolean pause = false; //�Ͻ������� ����
	
	Music dieMusic = new Music("enemy_dying_sound.mp3", false); //���� �� ȿ����
	
	public Enemy(int characterX, int characterY, Image characterImage) { //������ ��, ĳ������ �̹����� ��ǥ�� �޾ƿ´�
		this.characterX = characterX;
		this.characterY = characterY;
		this.characterImage = characterImage;
		Random random = new Random();
		enemyX = random.nextInt(Main.SCREEN_WIDTH); // x�� �������� ����
		//�ﰢ�Լ��� �Ϸ�������, ���нǷµ� �̼��Ͽ� �ȵǾ���, drawImage�� int�� �ۿ� �ȵǾ� �� �ȵ� ���� �����Ѵ�.
		//ĳ���Ϳ��� ��ġ ���̿� ���� ��������
		if(characterX - enemyX > 200) {
			transition = 1; //�� ������ ��Ī
			angle = 135;
		}
		else if(enemyX - characterX > 200) {
			transition = 2;
			angle = 225;
		}
		else if(characterX - enemyX > 100) {
			transition = 3;
			angle = 150;
		}
		else if(enemyX - characterX > 100) {
			transition = 4;
			angle = 210;
		}
		
	}
	
	public void draw(Graphics g) {
		//y���� ��� �����ϰ� x�ุ ����
		if (!pause) {// �Ͻ������� �ƴ� ��
			//������ ���� enemy�� �̵� ����
			if (transition == 1) {
				enemyX += 1;
				delay = 0;
			}
			if (transition == 2) {
				enemyX -= 1;
				delay = 0;
			}
			//delay�� �� ���� ������ ���� �� ���̴�. ������ ���� �� ������ x�� �ӵ��� ����� �Ѵ�.
			if (transition == 3 && delay == 2) {
				enemyX += 1;
				delay = 0;
			}
			if (transition == 4 && delay == 2) {
				enemyX -= 1;
				delay = 0;
			}
			if (transition == 3 || transition == 4) {
				delay++;
			}
			//�������� �ӵ��� ���ƾ� �Ѵ�.
			enemyY += enemySpeed;
		}

		//�׸���
		BufferedImage enemyImage = new BufferedImage(enemy.getWidth(null), enemy.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D enemyGraphics = (Graphics2D)enemyImage.getGraphics();
		enemyGraphics.rotate(Math.toRadians(angle), enemyImage.getWidth()/2, enemyImage.getHeight()/2);
		
		enemyGraphics.drawImage(enemy, 0 , 0, null);
		g.drawImage(enemyImage, enemyX , enemyY, null);
		
	}
	

	//getter and setter
	public Image getEnemy() {
		return enemy;
	}

	public void setEnemy(Image enemy) {
		this.enemy = enemy;
	}

	public int getEnemyX() {
		return enemyX;
	}

	public void setEnemyX(int enemyX) {
		this.enemyX = enemyX;
	}

	public int getEnemyY() {
		return enemyY;
	}

	public void setEnemyY(int enemyY) {
		this.enemyY = enemyY;
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}
	
	public Music getDieMusic() {
		return dieMusic;
	}

}
