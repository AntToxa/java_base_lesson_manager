package progschool.core;
import java.io.*;
import progschool.config.*;

/**
 * Менеджер для уроков курса "JAVA. Базовый курс"
 * Переносит файлы из папки на дропбоксе 
 * в рабочей директории с уроками
 * 
 * @version 1.1
 * @author AnToxa
 *
 */
public class ManagerFromDropBox extends BaseManager {
	
	public static void main(String[] args) {
		System.out.println("Копирование файлов из директории " + Config.DIR_DROPBOX+" в директорию " + Config.DIR_LESSONS);
        ManagerFromDropBox objManager = new ManagerFromDropBox();
         
        objManager.inputParams();
        if (objManager.copyLessons()) {
          if (filesCopyList.length>0) {
        	 System.out.println("Скопированны следующие файлы:");
        	    for (String f:filesCopyList) {
                   System.out.println(" "+f);
        	    }
        	 }
        	 System.out.println("Копирование завершенно!");
         }else{
        	 System.out.println("При копировании файлов произошла ошибка!");
         }
	}
		
	/**
	 * Копирует дз из папки на dropbox в рабочую область
	 * @param dirCurrentLesson - папка с уроками
	 * @param dirDropboxLesson - папка с уроками на dropbox
	 */
	public void copyDz(String dirCurrentLesson, String dirDropboxLesson) {
		
		File objProject = new File(dirDropboxLesson);
		
		String[] listDirs = objProject.list();
		int flagCopy = 0;
		for (String cd : listDirs) {
			if (testDz(cd)) {
				String dirLessonSc = dirCurrentLesson+cd;
				File objDirLessonSc = new File(dirLessonSc);
				String dirCopyDropBox = dirDropboxLesson+cd+"/ver"+verLesson+"/";
				File objDropBox = new File(dirCopyDropBox);
				
				if (!objDropBox.isDirectory()) {
					continue;
				}
					
				if (!objDirLessonSc.isDirectory()) {
					objDirLessonSc.mkdirs();
				}
				
				File[] listFilesDb = objDropBox.listFiles();
				for (File f: listFilesDb) {
					
					String fis = f.getPath();
					String fos = dirLessonSc+"/"+f.getName();
					if (filesCopyIndex>0) {
					  String[] buffArray = new String[filesCopyIndex];
					  buffArray = filesCopyList;
					  filesCopyList =  new String[filesCopyIndex+1];
					  for (int i=0;i<buffArray.length;i++) {
					     filesCopyList[i] = buffArray[i];
					  }
					} else {
						filesCopyList =  new String[filesCopyIndex+1];
					}
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
