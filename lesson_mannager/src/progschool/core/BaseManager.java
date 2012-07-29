package progschool.core;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import progschool.config.*;

/**
 * Менеджер для уроков курса "JAVA. Базовый курс"
 * Содержит основные методы
 * 
 * 
 * @version 1.0
 * @author AnToxa
 *
 */
public class BaseManager {
	
    /**
     * Версия урока
     */
	public static int verLesson;
	
	/**
	 * Номер урока
	 */
    public static int numberLesson;
    
    /**
     * Список скопированных файлов
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
	 * Проверяет название папки соответсвует регулярному выражению dz[0-9]+
	 * @param folder - название папки
	 * @return true - если соответствует, false - если нет
	 */
	public static boolean testDz(String folder){
		Pattern p = Pattern.compile("dz[0-9]+");
		Matcher m = p.matcher(folder);
		boolean b = m.matches();
		return b;
    }
	
	
	
	/**
	 * Копирует содержимое одного файла в другой
	 * @param fis - путь до копируемого файла
	 * @param fos - путь до файла в который надо 
	 * скопировать содержимое копируемого файла
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
	 * Проверяет существует ли директория
	 * @param checkDir - проверяемая директория
	 * @return true - если директория существует, false - если нет
	 */
	public boolean checkDir(String checkDir){
		File objDir = new File(checkDir);
		if(!objDir.isDirectory()){
			System.out.println("Произошла ошибка: "+checkDir+" не существует!");
			return false;
		}
		
		
		return true;
	}
	
	/**
	 * Копируем из одной папки в папку другую
	 * направление копирования задается в CopyDz
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
			System.out.println("Произошла ошибка: "+dirCurrentLesson+" не существует!");
			return false;
		}
		
		copyDz(dirCurrentLesson, dirDropboxLesson);
		return true;
	}
	
	/**
	 * Копирует дз из рабочей папки в dropbox
	 * @param dirCurrentLesson - папка с уроками
	 * @param dirDropboxLesson - папка с уроками на dropbox
	 */
	public void copyDz(String dirCurrentLesson, String dirDropboxLesson){
	
	}
	
	/**
	 * Ввод необходимых данных
	 */
	public void inputParams(){
		
		System.out.println("Введите номер урока:");
		numberLesson = getNextInt();
		while(numberLesson<1){
		  System.out.println("Не коректный номер урока, введите еще раз:");
		  numberLesson = getNextInt();
		}
		
		System.out.println("Введите версию урока:");
		verLesson = getNextInt();
		while(verLesson<1){
		  System.out.println("Не коректная версия урока, введите еще раз:");
		  verLesson = getNextInt();
		}
		
	}
	
	/**
	 * Получаем введенное целое значение из консоли
	 * @return если введено число>0 то введенное число, в противном случае 0
	 */
	public static int getNextInt(){
		Scanner sc = new Scanner(System.in);
		if(sc.hasNextInt()){
			  return sc.nextInt(); 
		  }
		return 0;
	}

}
