package LadyBug;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Save  { //게임 스코어 저장을 담당하는 클래스
	
	public static void main(String[] args)  {
		File file = new File("./src/data/data.dat"); //이 경로에 스코어가 기록

		 try {
		      if(file.createNewFile()){    //파일이 생성되는 시점
		        System.out.println("파일이 생성되었습니다.");
		      }else {
		        System.out.println("파일을 생성하지 못했습니다.");
		      }
		    } catch (IOException e) {
		      e.printStackTrace();
		      System.out.println("예외 처리");
		      System.out.println("파일을 생성하는 과정에서 오류가 발생했습니다.");
		    }
      
	}
	

	public static ArrayList loadScore() throws IOException{ //점수보기를 만들기 위해 저장되고 있는 점수들을 모두 받을 메소드
		BufferedReader inputStream = null;
		String l;
		int i;
		ArrayList scoreList = new ArrayList<>();
		
		try {
			inputStream = new BufferedReader(new FileReader("./src/data/data.dat")); //열어서

			while ((l = inputStream.readLine()) != null) { //한 줄 씩 읽어서
				try {
					i = Integer.parseInt(l); //정수화시킨다음
					scoreList.add(i); //리스트에 저장

				} 
				 catch (Exception e) {
					e.printStackTrace();
				}

			}
			
		}
		catch (FileNotFoundException e) {
				System.out.println("파일이 존재하지않습니다");
		} 
		 finally {
				if (inputStream != null) {
					inputStream.close();
				}
		 }
		return scoreList; //그걸 반환
	}
	
	public static int loadHighScore() throws IOException { //최고점수 초기화에 쓸 메소드
		BufferedReader inputStream = null;
		String l;
		int i = 0;
		try {
			inputStream = new BufferedReader(new FileReader("./src/data/data.dat")); //열어서

			if ((l = inputStream.readLine()) != null) {
				try {
					i = Integer.parseInt(l); //정수화하고 맨 윗줄 하나만 저장
					
				} 
				 catch (Exception e) {
					e.printStackTrace();
				}

			}
			
		}
		catch (FileNotFoundException e) {
				System.out.println("파일이 존재하지않습니다");
		} 
		 finally {
				if (inputStream != null) {
					inputStream.close();
				}
		 }
		return i; //그걸 반환
	}
	
	public static void save(int score) throws IOException  {//스코어를 저장 관리할 메소드
		BufferedReader inputStream = null;
		PrintWriter outputStream = null;
		ArrayList scoreList = new ArrayList<>();
		
		scoreList.add(score); //매개변수로 이번 판에 받은 점수를 리스트에 미리 입력해서 넣고
		try {
			inputStream = new BufferedReader(new FileReader("./src/data/data.dat")); //읽기로 열어서
			String l;
			int i;

			while ((l = inputStream.readLine()) != null) {
				i = Integer.parseInt(l); //정수화해서
				scoreList.add(i); //리스트에 다 넣어서
	
			}
			
		}
		catch (FileNotFoundException e) {
				System.out.println("파일이 존재하지않습니다");
		} 
		 finally {
				if (inputStream != null) {
					inputStream.close();
				}
		 }
		
		Collections.sort(scoreList, Comparator.reverseOrder()); //내림차순으로 새로 정렬
		
		try {
			outputStream = new PrintWriter(new FileWriter("./src/data/data.dat", false)); //그걸 쓰기로 열어서 새로 덮어쓰기
			for (int x = 0; x< scoreList.size(); x++) {
				outputStream.println(scoreList.get(x)); //한줄씩 쓰기
				outputStream.flush();
			}
		}
		catch (FileNotFoundException ex) {
			System.out.println("파일이 존재하지않습니다");
		}
		finally {
			if (outputStream != null)
				outputStream.close();
		}

	}
}
