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
	//적 이미지
	private Image enemy = new ImageIcon(Main.class.getResource("../images/enemy.png"))
			.getImage();
	
	//캐릭터 위치, 스피드
	private int enemyX;
	private int enemyY = -50; //적 생성 높이 고정
	private int enemySpeed = 3; //적 스피드 - 60프레임 1이었으나 x축 조절이 안되어 30프레임당 2로 조절
	private int characterX; //캐릭터꺼 받아옴
	private int characterY;
	private int angle = 180; // rotate angle
	private int transition = 0; //삼각함수 실패(계산실패라던지 drawImage int형 실패라던지)로 조건을 주기위해
	private int delay = 0; // 조금 이미지가 흔들리긴 하지만 drawimage의 int형 때문에 이런식으로 속도 조절
	private boolean pause = false; //일시정지를 위해
	
	Music dieMusic = new Music("enemy_dying_sound.mp3", false); //죽을 때 효과음
	
	public Enemy(int characterX, int characterY, Image characterImage) { //생성될 때, 캐릭터의 이미지와 좌표를 받아온다
		this.characterX = characterX;
		this.characterY = characterY;
		this.characterImage = characterImage;
		Random random = new Random();
		enemyX = random.nextInt(Main.SCREEN_WIDTH); // x축 시작지점 랜덤
		//삼각함수를 하려했으나, 수학실력도 미숙하여 안되었고, drawImage가 int형 밖에 안되어 잘 안되 직접 관리한다.
		//캐릭터와의 위치 차이에 따른 각도조절
		if(characterX - enemyX > 200) {
			transition = 1; //이 각도의 명칭
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
		//y축은 계속 가게하고 x축만 조절
		if (!pause) {// 일시정지가 아닐 때
			//각도에 따른 enemy의 이동 방향
			if (transition == 1) {
				enemyX += 1;
				delay = 0;
			}
			if (transition == 2) {
				enemyX -= 1;
				delay = 0;
			}
			//delay를 준 곳은 각도가 적게 휜 곳이다. 각도가 적게 휜 곳보다 x축 속도가 적어야 한다.
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
			//떨어지는 속도는 같아야 한다.
			enemyY += enemySpeed;
		}

		//그리기
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
