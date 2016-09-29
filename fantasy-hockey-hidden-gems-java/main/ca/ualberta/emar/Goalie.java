package ca.ualberta.emar;



import java.util.ArrayDeque;
import java.util.Arrays;

import org.json.simple.*;

public class Goalie {
	private String playerName;
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
	private ArrayDeque<Float> GAAQueue = new ArrayDeque<Float>(10);
	private ArrayDeque<Integer> timeOnIceQueue = new ArrayDeque<Integer>(10);
	private int totalTimeOnIce;
	
	//constructor takes JSONObject and adds goalie to DB
	public Goalie (JSONObject goalie, DataFromDB yesOrNo){
		this.playerName = (String)goalie.get("playerName");
		this.totalGamesPlayed = ((Long)goalie.get("gamesPlayed")).intValue();
		
		if (yesOrNo == DataFromDB.NO){
			this.savePctgQueue.add(((Double) goalie.get("savePctg")).floatValue());
			this.totalSavePctg = ((Double)goalie.get("savePctg")).floatValue();
			
			this.winsQueue.add(((Long)goalie.get("wins")).intValue());
			this.totalWins = ((Long)goalie.get("wins")).intValue();
			
			this.shutouts = ((Long)goalie.get("shutouts")).intValue();
			
			this.shotsAgainstQueue.add(((Long)goalie.get("shotsAgainst")).intValue());
			this.totalShotsAgainst = ((Long)goalie.get("shotsAgainst")).intValue();

			this.goalsAgainstQueue.add(((Long)goalie.get("goalsAgainst")).intValue());
			this.totalGoalsAgainst = ((Long)goalie.get("goalsAgainst")).intValue();
			
			this.savesQueue.add(((Long)goalie.get("saves")).intValue());
			this.totalSaves = ((Long)goalie.get("saves")).intValue();
			
			this.totalGAA = ((Double)goalie.get("goalsAgainstAverage")).floatValue();
			this.GAAQueue.add(((Double)goalie.get("goalsAgainstAverage")).floatValue());
			
			this.timeOnIceQueue.add(((Long)goalie.get("timeOnIce")).intValue());
			this.totalTimeOnIce = ((Long)goalie.get("timeOnIce")).intValue();
		}
		else{
			this.savePctgQueue = convertToArrayDeque((JSONArray)goalie.get("savePctgQueue"));
			this.totalSavePctg = ((Double)goalie.get("totalSavePctg")).floatValue();
			
			this.winsQueue = Skater.convertToArrayDeque((JSONArray)goalie.get("winsQueue"));
			this.totalWins = ((Long)goalie.get("totalWins")).intValue();
			
			this.shutouts = ((Long)goalie.get("shutouts")).intValue();
			
			this.goalsAgainstQueue = Skater.convertToArrayDeque((JSONArray)goalie.get("goalsAgainstQueue"));
			this.totalGoalsAgainst = ((Long)goalie.get("totalGoalsAgainst")).intValue();
			
			this.shotsAgainstQueue = Skater.convertToArrayDeque((JSONArray)goalie.get("shotsAgainstQueue"));
			this.totalShotsAgainst = ((Long)goalie.get("totalShotsAgainst")).intValue();
			
			this.savesQueue = Skater.convertToArrayDeque((JSONArray)goalie.get("savesQueue"));
			this.totalSaves = ((Long)goalie.get("totalSaves")).intValue();
			
			this.totalGAA = ((Double)goalie.get("totalGAA")).floatValue();
			this.GAAQueue = convertToArrayDeque((JSONArray)goalie.get("GAAQueue"));
			
			this.timeOnIceQueue = Skater.convertToArrayDeque((JSONArray)goalie.get("timeOnIceQueue"));
			this.totalTimeOnIce = ((Long)goalie.get("totalTimeOnIce")).intValue();
		}
	}
	
	public String getName(){
		return playerName;
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
		return GAAQueue;
	}
	
	protected void setGoalsAgainstAverage(ArrayDeque<Float> goalsAgainstAverage){
		this.GAAQueue = goalsAgainstAverage;
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
	protected JSONObject getGoalieJSON(){
		JSONObject goalie = new JSONObject();
		goalie.put("playerName", this.playerName);
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
		goalie.put("GAAQueue", convertToJSONArray(this.GAAQueue));
		goalie.put("timeOnIceQueue", Skater.convertToJSONArray(this.timeOnIceQueue));
		goalie.put("totalTimeOnIce", this.totalTimeOnIce);

		return goalie;
	}
	
	//http://stackoverflow.com/questions/18335214/how-to-add-arrayliststring-to-json-array-keeping-type-safety-in-mind
	@SuppressWarnings("unchecked")
	protected static JSONArray convertToJSONArray(ArrayDeque<Float> deque){
		JSONArray list = new JSONArray();
		ArrayDeque<Float> a = deque.clone();
		int size = deque.size();
		for (int i = 0; i < size; i++){
			list.add(a.removeFirst());
		}
		return list;
	}
	
	protected static ArrayDeque<Float> convertToArrayDeque(JSONArray array){
		ArrayDeque<Float> data = new ArrayDeque<Float>(10);
		try{
			for (Object o: array){
				Float tmp = ((Double)o).floatValue();
				data.add(tmp);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return data;
	}
	@Override
	public boolean equals(Object o){
		if (o == this)
			return true;
		
		if (!(o instanceof Goalie))
			return false;
		
		Goalie g = (Goalie)o;
		
		return this.playerName.equals(g.getName())
				&& this.totalGamesPlayed == g.getTotalGamesPlayed()
				&& Arrays.equals(this.savePctgQueue.toArray(), g.getSavePctg().toArray())
				&& this.totalSavePctg == g.getTotalSavePctg()
				&& Arrays.equals(this.winsQueue.toArray(), g.getWins().toArray())
				&& this.totalWins == g.getTotalWins()
				&& this.shutouts == g.getShutouts()
				&& Arrays.equals(this.shotsAgainstQueue.toArray(), g.getShotsAgainst().toArray())
				&& this.totalShotsAgainst == g.getTotalShotsAgainst()
				&& Arrays.equals(this.goalsAgainstQueue.toArray(), g.getGoalsAgainst().toArray())
				&& this.totalGoalsAgainst == g.getTotalGoalsAgainst()
				&& Arrays.equals(this.savesQueue.toArray(), g.getSaves().toArray())
				&& this.totalSaves == g.getTotalSaves()
				&& Arrays.equals(this.GAAQueue.toArray(), g.getGoalsAgainstAverage().toArray())
				&& this.totalGAA == g.getTotalGAA()
				&& Arrays.equals(this.timeOnIceQueue.toArray(), g.getTimeOnIce().toArray())
				&& this.totalTimeOnIce == g.getTotalTimeOnIce();
	}
}
