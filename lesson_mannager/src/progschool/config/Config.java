package progschool.config;
/**
 * Класс с конфигурационными настройками менеджера уроков
 * @author AnToxa
 *
 */
public class Config {
	
    /**
     * Директория в которой находиться папка с DropBox
     */
	public static final String DIR_DROPBOX = "";
	
	/**
	 * Директория в которой находятся уроки
	 */
    public static final String DIR_LESSONS = "";
    
    /**
     * Путь от директории с уроками до папок домашними заданиями 
     * {lesson} - заменяется на папку с текущим уроком
     */
    public static final String PATH_DZ = "{lesson}src/julyGroup/{lesson}anton_chesnokov/";
    
   
    
}
