import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;

public class DatabaseTest {
	
	Database d = Database.getInstance();


	@Test
	public void testGetDailyStats() {
		try{
		Database.getDailyStats();
		} catch (Exception e){
			
		}
	}
	@After
	public void tearDown(){
		try{
			File dir = new File("Daily Stats");
			FileUtils.deleteDirectory(dir);
		} catch (Exception e){
			System.out.println(e);
		}

	}
}
