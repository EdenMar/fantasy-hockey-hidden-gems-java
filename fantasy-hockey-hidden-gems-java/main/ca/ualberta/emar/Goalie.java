package ca.ualberta.emar;



import java.util.ArrayDeque;

import org.json.simple.*;

public class Goalie {
	private String name;
	private String playerPositionCode = "G";
	private int totalGamesPlayed;
//	private ArrayDeque<Integer> gamesPlayedQueue;
	private ArrayDeque<Float> savePctgQueue = new ArrayDeque<Float>(10);
	private float totalSavePctg;
	private ArrayDeque<Integer> winsQueue = new ArrayDeque<Integer>(10);
	private int totalWins;
	private int shutouts;
	private ArrayDeque<Integer> shotsAgainstQueue = new ArrayDeque<Integer>(10);
	private int totalShotsAgainst;
	private ArrayDeque<Integer> goalsAgainstQueue = new ArrayDeque<Integer>(10);
	private int totalGoalsAgainst;
	private ArrayDeque<Integer> savesQueue = new ArrayDeque<Integer>(10);
	private int totalSaves;
	private float totalGAA;
	private ArrayDeque<Float> goalsAgainstAverageQueue = new ArrayDeque<Float>(10);
	private ArrayDeque<Integer> timeOnIceQueue = new ArrayDeque<Integer>(10);
	private int totalTimeOnIce;
	
	//constructor takes JSONObject and adds goalie to DB
	public Goalie (JSONObject goalie, DataFromDB yesOrNo){
		this.name = (String)goalie.get("playerName");
		this.totalGamesPlayed = ((Long)goalie.get("gamesPlayed")).intValue();
		
		if (yesOrNo == DataFromDB.NO){
			this.savePctgQueue.add((float)((Double) goalie.get("savePctg")).doubleValue());
			this.totalSavePctg = (Float)goalie.get("savePctg");
			
			this.winsQueue.add(((Long)goalie.get("wins")).intValue());
			this.totalWins = ((Long)goalie.get("wins")).intValue();
			
			this.shutouts = ((Long)goalie.get("shutouts")).intValue();
			
			this.shotsAgainstQueue.add(((Long)goalie.get("shotsAgainst")).intValue());
			this.totalShotsAgainst = ((Long)goalie.get("shotsAgainst")).intValue();
					;
			this.goalsAgainstQueue.add((Integer)goalie.get("goalsAgainst"));
			this.totalGoalsAgainst = (Integer)goalie.get("goalsAgainst");
			
			this.savesQueue.add((Integer)goalie.get("saves"));
			this.totalSaves = (Integer)goalie.get("saves");
			
			this.totalGAA = (Float)goalie.get("goalsAgainstAverage");
			this.goalsAgainstAverageQueue.add((Float)goalie.get("goalsAgainstAverage"));
			
			this.timeOnIceQueue.add((Integer)goalie.get("timeOnIce"));
			this.totalTimeOnIce = (Integer)goalie.get("timeOnIce");
		}
	}
	
	public String getName(){
		return name;
	}
	
	public String getPlayerPositionCode(){
		return playerPositionCode;
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
	
	public ArrayDeque<Float> getSavePctg(){
		return savePctgQueue;
	}
	
	protected void setSavePctg(ArrayDeque<Float> savePctg){
		this.savePctgQueue = savePctg;
	}
	
	public float getTotalSavePctg(){
		return totalSavePctg;
	}
	
	protected void setTotalSavePctg(float totalSavePctg){
		this.totalSavePctg = totalSavePctg;
	}
	
	public ArrayDeque<Integer> getWins(){
		return winsQueue;
	}
	
	protected void setWins(ArrayDeque<Integer> wins){
		this.winsQueue = wins;
	}
	
	public int getTotalWins(){
		return totalWins;
	}
	
	protected void setTotalWins(int totalWins){
		this.totalWins = totalWins;
	}
	
	public int getShutouts(){
		return shutouts;
	}
	
	protected void setShutouts(int shutouts){
		this.shutouts = shutouts;
	}
	
	public ArrayDeque<Integer> getShotsAgainst(){
		return shotsAgainstQueue;
	}
	
	protected void setShotsAgainst(ArrayDeque<Integer> shotsAgainst){
		this.shotsAgainstQueue = shotsAgainst;
	}
	
	public int getTotalShotsAgainst(){
		return totalShotsAgainst;
	}
	
	protected void setTotalShotsAgainst(int totalShotsAgainst){
		this.totalShotsAgainst = totalShotsAgainst;
	}
	
	public ArrayDeque<Integer> getGoalsAgainst(){
		return goalsAgainstQueue;
	}
	
	protected void setGoalsAgainst(ArrayDeque<Integer> goalsAgainst){
		this.goalsAgainstQueue = goalsAgainst;
	}
	
	public int getTotalGoalsAgainst(){
		return totalGoalsAgainst;
	}
	
	protected void setTotalGoalsAgainst(int totalGoalsAgainst){
		this.totalGoalsAgainst = totalGoalsAgainst;
	}
	
	public ArrayDeque<Integer> getSaves(){
		return savesQueue;
	}
	
	protected void setSaves(ArrayDeque<Integer> saves){
		this.savesQueue = saves;
	}
	
	public int getTotalSaves(){
		return totalSaves;
	}
	
	protected void setTotalSaves(int totalSaves){
		this.totalSaves = totalSaves;
	}
	
	public float getTotalGAA(){
		return totalGAA;
	}
	
	protected void setTotalGAA(int totalGAA){
		this.totalGAA = totalGAA;
	}
	
	public ArrayDeque<Float> getGoalsAgainstAverage(){
		return goalsAgainstAverageQueue;
	}
	
	protected void setGoalsAgainstAverage(ArrayDeque<Float> goalsAgainstAverage){
		this.goalsAgainstAverageQueue = goalsAgainstAverage;
	}
	
	public ArrayDeque<Integer> getTimeOnIce(){
		return timeOnIceQueue;
	}
	
	protected void setTimeOnIce(ArrayDeque<Integer> timeOnIce){
		this.timeOnIceQueue = timeOnIce;
	}
	
	public float getTotalTimeOnIce(){
		return totalTimeOnIce;
	}
	
	protected void setTotalTimeOnIce(int totalTimeOnIce){
		this.totalTimeOnIce = totalTimeOnIce;
	}
	
	//SuppressedWarning as JSONObject inherits from HashMap, but doesn't inherit <K,V>
	//http://stackoverflow.com/questions/2927370/how-to-solve-this-java-type-safety-warning
	@SuppressWarnings("unchecked")
	protected JSONObject createGoalieJSON(){
		JSONObject goalie = new JSONObject();
		goalie.put("playerName", this.name);
		goalie.put("gamesPlayed", this.totalGamesPlayed);
		goalie.put("savePctgQueue", convertToJSONArray(this.savePctgQueue));
		goalie.put("totalSavePctg", this.totalSavePctg);
		goalie.put("winsQueue", Skater.convertToJSONArray(this.winsQueue));
		goalie.put("totalWins", this.totalWins);
		goalie.put("shutouts", this.shutouts);
		goalie.put("shotsAgainstQueue", Skater.convertToJSONArray(this.shotsAgainstQueue));
		goalie.put("totalShotsAgainst", this.totalShotsAgainst);
		goalie.put("goalsAgainstQueue", Skater.convertToJSONArray(this.goalsAgainstQueue));
		goalie.put("totalGoalsAgainst", this.totalGoalsAgainst);
		goalie.put("savesQueue", Skater.convertToJSONArray(this.savesQueue));
		goalie.put("totalSaves", this.totalSaves);
		goalie.put("totalGAA", this.totalGAA);
		goalie.put("goalsAgainstAverageQueue", convertToJSONArray(this.goalsAgainstAverageQueue));
		goalie.put("timeOnIceQueue", Skater.convertToJSONArray(this.timeOnIceQueue));
		goalie.put("totalTimeOnIce", this.totalTimeOnIce);

		return goalie;
	}
	
	protected static JSONArray convertToJSONArray(ArrayDeque<Float> deque){
		JSONArray list = new JSONArray();
		int size = deque.size();
		for (int i = 0; i < size; i++){
			list.add(deque.removeFirst());
		}
		return list;
	}
}
