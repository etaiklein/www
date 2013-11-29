
import java.io.File;
import java.io.IOException;
import java.net.SocketException;

import javax.mail.MessagingException;

import org.apache.commons.io.FileUtils;



public class Controller {
	private static String SOURCE_FOLDER = "C:\\wamp\\www\\src\\src\\";
	private static String OUTPUT_FOLDER = "C:\\Users\\Etai\\Desktop\\Dropbox\\Waterfall";
	private static String PROCESS = "firefox.exe";
	private static String FILES = "C:\\wamp\\www\\src\\src\\";
			private static Dropbox DROPBOX = new Dropbox();
//			private static String SOURCE_FOLDER = "C:\\KNAgent\\Data";
//			private static String OUTPUT_FOLDER = "C:\\Users\\knadmin\\Desktop\\Dropbox\\Waterfall\\Data";
//			private static String PROCESS = "TxnPlaybackEngine.exe";
//			private static String FILES = "C:\\Users\\knadmin\\workspace\\www\\src\\src";

	public static void main(String args[]) throws IOException, MessagingException{
		
		//delete and recreate filestructure on first run
		DeleteDirectory df = new DeleteDirectory(OUTPUT_FOLDER, DROPBOX);
		df.Delete();

		//Runtime rt = Runtime.getRuntime();
		//rt.exec("cmd /c c:\\path\\to\\python python\\test.py");
		LoggerTest logger = new LoggerTest(OUTPUT_FOLDER);
		logger.init();
		logger.log("created");

		//start the thread to copy files as tHe agent runs. 
		Thread f = new Thread(new CopyFiles(SOURCE_FOLDER, OUTPUT_FOLDER, logger));
		logger.log("running copier");
		f.start();

		//start the thread to take screencaps
		Check ch = new Check(OUTPUT_FOLDER, PROCESS, logger);
		Thread c = new Thread(ch);
		logger.log("running screecapper");
		c.start();


		while(true){
			//			Automate a = new Automate();
			//			a.run();
			//			logger.log("automating");
			
			if (ch.isMailed() == 1){

			//add the files needed to run it
			try {
				FileUtils.copyDirectory(new File(FILES), new File(OUTPUT_FOLDER));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			//mail me the result
			try{
				logger.log("trying to mail");
				Mail2 m = new Mail2(OUTPUT_FOLDER, logger);
				m.zipandmail();
				logger.log("mailed");
			}
			catch(RuntimeException e2){
				e2.printStackTrace();
			}

			
			ch.setMailed(0);
			
			//Delete the Directory
			DeleteDirectory d = new DeleteDirectory(OUTPUT_FOLDER, DROPBOX);
			d.Delete();
			d = new DeleteDirectory(OUTPUT_FOLDER + "\\ScreenCaps", DROPBOX);
			d.Delete();
			d = new DeleteDirectory(SOURCE_FOLDER);
			d.Delete();

			logger.log("deleting Data file");
			//wait  ten minutes
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
	}
}

/**



Also this: data: [
      {y: 34.4, color: 'red'},     // this point is red
      21.8,                        // default blue
      {y: 20.1, color: '#aaff99'}, // this will be greenish
      20]                          // default blue


Check! 1. Open VM
2. Make the shit run
3. Collect Test Data

1. Write full automation
	Check! a.write controller to run processing from data collection. C
	b. figure out how to run file from start on skytap
	c. email link to file.
2. Test on VM
3. Access remotely

1. Add colors 

- Send Seth e-mail with everything.
 **/