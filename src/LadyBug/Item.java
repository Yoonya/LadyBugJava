package LadyBug;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
//�������� ����� ������ �������� ���� ���� Ŭ����
public class Item extends JPanel {

	//�������� ���ؼ� ������ �̹��� ��ü? ��� �ؾߵǳ�
	private Image item;
	//1�� ������ �� ��ź
	private Image item1 = new ImageIcon(Main.class.getResource("../images/bomb_item.png"))
			.getImage();
	//2�� ������ �� ��ȣ��
	private Image item2 = new ImageIcon(Main.class.getResource("../images/flower_item.png"))
			.getImage();
	//3�� ������ ������ ����ź
	private Image item3 = new ImageIcon(Main.class.getResource("../images/guided_missile_item.png"))
			.getImage();
	//4�� ������ �� �̻���
	private Image item4 = new ImageIcon(Main.class.getResource("../images/bee_item.png"))
			.getImage();
	//���������� �̹��� ��ü, �̰��� �̹����� ����Ǿ��� ���� ����Ѵ�.
	private Image itemAction;
	//1�� �������� ����
	private Image itemAction1 = new ImageIcon(Main.class.getResource("../images/bomb_explosion.png"))
			.getImage();
	//2�� �������� ����
	private Image itemAction2 = new ImageIcon(Main.class.getResource("../images/flower_explosion.png"))
			.getImage();
	//3�� �������� ����
	private Image itemAction3 = new ImageIcon(Main.class.getResource("../images/guided_missile.png"))
			.getImage();
	//4�� �������� ����
	private Image itemAction4 = new ImageIcon(Main.class.getResource("../images/bee0.png"))
			.getImage();
	// ĳ���� ��ġ, ���ǵ�
	private int itemX;
	private int itemY = -50; // ������ ���� ���� ����
	private int itemSpeed = 2; // ������ ���ǵ� - 60������ 1�̾����� x�� ������ �ȵǾ� 30�����Ӵ� 2�� ����
	private int itemRandom; //�������� ������ ����
	private int transitionX; //������ �̵� ����
	private int transitionY = 0; //������ �̵�
	private int delay = 0; //y�� �̵� �ӵ��� x�� �̵��ӵ��� �ٸ��� �ϱ����� delay�� �߰��ߴ�.
	// �����۰� ��ȣ�ۿ��� ��ü ��ǥ��
	private int characterX; //2�� �������� ����� ���� �ʿ��ϴ�.
	private int characterY;
	private int enemyX; // 3�� �������� ����� ���� �ʿ��ϴ�.
	private int enemyY;
	private int enemyXSave;
	private int enemyYSave;
	// �ִϸ��̼� ������ �ޱ�
	private int Ani1 = 0; // �������� �׼��� ���� �ʿ��ϴ�.
	private int Ani2 = 0;
	private int Ani3 = 0;
	private int Ani4 = 0;
    // 3�� �����ۿ��� ����� ��
	int lengthX; 
	int lengthY;
	
	private boolean isAction = false; //������ ����
	private boolean pause = false; //�Ͻ������� ����
	
	//ȿ������
	Music item1Music = new Music("flower_bomb_sound.mp3", true);
	Music item3Music = new Music("leaf_bomb_sound.mp3", true);
	Music item3MusicTransition = new Music("leaf_rotation_sound.mp3", true);
	Music item4Music = new Music("bee_sound.mp3", true);
	
	public Item() {	
		Random random = new Random();
		itemX = random.nextInt(Main.SCREEN_WIDTH); // x�� �������� ����
		itemRandom = random.nextInt(4) + 1; // ������ ���� ����
		//���� �����ۿ� ���� ����
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
			

		//0�̸� ���� 1�̸� ���������� �̵��ϵ��� Y�� 0�̸� �Ʒ��� 1�̸� ���� ȭ�� �ȿ��� �̸����� ƨ��� ������ ���� ��������(Y�� ���� 0����) ���Ŀ� �ʿ��ϴ�.
		transitionX = random.nextInt(2);

	}

	public void draw(Graphics g) {
		//���� ���� �ƴ� ��
		if (!isAction) {
			if (!pause) { //�Ͻ������� �ƴ� ��
				// Y�� �̵�
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
				// X�� �̵�
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
				delay++; //x��� y���� �ӵ��� �ٸ��� �ϱ� ���� delay
				// ������ ����ź�� ���� ����, �������� �������� ��ÿ� ���� ��ġ�� �ʿ��� �׷��� ���� �� ��ġ�� ����
				if (itemRandom == 3) {
					//�� ��ġ�� ���� ��, �ش� ��ǥ�� ����
					enemyXSave = enemyX;
					enemyYSave = enemyY;
					//�����۰� �� ������ �Ÿ�
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
				}//3�� ������ ���� ��
			}//�Ͻ������� �ƴ� �� ��
			//���� ���⼭ �׸���
			BufferedImage itemImage = new BufferedImage(item.getWidth(null), item.getHeight(null),
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D itemGraphics = (Graphics2D) itemImage.getGraphics();

			itemGraphics.drawImage(item, 0, 0, null);
			g.drawImage(itemImage, itemX, itemY, null);
		}//���� ���� �ƴ� ��, �� 
		else {
			BufferedImage itemActionImage = new BufferedImage(itemAction.getWidth(null), itemAction.getHeight(null),
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D itemActionGraphics = (Graphics2D) itemActionImage.getGraphics();
			if (!pause) {//�Ͻ����� ���°� �ƴ϶��
				if (itemRandom == 1) { // Bomb�������� ��� ��
					itemActionGraphics.rotate(Math.toRadians(Ani1 += 3), itemActionImage.getWidth() / 2,
							itemActionImage.getHeight() / 2); //��� ���� rotate
					if(!item1Music.isRunning()) {//ȿ����
						item1Music.start();
						item1Music.setRunning(true);
					}

				}
				if (itemRandom == 2) {// flower�������� ��� ���鼭 ĳ���Ϳ� �پ�ٴ�
					itemActionGraphics.rotate(Math.toRadians(Ani2 += 3), itemActionImage.getWidth() / 2,
							itemActionImage.getHeight() / 2);
					itemX = characterX; //�̷��� ����ٴ�
					itemY = characterY;
				}
				if (itemRandom == 3) {// ������ ������
					itemActionGraphics.rotate(Math.toRadians(Ani3 += 10), itemActionImage.getWidth() / 2,
							itemActionImage.getHeight() / 2); //1��������ó�� ȸõ
					if ((itemX < enemyXSave + 10 && itemX > enemyXSave - 10)
							|| (itemY < enemyYSave + 10 && itemY > enemyYSave - 10)) { // ���� ��ǥ�� �������� ��, +-10�� ���� ���� ������ �� ��
						itemAction3 = new ImageIcon(Main.class.getResource("../images/guided_missile_explosion.png"))
								.getImage(); // �̹��� ü���� �����Ϳ��� ū������
						itemAction = itemAction3; // ���������� �̹��� ü����
						itemActionGraphics.rotate(Math.toRadians(Ani3 += 5), itemActionImage.getWidth() / 2,
								itemActionImage.getHeight() / 2); //�ٽ� ȸõ
						if(item3MusicTransition.isRunning()) {//������ �̵� ȿ����
							item3MusicTransition.close();
							item3MusicTransition.setRunning(false);
						}
						if(!item3Music.isRunning()) {//������ ���� ȿ����
							item3Music.start();
							item3Music.setRunning(true);
						}
					}//�� ��ġ ���� �� 
					else { // ������ �������� �ʾ��� �� = ������ ���ư��� ����
						if(!item3MusicTransition.isRunning()) { //������ ���ư��� ȿ����
							item3MusicTransition.start();
							item3MusicTransition.setRunning(true);
						}
						//�������� �Ծ��� �� �����ߴ� ��ǥ�� �밢�� �������� ���ư� 30���� ������ ���ư��� 1�� �Ŀ� ������ ��
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
					}//���ư��� ��
				}//3�� ������ ��
				if (itemRandom == 4) { // �� �������� �������� ���ư�
					//�׸� ���� �׸���
					if (Ani4 == 0) {
						itemAction4 = new ImageIcon(Main.class.getResource("../images/bee" + Ani4 + ".png")).getImage();
						itemAction = itemAction4;
						Ani4 = 1;
					} else if (Ani4 == 1) {
						itemAction4 = new ImageIcon(Main.class.getResource("../images/bee" + Ani4 + ".png")).getImage();
						itemAction = itemAction4;
						Ani4 = 0;
					}
					//�̵��ӵ�
					itemY -= 2;
					//ȿ���� ����
					if(itemY > -100 && !item4Music.isRunning()) {//���ǵ鵵 �������̱� ������ �� �����ؾ� �Ѵ�
						item4Music.start();
						item4Music.setRunning(true);
					}
					if(itemY <= -100  && item4Music.isRunning()) {
						item4Music.close();
						item4Music.setRunning(false);
					}
					
				}//�� ������ ��
			}
			//���� �׸���
			itemActionGraphics.drawImage(itemAction, 0, 0, null);
			//�������� �߾ӿ��� �������� ���ΰ�� (33�� ĳ������ �̹��� ũ�� ����) = ��ǥ�� ������ ���� ����̱� ������ �߾����� ���ߴ� ���
			g.drawImage(itemActionImage, itemX - (itemActionImage.getWidth() / 2) + 33, itemY - (itemActionImage.getHeight() / 2) + 33, null);		
		}//������ ���� �� ��
	}//draw ��
	
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
