package progschool.core;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import progschool.config.*;

/**
 * �������� ��� ������ ����� "JAVA. ������� ����"
 * �������� �������� ������
 * 
 * 
 * @version 1.0
 * @author AnToxa
 *
 */
public class BaseManager {
	
    /**
     * ������ �����
     */
	public static int verLesson;
	
	/**
	 * ����� �����
	 */
    public static int numberLesson;
    
    /**
     * ������ ������������� ������
     */
    public static String[] filesCopyList;
    
    /**
     * 
     * @param args
     */
    
    public static int filesCopyIndex = 0;
    
	
	
	public boolean sendMail(){
		
		return true;
		
	}
	
	
	
	/**
	 * ��������� �������� ����� ������������ ����������� ��������� dz[0-9]+
	 * @param folder - �������� �����
	 * @return true - ���� �������������, false - ���� ���
	 */
	public static boolean testDz(String folder){
		Pattern p = Pattern.compile("dz[0-9]+");
		Matcher m = p.matcher(folder);
		boolean b = m.matches();
		return b;
    }
	
	
	
	/**
	 * �������� ���������� ������ ����� � ������
	 * @param fis - ���� �� ����������� �����
	 * @param fos - ���� �� ����� � ������� ���� 
	 * ����������� ���������� ����������� �����
	 * @throws IOException
	 */
	public static void fileCopy(String fis,  String fos) throws IOException {
		FileInputStream is = new FileInputStream(fis);
		FileOutputStream os = new FileOutputStream(fos);
		int nLength;
		byte[] buf = new byte[8000];
	    while(true) {
		  nLength = is.read(buf);
		  if(nLength < 0)   break;
		  os.write(buf, 0, nLength); 
		}
		is.close();
		os.close();
    }

	/**
	 * ��������� ���������� �� ����������
	 * @param checkDir - ����������� ����������
	 * @return true - ���� ���������� ����������, false - ���� ���
	 */
	public boolean checkDir(String checkDir){
		File objDir = new File(checkDir);
		if(!objDir.isDirectory()){
			System.out.println("��������� ������: "+checkDir+" �� ����������!");
			return false;
		}
		
		
		return true;
	}
	
	/**
	 * �������� �� ����� ����� � ����� ������
	 * ����������� ����������� �������� � CopyDz
	 */
	public boolean copyLessons(){
		String currentLesson = "lesson"+numberLesson+"/";
		String dirDropbox = Config.DIR_DROPBOX;
		
		if(!this.checkDir(dirDropbox)){
			return false;
		}
		String dirDropboxLesson = dirDropbox+currentLesson;
		File objDir = new File(dirDropboxLesson);
		if(!objDir.isDirectory()){
			objDir.mkdirs();
		}
		String dirCurrentLesson = Config.DIR_LESSONS+Config.PATH_DZ.replace("{lesson}", currentLesson);
		objDir = new File(dirCurrentLesson);
		if(!objDir.isDirectory()){
			System.out.println("��������� ������: "+dirCurrentLesson+" �� ����������!");
			return false;
		}
		
		copyDz(dirCurrentLesson, dirDropboxLesson);
		return true;
	}
	
	/**
	 * �������� �� �� ������� ����� � dropbox
	 * @param dirCurrentLesson - ����� � �������
	 * @param dirDropboxLesson - ����� � ������� �� dropbox
	 */
	public void copyDz(String dirCurrentLesson, String dirDropboxLesson){
	
	}
	
	/**
	 * ���� ����������� ������
	 */
	public void inputParams(){
		
		System.out.println("������� ����� �����:");
		numberLesson = getNextInt();
		while(numberLesson<1){
		  System.out.println("�� ��������� ����� �����, ������� ��� ���:");
		  numberLesson = getNextInt();
		}
		
		System.out.println("������� ������ �����:");
		verLesson = getNextInt();
		while(verLesson<1){
		  System.out.println("�� ��������� ������ �����, ������� ��� ���:");
		  verLesson = getNextInt();
		}
		
	}
	
	/**
	 * �������� ��������� ����� �������� �� �������
	 * @return ���� ������� �����>0 �� ��������� �����, � ��������� ������ 0
	 */
	public static int getNextInt(){
		Scanner sc = new Scanner(System.in);
		if(sc.hasNextInt()){
			  return sc.nextInt(); 
		  }
		return 0;
	}

}
