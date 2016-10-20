package ca.ualberta.emar;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class IntegerReport {

	private HashMap<String, ArrayDeque<Integer>> namesToQueue;
	private HashMap<Integer, List<String>> totalToNames;
	private String statName;
	private static final Date DATE = new Date();
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static final String NOW = new String(sdf.format(DATE));

	
	public IntegerReport(String statName){
		namesToQueue = new HashMap<String, ArrayDeque<Integer>>();
		totalToNames = new HashMap<Integer, List<String>>();
		this.statName = statName;
		
	}
	
	protected void add(String playerName, ArrayDeque<Integer> statQueue){
		namesToQueue.put(playerName, statQueue);
	}
	
	protected int getLastXGamesTotal(ArrayDeque<Integer> stat, int lastXGames){
		ArrayDeque<Integer> c = stat.clone();
		int count = 0;
		for (int i = 0; i < lastXGames; i++){
			count += c.poll();
		}
		return count;
	}

	
	protected Map<Integer, List<String>> sortStats(int lastXGames){
		//need to iterate over all the names in namesToQueue
		//and create the totals
		Iterator<Entry<String, ArrayDeque<Integer>>> iter = namesToQueue.entrySet().iterator();
		while(iter.hasNext()){
			//key is the total, so need to find it for the last 3 games
			//value is the arraylist of playerNames
			Entry<String, ArrayDeque<Integer>> pair = iter.next();
			String playerName = (String) pair.getKey();
			int total = this.getLastXGamesTotal(namesToQueue.get(playerName), lastXGames);
			//if key doesn't exist, then create new array and add name to it
			//before pairing it with the key
			if (!totalToNames.containsKey(total)){
				List<String> array = new ArrayList<String>();
				array.add(playerName);
				totalToNames.put(total, array);
				
			}
			else{
				List<String> array = totalToNames.get(total);
				array.add(playerName);
			}
		}
		
		
		Map<Integer, List<String>> sorted = new TreeMap<Integer, List<String>>(Collections.reverseOrder());
		sorted.putAll(totalToNames);
		
		return sorted;
		
	}
	
	protected void createReport(int lastXGames){
		File dir = new File("Reports/" + NOW);
		if (!dir.exists()){
			dir.mkdirs();
		}
		
		Map<Integer, List<String>> sorted = this.sortStats(lastXGames);
		File filePath = new File(dir, NOW +  " Last " + lastXGames + " " + this.statName + ".csv");
		try{
			FileWriter writer = new FileWriter(filePath);
			//header
			writer.append("name");
			writer.append(',');
			writer.append(this.statName);
			writer.append(',');
			writer.append("stats queue");
			writer.append('\n');
			
			for (Integer key : sorted.keySet()){
				for (String name : sorted.get(key)){
					writer.append(name);
					writer.append(',');
					writer.append(key.toString());
					writer.append(',');
					Integer[] fullArray = namesToQueue.get(name).toArray(new Integer[10]);
					Integer[] partArray = Arrays.copyOfRange(fullArray, 0, lastXGames);
					writer.append('"' + Arrays.toString(partArray) + '"');
					writer.append('\n');
				}
			}
			writer.flush();
			writer.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	

}
