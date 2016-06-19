import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class DatabaseTest {
	
	Database d = Database.getInstance();

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void testGetDailyStatsCorrectness() {

		try{
			File subfolder = folder.newFolder("subfolder");
			Database.getDailyStats(subfolder);
			File createdDir = new File(subfolder, "Daily Stats");
			assertEquals("Daily Stats folder not created", true, createdDir.exists());
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String now = new String(sdf.format(date));
			File skater = new File(createdDir, now + " Skater Stats.json");
			File goalie = new File(createdDir, now + " Goalie Stats.json");
			assertEquals("Skater Stats file not created", true, skater.exists());
			assertEquals("Goalie Stats file not created", true, goalie.exists());
			File skaterControl = new File("Skater Control.json");
			File goalieControl = new File("Goalie Control.json");
			assertEquals("Skater files differ", 
					FileUtils.readFileToString(skaterControl, "utf-8"), 
					FileUtils.readFileToString(skater, "utf-8"));
			assertEquals("Goalie files differ", 
					FileUtils.readFileToString(goalieControl, "utf-8"), 
					FileUtils.readFileToString(goalie, "utf-8"));
	

		} catch (Exception e){
			System.out.println(e);
		}
	}
	
	@Test
	public void testGetDailyStatsNumberFiles(){
		try{
			File subfolder = folder.newFolder("subfolder");
			Database.getDailyStats(subfolder);
			Database.getDailyStats(subfolder);
			File createdDir = new File(subfolder, "Daily Stats");
			assertEquals("Wrong number of files created", createdDir.listFiles().length, 2);
		} catch (Exception e){
			System.out.println(e);
		}
	}
	
	
}
