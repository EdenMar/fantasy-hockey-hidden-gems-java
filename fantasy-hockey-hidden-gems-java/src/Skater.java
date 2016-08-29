import java.util.ArrayDeque;
import org.json.simple.*;

public class Skater {
	
	private String name;
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
	
	
	//constructor assumes playerJSON is from a player that doesn't exist in the DB
	public Skater(JSONObject playerJSON){
		this.name = (String) playerJSON.get("playerName");
		this.playerPositionCode = (String) playerJSON.get("playerPositionCode");
		this.totalGamesPlayed = (Integer) playerJSON.get("gamesPlayed");
		this.goalsQueue.add((Integer)playerJSON.get("goals"));
		this.totalGoals = (Integer)playerJSON.get("goals");
		this.assistsQueue.add((Integer)playerJSON.get("assists"));
		this.totalAssists = (Integer)playerJSON.get("assists");
		this.pointsQueue.add((Integer)playerJSON.get("points"));
		this.totalPoints = (Integer)playerJSON.get("points");
		this.plusMinusQueue.add((Integer)playerJSON.get("plusMinus"));
		this.totalPlusMinus = (Integer)playerJSON.get("plusMinus");
		this.penaltyMinutesQueue.add((Integer)playerJSON.get("penaltyMinutes"));
		this.totalPenaltyMinutes = (Integer)playerJSON.get("penaltyMinutes");
		this.ppPointsQueue.add((Integer)playerJSON.get("ppPoints"));
		this.totalppPoints = (Integer)playerJSON.get("ppPoints");
		this.ppGoalsQueue.add((Integer)playerJSON.get("ppGoals"));
		this.totalppGoals = (Integer)playerJSON.get("ppGoals");
		this.shGoals = (Integer)playerJSON.get("shGoals");
		this.shotsQueue.add((Integer)playerJSON.get("shots"));
		this.totalShots = (Integer)playerJSON.get("shots");
		
		
	}

	public String getName(){
		return name;
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
		return goalsQueue;
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
	
	//returns a JSONObject to be written to DB, different <K, V> than NHL website
	//SuppressedWarning as JSONObject inherits from HashMap, but doesn't inherit <K,V>
	//http://stackoverflow.com/questions/2927370/how-to-solve-this-java-type-safety-warning
	@SuppressWarnings("unchecked")
	protected JSONObject createSkaterJSON(){
		JSONObject skater = new JSONObject();
		skater.put("name", this.name);
		skater.put("gamesPlayed", this.totalGamesPlayed);
		skater.put("playerPositionCode", this.playerPositionCode);
		skater.put("goalsQueue", this.goalsQueue);
		skater.put("totalGoal", this.totalGoals);
		skater.put("assistsQueue", this.assistsQueue);
		skater.put("totalAssists", this.totalAssists);
		skater.put("totalPoints", this.totalPoints);
		skater.put("pointsQueue", this.pointsQueue);
		skater.put("plusMinusQueue", this.plusMinusQueue);
		skater.put("totalPlusMinus", this.totalPlusMinus);
		skater.put("penaltyMinutesQueue", this.penaltyMinutesQueue);
		skater.put("totalPenaltyMinutes", this.totalPenaltyMinutes);
		skater.put("ppGoalsQueue", this.ppGoalsQueue);
		skater.put("totalppGoals", this.totalppGoals);
		skater.put("ppPointsQueue", this.ppPointsQueue);
		skater.put("totalppPoints", this.totalppPoints);
		skater.put("shotsQueue", this.shotsQueue);
		skater.put("totalShots", this.totalShots);
		skater.put("shGoals", this.shGoals);
		return skater;
	}
	
}
