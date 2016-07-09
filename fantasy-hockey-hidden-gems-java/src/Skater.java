
public class Skater {
	
	private String name;
	private String playerPositionCode;
	private SimpleFixedQueue gamesPlayed;
	private int totalGamesPlayed;
	private SimpleFixedQueue goals;
	private int totalGoals;
	private SimpleFixedQueue assists;
	private int totalAssists;
	private SimpleFixedQueue points;
	private int totalPoints;
	private SimpleFixedQueue plusMinus;
	private int totalPlusMinus;
	private SimpleFixedQueue penaltyMinutes;
	private int totalPenaltyMinutes;
	private SimpleFixedQueue ppPoints;
	private int totalppPoints;
	private SimpleFixedQueue ppGoals;
	private int totalppGoals;
	private int shGoals;
	private SimpleFixedQueue shots;
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
	
	public SimpleFixedQueue getGamesPlayed(){
		return gamesPlayed;
	}
	
	protected void setGamesPlayed(SimpleFixedQueue gamesPlayed){
		this.gamesPlayed = gamesPlayed;
	}
	
	protected void setTotalGoals(int totalGoals){
		this.totalGoals = totalGoals;
	}
	
	public int getTotalGoals(){
		return totalGoals;
	}
	
	protected void setGoals(SimpleFixedQueue goals){
		this.goals = goals;
	}
	
	public SimpleFixedQueue getGoals(){
		return goals;
	}
	
	public int getTotalAssists(){
		return totalAssists;
	}
	
	protected void setTotalAssists(int totalAssists){
		this.totalAssists = totalAssists;
	}
	
	public SimpleFixedQueue getAssists(){
		return assists;
	}
	
	protected void setAssists(SimpleFixedQueue assists){
		this.assists = assists;
	}
	
	public SimpleFixedQueue getPoints(){
		return points;
	}
	
	protected void setPoints(SimpleFixedQueue points){
		this.points = points;
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
	
	public SimpleFixedQueue getPlusMinus(){
		return plusMinus;
	}
	
	protected void setPlusMinus(SimpleFixedQueue plusMinus){
		this.plusMinus = plusMinus;
	}
	
	public int getTotalPenaltyMinutes(){
		return totalPenaltyMinutes;
	}
	
	protected void setTotalPenaltyMinutes(int totalPenaltyMinutes){
		this.totalPenaltyMinutes = totalPenaltyMinutes;
	}
	
	public SimpleFixedQueue getPenaltyMinutes(){
		return penaltyMinutes;
	}
	
	protected void setPenaltyMinutes(SimpleFixedQueue penaltyMinutes){
		this.penaltyMinutes = penaltyMinutes;
	}
	
	public int getTotalppPoints(){
		return totalppPoints;
	}
	
	protected void setTotalppPoints(int totalppPoints){
		this.totalppPoints = totalppPoints;
	}
	
	public SimpleFixedQueue getppPoints(){
		return ppPoints;
	}
	
	protected void setppPoints(SimpleFixedQueue ppPoints){
		this.ppPoints = ppPoints;
	}
	
	public int getTotalppGoals(){
		return totalppGoals;
	}
	
	protected void setTotalppGoals(int totalppGoals){
		this.totalppGoals = totalppGoals;
	}
	
	public SimpleFixedQueue getppGoals(){
		return ppGoals;
	}
	
	protected void setppGoals(SimpleFixedQueue ppGoals){
		this.ppGoals = ppGoals;
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
	
	public SimpleFixedQueue getShots(){
		return shots;
	}
	
	protected void setShots(SimpleFixedQueue shots){
		this.shots = shots;
	}
}
