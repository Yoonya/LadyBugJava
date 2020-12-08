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

	// 더블 버퍼 기법을 사용하기 위함(이미지를 버퍼에 두고 계속 호출하는 기법)
	private Image screenImage; 
	private Graphics screenGraphic;
	//이미지(패널에 사용)
	private Image mainTitle = new ImageIcon(Main.class.getResource("../images/MainTitle.png"))
			.getImage(); //메인 타이틀 이미지
	private Image scoreBoard = new ImageIcon(Main.class.getResource("../images/scoreboard.png"))
			.getImage(); //메인 타이틀 이미지
	private Image pauseMenuImage = new ImageIcon(Main.class.getResource("../images/pause_frame.png"))
			.getImage(); //일시정지 버튼 눌렀을 때, 이미지
	private Image gameEndMenuImage = new ImageIcon(Main.class.getResource("../images/game_over_frame.png"))
			.getImage(); //게임이 끝났을 때, 메뉴 이미지
	private Image gameEndMenuHighScoreImage = new ImageIcon(Main.class.getResource("../images/game_over_new_record_frame.png"))
			.getImage(); //게임이 끝났을 때, 최고점수 달성 시 메뉴 이미지 //이거 이미지 넣고 고쳐
	private Image retryMenuImage = new ImageIcon(Main.class.getResource("../images/retry_frame.png"))
			.getImage(); //게임이 끝났을 때, 확인버튼 누르고 난 이후, 다시하기와 종료버튼이 뜨게하는 이미지
	private Image background1 = new ImageIcon(Main.class.getResource("../images/background.png"))
			.getImage(); //배경이미지1
	private Image background2 = new ImageIcon(Main.class.getResource("../images/background2.png"))
			.getImage(); //배경이미지2
	private Image cloud1 = new ImageIcon(Main.class.getResource("../images/cloud.png"))
			.getImage(); //배경만 넣기 심심해서 들어가야할 구름1
	private Image cloud2 = new ImageIcon(Main.class.getResource("../images/cloud2.png"))
			.getImage(); //배경만 넣기 심심해서 들어가야할 구름2
	private Image cloud3 = new ImageIcon(Main.class.getResource("../images/cloud3.png"))
			.getImage(); //배경만 넣기 심심해서 들어가야할 구름3
	//이미지 아이콘들, 여러 컴포넌트들에 사용
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
	
	//배경 그리기 변수
	private float backgroundSpeed = 0.5f; //배경 스크롤 속도
	private float cloud1X = 0; //구름 1, 2, 3번의 최초 시작 지점
	private float cloud2X = -200;
	private float cloud3X = 600;
	private float cloud1Y = 0;
	private float cloud2Y = 0;
	private float cloud3Y = 0;
	private float backgroundX = 0; //배경 이미지들의 최초 시작 시점
	private float backgroundY = 0;
	private float background2Y = 0 - Main.SCREEN_HEIGHT;
	//boolean값
	private boolean isMainScreen = true; //메인 타이틀 화면
	private boolean isGameScreen = false; //게임 실행 창 화면
	private boolean isScoreScreen = false; //점수 보기 화면 
	//컴포넌트
	private JLabel windowBar = new JLabel(windowBarImage); //버그를 고치다보니 넣은 윈도우바
	private JButton exitButton = new JButton(exitButtonImage); //메인화면과 게임화면에 넣을 버튼들
	private JButton gameStartButton = new JButton(gameStartButtonImage);
	private JButton scoreButton = new JButton(scoreButtonImage);
	private JButton scoreBackButton = new JButton(scoreBoardBackButtonImage);
	private JButton pauseButton = new JButton(pauseButtonImage);
	private JButton soundButton = new JButton(soundButtonOnImage);
	private JPanel pauseMenu; //일시정지를 누를 때, 나오는 패널
	private JPanel gameEndMenu; //게임이 끝났을 때, 나오는 패널
	private JPanel retryMenu; //게임이 끝났을 때, 확인을 누른 후, 나오는 패널
	private JButton resumeButton = new JButton(resumeButtonImage); //위 패널들에 들어가는 여러 버튼들
	private JButton replayButton = new JButton(replayButtonImage);
	private JButton mainmenuButton = new JButton(mainmenuButtonImage);
	private JButton gameEndOkButton = new JButton(gameEndOkButtonImage);
	private JButton gameEndRetryButton = new JButton(gameEndReplayButtonImage);
	private JButton gameEndMainMenuButton = new JButton(gameEndMainMenuButtonImage);
	private JLabel gameEndscoreLabel = new JLabel(); //게임이 끝났을 때, 나오는 최종 점수
	private JLabel scoreLabel = new JLabel("score : 0"); //게임진행 중일때, 계속 출력되고 있는 점수

	//windowBar를 추가하게 되었기 때문에, 마우스 좌표 받을 것
	private int mouseX, mouseY;
	//다른 클래스
	private Character character; //캐릭터 클래스
	private Enemy enemy; //적 클래스
	private ArrayList<Enemy> enemylist; //적들을 한번에 그리고 관리하기 위해 연결리스트 선언
	private Item item; //아이템 클래스
	private ArrayList<Item> itemlist; //아이템을 한번에 그리고 관리하기 위해 연결리스트 선언
	private Music backgroundMusic; //배경음악 선언
	private Save save; //점수기록 관리
	private ArrayList scoreList = new ArrayList<>(); //점수들 저장
	private ArrayList<JLabel> scoreListLabel = new ArrayList<>();//점수들 화면에 보여줄 것
	//변수
	private int getItemX, getItemY, getItemRandom; //아이템의 x, y좌표와 item number를 받아온다
	private int score = 0; // 점수는 0부터 시작
	private int highScore = 0; // 최초 최고점수는 0부터 시작
	private boolean pauseClick = false; //게임 일시정지
	private boolean gameEnd = false; //게임 끝
	//타이머
	private Timer enemyTimer; //적 생성 타이머
	private TimerTask enemyTT; 
	private Timer itemTimer; //아이템 생성 타이머
	private TimerTask itemTT;
	
	public LadyBug() { 
		// 발생한 오류 paint로는 버튼을 못 그려냄 -> paintComponents로 그리니 배경을 못그려냄 -> setBackground(new Color(0,0,0,0))로
		// paintComponents 할 때마다 뒷배경을 회색이 아닌 흰색으로 만들 필요가 있다고 하는 경우 ->
		// 그래서 추가했더니, Frame is decorated 오류 ->  setUndecorated로 지워버림 ->
		// 결국 windowBar 까지 구현하게 됨
		setUndecorated(true);
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setLayout(null); //절대좌표 지정
		setResizable(false); 
		setLocationRelativeTo(null); //실행시킬 때, 화면 정중앙으로
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setBackground(new Color(0,0,0,0)); // 배경 투명으로 설정
		setTitle("LadyBug");
		setVisible(true);	
		
		//게임이 시작될 때, 최고점수 불러오기
		try {//현재 가장 높은 점수 받아오기
			highScore = save.loadHighScore();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		//기본적으로 JButton은 기본 모양 템플릿이 있어서 바꿔줘야한다.
		//Border 없애주고, 내용영역 채우기 안하고, 선택되었을 때 테두리 생기는거 없앰
		exitButton.setBounds(450, 0, 30, 30); 
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {//마우스가 해당 버튼 위에 올라왔을 때 이벤트 처리
				exitButton.setIcon(exitButtonEnteredImage); //클릭그림
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//마우스가 손가락 모양으로
			}
			@Override
			public void mouseExited(MouseEvent e) {//마우스가 해당 버튼 위에 나갔을 때 이벤트 처리
				exitButton.setIcon(exitButtonImage); //원래그림
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //마우스를 원래모양으로
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스가 해당 버튼을 눌렀을 때 이벤트 처리
				System.exit(0); //종료
			}
		});
		
		add(exitButton);
		
		//게임시작 버튼
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
				isMainScreen = false; //메인스크린과 게임스크린의 전환
				isGameScreen = true;
				gameStartButton.setVisible(false); //여러기능들을 끄고 킨다.
				scoreButton.setVisible(false);
				pauseButton.setVisible(true);
				soundButton.setVisible(true);
				scoreLabel.setVisible(true);
				backgroundMusic = new Music("background_music.mp3", true); //게임음악
				backgroundMusic.start(); //배경음악 시작
				Music buttonEnteredMusic = new Music("beep.mp3", false); //버튼 클릭 소리
				buttonEnteredMusic.start();//효과음시작
				
				character = new Character(); //캐릭터 선언
				add(character);//캐릭터가 panel이라 add	
				//character.setFocusable(true);
				//character.requestFocus();
				
				//최초 적 생성은 직접 초기화 해주고 초가시켜준다.
				enemy = new Enemy(character.getCharacterX(), character.getCharacterY(), character.getCharacter());
				enemylist = new ArrayList<>();
				enemylist.add(enemy);
				//타이머 시작
				enemyTimer = new Timer();
				enemyTT = new TimerTask() {
					@Override
					public void run() {
						//시간마다 적을 생성해서 리스트에 추가
						enemy = new Enemy(character.getCharacterX(), character.getCharacterY(), character.getCharacter());
						enemylist.add(enemy);
					}
				};
				//현재는 0.4초당 1개 생성으로 설정
				enemyTimer.scheduleAtFixedRate(enemyTT, 0, 400);
				
				//아이템도 마찬가지
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
				//현재는 5초 후에 한번 최초로 나오고 10초마다 나오는 것으로 설정
				itemTimer.scheduleAtFixedRate(itemTT, 5000, 10000);
							
				character.setFocusable(true);//캐릭터 포커스 재설정
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
			public void mouseEntered(MouseEvent e) {//마우스가 해당 버튼 위에 올라왔을 때 이벤트 처리
				scoreButton.setIcon(scoreButtonEnteredImage); //클릭그림
				scoreButton.setBounds(270, 510, 205, 99);
				scoreButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//마우스가 손가락 모양으로
			}
			@Override
			public void mouseExited(MouseEvent e) {//마우스가 해당 버튼 위에 나갔을 때 이벤트 처리
				scoreButton.setIcon(scoreButtonImage); //원래그림
				scoreButton.setBounds(290, 520, 169, 82);
				scoreButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //마우스를 원래모양으로
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스가 해당 버튼을 눌렀을 때 이벤트 처리
				Music buttonEnteredMusic = new Music("beep.mp3", false); //버튼을 클릭했을 때 음악 1번만 실행
				buttonEnteredMusic.start();//음악시작
				isMainScreen = false; //점수보기 창으로 전환
				isScoreScreen = true;
				gameStartButton.setVisible(false);
				scoreButton.setVisible(false);
				scoreBackButton.setVisible(true);
				
				//이곳은 파일 입출력, 파일에서 점수가 기록된 것을 받아옴
				try {
					scoreList = save.loadScore();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				
				for(int i = 0; i<scoreList.size(); i++) {
					if(i == 8) break; //최대 8등까지 지원
					JLabel label = new JLabel(i+1 + "               " + scoreList.get(i));//점수 보이기
					label.setFont(new Font("궁서", Font.BOLD, 31));//점수 설정
					label.setForeground(Color.black); //설정
					label.setBounds(120, 230+60*(i+1), 300, 70); //위치 대충 설정
					add(label); // 넣고
					scoreListLabel.add(label); //관리하게 쉽게 리스트에 저장
				}
			}
		});

		add(scoreButton);
		
		scoreBackButton.setBounds(10, 40, 52, 38); //점수보기에서 뒤로가기 버튼
		scoreBackButton.setBorderPainted(false);
		scoreBackButton.setContentAreaFilled(false);
		scoreBackButton.setFocusPainted(false);
		scoreBackButton.setVisible(false);
		scoreBackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {//마우스가 해당 버튼 위에 올라왔을 때 이벤트 처리
				scoreBackButton.setIcon(scoreBoardBackButtonEnteredImage); //클릭그림
				scoreBackButton.setBounds(10, 40, 52, 38);
				scoreBackButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//마우스가 손가락 모양으로
			}
			@Override
			public void mouseExited(MouseEvent e) {//마우스가 해당 버튼 위에 나갔을 때 이벤트 처리
				scoreBackButton.setIcon(scoreBoardBackButtonImage); //원래그림
				scoreBackButton.setBounds(10, 40, 52, 38);
				scoreBackButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //마우스를 원래모양으로
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스가 해당 버튼을 눌렀을 때 이벤트 처리
				Music buttonEnteredMusic = new Music("beep.mp3", false); //버튼을 클릭했을 때 음악 1번만 실행
				buttonEnteredMusic.start();//음악시작
				isMainScreen = true; //점수보기 창으로 전환
				isScoreScreen = false;
				gameStartButton.setVisible(true);
				scoreButton.setVisible(true);
				scoreBackButton.setVisible(false);
				
				for(int i = 0; i<scoreListLabel.size(); i++) {
					scoreListLabel.get(i).setVisible(false);
				}
				scoreListLabel.clear();//점수보기에 썼던 리스트 초기화
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
			public void mouseEntered(MouseEvent e) {//마우스가 해당 버튼 위에 올라왔을 때 이벤트 처리
				pauseButton.setIcon(pauseButtonEnteredImage); //클릭그림
				pauseButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//마우스가 손가락 모양으로
			}
			@Override
			public void mouseExited(MouseEvent e) {//마우스가 해당 버튼 위에 나갔을 때 이벤트 처리
				pauseButton.setIcon(pauseButtonImage); //원래그림
				pauseButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //마우스를 원래모양으로
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스가 해당 버튼을 눌렀을 때 이벤트 처리
				Music buttonEnteredMusic = new Music("beep.mp3", false); //버튼을 클릭했을 때 음악 1번만 실행
				buttonEnteredMusic.start();//음악시작
				pauseMenu.setVisible(true); //일시정지 패널 호출
				pauseClick = true; //게임 일시정지 
				enemyTimer.cancel(); //일시정지를 위한 타이머 캔슬
				itemTimer.cancel();
			}
		});
		
		add(pauseButton);
		
		//배경화면을 음소거하기 위한 버튼
		soundButton.setBounds(320, 30, 76, 74); 
		soundButton.setBorderPainted(false);
		soundButton.setContentAreaFilled(false);
		soundButton.setFocusPainted(false);
		soundButton.setVisible(false);
		soundButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {//마우스가 해당 버튼 위에 올라왔을 때 이벤트 처리
				if(soundButton.getIcon() == soundButtonOnImage)
					soundButton.setIcon(soundButtonOnEnteredImage); //클릭그림
				if(soundButton.getIcon() == soundButtonOffImage)
					soundButton.setIcon(soundButtonOffEnteredImage);
				soundButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//마우스가 손가락 모양으로
			}
			@Override
			public void mouseExited(MouseEvent e) {//마우스가 해당 버튼 위에 나갔을 때 이벤트 처리
				if(soundButton.getIcon() == soundButtonOnEnteredImage)
					soundButton.setIcon(soundButtonOnImage); //클릭그림
				if(soundButton.getIcon() == soundButtonOffEnteredImage)
					soundButton.setIcon(soundButtonOffImage);
				soundButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //마우스를 원래모양으로
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스가 해당 버튼을 눌렀을 때 이벤트 처리
				if(soundButton.getIcon() == soundButtonOnEnteredImage) { //배경음악이 실행된 상태
					soundButton.setIcon(soundButtonOffImage); //클릭그림
					backgroundMusic.close(); //배경음악 종료
					character.setFocusable(true); //버튼을 누를 때마다 해 줘야함
					character.requestFocus();
				}
				if(soundButton.getIcon() == soundButtonOffEnteredImage) {//배경음악 음소거된 상태
					soundButton.setIcon(soundButtonOnImage);	
					backgroundMusic = new Music("background_music.mp3", true); //게임음악
					backgroundMusic.start(); //배경음악 재시작
					character.setFocusable(true);
					character.requestFocus();
				}
		
			}
		});
		
		add(soundButton);
		
		windowBar.setBounds(0,0,480,30);
		windowBar.addMouseListener(new MouseAdapter() {
			//눌렀을 때만, 아래 MotionListener에서 mouseMoved에 했다간 마우스좌표가 실시간으로 바뀌기도 하고
			//창은 눌렀을 때만 이동하는게 맞다
			@Override
			public void mousePressed(MouseEvent e) { 
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		windowBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				//현재 스크린좌표들
				int x = e.getXOnScreen(); 
				int y = e.getYOnScreen();
				//설정
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(windowBar);
		
		//게임 내 pause메뉴
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

		//게임재개 버튼
		resumeButton.setBounds(70, 90, 169, 82); 
		resumeButton.setBorderPainted(false);
		resumeButton.setContentAreaFilled(false);
		resumeButton.setFocusPainted(false);
		resumeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {//마우스가 해당 버튼 위에 올라왔을 때 이벤트 처리
				resumeButton.setBounds(52, 80, 205, 99); 
				resumeButton.setIcon(resumeButtonEnteredImage); //클릭그림
				resumeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//마우스가 손가락 모양으로
			}
			@Override
			public void mouseExited(MouseEvent e) {//마우스가 해당 버튼 위에 나갔을 때 이벤트 처리
				resumeButton.setBounds(70, 90, 169, 82); 
				resumeButton.setIcon(resumeButtonImage); //원래그림
				resumeButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //마우스를 원래모양으로
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스가 해당 버튼을 눌렀을 때 이벤트 처리
				Music buttonEnteredMusic = new Music("beep.mp3", false); //버튼을 클릭했을 때 음악 1번만 실행
				buttonEnteredMusic.start();//음악시작
				pauseMenu.setVisible(false);//일시정지 메뉴 호출
				character.setFocusable(true);
				character.requestFocus();
				pauseClick = false;//게임 재개를 위해 일시정지 제거
				//cancel되었던 타이머 다시 시작
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
		
		//다시하기 버튼
		replayButton.setBounds(70, 185, 169, 82); 
		replayButton.setBorderPainted(false);
		replayButton.setContentAreaFilled(false);
		replayButton.setFocusPainted(false);
		replayButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {//마우스가 해당 버튼 위에 올라왔을 때 이벤트 처리
				replayButton.setBounds(52, 175, 205, 99); 
				replayButton.setIcon(replayButtonEnteredImage); //클릭그림
				replayButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//마우스가 손가락 모양으로
			}
			@Override
			public void mouseExited(MouseEvent e) {//마우스가 해당 버튼 위에 나갔을 때 이벤트 처리
				replayButton.setBounds(70, 185, 169, 82); 
				replayButton.setIcon(replayButtonImage); //원래그림
				replayButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //마우스를 원래모양으로
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스가 해당 버튼을 눌렀을 때 이벤트 처리
				Music buttonEnteredMusic = new Music("beep.mp3", false); //버튼을 클릭했을 때 음악 1번만 실행
				buttonEnteredMusic.start();//음악시작
				reset(); //게임리셋메소드 호출
				pauseMenu.setVisible(false);
				character.setFocusable(true);
				character.requestFocus();
				pauseClick = false; //다시시작했으니, 일시정지 풀기
			}
		});
		pauseMenu.add(replayButton);
		
		//메인메뉴로 돌아가는 버튼
		mainmenuButton.setBounds(70, 280, 169, 82); 
		mainmenuButton.setBorderPainted(false);
		mainmenuButton.setContentAreaFilled(false);
		mainmenuButton.setFocusPainted(false);
		mainmenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {//마우스가 해당 버튼 위에 올라왔을 때 이벤트 처리
				mainmenuButton.setBounds(52, 270, 205, 99); 
				mainmenuButton.setIcon(mainmenuButtonEnteredImage); //클릭그림
				mainmenuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//마우스가 손가락 모양으로
			}
			@Override
			public void mouseExited(MouseEvent e) {//마우스가 해당 버튼 위에 나갔을 때 이벤트 처리
				mainmenuButton.setBounds(70, 280, 169, 82); 
				mainmenuButton.setIcon(mainmenuButtonImage); //원래그림
				mainmenuButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //마우스를 원래모양으로
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스가 해당 버튼을 눌렀을 때 이벤트 처리
				backgroundMusic.close();
				Music buttonEnteredMusic = new Music("beep.mp3", false); //버튼을 클릭했을 때 음악 1번만 실행
				buttonEnteredMusic.start();//음악시작
				pauseMenu.setVisible(false); 
				mainMenu(); //메인메뉴 복귀 메소드
				pauseClick = false; //일시정지 풀고
			}
		});
		pauseMenu.add(mainmenuButton);
		
		scoreLabel.setBounds(10, 30, 300, 70);
		scoreLabel.setFont(new Font(null, Font.BOLD, 31));
		scoreLabel.setForeground(Color.white);
		scoreLabel.setVisible(false);
		add(scoreLabel);
		
		// 게임 끝 메뉴
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

		//게임 끝났을 때, 최종점수 출력
		gameEndscoreLabel.setBounds(80, 120, 300, 70);
		gameEndscoreLabel.setFont(new Font(null, Font.BOLD, 31));
		gameEndscoreLabel.setForeground(Color.black);
		gameEndMenu.add(gameEndscoreLabel);

		//점수확인 창 ok버튼으로 생각
		gameEndOkButton.setBounds(130, 220, 169, 82);
		gameEndOkButton.setBorderPainted(false);
		gameEndOkButton.setContentAreaFilled(false);
		gameEndOkButton.setFocusPainted(false);
		gameEndOkButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우스가 해당 버튼 위에 올라왔을 때 이벤트 처리
				gameEndOkButton.setBounds(112, 210, 205, 99);
				gameEndOkButton.setIcon(gameEndOkButtonEnteredImage); // 클릭그림
				gameEndOkButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우스가 손가락 모양으로
			}

			@Override
			public void mouseExited(MouseEvent e) {// 마우스가 해당 버튼 위에 나갔을 때 이벤트 처리
				gameEndOkButton.setBounds(130, 200, 169, 82);
				gameEndOkButton.setIcon(gameEndOkButtonImage); // 원래그림
				gameEndOkButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스를 원래모양으로
			}

			@Override
			public void mousePressed(MouseEvent e) {// 마우스가 해당 버튼을 눌렀을 때 이벤트 처리
				Music buttonEnteredMusic = new Music("beep.mp3", false); // 버튼을 클릭했을 때 음악 1번만 실행
				buttonEnteredMusic.start();// 음악시작
				gameEndMenu.setVisible(false); //게임 끝 메뉴는 지우고 retry 패널 호출
				retryMenu.setVisible(true);
				
				//이곳은 파일 입출력, 확인을 누를 때, 점수 저장 및 최고점수 변환이 일어남	
				try {//현재 점수 저장
					save.save(score);
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				try {//현재 가장 높은 점수 받아오기
					highScore = save.loadHighScore();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		gameEndMenu.add(gameEndOkButton);

		// 게임 끝과 이어서 다시하기 메뉴
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

		//게임이 끝나고 다시하기 버튼
		gameEndRetryButton.setBounds(60, 170, 120, 59);
		gameEndRetryButton.setBorderPainted(false);
		gameEndRetryButton.setContentAreaFilled(false);
		gameEndRetryButton.setFocusPainted(false);
		gameEndRetryButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우스가 해당 버튼 위에 올라왔을 때 이벤트 처리
				gameEndRetryButton.setBounds(48, 170, 130, 63);
				gameEndRetryButton.setIcon(gameEndReplayButtonEnteredImage); // 클릭그림
				gameEndRetryButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우스가 손가락 모양으로
			}

			@Override
			public void mouseExited(MouseEvent e) {// 마우스가 해당 버튼 위에 나갔을 때 이벤트 처리
				gameEndRetryButton.setBounds(60, 170, 120, 59);
				gameEndRetryButton.setIcon(gameEndReplayButtonImage); // 원래그림
				gameEndRetryButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스를 원래모양으로
			}

			@Override
			public void mousePressed(MouseEvent e) {// 마우스가 해당 버튼을 눌렀을 때 이벤트 처리
				Music buttonEnteredMusic = new Music("beep.mp3", false); // 버튼을 클릭했을 때 음악 1번만 실행
				buttonEnteredMusic.start();// 음악시작
				reset(); // 리셋메소드
				pauseMenu.setVisible(false); //패널들 전부 없애고
				retryMenu.setVisible(false);
				gameEnd = false; //게임이 끝 변수 재설정
				pauseClick = false; //일시정지 해제
				character.setFocusable(true);
				character.requestFocus();
				
			}
		});
		retryMenu.add(gameEndRetryButton);

		//게임이 끝나고 메인메뉴로 가는 버튼
		gameEndMainMenuButton.setBounds(200, 170, 120, 59);
		gameEndMainMenuButton.setBorderPainted(false);
		gameEndMainMenuButton.setContentAreaFilled(false);
		gameEndMainMenuButton.setFocusPainted(false);
		gameEndMainMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우스가 해당 버튼 위에 올라왔을 때 이벤트 처리
				gameEndMainMenuButton.setBounds(188, 170, 130, 63);
				gameEndMainMenuButton.setIcon(gameEndMainMenuButtonEnteredImage); // 클릭그림
				gameEndMainMenuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우스가 손가락 모양으로
			}

			@Override
			public void mouseExited(MouseEvent e) {// 마우스가 해당 버튼 위에 나갔을 때 이벤트 처리
				gameEndMainMenuButton.setBounds(200, 170, 120, 59);
				gameEndMainMenuButton.setIcon(gameEndMainMenuButtonImage); // 원래그림
				gameEndMainMenuButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스를 원래모양으로
			}

			@Override
			public void mousePressed(MouseEvent e) {// 마우스가 해당 버튼을 눌렀을 때 이벤트 처리
				backgroundMusic.close();
				Music buttonEnteredMusic = new Music("beep.mp3", false); //버튼을 클릭했을 때 음악 1번만 실행
				buttonEnteredMusic.start();//음악시작
				mainMenu();//메인메뉴 메소드 호출
				retryMenu.setVisible(false); // 패널 없애고
				pauseClick = false; //일시정지 풀고
				gameEnd = false; //게임 끝 변수 초기화 해주고
			}
		});
		retryMenu.add(gameEndMainMenuButton);
	}
	
	public void paint(Graphics g) {
		//페인트는 더블 버퍼링 기법 사용
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 이 크기의 이미지를 만들어 초기화
		screenGraphic = screenImage.getGraphics(); // 위 이미지의 그래픽객체생성
		draw(screenGraphic); //그래픽에 그림 그려두고
		g.drawImage(screenImage, 0, 0, null); //그림	
	}

	public void draw(Graphics g) {
		if(isMainScreen) {//메인화면일 때는, 메인타이틀 호출
			g.drawImage(mainTitle, 0, 0, null);
		}
		if(isGameScreen) {//게임화면 일때
			if (pauseClick) { //일시정지기능
				//그 자리에 그려주면 된다.
				//일시정지된 화면을 그려주는 것
				g.drawImage(background1, (int) backgroundX, (int) backgroundY, null);
				g.drawImage(background2, (int) backgroundX, (int) background2Y, null);
				// 아이템
				for (int i = 0; i < itemlist.size(); i++) {
					item = (Item) itemlist.get(i);
					item.setPause(true);
					item.draw(g); 
					
				}
				// 적
				for (int i = 0; i < enemylist.size(); i++) {
					enemy = (Enemy) enemylist.get(i);
					enemy.setPause(true);
					enemy.draw(g);
				}

				character.draw(g); // 구름 아래로
			} 
			else {//pauseClick이 아닐 때
				if(gameEnd) {//게임 종료 했을 때
					//위의 pause처럼 일단 일시정지
					g.drawImage(background1, (int) backgroundX, (int) backgroundY, null);
					g.drawImage(background2, (int) backgroundX, (int) background2Y, null);
					// 아이템
					for (int i = 0; i < itemlist.size(); i++) {
						item = (Item) itemlist.get(i);
						item.setPause(true);
						item.draw(g);

					}
					// 적
					for (int i = 0; i < enemylist.size(); i++) {
						enemy = (Enemy) enemylist.get(i);
						enemy.setPause(true);
						enemy.draw(g);
					}

					character.draw(g); // 구름 아래로
					character.setPause(true);
					
					gameEndscoreLabel.setText("Score : " + score);
					if(!gameEndMenu.isVisible()&&!retryMenu.isVisible()) {
						gameEndMenu.setVisible(true);
					}
					
				
				}
				else {//gameEnd가 아닐 때
					//여기부터 일반적인 게임화면
					try {
						Random random = new Random();
						// 배경스크롤
						// 타이머를 할 필요 없이 일반적으로 이미지 2개가 반복하는 것이기에 이미지가 전부 스크롤되면 초기 좌표로 되돌아간다
						if (background2Y >= 0 && backgroundY >= Main.SCREEN_HEIGHT)
							backgroundY = 0 - Main.SCREEN_HEIGHT;
						if (backgroundY >= 0 && background2Y >= Main.SCREEN_HEIGHT)
							background2Y = 0 - Main.SCREEN_HEIGHT;
						g.drawImage(background1, (int) backgroundX, (int) (backgroundY += backgroundSpeed), null);
						g.drawImage(background2, (int) backgroundX, (int) (background2Y += backgroundSpeed), null);

						//아이템을 그린다
						//아이템을 그릴 때는 현재 생성되어 있는 아이템을 전부 그리기위해 list를 사용한다
						for (int i = 0; i < itemlist.size(); i++) {
							item = (Item) itemlist.get(i);
							item.setPause(false);//item에 pasue변수가 있는데 그것을 false처리
							//충돌 기능 - 캐릭터와 충돌시
							if (colison(item.getItemX(), item.getItemY(), character.getCharacterX(),
									character.getCharacterY(), item.getItem(), character.getCharacter())) {
								
								if (item.getEnemyX() != 0) {
									item.setIsAction(true); //아이템이 Action되도록 설정한다
									// item의 구조상 false 부분이 한번 실행되어야 하기 때문에 설정 되어있으면 true로 바뀌도록, 
									// false가 한번 실행안되면 0,0으로만 날아간다
								}

							
								//4번 아이템만 관리하는 것이 다르기 때문에 분리해둔다.
								if (item.getItemRandom() != 4) { //
									if (item.isAction()) { //아이템이 실행 중이라면
										// 충돌한 후 3초 뒤에 사라지는 타이머
										Item itemRemove = item; 
										Timer itemRemoveTimer = new Timer();
										TimerTask itemRemoveTT = new TimerTask() {
											@Override
											public void run() {
												itemlist.remove(itemRemove);
												//노래를 닫아준다
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

								//여기는 나뭇잎 유도탄 아이템을 위해 설정해둔다.
								//충돌했을 때, 최초 적 좌표를 가져오기 위해 여기에 선언한다
								int j = random.nextInt(enemylist.size());
								if (item.getEnemyX() == 0) { // 적의 좌표 값이 설정되어 있지 않은 경우에만
									item.setEnemyX(enemylist.get(j).getEnemyX());
									item.setEnemyY(enemylist.get(j).getEnemyY());
								}

							}//충돌 문 종료
							
							if (item.getItemRandom() == 4) {
								// 4번 아이템은 다른 방식으로 처리
								if (item.getItemY() <= -100) { //화면 밖을 벗어나면 제거하도록
									if (item.isAction()) {
										itemlist.remove(item);
									}
								}
							}

							//2번 보호막 아이템을 위한 아이템에 캐릭터 좌표 값 설정
							//보호막 아이템은 실시간으로 캐릭터 좌표를 따라가야 하기에 여기에 선언
							item.setCharacterX(character.getCharacterX());
							item.setCharacterY(character.getCharacterY());

							//여기는 적과의 충돌을 관리한다.
							if (item.isAction()) { //아이템이 실행 중일 때
								for (int x = 0; x < enemylist.size(); x++) { //적 하나마다 충돌감지
									enemy = enemylist.get(x);
									enemy.setPause(false);
									//충돌했다면
									if (colison(enemy.getEnemyX(), enemy.getEnemyY(), item.getItemX(), item.getItemY(),
											enemy.getEnemy(), item.getItemAction())) {

										enemylist.remove(enemy); //적이 닿으면 삭제
										enemy.getDieMusic().start(); //적이 죽었을 때, 음악 실행
										score += 10; // 점수 증가
									}
								}
							}
							item.draw(g); // 가려서 적이 보이지 않도록 하지 않기 위해 적보다 먼저 씀

						}//item for문 종료

						// 적을 그린다. 아이템과 마찬가지로 list로 그림
						for (int i = 0; i < enemylist.size(); i++) {
							enemy = (Enemy) enemylist.get(i);
							enemy.setPause(false);
							if (enemy.getEnemyY() > 900 || enemy.getEnemyX() > 500 || enemy.getEnemyX() < -120)
								enemylist.remove(i); // 화면 밖을 일정 넘어가면 삭제
							enemy.draw(g);
							// 적을 하나씩 그릴 때마다 캐릭터와 충돌감지
							if (colison(enemy.getEnemyX(), enemy.getEnemyY(), character.getCharacterX(),
									character.getCharacterY(), enemy.getEnemy(), character.getCharacter())) {
								gameEnd = true; //적과 닿으면 게임 종료
								Music dieMusic = new Music("lady_bug_die_sound.mp3", false); 
								dieMusic.start();//죽었을 때, 음악 설정
							}
						}//적  for문 종료

						character.draw(g); // 구름 아래로

						//구름스크롤, 난수에 설정된 숫자는 그냥 한거고, 조건식은 범위 벗어나면 다시그리기
						//&&연산자인 이유는 ||로 하면 최초 생성된 시점에서 조건문에 걸려버림
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
						//구름도 그냥 배경화면 속도에 맞췄다
						g.drawImage(cloud1, (int) (cloud1X += backgroundSpeed), (int) (cloud1Y += backgroundSpeed),
								null);
						g.drawImage(cloud2, (int) (cloud2X += backgroundSpeed), (int) (cloud2Y += backgroundSpeed),
								null);
						g.drawImage(cloud3, (int) (cloud3X -= backgroundSpeed), (int) (cloud3Y += backgroundSpeed),
								null);

						// 점수 실시간 증가
						scoreLabel.setText("score : " + score);
						score++;
						Thread.sleep(1000 / 30);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}//게임 종료가 아닐 때(게임 화면), 끝
			}//일시정지가 아닐 때 ,끝

		} //isGameScreen 끝
		if(isScoreScreen) {
			g.drawImage(scoreBoard, 0, 0, null);
		}
		paintComponents(g); //컴포넌트의 이미지들을 그려준다.
		repaint();
	}
	
	public void reset() {//게임 다시하기 기능
		score = 0; // 점수 초기화
		for(int i = 0; i<itemlist.size(); i++) { //아이템 소리를 끈다.
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
		itemlist.clear(); //적과 아이템은 시작하자마자 생성되서 상관없음
		enemylist.clear();
		itemTimer.cancel(); //타이머 캔슬
		enemyTimer.cancel();
		character.setCharacterX(215); //캐릭터 최초 위치
		character.setCharacterY(Main.SCREEN_HEIGHT - 100);
		character.setPause(false);
		
		enemyTimer = new Timer(); //취소하고 타이머 재설정
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
	
	public void mainMenu() {//메인메뉴 버튼 기능
		isMainScreen = true; //화면 전환
		isGameScreen = false;
		gameStartButton.setVisible(true);
		scoreButton.setVisible(true);
		pauseButton.setVisible(false);
		soundButton.setVisible(false);
		scoreLabel.setVisible(false);
		
		score = 0; //이 아래는 reset과 같음
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
		itemlist.clear(); //적과 아이템은 시작하자마자 생성되서 상관없음
		enemylist.clear();
		itemTimer.cancel();
		enemyTimer.cancel();
		character = null; //캐릭터는 지운다
	}
	
	public boolean colison(int x1, int y1, int x2, int y2, Image image1, Image image2) { //원형 충돌 감지
	//원리로는 두 원(Character or Enemy or Item)의 현재 위치를 실시간으로 받아서 두 원의 거리를 공식으로 구한다. 
	//그리고 두 원의 반지름의 길이를 더해서 그 값 안으로 들어오면 충돌이라고 인식한다.	
		int x = x2 - x1;  //x축 길이
		int y = y2 - y1;  //y축 길이
		int length = (int)Math.sqrt((x*x + y*y)); //z축 길이
		
		int sum = (image1.getWidth(null)/ 2) + (image2.getWidth(null)/ 2); //두 이미지 반지름의 합
		
		if(length < sum) return true; //반지름보다 z축 길이가 작으면 충돌
		
		return false; //아님 말고
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