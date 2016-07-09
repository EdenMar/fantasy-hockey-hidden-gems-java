
public class Goalie {
	private String name;
	private String playerPositionCode = "G";
	private int totalGamesPlayed;
	private SimpleFixedQueue gamesPlayed;
	private SimpleFixedQueue savePctg;
	private float totalSavePctg;
	private SimpleFixedQueue wins;
	private int totalWins;
	private int shutouts;
	private SimpleFixedQueue shotsAgainst;
	private int totalShotsAgainst;
	private SimpleFixedQueue goalsAgainst;
	private int totalGoalsAgainst;
	private SimpleFixedQueue saves;
	private int totalSaves;
	private float totalGAA;
	private SimpleFixedQueueFloat goalsAgainstAverage;
	private SimpleFixedQueueFloat timeOnIce;
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
	
	public SimpleFixedQueue getGamesPlayed(){
		return gamesPlayed;
	}
	
	protected void setGamesPlayed(SimpleFixedQueue gamesPlayed){
		this.gamesPlayed = gamesPlayed;
	}
	
	public SimpleFixedQueue getSavePctg(){
		return savePctg;
	}
	
	protected void setSavePctg(SimpleFixedQueue savePctg){
		this.savePctg = savePctg;
	}
	
	public float getTotalSavePctg(){
		return totalSavePctg;
	}
	
	protected void setTotalSavePctg(float totalSavePctg){
		this.totalSavePctg = totalSavePctg;
	}
	
	public SimpleFixedQueue getWins(){
		return wins;
	}
	
	protected void setWins(SimpleFixedQueue wins){
		this.wins = wins;
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
	
	public SimpleFixedQueue getShotsAgainst(){
		return shotsAgainst;
	}
	
	protected void setShotsAgainst(SimpleFixedQueue shotsAgainst){
		this.shotsAgainst = shotsAgainst;
	}
	
	public int getTotalShotsAgainst(){
		return totalShotsAgainst;
	}
	
	protected void setTotalShotsAgainst(int totalShotsAgainst){
		this.totalShotsAgainst = totalShotsAgainst;
	}
	
	public SimpleFixedQueue getGoalsAgainst(){
		return goalsAgainst;
	}
	
	protected void setGoalsAgainst(SimpleFixedQueue goalsAgainst){
		this.goalsAgainst = goalsAgainst;
	}
	
	public int getTotalGoalsAgainst(){
		return totalGoalsAgainst;
	}
	
	protected void setTotalGoalsAgainst(int totalGoalsAgainst){
		this.totalGoalsAgainst = totalGoalsAgainst;
	}
	
	public SimpleFixedQueue getSaves(){
		return saves;
	}
	
	protected void setSaves(SimpleFixedQueue saves){
		this.saves = saves;
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
	
	public SimpleFixedQueueFloat getGoalsAgainstAverage(){
		return goalsAgainstAverage;
	}
	
	protected void setGoalsAgainstAverage(SimpleFixedQueueFloat goalsAgainstAverage){
		this.goalsAgainstAverage = goalsAgainstAverage;
	}
	
	public SimpleFixedQueueFloat getTimeOnIce(){
		return timeOnIce;
	}
	
	protected void setTimeOnIce(SimpleFixedQueueFloat timeOnIce){
		this.timeOnIce = timeOnIce;
	}
	
	public float getTotalTimeOnIce(){
		return totalTimeOnIce;
	}
	
	protected void setTotalTimeOnIce(int totalTimeOnIce){
		this.totalTimeOnIce = totalTimeOnIce;
	}
}
