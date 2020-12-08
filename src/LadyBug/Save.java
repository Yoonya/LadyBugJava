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

public class Save  { //���� ���ھ� ������ ����ϴ� Ŭ����
	
	public static void main(String[] args)  {
		File file = new File("./src/data/data.dat"); //�� ��ο� ���ھ ���

		 try {
		      if(file.createNewFile()){    //������ �����Ǵ� ����
		        System.out.println("������ �����Ǿ����ϴ�.");
		      }else {
		        System.out.println("������ �������� ���߽��ϴ�.");
		      }
		    } catch (IOException e) {
		      e.printStackTrace();
		      System.out.println("���� ó��");
		      System.out.println("������ �����ϴ� �������� ������ �߻��߽��ϴ�.");
		    }
      
	}
	

	public static ArrayList loadScore() throws IOException{ //�������⸦ ����� ���� ����ǰ� �ִ� �������� ��� ���� �޼ҵ�
		BufferedReader inputStream = null;
		String l;
		int i;
		ArrayList scoreList = new ArrayList<>();
		
		try {
			inputStream = new BufferedReader(new FileReader("./src/data/data.dat")); //���

			while ((l = inputStream.readLine()) != null) { //�� �� �� �о
				try {
					i = Integer.parseInt(l); //����ȭ��Ų����
					scoreList.add(i); //����Ʈ�� ����

				} 
				 catch (Exception e) {
					e.printStackTrace();
				}

			}
			
		}
		catch (FileNotFoundException e) {
				System.out.println("������ ���������ʽ��ϴ�");
		} 
		 finally {
				if (inputStream != null) {
					inputStream.close();
				}
		 }
		return scoreList; //�װ� ��ȯ
	}
	
	public static int loadHighScore() throws IOException { //�ְ����� �ʱ�ȭ�� �� �޼ҵ�
		BufferedReader inputStream = null;
		String l;
		int i = 0;
		try {
			inputStream = new BufferedReader(new FileReader("./src/data/data.dat")); //���

			if ((l = inputStream.readLine()) != null) {
				try {
					i = Integer.parseInt(l); //����ȭ�ϰ� �� ���� �ϳ��� ����
					
				} 
				 catch (Exception e) {
					e.printStackTrace();
				}

			}
			
		}
		catch (FileNotFoundException e) {
				System.out.println("������ ���������ʽ��ϴ�");
		} 
		 finally {
				if (inputStream != null) {
					inputStream.close();
				}
		 }
		return i; //�װ� ��ȯ
	}
	
	public static void save(int score) throws IOException  {//���ھ ���� ������ �޼ҵ�
		BufferedReader inputStream = null;
		PrintWriter outputStream = null;
		ArrayList scoreList = new ArrayList<>();
		
		scoreList.add(score); //�Ű������� �̹� �ǿ� ���� ������ ����Ʈ�� �̸� �Է��ؼ� �ְ�
		try {
			inputStream = new BufferedReader(new FileReader("./src/data/data.dat")); //�б�� ���
			String l;
			int i;

			while ((l = inputStream.readLine()) != null) {
				i = Integer.parseInt(l); //����ȭ�ؼ�
				scoreList.add(i); //����Ʈ�� �� �־
	
			}
			
		}
		catch (FileNotFoundException e) {
				System.out.println("������ ���������ʽ��ϴ�");
		} 
		 finally {
				if (inputStream != null) {
					inputStream.close();
				}
		 }
		
		Collections.sort(scoreList, Comparator.reverseOrder()); //������������ ���� ����
		
		try {
			outputStream = new PrintWriter(new FileWriter("./src/data/data.dat", false)); //�װ� ����� ��� ���� �����
			for (int x = 0; x< scoreList.size(); x++) {
				outputStream.println(scoreList.get(x)); //���پ� ����
				outputStream.flush();
			}
		}
		catch (FileNotFoundException ex) {
			System.out.println("������ ���������ʽ��ϴ�");
		}
		finally {
			if (outputStream != null)
				outputStream.close();
		}

	}
}
