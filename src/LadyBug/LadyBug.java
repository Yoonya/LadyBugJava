package LadyBug;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class LadyBug extends JFrame {

	// ���� ���� ����� ����ϱ� ����(�̹����� ���ۿ� �ΰ� ��� ȣ���ϴ� ���)
	private Image screenImage; 
	private Graphics screenGraphic;
	//�̹���(�гο� ���)
	private Image mainTitle = new ImageIcon(Main.class.getResource("../images/MainTitle.png"))
			.getImage(); //���� Ÿ��Ʋ �̹���
	private Image scoreBoard = new ImageIcon(Main.class.getResource("../images/scoreboard.png"))
			.getImage(); //���� Ÿ��Ʋ �̹���
	private Image pauseMenuImage = new ImageIcon(Main.class.getResource("../images/pause_frame.png"))
			.getImage(); //�Ͻ����� ��ư ������ ��, �̹���
	private Image gameEndMenuImage = new ImageIcon(Main.class.getResource("../images/game_over_frame.png"))
			.getImage(); //������ ������ ��, �޴� �̹���
	private Image gameEndMenuHighScoreImage = new ImageIcon(Main.class.getResource("../images/game_over_new_record_frame.png"))
			.getImage(); //������ ������ ��, �ְ����� �޼� �� �޴� �̹��� //�̰� �̹��� �ְ� ����
	private Image retryMenuImage = new ImageIcon(Main.class.getResource("../images/retry_frame.png"))
			.getImage(); //������ ������ ��, Ȯ�ι�ư ������ �� ����, �ٽ��ϱ�� �����ư�� �߰��ϴ� �̹���
	private Image background1 = new ImageIcon(Main.class.getResource("../images/background.png"))
			.getImage(); //����̹���1
	private Image background2 = new ImageIcon(Main.class.getResource("../images/background2.png"))
			.getImage(); //����̹���2
	private Image cloud1 = new ImageIcon(Main.class.getResource("../images/cloud.png"))
			.getImage(); //��游 �ֱ� �ɽ��ؼ� ������ ����1
	private Image cloud2 = new ImageIcon(Main.class.getResource("../images/cloud2.png"))
			.getImage(); //��游 �ֱ� �ɽ��ؼ� ������ ����2
	private Image cloud3 = new ImageIcon(Main.class.getResource("../images/cloud3.png"))
			.getImage(); //��游 �ֱ� �ɽ��ؼ� ������ ����3
	//�̹��� �����ܵ�, ���� ������Ʈ�鿡 ���
	private ImageIcon windowBarImage = new ImageIcon(Main.class.getResource("../images/windowBar.png"));
	private ImageIcon exitButtonImage = new ImageIcon(Main.class.getResource("../images/exit_button.png"));
	private ImageIcon gameStartButtonImage = new ImageIcon(Main.class.getResource("../images/game_start_button.png"));
	private ImageIcon scoreButtonImage = new ImageIcon(Main.class.getResource("../images/score_button.png"));
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exit_button_clicked.png"));
	private ImageIcon gameStartButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/game_start_button_clicked.png"));
	private ImageIcon scoreButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/score_button_clicked.png"));
	private ImageIcon pauseButtonImage = new ImageIcon(Main.class.getResource("../images/pause_button.png"));
	private ImageIcon pauseButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/pause_button_clicked.png"));
	private ImageIcon soundButtonOnImage = new ImageIcon(Main.class.getResource("../images/sound_on.png"));
	private ImageIcon soundButtonOffImage = new ImageIcon(Main.class.getResource("../images/sound_off.png"));
	private ImageIcon soundButtonOnEnteredImage = new ImageIcon(Main.class.getResource("../images/sound_on_clicked.png"));
	private ImageIcon soundButtonOffEnteredImage = new ImageIcon(Main.class.getResource("../images/sound_off_clicked.png"));
	private ImageIcon resumeButtonImage = new ImageIcon(Main.class.getResource("../images/resume_button.png"));
	private ImageIcon resumeButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/resume_button_clicked.png"));
	private ImageIcon replayButtonImage = new ImageIcon(Main.class.getResource("../images/replay_button.png"));
	private ImageIcon replayButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/replay_button_clicked.png"));
	private ImageIcon mainmenuButtonImage = new ImageIcon(Main.class.getResource("../images/main_menu_button.png"));
	private ImageIcon mainmenuButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/main_menu_button_clicked.png"));
	private ImageIcon gameEndOkButtonImage = new ImageIcon(Main.class.getResource("../images/ok_button.png"));
	private ImageIcon gameEndOkButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/ok_button_clicked.png"));
	private ImageIcon gameEndReplayButtonImage = new ImageIcon(Main.class.getResource("../images/replay_button2.png"));
	private ImageIcon gameEndReplayButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/replay_button_clicked2.png"));
	private ImageIcon gameEndMainMenuButtonImage = new ImageIcon(Main.class.getResource("../images/main_menu_button2.png"));
	private ImageIcon gameEndMainMenuButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/main_menu_button_clicked2.png"));
	private ImageIcon scoreBoardBackButtonImage = new ImageIcon(Main.class.getResource("../images/back_button.9.png"));
	private ImageIcon scoreBoardBackButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/back_button_clicked.9.png"));
	
	//��� �׸��� ����
	private float backgroundSpeed = 0.5f; //��� ��ũ�� �ӵ�
	private float cloud1X = 0; //���� 1, 2, 3���� ���� ���� ����
	private float cloud2X = -200;
	private float cloud3X = 600;
	private float cloud1Y = 0;
	private float cloud2Y = 0;
	private float cloud3Y = 0;
	private float backgroundX = 0; //��� �̹������� ���� ���� ����
	private float backgroundY = 0;
	private float background2Y = 0 - Main.SCREEN_HEIGHT;
	//boolean��
	private boolean isMainScreen = true; //���� Ÿ��Ʋ ȭ��
	private boolean isGameScreen = false; //���� ���� â ȭ��
	private boolean isScoreScreen = false; //���� ���� ȭ�� 
	//������Ʈ
	private JLabel windowBar = new JLabel(windowBarImage); //���׸� ��ġ�ٺ��� ���� �������
	private JButton exitButton = new JButton(exitButtonImage); //����ȭ��� ����ȭ�鿡 ���� ��ư��
	private JButton gameStartButton = new JButton(gameStartButtonImage);
	private JButton scoreButton = new JButton(scoreButtonImage);
	private JButton scoreBackButton = new JButton(scoreBoardBackButtonImage);
	private JButton pauseButton = new JButton(pauseButtonImage);
	private JButton soundButton = new JButton(soundButtonOnImage);
	private JPanel pauseMenu; //�Ͻ������� ���� ��, ������ �г�
	private JPanel gameEndMenu; //������ ������ ��, ������ �г�
	private JPanel retryMenu; //������ ������ ��, Ȯ���� ���� ��, ������ �г�
	private JButton resumeButton = new JButton(resumeButtonImage); //�� �гε鿡 ���� ���� ��ư��
	private JButton replayButton = new JButton(replayButtonImage);
	private JButton mainmenuButton = new JButton(mainmenuButtonImage);
	private JButton gameEndOkButton = new JButton(gameEndOkButtonImage);
	private JButton gameEndRetryButton = new JButton(gameEndReplayButtonImage);
	private JButton gameEndMainMenuButton = new JButton(gameEndMainMenuButtonImage);
	private JLabel gameEndscoreLabel = new JLabel(); //������ ������ ��, ������ ���� ����
	private JLabel scoreLabel = new JLabel("score : 0"); //�������� ���϶�, ��� ��µǰ� �ִ� ����

	//windowBar�� �߰��ϰ� �Ǿ��� ������, ���콺 ��ǥ ���� ��
	private int mouseX, mouseY;
	//�ٸ� Ŭ����
	private Character character; //ĳ���� Ŭ����
	private Enemy enemy; //�� Ŭ����
	private ArrayList<Enemy> enemylist; //������ �ѹ��� �׸��� �����ϱ� ���� ���Ḯ��Ʈ ����
	private Item item; //������ Ŭ����
	private ArrayList<Item> itemlist; //�������� �ѹ��� �׸��� �����ϱ� ���� ���Ḯ��Ʈ ����
	private Music backgroundMusic; //������� ����
	private Save save; //������� ����
	private ArrayList scoreList = new ArrayList<>(); //������ ����
	private ArrayList<JLabel> scoreListLabel = new ArrayList<>();//������ ȭ�鿡 ������ ��
	//����
	private int getItemX, getItemY, getItemRandom; //�������� x, y��ǥ�� item number�� �޾ƿ´�
	private int score = 0; // ������ 0���� ����
	private int highScore = 0; // ���� �ְ������� 0���� ����
	private boolean pauseClick = false; //���� �Ͻ�����
	private boolean gameEnd = false; //���� ��
	//Ÿ�̸�
	private Timer enemyTimer; //�� ���� Ÿ�̸�
	private TimerTask enemyTT; 
	private Timer itemTimer; //������ ���� Ÿ�̸�
	private TimerTask itemTT;
	
	public LadyBug() { 
		// �߻��� ���� paint�δ� ��ư�� �� �׷��� -> paintComponents�� �׸��� ����� ���׷��� -> setBackground(new Color(0,0,0,0))��
		// paintComponents �� ������ �޹���� ȸ���� �ƴ� ������� ���� �ʿ䰡 �ִٰ� �ϴ� ��� ->
		// �׷��� �߰��ߴ���, Frame is decorated ���� ->  setUndecorated�� �������� ->
		// �ᱹ windowBar ���� �����ϰ� ��
		setUndecorated(true);
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setLayout(null); //������ǥ ����
		setResizable(false); 
		setLocationRelativeTo(null); //�����ų ��, ȭ�� ���߾�����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setBackground(new Color(0,0,0,0)); // ��� �������� ����
		setTitle("LadyBug");
		setVisible(true);	
		
		//������ ���۵� ��, �ְ����� �ҷ�����
		try {//���� ���� ���� ���� �޾ƿ���
			highScore = save.loadHighScore();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		//�⺻������ JButton�� �⺻ ��� ���ø��� �־ �ٲ�����Ѵ�.
		//Border �����ְ�, ���뿵�� ä��� ���ϰ�, ���õǾ��� �� �׵θ� ����°� ����
		exitButton.setBounds(450, 0, 30, 30); 
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {//���콺�� �ش� ��ư ���� �ö���� �� �̺�Ʈ ó��
				exitButton.setIcon(exitButtonEnteredImage); //Ŭ���׸�
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//���콺�� �հ��� �������
			}
			@Override
			public void mouseExited(MouseEvent e) {//���콺�� �ش� ��ư ���� ������ �� �̺�Ʈ ó��
				exitButton.setIcon(exitButtonImage); //�����׸�
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //���콺�� �����������
			}
			@Override
			public void mousePressed(MouseEvent e) {//���콺�� �ش� ��ư�� ������ �� �̺�Ʈ ó��
				System.exit(0); //����
			}
		});
		
		add(exitButton);
		
		//���ӽ��� ��ư
		gameStartButton.setBounds(290, 420, 169, 82);
		gameStartButton.setBorderPainted(false);
		gameStartButton.setContentAreaFilled(false);
		gameStartButton.setFocusPainted(false);
		gameStartButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				gameStartButton.setIcon(gameStartButtonEnteredImage); 
				gameStartButton.setBounds(270, 400, 205, 99);
				gameStartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				gameStartButton.setIcon(gameStartButtonImage); 
				gameStartButton.setBounds(290, 420, 169, 82);
				gameStartButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 
			}
			@Override
			public void mousePressed(MouseEvent e) {
				isMainScreen = false; //���ν�ũ���� ���ӽ�ũ���� ��ȯ
				isGameScreen = true;
				gameStartButton.setVisible(false); //������ɵ��� ���� Ų��.
				scoreButton.setVisible(false);
				pauseButton.setVisible(true);
				soundButton.setVisible(true);
				scoreLabel.setVisible(true);
				backgroundMusic = new Music("background_music.mp3", true); //��������
				backgroundMusic.start(); //������� ����
				Music buttonEnteredMusic = new Music("beep.mp3", false); //��ư Ŭ�� �Ҹ�
				buttonEnteredMusic.start();//ȿ��������
				
				character = new Character(); //ĳ���� ����
				add(character);//ĳ���Ͱ� panel�̶� add	
				//character.setFocusable(true);
				//character.requestFocus();
				
				//���� �� ������ ���� �ʱ�ȭ ���ְ� �ʰ������ش�.
				enemy = new Enemy(character.getCharacterX(), character.getCharacterY(), character.getCharacter());
				enemylist = new ArrayList<>();
				enemylist.add(enemy);
				//Ÿ�̸� ����
				enemyTimer = new Timer();
				enemyTT = new TimerTask() {
					@Override
					public void run() {
						//�ð����� ���� �����ؼ� ����Ʈ�� �߰�
						enemy = new Enemy(character.getCharacterX(), character.getCharacterY(), character.getCharacter());
						enemylist.add(enemy);
					}
				};
				//����� 0.4�ʴ� 1�� �������� ����
				enemyTimer.scheduleAtFixedRate(enemyTT, 0, 400);
				
				//�����۵� ��������
				item = new Item();
				itemlist = new ArrayList<>();
				itemlist.add(item);
				
				itemTimer = new Timer();
				itemTT = new TimerTask() {
					@Override
					public void run() {
						item = new Item();
						itemlist.add(item);
					}
				};
				//����� 5�� �Ŀ� �ѹ� ���ʷ� ������ 10�ʸ��� ������ ������ ����
				itemTimer.scheduleAtFixedRate(itemTT, 5000, 10000);
							
				character.setFocusable(true);//ĳ���� ��Ŀ�� �缳��
				character.requestFocus();
			}
		});
		
		add(gameStartButton);
		
		scoreButton.setBounds(290, 520, 169, 82);
		scoreButton.setBorderPainted(false);
		scoreButton.setContentAreaFilled(false);
		scoreButton.setFocusPainted(false);
		scoreButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {//���콺�� �ش� ��ư ���� �ö���� �� �̺�Ʈ ó��
				scoreButton.setIcon(scoreButtonEnteredImage); //Ŭ���׸�
				scoreButton.setBounds(270, 510, 205, 99);
				scoreButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//���콺�� �հ��� �������
			}
			@Override
			public void mouseExited(MouseEvent e) {//���콺�� �ش� ��ư ���� ������ �� �̺�Ʈ ó��
				scoreButton.setIcon(scoreButtonImage); //�����׸�
				scoreButton.setBounds(290, 520, 169, 82);
				scoreButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //���콺�� �����������
			}
			@Override
			public void mousePressed(MouseEvent e) {//���콺�� �ش� ��ư�� ������ �� �̺�Ʈ ó��
				Music buttonEnteredMusic = new Music("beep.mp3", false); //��ư�� Ŭ������ �� ���� 1���� ����
				buttonEnteredMusic.start();//���ǽ���
				isMainScreen = false; //�������� â���� ��ȯ
				isScoreScreen = true;
				gameStartButton.setVisible(false);
				scoreButton.setVisible(false);
				scoreBackButton.setVisible(true);
				
				//�̰��� ���� �����, ���Ͽ��� ������ ��ϵ� ���� �޾ƿ�
				try {
					scoreList = save.loadScore();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				
				for(int i = 0; i<scoreList.size(); i++) {
					if(i == 8) break; //�ִ� 8����� ����
					JLabel label = new JLabel(i+1 + "               " + scoreList.get(i));//���� ���̱�
					label.setFont(new Font("�ü�", Font.BOLD, 31));//���� ����
					label.setForeground(Color.black); //����
					label.setBounds(120, 230+60*(i+1), 300, 70); //��ġ ���� ����
					add(label); // �ְ�
					scoreListLabel.add(label); //�����ϰ� ���� ����Ʈ�� ����
				}
			}
		});

		add(scoreButton);
		
		scoreBackButton.setBounds(10, 40, 52, 38); //�������⿡�� �ڷΰ��� ��ư
		scoreBackButton.setBorderPainted(false);
		scoreBackButton.setContentAreaFilled(false);
		scoreBackButton.setFocusPainted(false);
		scoreBackButton.setVisible(false);
		scoreBackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {//���콺�� �ش� ��ư ���� �ö���� �� �̺�Ʈ ó��
				scoreBackButton.setIcon(scoreBoardBackButtonEnteredImage); //Ŭ���׸�
				scoreBackButton.setBounds(10, 40, 52, 38);
				scoreBackButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//���콺�� �հ��� �������
			}
			@Override
			public void mouseExited(MouseEvent e) {//���콺�� �ش� ��ư ���� ������ �� �̺�Ʈ ó��
				scoreBackButton.setIcon(scoreBoardBackButtonImage); //�����׸�
				scoreBackButton.setBounds(10, 40, 52, 38);
				scoreBackButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //���콺�� �����������
			}
			@Override
			public void mousePressed(MouseEvent e) {//���콺�� �ش� ��ư�� ������ �� �̺�Ʈ ó��
				Music buttonEnteredMusic = new Music("beep.mp3", false); //��ư�� Ŭ������ �� ���� 1���� ����
				buttonEnteredMusic.start();//���ǽ���
				isMainScreen = true; //�������� â���� ��ȯ
				isScoreScreen = false;
				gameStartButton.setVisible(true);
				scoreButton.setVisible(true);
				scoreBackButton.setVisible(false);
				
				for(int i = 0; i<scoreListLabel.size(); i++) {
					scoreListLabel.get(i).setVisible(false);
				}
				scoreListLabel.clear();//�������⿡ ��� ����Ʈ �ʱ�ȭ
			}
		});

		add(scoreBackButton);
		
		pauseButton.setBounds(400, 30, 76, 74); 
		pauseButton.setBorderPainted(false);
		pauseButton.setContentAreaFilled(false);
		pauseButton.setFocusPainted(false);
		pauseButton.setVisible(false);
		pauseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {//���콺�� �ش� ��ư ���� �ö���� �� �̺�Ʈ ó��
				pauseButton.setIcon(pauseButtonEnteredImage); //Ŭ���׸�
				pauseButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//���콺�� �հ��� �������
			}
			@Override
			public void mouseExited(MouseEvent e) {//���콺�� �ش� ��ư ���� ������ �� �̺�Ʈ ó��
				pauseButton.setIcon(pauseButtonImage); //�����׸�
				pauseButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //���콺�� �����������
			}
			@Override
			public void mousePressed(MouseEvent e) {//���콺�� �ش� ��ư�� ������ �� �̺�Ʈ ó��
				Music buttonEnteredMusic = new Music("beep.mp3", false); //��ư�� Ŭ������ �� ���� 1���� ����
				buttonEnteredMusic.start();//���ǽ���
				pauseMenu.setVisible(true); //�Ͻ����� �г� ȣ��
				pauseClick = true; //���� �Ͻ����� 
				enemyTimer.cancel(); //�Ͻ������� ���� Ÿ�̸� ĵ��
				itemTimer.cancel();
			}
		});
		
		add(pauseButton);
		
		//���ȭ���� ���Ұ��ϱ� ���� ��ư
		soundButton.setBounds(320, 30, 76, 74); 
		soundButton.setBorderPainted(false);
		soundButton.setContentAreaFilled(false);
		soundButton.setFocusPainted(false);
		soundButton.setVisible(false);
		soundButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {//���콺�� �ش� ��ư ���� �ö���� �� �̺�Ʈ ó��
				if(soundButton.getIcon() == soundButtonOnImage)
					soundButton.setIcon(soundButtonOnEnteredImage); //Ŭ���׸�
				if(soundButton.getIcon() == soundButtonOffImage)
					soundButton.setIcon(soundButtonOffEnteredImage);
				soundButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//���콺�� �հ��� �������
			}
			@Override
			public void mouseExited(MouseEvent e) {//���콺�� �ش� ��ư ���� ������ �� �̺�Ʈ ó��
				if(soundButton.getIcon() == soundButtonOnEnteredImage)
					soundButton.setIcon(soundButtonOnImage); //Ŭ���׸�
				if(soundButton.getIcon() == soundButtonOffEnteredImage)
					soundButton.setIcon(soundButtonOffImage);
				soundButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //���콺�� �����������
			}
			@Override
			public void mousePressed(MouseEvent e) {//���콺�� �ش� ��ư�� ������ �� �̺�Ʈ ó��
				if(soundButton.getIcon() == soundButtonOnEnteredImage) { //��������� ����� ����
					soundButton.setIcon(soundButtonOffImage); //Ŭ���׸�
					backgroundMusic.close(); //������� ����
					character.setFocusable(true); //��ư�� ���� ������ �� �����
					character.requestFocus();
				}
				if(soundButton.getIcon() == soundButtonOffEnteredImage) {//������� ���Ұŵ� ����
					soundButton.setIcon(soundButtonOnImage);	
					backgroundMusic = new Music("background_music.mp3", true); //��������
					backgroundMusic.start(); //������� �����
					character.setFocusable(true);
					character.requestFocus();
				}
		
			}
		});
		
		add(soundButton);
		
		windowBar.setBounds(0,0,480,30);
		windowBar.addMouseListener(new MouseAdapter() {
			//������ ����, �Ʒ� MotionListener���� mouseMoved�� �ߴٰ� ���콺��ǥ�� �ǽð����� �ٲ�⵵ �ϰ�
			//â�� ������ ���� �̵��ϴ°� �´�
			@Override
			public void mousePressed(MouseEvent e) { 
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		windowBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				//���� ��ũ����ǥ��
				int x = e.getXOnScreen(); 
				int y = e.getYOnScreen();
				//����
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(windowBar);
		
		//���� �� pause�޴�
		pauseMenu = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(pauseMenuImage, 0, 0, null);
				paintComponents(g);
			}
		};
		pauseMenu.setBounds(93, 250, 312, 412);
		pauseMenu.setLayout(null);
		pauseMenu.setVisible(false);
		add(pauseMenu);

		//�����簳 ��ư
		resumeButton.setBounds(70, 90, 169, 82); 
		resumeButton.setBorderPainted(false);
		resumeButton.setContentAreaFilled(false);
		resumeButton.setFocusPainted(false);
		resumeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {//���콺�� �ش� ��ư ���� �ö���� �� �̺�Ʈ ó��
				resumeButton.setBounds(52, 80, 205, 99); 
				resumeButton.setIcon(resumeButtonEnteredImage); //Ŭ���׸�
				resumeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//���콺�� �հ��� �������
			}
			@Override
			public void mouseExited(MouseEvent e) {//���콺�� �ش� ��ư ���� ������ �� �̺�Ʈ ó��
				resumeButton.setBounds(70, 90, 169, 82); 
				resumeButton.setIcon(resumeButtonImage); //�����׸�
				resumeButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //���콺�� �����������
			}
			@Override
			public void mousePressed(MouseEvent e) {//���콺�� �ش� ��ư�� ������ �� �̺�Ʈ ó��
				Music buttonEnteredMusic = new Music("beep.mp3", false); //��ư�� Ŭ������ �� ���� 1���� ����
				buttonEnteredMusic.start();//���ǽ���
				pauseMenu.setVisible(false);//�Ͻ����� �޴� ȣ��
				character.setFocusable(true);
				character.requestFocus();
				pauseClick = false;//���� �簳�� ���� �Ͻ����� ����
				//cancel�Ǿ��� Ÿ�̸� �ٽ� ����
				enemyTimer = new Timer();
				enemyTT = new TimerTask() {
					@Override
					public void run() {
						enemy = new Enemy(character.getCharacterX(), character.getCharacterY(), character.getCharacter());
						enemylist.add(enemy);		
					}
				};
				
				enemyTimer.scheduleAtFixedRate(enemyTT, 0, 400);
				
				itemTimer = new Timer();
				itemTT = new TimerTask() {
					@Override
					public void run() {
						item = new Item();
						itemlist.add(item);
					}
				};
				
				itemTimer.scheduleAtFixedRate(itemTT, 5000, 10000);
			}
		});
		pauseMenu.add(resumeButton);
		
		//�ٽ��ϱ� ��ư
		replayButton.setBounds(70, 185, 169, 82); 
		replayButton.setBorderPainted(false);
		replayButton.setContentAreaFilled(false);
		replayButton.setFocusPainted(false);
		replayButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {//���콺�� �ش� ��ư ���� �ö���� �� �̺�Ʈ ó��
				replayButton.setBounds(52, 175, 205, 99); 
				replayButton.setIcon(replayButtonEnteredImage); //Ŭ���׸�
				replayButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//���콺�� �հ��� �������
			}
			@Override
			public void mouseExited(MouseEvent e) {//���콺�� �ش� ��ư ���� ������ �� �̺�Ʈ ó��
				replayButton.setBounds(70, 185, 169, 82); 
				replayButton.setIcon(replayButtonImage); //�����׸�
				replayButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //���콺�� �����������
			}
			@Override
			public void mousePressed(MouseEvent e) {//���콺�� �ش� ��ư�� ������ �� �̺�Ʈ ó��
				Music buttonEnteredMusic = new Music("beep.mp3", false); //��ư�� Ŭ������ �� ���� 1���� ����
				buttonEnteredMusic.start();//���ǽ���
				reset(); //���Ӹ��¸޼ҵ� ȣ��
				pauseMenu.setVisible(false);
				character.setFocusable(true);
				character.requestFocus();
				pauseClick = false; //�ٽý���������, �Ͻ����� Ǯ��
			}
		});
		pauseMenu.add(replayButton);
		
		//���θ޴��� ���ư��� ��ư
		mainmenuButton.setBounds(70, 280, 169, 82); 
		mainmenuButton.setBorderPainted(false);
		mainmenuButton.setContentAreaFilled(false);
		mainmenuButton.setFocusPainted(false);
		mainmenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {//���콺�� �ش� ��ư ���� �ö���� �� �̺�Ʈ ó��
				mainmenuButton.setBounds(52, 270, 205, 99); 
				mainmenuButton.setIcon(mainmenuButtonEnteredImage); //Ŭ���׸�
				mainmenuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//���콺�� �հ��� �������
			}
			@Override
			public void mouseExited(MouseEvent e) {//���콺�� �ش� ��ư ���� ������ �� �̺�Ʈ ó��
				mainmenuButton.setBounds(70, 280, 169, 82); 
				mainmenuButton.setIcon(mainmenuButtonImage); //�����׸�
				mainmenuButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //���콺�� �����������
			}
			@Override
			public void mousePressed(MouseEvent e) {//���콺�� �ش� ��ư�� ������ �� �̺�Ʈ ó��
				backgroundMusic.close();
				Music buttonEnteredMusic = new Music("beep.mp3", false); //��ư�� Ŭ������ �� ���� 1���� ����
				buttonEnteredMusic.start();//���ǽ���
				pauseMenu.setVisible(false); 
				mainMenu(); //���θ޴� ���� �޼ҵ�
				pauseClick = false; //�Ͻ����� Ǯ��
			}
		});
		pauseMenu.add(mainmenuButton);
		
		scoreLabel.setBounds(10, 30, 300, 70);
		scoreLabel.setFont(new Font(null, Font.BOLD, 31));
		scoreLabel.setForeground(Color.white);
		scoreLabel.setVisible(false);
		add(scoreLabel);
		
		// ���� �� �޴�
		gameEndMenu = new JPanel() {
			public void paint(Graphics g) {
				if(highScore < score) {
					g.drawImage(gameEndMenuHighScoreImage, 0, 0, null);
					paintComponents(g);		
				}	
				else {
					g.drawImage(gameEndMenuImage, 0, 0, null);
					paintComponents(g);
				}	
				
			}
		};
		gameEndMenu.setBounds(50, 250, 399, 358);
		gameEndMenu.setLayout(null);
		gameEndMenu.setVisible(false);
		add(gameEndMenu);

		//���� ������ ��, �������� ���
		gameEndscoreLabel.setBounds(80, 120, 300, 70);
		gameEndscoreLabel.setFont(new Font(null, Font.BOLD, 31));
		gameEndscoreLabel.setForeground(Color.black);
		gameEndMenu.add(gameEndscoreLabel);

		//����Ȯ�� â ok��ư���� ����
		gameEndOkButton.setBounds(130, 220, 169, 82);
		gameEndOkButton.setBorderPainted(false);
		gameEndOkButton.setContentAreaFilled(false);
		gameEndOkButton.setFocusPainted(false);
		gameEndOkButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ���콺�� �ش� ��ư ���� �ö���� �� �̺�Ʈ ó��
				gameEndOkButton.setBounds(112, 210, 205, 99);
				gameEndOkButton.setIcon(gameEndOkButtonEnteredImage); // Ŭ���׸�
				gameEndOkButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// ���콺�� �հ��� �������
			}

			@Override
			public void mouseExited(MouseEvent e) {// ���콺�� �ش� ��ư ���� ������ �� �̺�Ʈ ó��
				gameEndOkButton.setBounds(130, 200, 169, 82);
				gameEndOkButton.setIcon(gameEndOkButtonImage); // �����׸�
				gameEndOkButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ���콺�� �����������
			}

			@Override
			public void mousePressed(MouseEvent e) {// ���콺�� �ش� ��ư�� ������ �� �̺�Ʈ ó��
				Music buttonEnteredMusic = new Music("beep.mp3", false); // ��ư�� Ŭ������ �� ���� 1���� ����
				buttonEnteredMusic.start();// ���ǽ���
				gameEndMenu.setVisible(false); //���� �� �޴��� ����� retry �г� ȣ��
				retryMenu.setVisible(true);
				
				//�̰��� ���� �����, Ȯ���� ���� ��, ���� ���� �� �ְ����� ��ȯ�� �Ͼ	
				try {//���� ���� ����
					save.save(score);
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				try {//���� ���� ���� ���� �޾ƿ���
					highScore = save.loadHighScore();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		gameEndMenu.add(gameEndOkButton);

		// ���� ���� �̾ �ٽ��ϱ� �޴�
		retryMenu = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(retryMenuImage, 0, 0, null);
				paintComponents(g);
			}
		};
		retryMenu.setBounds(50, 280, 383, 295);
		retryMenu.setLayout(null);
		retryMenu.setVisible(false);
		add(retryMenu);

		//������ ������ �ٽ��ϱ� ��ư
		gameEndRetryButton.setBounds(60, 170, 120, 59);
		gameEndRetryButton.setBorderPainted(false);
		gameEndRetryButton.setContentAreaFilled(false);
		gameEndRetryButton.setFocusPainted(false);
		gameEndRetryButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ���콺�� �ش� ��ư ���� �ö���� �� �̺�Ʈ ó��
				gameEndRetryButton.setBounds(48, 170, 130, 63);
				gameEndRetryButton.setIcon(gameEndReplayButtonEnteredImage); // Ŭ���׸�
				gameEndRetryButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// ���콺�� �հ��� �������
			}

			@Override
			public void mouseExited(MouseEvent e) {// ���콺�� �ش� ��ư ���� ������ �� �̺�Ʈ ó��
				gameEndRetryButton.setBounds(60, 170, 120, 59);
				gameEndRetryButton.setIcon(gameEndReplayButtonImage); // �����׸�
				gameEndRetryButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ���콺�� �����������
			}

			@Override
			public void mousePressed(MouseEvent e) {// ���콺�� �ش� ��ư�� ������ �� �̺�Ʈ ó��
				Music buttonEnteredMusic = new Music("beep.mp3", false); // ��ư�� Ŭ������ �� ���� 1���� ����
				buttonEnteredMusic.start();// ���ǽ���
				reset(); // ���¸޼ҵ�
				pauseMenu.setVisible(false); //�гε� ���� ���ְ�
				retryMenu.setVisible(false);
				gameEnd = false; //������ �� ���� �缳��
				pauseClick = false; //�Ͻ����� ����
				character.setFocusable(true);
				character.requestFocus();
				
			}
		});
		retryMenu.add(gameEndRetryButton);

		//������ ������ ���θ޴��� ���� ��ư
		gameEndMainMenuButton.setBounds(200, 170, 120, 59);
		gameEndMainMenuButton.setBorderPainted(false);
		gameEndMainMenuButton.setContentAreaFilled(false);
		gameEndMainMenuButton.setFocusPainted(false);
		gameEndMainMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ���콺�� �ش� ��ư ���� �ö���� �� �̺�Ʈ ó��
				gameEndMainMenuButton.setBounds(188, 170, 130, 63);
				gameEndMainMenuButton.setIcon(gameEndMainMenuButtonEnteredImage); // Ŭ���׸�
				gameEndMainMenuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// ���콺�� �հ��� �������
			}

			@Override
			public void mouseExited(MouseEvent e) {// ���콺�� �ش� ��ư ���� ������ �� �̺�Ʈ ó��
				gameEndMainMenuButton.setBounds(200, 170, 120, 59);
				gameEndMainMenuButton.setIcon(gameEndMainMenuButtonImage); // �����׸�
				gameEndMainMenuButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ���콺�� �����������
			}

			@Override
			public void mousePressed(MouseEvent e) {// ���콺�� �ش� ��ư�� ������ �� �̺�Ʈ ó��
				backgroundMusic.close();
				Music buttonEnteredMusic = new Music("beep.mp3", false); //��ư�� Ŭ������ �� ���� 1���� ����
				buttonEnteredMusic.start();//���ǽ���
				mainMenu();//���θ޴� �޼ҵ� ȣ��
				retryMenu.setVisible(false); // �г� ���ְ�
				pauseClick = false; //�Ͻ����� Ǯ��
				gameEnd = false; //���� �� ���� �ʱ�ȭ ���ְ�
			}
		});
		retryMenu.add(gameEndMainMenuButton);
	}
	
	public void paint(Graphics g) {
		//����Ʈ�� ���� ���۸� ��� ���
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // �� ũ���� �̹����� ����� �ʱ�ȭ
		screenGraphic = screenImage.getGraphics(); // �� �̹����� �׷��Ȱ�ü����
		draw(screenGraphic); //�׷��ȿ� �׸� �׷��ΰ�
		g.drawImage(screenImage, 0, 0, null); //�׸�	
	}

	public void draw(Graphics g) {
		if(isMainScreen) {//����ȭ���� ����, ����Ÿ��Ʋ ȣ��
			g.drawImage(mainTitle, 0, 0, null);
		}
		if(isGameScreen) {//����ȭ�� �϶�
			if (pauseClick) { //�Ͻ��������
				//�� �ڸ��� �׷��ָ� �ȴ�.
				//�Ͻ������� ȭ���� �׷��ִ� ��
				g.drawImage(background1, (int) backgroundX, (int) backgroundY, null);
				g.drawImage(background2, (int) backgroundX, (int) background2Y, null);
				// ������
				for (int i = 0; i < itemlist.size(); i++) {
					item = (Item) itemlist.get(i);
					item.setPause(true);
					item.draw(g); 
					
				}
				// ��
				for (int i = 0; i < enemylist.size(); i++) {
					enemy = (Enemy) enemylist.get(i);
					enemy.setPause(true);
					enemy.draw(g);
				}

				character.draw(g); // ���� �Ʒ���
			} 
			else {//pauseClick�� �ƴ� ��
				if(gameEnd) {//���� ���� ���� ��
					//���� pauseó�� �ϴ� �Ͻ�����
					g.drawImage(background1, (int) backgroundX, (int) backgroundY, null);
					g.drawImage(background2, (int) backgroundX, (int) background2Y, null);
					// ������
					for (int i = 0; i < itemlist.size(); i++) {
						item = (Item) itemlist.get(i);
						item.setPause(true);
						item.draw(g);

					}
					// ��
					for (int i = 0; i < enemylist.size(); i++) {
						enemy = (Enemy) enemylist.get(i);
						enemy.setPause(true);
						enemy.draw(g);
					}

					character.draw(g); // ���� �Ʒ���
					character.setPause(true);
					
					gameEndscoreLabel.setText("Score : " + score);
					if(!gameEndMenu.isVisible()&&!retryMenu.isVisible()) {
						gameEndMenu.setVisible(true);
					}
					
				
				}
				else {//gameEnd�� �ƴ� ��
					//������� �Ϲ����� ����ȭ��
					try {
						Random random = new Random();
						// ��潺ũ��
						// Ÿ�̸Ӹ� �� �ʿ� ���� �Ϲ������� �̹��� 2���� �ݺ��ϴ� ���̱⿡ �̹����� ���� ��ũ�ѵǸ� �ʱ� ��ǥ�� �ǵ��ư���
						if (background2Y >= 0 && backgroundY >= Main.SCREEN_HEIGHT)
							backgroundY = 0 - Main.SCREEN_HEIGHT;
						if (backgroundY >= 0 && background2Y >= Main.SCREEN_HEIGHT)
							background2Y = 0 - Main.SCREEN_HEIGHT;
						g.drawImage(background1, (int) backgroundX, (int) (backgroundY += backgroundSpeed), null);
						g.drawImage(background2, (int) backgroundX, (int) (background2Y += backgroundSpeed), null);

						//�������� �׸���
						//�������� �׸� ���� ���� �����Ǿ� �ִ� �������� ���� �׸������� list�� ����Ѵ�
						for (int i = 0; i < itemlist.size(); i++) {
							item = (Item) itemlist.get(i);
							item.setPause(false);//item�� pasue������ �ִµ� �װ��� falseó��
							//�浹 ��� - ĳ���Ϳ� �浹��
							if (colison(item.getItemX(), item.getItemY(), character.getCharacterX(),
									character.getCharacterY(), item.getItem(), character.getCharacter())) {
								
								if (item.getEnemyX() != 0) {
									item.setIsAction(true); //�������� Action�ǵ��� �����Ѵ�
									// item�� ������ false �κ��� �ѹ� ����Ǿ�� �ϱ� ������ ���� �Ǿ������� true�� �ٲ��, 
									// false�� �ѹ� ����ȵǸ� 0,0���θ� ���ư���
								}

							
								//4�� �����۸� �����ϴ� ���� �ٸ��� ������ �и��صд�.
								if (item.getItemRandom() != 4) { //
									if (item.isAction()) { //�������� ���� ���̶��
										// �浹�� �� 3�� �ڿ� ������� Ÿ�̸�
										Item itemRemove = item; 
										Timer itemRemoveTimer = new Timer();
										TimerTask itemRemoveTT = new TimerTask() {
											@Override
											public void run() {
												itemlist.remove(itemRemove);
												//�뷡�� �ݾ��ش�
												if(itemRemove.getItemRandom() == 1) {
													itemRemove.item1Music.close();
												}
												if(itemRemove.getItemRandom() == 3) {
													itemRemove.item3Music.close();
												}
												
											}
										};

										itemRemoveTimer.schedule(itemRemoveTT, 3000);

									}
								}

								//����� ������ ����ź �������� ���� �����صд�.
								//�浹���� ��, ���� �� ��ǥ�� �������� ���� ���⿡ �����Ѵ�
								int j = random.nextInt(enemylist.size());
								if (item.getEnemyX() == 0) { // ���� ��ǥ ���� �����Ǿ� ���� ���� ��쿡��
									item.setEnemyX(enemylist.get(j).getEnemyX());
									item.setEnemyY(enemylist.get(j).getEnemyY());
								}

							}//�浹 �� ����
							
							if (item.getItemRandom() == 4) {
								// 4�� �������� �ٸ� ������� ó��
								if (item.getItemY() <= -100) { //ȭ�� ���� ����� �����ϵ���
									if (item.isAction()) {
										itemlist.remove(item);
									}
								}
							}

							//2�� ��ȣ�� �������� ���� �����ۿ� ĳ���� ��ǥ �� ����
							//��ȣ�� �������� �ǽð����� ĳ���� ��ǥ�� ���󰡾� �ϱ⿡ ���⿡ ����
							item.setCharacterX(character.getCharacterX());
							item.setCharacterY(character.getCharacterY());

							//����� ������ �浹�� �����Ѵ�.
							if (item.isAction()) { //�������� ���� ���� ��
								for (int x = 0; x < enemylist.size(); x++) { //�� �ϳ����� �浹����
									enemy = enemylist.get(x);
									enemy.setPause(false);
									//�浹�ߴٸ�
									if (colison(enemy.getEnemyX(), enemy.getEnemyY(), item.getItemX(), item.getItemY(),
											enemy.getEnemy(), item.getItemAction())) {

										enemylist.remove(enemy); //���� ������ ����
										enemy.getDieMusic().start(); //���� �׾��� ��, ���� ����
										score += 10; // ���� ����
									}
								}
							}
							item.draw(g); // ������ ���� ������ �ʵ��� ���� �ʱ� ���� ������ ���� ��

						}//item for�� ����

						// ���� �׸���. �����۰� ���������� list�� �׸�
						for (int i = 0; i < enemylist.size(); i++) {
							enemy = (Enemy) enemylist.get(i);
							enemy.setPause(false);
							if (enemy.getEnemyY() > 900 || enemy.getEnemyX() > 500 || enemy.getEnemyX() < -120)
								enemylist.remove(i); // ȭ�� ���� ���� �Ѿ�� ����
							enemy.draw(g);
							// ���� �ϳ��� �׸� ������ ĳ���Ϳ� �浹����
							if (colison(enemy.getEnemyX(), enemy.getEnemyY(), character.getCharacterX(),
									character.getCharacterY(), enemy.getEnemy(), character.getCharacter())) {
								gameEnd = true; //���� ������ ���� ����
								Music dieMusic = new Music("lady_bug_die_sound.mp3", false); 
								dieMusic.start();//�׾��� ��, ���� ����
							}
						}//��  for�� ����

						character.draw(g); // ���� �Ʒ���

						//������ũ��, ������ ������ ���ڴ� �׳� �ѰŰ�, ���ǽ��� ���� ����� �ٽñ׸���
						//&&�������� ������ ||�� �ϸ� ���� ������ �������� ���ǹ��� �ɷ�����
						if (cloud1X > Main.SCREEN_WIDTH && cloud1Y > Main.SCREEN_HEIGHT) {
							cloud1X = random.nextInt(100) - 200;
							cloud1Y = random.nextInt(500) - 500;
						}
						if (cloud2X > Main.SCREEN_WIDTH && cloud2Y > Main.SCREEN_HEIGHT) {
							cloud2X = random.nextInt(100) - 400;
							cloud2Y = random.nextInt(500) - 500;
						}
						if (cloud3X < -Main.SCREEN_WIDTH && cloud3Y > Main.SCREEN_HEIGHT) {
							cloud3X = random.nextInt(100) + 500;
							cloud3Y = random.nextInt(500) - 500;
						}
						//������ �׳� ���ȭ�� �ӵ��� �����
						g.drawImage(cloud1, (int) (cloud1X += backgroundSpeed), (int) (cloud1Y += backgroundSpeed),
								null);
						g.drawImage(cloud2, (int) (cloud2X += backgroundSpeed), (int) (cloud2Y += backgroundSpeed),
								null);
						g.drawImage(cloud3, (int) (cloud3X -= backgroundSpeed), (int) (cloud3Y += backgroundSpeed),
								null);

						// ���� �ǽð� ����
						scoreLabel.setText("score : " + score);
						score++;
						Thread.sleep(1000 / 30);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}//���� ���ᰡ �ƴ� ��(���� ȭ��), ��
			}//�Ͻ������� �ƴ� �� ,��

		} //isGameScreen ��
		if(isScoreScreen) {
			g.drawImage(scoreBoard, 0, 0, null);
		}
		paintComponents(g); //������Ʈ�� �̹������� �׷��ش�.
		repaint();
	}
	
	public void reset() {//���� �ٽ��ϱ� ���
		score = 0; // ���� �ʱ�ȭ
		for(int i = 0; i<itemlist.size(); i++) { //������ �Ҹ��� ����.
			if(itemlist.get(i).item1Music.isRunning()) {
				itemlist.get(i).item1Music.close();
			}
			if(itemlist.get(i).item3Music.isRunning()) {
				itemlist.get(i).item3Music.close();
			}
			if(itemlist.get(i).item3MusicTransition.isRunning()) {
				itemlist.get(i).item3MusicTransition.close();
			}
			if(itemlist.get(i).item4Music.isRunning()) {
				itemlist.get(i).item4Music.close();
			}
		}
		itemlist.clear(); //���� �������� �������ڸ��� �����Ǽ� �������
		enemylist.clear();
		itemTimer.cancel(); //Ÿ�̸� ĵ��
		enemyTimer.cancel();
		character.setCharacterX(215); //ĳ���� ���� ��ġ
		character.setCharacterY(Main.SCREEN_HEIGHT - 100);
		character.setPause(false);
		
		enemyTimer = new Timer(); //����ϰ� Ÿ�̸� �缳��
		enemyTT = new TimerTask() {
			@Override
			public void run() {
				enemy = new Enemy(character.getCharacterX(), character.getCharacterY(), character.getCharacter());
				enemylist.add(enemy);
				
			}
		};
		
		enemyTimer.scheduleAtFixedRate(enemyTT, 0, 400);
		
		itemTimer = new Timer();
		itemTT = new TimerTask() {
			@Override
			public void run() {
				item = new Item();
				itemlist.add(item);
			}
		};
		
		itemTimer.scheduleAtFixedRate(itemTT, 5000, 10000);
	}
	
	public void mainMenu() {//���θ޴� ��ư ���
		isMainScreen = true; //ȭ�� ��ȯ
		isGameScreen = false;
		gameStartButton.setVisible(true);
		scoreButton.setVisible(true);
		pauseButton.setVisible(false);
		soundButton.setVisible(false);
		scoreLabel.setVisible(false);
		
		score = 0; //�� �Ʒ��� reset�� ����
		for(int i = 0; i<itemlist.size(); i++) {
			if(itemlist.get(i).item1Music.isRunning()) {
				itemlist.get(i).item1Music.close();
			}
			if(itemlist.get(i).item3Music.isRunning()) {
				itemlist.get(i).item3Music.close();
			}
			if(itemlist.get(i).item3MusicTransition.isRunning()) {
				itemlist.get(i).item3MusicTransition.close();
			}
			if(itemlist.get(i).item4Music.isRunning()) {
				itemlist.get(i).item4Music.close();
			}
		}
		itemlist.clear(); //���� �������� �������ڸ��� �����Ǽ� �������
		enemylist.clear();
		itemTimer.cancel();
		enemyTimer.cancel();
		character = null; //ĳ���ʹ� �����
	}
	
	public boolean colison(int x1, int y1, int x2, int y2, Image image1, Image image2) { //���� �浹 ����
	//�����δ� �� ��(Character or Enemy or Item)�� ���� ��ġ�� �ǽð����� �޾Ƽ� �� ���� �Ÿ��� �������� ���Ѵ�. 
	//�׸��� �� ���� �������� ���̸� ���ؼ� �� �� ������ ������ �浹�̶�� �ν��Ѵ�.	
		int x = x2 - x1;  //x�� ����
		int y = y2 - y1;  //y�� ����
		int length = (int)Math.sqrt((x*x + y*y)); //z�� ����
		
		int sum = (image1.getWidth(null)/ 2) + (image2.getWidth(null)/ 2); //�� �̹��� �������� ��
		
		if(length < sum) return true; //���������� z�� ���̰� ������ �浹
		
		return false; //�ƴ� ����
	}
	
	//getter and setter
	public boolean isMainScreen() {
		return isMainScreen;
	}

	public void setMainScreen(boolean isMainScreen) {
		this.isMainScreen = isMainScreen;
	}

	public boolean isGameScreen() {
		return isGameScreen;
	}

	public void setGameScreen(boolean isGameScreen) {
		this.isGameScreen = isGameScreen;
	}

	public boolean isScoreScreen() {
		return isScoreScreen;
	}

	public void setScoreScreen(boolean isScoreScreen) {
		this.isScoreScreen = isScoreScreen;
	}
}