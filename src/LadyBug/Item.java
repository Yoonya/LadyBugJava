package LadyBug;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
//아이템의 사용을 포함한 전반적인 것을 담은 클래스
public class Item extends JPanel {

	//아이템을 정해서 삽입할 이미지 모체? 라고 해야되나
	private Image item;
	//1번 아이템 꽃 폭탄
	private Image item1 = new ImageIcon(Main.class.getResource("../images/bomb_item.png"))
			.getImage();
	//2번 아이템 꽃 보호막
	private Image item2 = new ImageIcon(Main.class.getResource("../images/flower_item.png"))
			.getImage();
	//3번 아이템 나뭇잎 유도탄
	private Image item3 = new ImageIcon(Main.class.getResource("../images/guided_missile_item.png"))
			.getImage();
	//4번 아이템 벌 미사일
	private Image item4 = new ImageIcon(Main.class.getResource("../images/bee_item.png"))
			.getImage();
	//마찬가지로 이미지 모체, 이곳은 이미지가 실행되었을 때를 담당한다.
	private Image itemAction;
	//1번 아이템의 실행
	private Image itemAction1 = new ImageIcon(Main.class.getResource("../images/bomb_explosion.png"))
			.getImage();
	//2번 아이템의 실행
	private Image itemAction2 = new ImageIcon(Main.class.getResource("../images/flower_explosion.png"))
			.getImage();
	//3번 아이템의 실행
	private Image itemAction3 = new ImageIcon(Main.class.getResource("../images/guided_missile.png"))
			.getImage();
	//4번 아이템의 실행
	private Image itemAction4 = new ImageIcon(Main.class.getResource("../images/bee0.png"))
			.getImage();
	// 캐릭터 위치, 스피드
	private int itemX;
	private int itemY = -50; // 아이템 생성 높이 고정
	private int itemSpeed = 2; // 아이템 스피드 - 60프레임 1이었으나 x축 조절이 안되어 30프레임당 2로 조절
	private int itemRandom; //랜덤으로 아이템 지정
	private int transitionX; //아이템 이동 설정
	private int transitionY = 0; //아이템 이동
	private int delay = 0; //y축 이동 속도와 x축 이동속도와 다르게 하기위해 delay를 추가했다.
	// 아이템과 상호작용할 객체 좌표들
	private int characterX; //2번 아이템의 사용을 위해 필요하다.
	private int characterY;
	private int enemyX; // 3번 아이템의 사용을 위해 필요하다.
	private int enemyY;
	private int enemyXSave;
	private int enemyYSave;
	// 애니메이션 프레임 앵글
	private int Ani1 = 0; // 아이템의 액션을 위해 필요하다.
	private int Ani2 = 0;
	private int Ani3 = 0;
	private int Ani4 = 0;
    // 3번 아이템에서 사용할 것
	int lengthX; 
	int lengthY;
	
	private boolean isAction = false; //아이템 실행
	private boolean pause = false; //일시정지를 위해
	
	//효과음들
	Music item1Music = new Music("flower_bomb_sound.mp3", true);
	Music item3Music = new Music("leaf_bomb_sound.mp3", true);
	Music item3MusicTransition = new Music("leaf_rotation_sound.mp3", true);
	Music item4Music = new Music("bee_sound.mp3", true);
	
	public Item() {	
		Random random = new Random();
		itemX = random.nextInt(Main.SCREEN_WIDTH); // x축 시작지점 랜덤
		itemRandom = random.nextInt(4) + 1; // 아이템 랜덤 지정
		//랜덤 아이템에 따른 지정
		if (itemRandom == 1) {
			item = item1;
			itemAction = itemAction1;
		}
			
		if (itemRandom == 2) {
			item = item2;
			itemAction = itemAction2;
		}
			
		if (itemRandom == 3) {
			item = item3;
			itemAction = itemAction3;
		}
			
		if (itemRandom == 4) {
			item = item4;
			itemAction = itemAction4;
		}
			

		//0이면 왼쪽 1이면 오른쪽으로 이동하도록 Y도 0이면 아래로 1이면 위로 화면 안에서 이리저리 튕기기 때문에 최초 랜덤설정(Y는 최초 0고정) 이후에 필요하다.
		transitionX = random.nextInt(2);

	}

	public void draw(Graphics g) {
		//실행 중이 아닐 때
		if (!isAction) {
			if (!pause) { //일시정지가 아닐 때
				// Y축 이동
				if (transitionY == 0) {
					itemY += itemSpeed;
				}
				if (transitionY == 1) {
					itemY -= itemSpeed;
				}
				if (itemY > 814) {
					transitionY = 1;
				}
				if (itemY < 0) {
					transitionY = 0;
				}
				// X축 이동
				if (transitionX == 0 && delay == 1) {
					itemX -= itemSpeed;
					delay = 0;
					if (itemX < 0) {
						transitionX = 1;
					}
				}
				if (transitionX == 1 && delay == 1) {
					itemX += itemSpeed;
					delay = 0;
					if (itemX > 414) {
						transitionX = 0;
					}
				}
				delay++; //x축과 y축의 속도를 다르게 하기 위한 delay
				// 나뭇잎 유도탄일 때의 설정, 아이템을 습득했을 당시에 적의 위치가 필요함 그러기 위해 이 위치에 선언
				if (itemRandom == 3) {
					//이 위치에 선언 후, 해당 좌표를 저장
					enemyXSave = enemyX;
					enemyYSave = enemyY;
					//아이템과 적 사이의 거리
					if (itemX > enemyXSave && itemY > enemyYSave) {
						lengthX = itemX - enemyX;
						lengthY = itemY - enemyY;
					}
					if (itemX > enemyXSave && itemY < enemyYSave) {
						lengthX = itemX - enemyX;
						lengthY = enemyY - itemY;
					}
					if (itemX < enemyXSave && itemY > enemyYSave) {
						lengthX = enemyX - itemX;
						lengthY = itemY - enemyY;
					}
					if (itemX < enemyXSave && itemY < enemyYSave) {
						lengthX = enemyX - itemX;
						lengthY = enemyY - itemY;
					}
				}//3번 아이템 설정 끝
			}//일시정지가 아닐 때 끝
			//드디어 여기서 그리기
			BufferedImage itemImage = new BufferedImage(item.getWidth(null), item.getHeight(null),
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D itemGraphics = (Graphics2D) itemImage.getGraphics();

			itemGraphics.drawImage(item, 0, 0, null);
			g.drawImage(itemImage, itemX, itemY, null);
		}//실행 중이 아닐 때, 끝 
		else {
			BufferedImage itemActionImage = new BufferedImage(itemAction.getWidth(null), itemAction.getHeight(null),
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D itemActionGraphics = (Graphics2D) itemActionImage.getGraphics();
			if (!pause) {//일시정지 상태가 아니라면
				if (itemRandom == 1) { // Bomb아이템은 계속 돔
					itemActionGraphics.rotate(Math.toRadians(Ani1 += 3), itemActionImage.getWidth() / 2,
							itemActionImage.getHeight() / 2); //계속 도는 rotate
					if(!item1Music.isRunning()) {//효과음
						item1Music.start();
						item1Music.setRunning(true);
					}

				}
				if (itemRandom == 2) {// flower아이템은 계속 돌면서 캐릭터와 붙어다님
					itemActionGraphics.rotate(Math.toRadians(Ani2 += 3), itemActionImage.getWidth() / 2,
							itemActionImage.getHeight() / 2);
					itemX = characterX; //이러면 따라다님
					itemY = characterY;
				}
				if (itemRandom == 3) {// 나뭇잎 아이템
					itemActionGraphics.rotate(Math.toRadians(Ani3 += 10), itemActionImage.getWidth() / 2,
							itemActionImage.getHeight() / 2); //1번아이템처럼 회천
					if ((itemX < enemyXSave + 10 && itemX > enemyXSave - 10)
							|| (itemY < enemyYSave + 10 && itemY > enemyYSave - 10)) { // 적의 좌표에 도착했을 때, +-10은 대충 오차 범위를 준 것
						itemAction3 = new ImageIcon(Main.class.getResource("../images/guided_missile_explosion.png"))
								.getImage(); // 이미지 체인지 작은것에서 큰것으로
						itemAction = itemAction3; // 마찬가지로 이미지 체인지
						itemActionGraphics.rotate(Math.toRadians(Ani3 += 5), itemActionImage.getWidth() / 2,
								itemActionImage.getHeight() / 2); //다시 회천
						if(item3MusicTransition.isRunning()) {//나뭇잎 이동 효과음
							item3MusicTransition.close();
							item3MusicTransition.setRunning(false);
						}
						if(!item3Music.isRunning()) {//나뭇잎 터짐 효과음
							item3Music.start();
							item3Music.setRunning(true);
						}
					}//적 위치 도착 끝 
					else { // 적에게 도착하지 않았을 때 = 적에게 날아가는 도중
						if(!item3MusicTransition.isRunning()) { //적에게 날아가는 효과음
							item3MusicTransition.start();
							item3MusicTransition.setRunning(true);
						}
						//아이템을 먹었을 때 설정했던 좌표에 대각선 방향으로 날아감 30으로 나눠서 날아가니 1초 후에 도착할 것
						if (itemX > enemyXSave && itemY > enemyYSave) {
							itemX -= lengthX / 30;
							itemY -= lengthY / 30;
						}
						if (itemX > enemyXSave && itemY < enemyYSave) {
							itemX -= lengthX / 30;
							itemY += lengthY / 30;

						}
						if (itemX < enemyXSave && itemY > enemyYSave) {
							itemX += lengthX / 30;
							itemY -= lengthY / 30;
						}
						if (itemX < enemyXSave && itemY < enemyYSave) {
							itemX += lengthX / 30;
							itemY += lengthY / 30;
						}
					}//날아가기 끝
				}//3번 아이템 끝
				if (itemRandom == 4) { // 벌 아이템은 직선으로 나아감
					//그림 교차 그리기
					if (Ani4 == 0) {
						itemAction4 = new ImageIcon(Main.class.getResource("../images/bee" + Ani4 + ".png")).getImage();
						itemAction = itemAction4;
						Ani4 = 1;
					} else if (Ani4 == 1) {
						itemAction4 = new ImageIcon(Main.class.getResource("../images/bee" + Ani4 + ".png")).getImage();
						itemAction = itemAction4;
						Ani4 = 0;
					}
					//이동속도
					itemY -= 2;
					//효과음 관리
					if(itemY > -100 && !item4Music.isRunning()) {//음악들도 스레드이기 때문에 잘 관리해야 한다
						item4Music.start();
						item4Music.setRunning(true);
					}
					if(itemY <= -100  && item4Music.isRunning()) {
						item4Music.close();
						item4Music.setRunning(false);
					}
					
				}//벌 아이템 끝
			}
			//드디어 그리기
			itemActionGraphics.drawImage(itemAction, 0, 0, null);
			//아이템이 중앙에서 터지도록 세부계산 (33은 캐릭터의 이미지 크기 절반) = 좌표의 기준은 왼쪽 상단이기 때문에 중앙으로 맞추는 계산
			g.drawImage(itemActionImage, itemX - (itemActionImage.getWidth() / 2) + 33, itemY - (itemActionImage.getHeight() / 2) + 33, null);		
		}//아이템 실행 중 끝
	}//draw 끝
	
	//getter and setter
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

	public boolean isAction() {
		return isAction;
	}

	public void setIsAction(boolean isAction) {
		this.isAction = isAction;
	}

	public int getItemRandom() {
		return itemRandom;
	}

	public void setItemRandom(int itemRandom) {
		this.itemRandom = itemRandom;
	}

	public Image getItem() {
		return item;
	}

	public void setItem(Image item) {
		this.item = item;
	}

	public int getItemX() {
		return itemX;
	}

	public void setItemX(int itemX) {
		this.itemX = itemX;
	}

	public int getItemY() {
		return itemY;
	}

	public void setItemY(int itemY) {
		this.itemY = itemY;
	}

	public Image getItemAction() {
		return itemAction;
	}

	public void setItemAction(Image itemAction) {
		this.itemAction = itemAction;
	}
	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

}
