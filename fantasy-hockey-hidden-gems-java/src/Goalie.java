import java.util.ArrayDeque;

public class Goalie {
	private String name;
	private String playerPositionCode = "G";
	private int totalGamesPlayed;
//	private ArrayDeque<Integer> gamesPlayedQueue;
	private ArrayDeque<Float> savePctgQueue;
	private float totalSavePctg;
	private ArrayDeque<Integer> winsQueue;
	private int totalWins;
	private int shutouts;
	private ArrayDeque<Integer> shotsAgainstQueue;
	private int totalShotsAgainst;
	private ArrayDeque<Integer> goalsAgainstQueue;
	private int totalGoalsAgainst;
	private ArrayDeque<Integer> savesQueue;
	private int totalSaves;
	private float totalGAA;
	private ArrayDeque<Float> goalsAgainstAverageQueue;
	private ArrayDeque<Integer> timeOnIceQueue;
	private int totalTimeOnIce;
	
	
	public Goalie (String name){
		this.name = name;
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
}
