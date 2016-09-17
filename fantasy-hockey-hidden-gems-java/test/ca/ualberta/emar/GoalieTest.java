package ca.ualberta.emar;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class GoalieTest {

	Goalie g;
	
	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void test() throws IOException {
		TemporaryFolder folder = new TemporaryFolder();
		File subfolder = folder.newFolder("subfolder");
		
		
	}

}
