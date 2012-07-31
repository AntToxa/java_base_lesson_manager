package progschool.core;
import java.io.*;
import progschool.config.*;

/**
 * Менеджер для уроков курса "JAVA. Базовый курс"
 * Переносит файлы из рабочей директории с уроками
 * в папку на дропбоксе
 * 
 * @version 1.0
 * @author AnToxa
 *
 */
public class ManagerToDropBox extends BaseManager{
	
	public static void main(String[] args) {
		System.out.println("Копирование файлов из директории "+Config.DIR_LESSONS+" в директорию "+Config.DIR_DROPBOX);
         ManagerToDropBox objManager = new ManagerToDropBox();
         
         objManager.inputParams();
         if(objManager.copyLessons()){
        	 if(filesCopyList.length>0){
        		 System.out.println("Скопированны следующие файлы:");
        		 for(String f:filesCopyList){
        			 System.out.println(" "+f);
        		 }
        	 }
        	 System.out.println("Копирование завершенно!");
         }else{
        	 System.out.println("При копировании файлов произошла ошибка!");
         }
	}
	
	
	
	
		
	/**
	 * Копирует дз из рабочей папки в dropbox
	 * @param dirCurrentLesson - папка с уроками
	 * @param dirDropboxLesson - папка с уроками на dropbox
	 */
	public void copyDz(String dirCurrentLesson, String dirDropboxLesson){
		File objProject = new File(dirCurrentLesson);
		
		String[] listDirs = objProject.list();
		int flagCopy = 0;
		for(String cd : listDirs){
			if(testDz(cd)){
				String dirCopyDropBox = dirDropboxLesson+cd+"/ver"+verLesson+"/";
				File objDropBox = new File(dirCopyDropBox);
				if(objDropBox.isDirectory()&&flagCopy<2){
					System.out.println("Директория "+dirCopyDropBox+" уже существует,");
					System.out.println("все хранящиеся файлы в ней будут перезаписаны,");
					System.out.println("вы уверены что хотите продолжить?");
					System.out.println("0 - нет, 1 - да, 2 - да для всех, 3 - отменить копирование.");
					flagCopy = getNextInt();
				}
				
				
				if(!objDropBox.isDirectory()){
					objDropBox.mkdirs();
				}else{
					if(flagCopy==3){
						break;
					}else if(flagCopy == 0){
						continue;
					}	
				}
				
				String dirLessonSc = dirCurrentLesson+cd;
				File objDirLessonSc = new File(dirLessonSc);
				//System.out.println(dirLessonSc);
				
				File[] listFilesSc = objDirLessonSc.listFiles();
				for(File f: listFilesSc){
					String fis = f.getPath();
					String fos = dirCopyDropBox+f.getName();
					if(filesCopyIndex>0){
					  String[] buffArray = new String[filesCopyIndex];
					  buffArray = filesCopyList;
					  filesCopyList =  new String[filesCopyIndex+1];
					  for(int i=0;i<buffArray.length;i++){
					     filesCopyList[i] = buffArray[i];
					  }
					}else{
						filesCopyList =  new String[filesCopyIndex+1];
					}
					//System.out.println(l);
					filesCopyList[filesCopyIndex] = fos;
					filesCopyIndex++;
					try {
						fileCopy(fis, fos);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				
			}
		}
	}
	
	
}
