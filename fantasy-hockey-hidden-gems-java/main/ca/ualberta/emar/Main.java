package ca.ualberta.emar;

public class Main {

	public static void main(String[] args) {

		Database.getInstance();
		try {
			Database.getDailyStats();
			Database.manageSkaterDatabase();
			Database.manageGoalieDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
