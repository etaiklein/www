import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.dropbox.core.DbxException;

public class CopyFiles implements Runnable{
	private String SOURCE_FOLDER = "C:\\KNAgent\\Data";
	private String OUTPUT_FOLDER = "C:\\Users\\knadmin\\Desktop\\Data";
	private LoggerTest logger = new LoggerTest();
	private Dropbox d = null;

	public CopyFiles(String source, String output, LoggerTest mylogger){
		SOURCE_FOLDER = source;
		OUTPUT_FOLDER = output;
		logger = mylogger;
	}

	public CopyFiles(String source, String output, LoggerTest mylogger, Dropbox dr){
		SOURCE_FOLDER = source;
		OUTPUT_FOLDER = output;
		logger = mylogger;
		d = dr;
	}
	
	public void run(){
		run(true);
	}

	public void run(boolean b) {
		String temp = "";
		if (d == null){
		while(b){
			try {
				if (!(new File(OUTPUT_FOLDER).exists())){
					new File(OUTPUT_FOLDER).mkdir();
				}
				if (new File(SOURCE_FOLDER).exists()){
					//copies directories
					String[] mylist = new File(SOURCE_FOLDER).list();

					String mystring = "";
					for(int i=0; i<mylist.length; i++){
						mystring+= (mylist[i] + ", ");
					}
					if (!mystring.equals(temp)){
						FileUtils.copyDirectory(new File(SOURCE_FOLDER), new File(OUTPUT_FOLDER));
						logger.log("copied " + mystring.substring(0,mystring.length()-2));
					}
					temp = mystring;
					//give it a rest
					//System.out.println("copying");
					Thread.sleep(1000);


				}
			} catch (IOException | InterruptedException | IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		}
		else{
			while(b){
				try {
					if (!(new File(OUTPUT_FOLDER).exists())){
						new File(OUTPUT_FOLDER).mkdir();
					}
					if (new File(SOURCE_FOLDER).exists()){
						//copies directories
						String[] mylist = new File(SOURCE_FOLDER).list();

						String mystring = "";
						for(int i=0; i<mylist.length; i++){
							mystring+= (mylist[i] + ", ");
						}
						if (!mystring.equals(temp)){
							//recursively upload
							d.recursiveUpload(SOURCE_FOLDER, OUTPUT_FOLDER);
							logger.log("copied " + mystring.substring(0,mystring.length()-2));
						}
						temp = mystring;
						//give it a rest
						//System.out.println("copying");
						Thread.sleep(1000);


					}
				} catch (IOException | InterruptedException | IllegalArgumentException | DbxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			}
	}
		
	}


