import java.util.ArrayDeque;

public class Skater {
	
	private String name;
	private String playerPositionCode;
//	private ArrayDeque<Integer> gamesPlayedQueue;
	private int totalGamesPlayed;
	private ArrayDeque<Integer> goalsQueue;
	private int totalGoals;
	private ArrayDeque<Integer> assistsQueue;
	private int totalAssists;
	private ArrayDeque<Integer> pointsQueue;
	private int totalPoints;
	private ArrayDeque<Integer> plusMinusQueue;
	private int totalPlusMinus;
	private ArrayDeque<Integer> penaltyMinutesQueue;
	private int totalPenaltyMinutes;
	private ArrayDeque<Integer> ppPointsQueue;
	private int totalppPoints;
	private ArrayDeque<Integer> ppGoalsQueue;
	private int totalppGoals;
	private int shGoals;
	private ArrayDeque<Integer> shotsQueue;
	private int totalShots;
	
	public Skater(String playerName){
		name = playerName;
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
}
