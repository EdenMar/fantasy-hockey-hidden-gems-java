import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SkaterTest {

	Skater skater;

	@Before
	public void setUp() throws Exception {
		skater = new Skater("Connor McDavid");
	}

	@Test
	public void testGetName() {
		assertEquals(skater.getName(), "Connor McDavid");
	}

	@Test
	public void testSetTotalGamesPlayed() {
		skater.setTotalGamesPlayed(20);
		assertEquals(skater.getTotalGamesPlayed(), 20);
	}

	@Test
	public void testSetGamesPlayed() {
		SimpleFixedQueue tmp = new SimpleFixedQueue();
		tmp.enqueue(5);
		skater.setGamesPlayed(tmp);
		SimpleFixedQueue tmp2 = skater.getGamesPlayed();
		assertEquals(tmp2.dequeue(), 5);
	}
	
	@Test
	public void testGroupOfGettersSetters(){
		SimpleFixedQueue goals = new SimpleFixedQueue();
		goals.enqueue(10);
		SimpleFixedQueue assists = new SimpleFixedQueue();
		assists.enqueue(20);
		SimpleFixedQueue points = new SimpleFixedQueue();
		points.enqueue(30);
		int totalGoals = 20;
		int totalAssists = 10;
		int totalPoints = 30;
		SimpleFixedQueue plusMinus = new SimpleFixedQueue();
		SimpleFixedQueue penaltyMinutes = new SimpleFixedQueue();
		SimpleFixedQueue ppPoints = new SimpleFixedQueue();
		int totalPlusMinus = -3;
		int totalPenaltyMinutes = 100;
		int totalppPoints = 2;
		plusMinus.enqueue(5);
		penaltyMinutes.enqueue(12);
		ppPoints.enqueue(1);
		skater.setAssists(assists);
		skater.setPoints(points);
		skater.setGoals(goals);
		skater.setTotalAssists(totalAssists);
		skater.setTotalGoals(totalGoals);
		skater.setTotalPoints(totalPoints);
		skater.setPlusMinus(plusMinus);
		skater.setPenaltyMinutes(penaltyMinutes);
		skater.setppPoints(ppPoints);
		skater.setTotalPlusMinus(totalPlusMinus);
		skater.setTotalPenaltyMinutes(totalPenaltyMinutes);
		skater.setTotalppPoints(totalppPoints);
		assertSame(skater.getAssists(), assists);
		assertSame(skater.getPoints(), points);
		assertSame(skater.getGoals(), goals);
		assertSame(skater.getPlusMinus(), plusMinus);
		assertSame(skater.getPenaltyMinutes(), penaltyMinutes);
		assertSame(skater.getppPoints(), ppPoints);
		assertEquals(skater.getTotalAssists(), totalAssists);
		assertEquals(skater.getTotalGoals(), totalGoals);
		assertEquals(skater.getTotalPoints(), totalPoints);
		assertEquals(skater.getTotalPenaltyMinutes(), totalPenaltyMinutes);
		assertEquals(skater.getTotalPlusMinus(), totalPlusMinus);
		assertEquals(skater.getTotalppPoints(), totalppPoints);
		
		
		
	}

}
