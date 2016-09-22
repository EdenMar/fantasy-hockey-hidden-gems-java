package ca.ualberta.emar;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;

import org.json.simple.*;


public class Skater {
	

	
	private String playerName;
	private String playerPositionCode;
//	private ArrayDeque<Integer> gamesPlayedQueue;
	private int totalGamesPlayed;
	private ArrayDeque<Integer> goalsQueue = new ArrayDeque<Integer>(10);
	private int totalGoals;
	private ArrayDeque<Integer> assistsQueue = new ArrayDeque<Integer>(10);
	private int totalAssists;
	private ArrayDeque<Integer> pointsQueue = new ArrayDeque<Integer>(10);
	private int totalPoints;
	private ArrayDeque<Integer> plusMinusQueue = new ArrayDeque<Integer>(10);
	private int totalPlusMinus;
	private ArrayDeque<Integer> penaltyMinutesQueue = new ArrayDeque<Integer>(10);
	private int totalPenaltyMinutes;
	private ArrayDeque<Integer> ppPointsQueue = new ArrayDeque<Integer>(10);
	private int totalppPoints;
	private ArrayDeque<Integer> ppGoalsQueue = new ArrayDeque<Integer>(10);
	private int totalppGoals;
	private int shGoals;
	private ArrayDeque<Integer> shotsQueue = new ArrayDeque<Integer>(10);
	private int totalShots;
	
	
	//depending on whether player is in DB or not, the keys to the JSONObject provided changes
	public Skater(JSONObject playerJSON, ExistsInDB yesOrNo){
		this.playerName = (String) playerJSON.get("playerName");
		this.playerPositionCode = (String) playerJSON.get("playerPositionCode");
		this.totalGamesPlayed = ((Long)playerJSON.get("gamesPlayed")).intValue();
		
		if (yesOrNo == ExistsInDB.NO){
	
			this.totalGoals = ((Long)playerJSON.get("goals")).intValue();
			this.goalsQueue.add(this.getTotalGoals());
			
			this.totalAssists = ((Long)playerJSON.get("assists")).intValue();
			this.assistsQueue.add(this.getTotalAssists());
	
			this.totalPoints = ((Long)playerJSON.get("points")).intValue();
			this.pointsQueue.add(this.getTotalPoints());
			
			this.totalPlusMinus = ((Long)playerJSON.get("plusMinus")).intValue();
			this.plusMinusQueue.add(this.getTotalPlusMinus());
			
			this.totalPenaltyMinutes = ((Long)playerJSON.get("penaltyMinutes")).intValue();
			this.penaltyMinutesQueue.add(this.getTotalPenaltyMinutes());
	
			this.totalppPoints = ((Long)playerJSON.get("ppPoints")).intValue();
			this.ppPointsQueue.add(this.getTotalppPoints());
			
			this.totalppGoals = ((Long)playerJSON.get("ppGoals")).intValue();
			this.ppGoalsQueue.add(this.getTotalppGoals());
			
			
			this.shGoals = ((Long)playerJSON.get("shGoals")).intValue();
	
			this.totalShots = ((Long)playerJSON.get("shots")).intValue();
			this.shotsQueue.add(this.getTotalShots());
		}
		else{
			
			this.goalsQueue = convertToArrayDeque((JSONArray)playerJSON.get("goalsQueue"));
			this.totalGoals = ((Long)playerJSON.get("totalGoals")).intValue();
			
			this.assistsQueue = convertToArrayDeque((JSONArray)playerJSON.get("assistsQueue"));
			this.totalAssists = ((Long)playerJSON.get("totalAssists")).intValue();
			
			this.pointsQueue = convertToArrayDeque((JSONArray)playerJSON.get("pointsQueue"));
			this.totalPoints = ((Long)playerJSON.get("totalPoints")).intValue();
			
			this.plusMinusQueue = convertToArrayDeque((JSONArray)playerJSON.get("plusMinusQueue"));
			this.totalPlusMinus = ((Long)playerJSON.get("totalPlusMinus")).intValue();
			
			this.totalPenaltyMinutes = ((Long)playerJSON.get("totalPenaltyMinutes")).intValue();
			this.penaltyMinutesQueue = convertToArrayDeque((JSONArray)playerJSON.get("penaltyMinutesQueue"));
			
			this.totalppPoints = ((Long)playerJSON.get("totalppPoints")).intValue();
			this.ppPointsQueue = convertToArrayDeque((JSONArray)playerJSON.get("ppPointsQueue"));
			
			this.totalppGoals = ((Long)playerJSON.get("totalppGoals")).intValue();
			this.ppGoalsQueue = convertToArrayDeque((JSONArray)playerJSON.get("ppGoalsQueue"));
			
			this.shGoals = ((Long)playerJSON.get("shGoals")).intValue();
			
			this.totalShots = ((Long)playerJSON.get("totalShots")).intValue();
			this.shotsQueue = convertToArrayDeque((JSONArray)playerJSON.get("shotsQueue"));
			
		}

	}

	public String getName(){
		return playerName;
	}
	
	public String getPlayerPositionCode(){
		return playerPositionCode;
	}
	
	public void setPlayerPositionCode(String playerPositionCode){
		this.playerPositionCode = playerPositionCode;
	}
	
	public int getTotalGamesPlayed(){
		return totalGamesPlayed;
	}
	
	protected void setTotalGamesPlayed(int totalGamesPlayed){
		this.totalGamesPlayed = totalGamesPlayed;
	}
	
//	public ArrayDeque<Integer> getGamesPlayed(){
//		return gamesPlayedQueue;
//	}
//	
//	protected void setGamesPlayed(ArrayDeque<Integer> gamesPlayed){
//		this.gamesPlayedQueue = gamesPlayed;
//	}
	
	protected void setTotalGoals(int totalGoals){
		this.totalGoals = totalGoals;
	}
	
	public int getTotalGoals(){
		return totalGoals;
	}
	
	protected void setGoals(ArrayDeque<Integer> goals){
		this.goalsQueue = goals;
	}
	
	public ArrayDeque<Integer> getGoals(){
		ArrayDeque<Integer> g = new ArrayDeque<Integer>(10);
		g = this.goalsQueue.clone();
		return g;
	}
	
	public int getTotalAssists(){
		return totalAssists;
	}
	
	protected void setTotalAssists(int totalAssists){
		this.totalAssists = totalAssists;
	}
	
	public ArrayDeque<Integer> getAssists(){
		return assistsQueue;
	}
	
	protected void setAssists(ArrayDeque<Integer> assists){
		this.assistsQueue = assists;
	}
	
	public ArrayDeque<Integer> getPoints(){
		return pointsQueue;
	}
	
	protected void setPoints(ArrayDeque<Integer> points){
		this.pointsQueue = points;
	}
	
	public int getTotalPoints(){
		return totalPoints;
	}
	
	protected void setTotalPoints(int totalPoints){
		this.totalPoints = totalPoints;
	}
	
	public int getTotalPlusMinus(){
		return totalPlusMinus;
	}
	
	protected void setTotalPlusMinus(int totalPlusMinus){
		this.totalPlusMinus = totalPlusMinus;
	}
	
	public ArrayDeque<Integer> getPlusMinus(){
		return plusMinusQueue;
	}
	
	protected void setPlusMinus(ArrayDeque<Integer> plusMinus){
		this.plusMinusQueue = plusMinus;
	}
	
	public int getTotalPenaltyMinutes(){
		return totalPenaltyMinutes;
	}
	
	protected void setTotalPenaltyMinutes(int totalPenaltyMinutes){
		this.totalPenaltyMinutes = totalPenaltyMinutes;
	}
	
	public ArrayDeque<Integer> getPenaltyMinutes(){
		return penaltyMinutesQueue;
	}
	
	protected void setPenaltyMinutes(ArrayDeque<Integer> penaltyMinutes){
		this.penaltyMinutesQueue = penaltyMinutes;
	}
	
	public int getTotalppPoints(){
		return totalppPoints;
	}
	
	protected void setTotalppPoints(int totalppPoints){
		this.totalppPoints = totalppPoints;
	}
	
	public ArrayDeque<Integer> getppPoints(){
		return ppPointsQueue;
	}
	
	protected void setppPoints(ArrayDeque<Integer> ppPoints){
		this.ppPointsQueue = ppPoints;
	}
	
	public int getTotalppGoals(){
		return totalppGoals;
	}
	
	protected void setTotalppGoals(int totalppGoals){
		this.totalppGoals = totalppGoals;
	}
	
	public ArrayDeque<Integer> getppGoals(){
		return ppGoalsQueue;
	}
	
	protected void setppGoals(ArrayDeque<Integer> ppGoals){
		this.ppGoalsQueue = ppGoals;
	}
	
	public int getshGoals(){
		return shGoals;
	}
	
	protected void setshGoals(int shGoals){
		this.shGoals = shGoals;
	}
	
	public int getTotalShots(){
		return totalShots;
	}
	
	protected void setTotalShots(int totalShots){
		this.totalShots = totalShots;
	}
	
	public ArrayDeque<Integer> getShots(){
		return shotsQueue;
	}
	
	protected void setShots(ArrayDeque<Integer> shots){
		this.shotsQueue = shots;
	}
	
	/*
	 * When inputting from a JSON file, some data comes in as JSONArrays. When instantiating
	 * from a JSON file, need to convert the JSONArray data back into ArrayDeque. This
	 * method helps do that
	 */
	protected static ArrayDeque<Integer> convertToArrayDeque(JSONArray array){
		ArrayDeque<Integer> data = new ArrayDeque<Integer>(10);
		try{
			for (Object o : array){
				Integer tmp = ((Long)o).intValue();
				data.add(tmp);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	
	/*
	 * When outputting to a JSON file, the toJSONString() method changes the 
	 * ArrayDeque objects to String objects; reading a JSON file back means
	 * the data cannot be converted to a JSONArray, but a String instead.
	 * This method acts as a utility method to help createSkaterJSON() create 
	 * a JSONObject with JSONArrays instead 
	 */
	//http://stackoverflow.com/questions/18335214/how-to-add-arrayliststring-to-json-array-keeping-type-safety-in-mind
	@SuppressWarnings("unchecked")
	protected static JSONArray convertToJSONArray(ArrayDeque<Integer> deque){
		JSONArray list = new JSONArray();
		ArrayDeque<Integer> a = deque.clone();
		int size = deque.size();
		for (int i = 0; i < size; i++){
			list.add(a.removeFirst());
		}
		return list;
	}
	
	//returns a JSONObject to be written to DB, different <K, V> than NHL website
	//SuppressedWarning as JSONObject inherits from HashMap, but doesn't inherit <K,V>
	//http://stackoverflow.com/questions/2927370/how-to-solve-this-java-type-safety-warning
	@SuppressWarnings("unchecked")
	protected JSONObject getSkaterJSONObject(){
		JSONObject skater = new JSONObject();
		skater.put("playerName", this.playerName);
		skater.put("gamesPlayed", this.totalGamesPlayed);
		skater.put("playerPositionCode", this.playerPositionCode);
		skater.put("goalsQueue", convertToJSONArray(this.goalsQueue));
		skater.put("totalGoals", this.totalGoals);
		skater.put("assistsQueue", convertToJSONArray(this.assistsQueue));
		skater.put("totalAssists", this.totalAssists);
		skater.put("totalPoints", this.totalPoints);
		skater.put("pointsQueue", convertToJSONArray(this.pointsQueue));
		skater.put("plusMinusQueue", convertToJSONArray(this.plusMinusQueue));
		skater.put("totalPlusMinus", this.totalPlusMinus);
		skater.put("penaltyMinutesQueue", convertToJSONArray(this.penaltyMinutesQueue));
		skater.put("totalPenaltyMinutes", this.totalPenaltyMinutes);
		skater.put("ppGoalsQueue", convertToJSONArray(this.ppGoalsQueue));
		skater.put("totalppGoals", this.totalppGoals);
		skater.put("ppPointsQueue", convertToJSONArray(this.ppPointsQueue));
		skater.put("totalppPoints", this.totalppPoints);
		skater.put("shotsQueue", convertToJSONArray(this.shotsQueue));
		skater.put("totalShots", this.totalShots);
		skater.put("shGoals", this.shGoals);
		return skater;
	}
	
	@Override
	public boolean equals(Object o){

		if (o == this){
			System.out.println("why");
			return true;
		}
		
		if (!(o instanceof Skater)){
			System.out.println("yeah");
			return false;
		}
		
		Skater s = (Skater)o;
		
		return 
				this.playerName.equals(s.getName())
		&& this.totalGamesPlayed == s.getTotalGamesPlayed()
		&& this.playerPositionCode.equals(s.getPlayerPositionCode())
		&& Arrays.equals(this.goalsQueue.toArray(), s.getGoals().toArray())
		&& this.totalGoals == s.getTotalGoals()
		&& Arrays.equals(this.assistsQueue.toArray(), s.getAssists().toArray())
		&& this.totalAssists == s.getTotalAssists()
		&& Arrays.equals(this.pointsQueue.toArray(), s.getPoints().toArray())
		&& this.totalPoints == s.getTotalPoints()
		&& this.totalPlusMinus == s.getTotalPlusMinus()
		&& Arrays.equals(this.plusMinusQueue.toArray(), s.getPlusMinus().toArray())
		&& this.totalPenaltyMinutes == s.getTotalPenaltyMinutes()
		&& Arrays.equals(this.penaltyMinutesQueue.toArray(), s.getPenaltyMinutes().toArray())
		&& this.totalppGoals == s.getTotalppGoals()
		&& Arrays.equals(this.ppGoalsQueue.toArray(), s.getppGoals().toArray())
		&& this.totalppPoints == s.getTotalppPoints()
		&& Arrays.equals(this.ppPointsQueue.toArray(), s.getppPoints().toArray())
		&& this.totalShots == s.getTotalShots()
		&& Arrays.equals(this.shotsQueue.toArray(), s.getShots().toArray())
		&& this.shGoals == s.getshGoals();
	}
}


