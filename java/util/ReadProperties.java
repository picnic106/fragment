public class ReadProperties{


**
	 * 读取配置文件
	 * @param path
	 * @return
	 */
	public static Properties loadProerties(String path){
		File file= new File(path);
		if(file.exists()){
			Properties p = new Properties();
			try {
				p.load(new FileInputStream(file));
				return p;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	}
