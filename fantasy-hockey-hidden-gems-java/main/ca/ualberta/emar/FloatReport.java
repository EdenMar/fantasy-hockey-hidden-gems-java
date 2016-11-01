package ca.ualberta.emar;

import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
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
import java.util.TreeMap;
import java.util.Map.Entry;

public class FloatReport {

	private HashMap<String, ArrayDeque<Float>> namesToQueue;
	private HashMap<Float, List<String>> totalToNames;
	private String statName;
	private static final Date DATE = new Date();
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static final String NOW = new String(sdf.format(DATE));
	private static final DecimalFormat DF = new DecimalFormat("0.0000");
	
	public FloatReport(String name){
		namesToQueue = new HashMap<String, ArrayDeque<Float>>();
		totalToNames = new HashMap<Float, List<String>>();
		this.statName = name;
	}
	
	protected void add(String playerName, ArrayDeque<Float> statQueue){
		namesToQueue.put(playerName, statQueue);
	}
	
	protected Float getXLastGamesAvg(ArrayDeque<Float> stat, int lastXGames){
		float average = 0f;
		ArrayDeque<Float> clone = stat.clone();
		if (stat.size() >= lastXGames){
			for (int i = 0; i < lastXGames; i++){
				average += clone.pollLast();
			}
		}
		else{
			for (int i = 0; i < stat.size(); i++){
				average += clone.pollLast();
			}
		}
		
		return new Float(DF.format(average / (float)lastXGames));
	}
	
	protected Map<Float, List<String>> sortStats(int lastXGames){
		Iterator<Entry<String, ArrayDeque<Float>>> iter = namesToQueue.entrySet().iterator();
		
		while (iter.hasNext()){
			Entry<String, ArrayDeque<Float>> pair = iter.next();
			String playerName = (String)pair.getKey();
			Float avg = this.getXLastGamesAvg(namesToQueue.get(playerName), lastXGames);
			
			if (!totalToNames.containsKey(avg)){
				List<String> array = new ArrayList<String>();
				array.add(playerName);
				totalToNames.put(avg, array);
			}
			else{
				List<String> array = totalToNames.get(avg);
				array.add(playerName);
			}
		}
		
		Map<Float, List<String>> sorted;
		if (this.statName.equals("savePctg")){
			sorted = new TreeMap<Float, List<String>>(Collections.reverseOrder());
		}
		else{
			sorted = new TreeMap<Float, List<String>>();
		}
		
		sorted.putAll(totalToNames);
		
		return sorted;
	}
	
	protected void createReport(int lastXGames){
		File dir = new File("Reports/" + NOW);
		
		if (!dir.exists()){
			dir.mkdirs();
		}
		
		Map<Float, List<String>> sorted = this.sortStats(lastXGames);
		File filePath = new File(dir, NOW + " Last " + lastXGames + " " + this.statName + ".csv");
		
		try{
			FileWriter writer = new FileWriter(filePath);
			writer.append("name");
			writer.append(',');
			writer.append(this.statName);
			writer.append(',');
			writer.append("stats queue");
			writer.append('\n');
			
			for (Float key: sorted.keySet()){
				for(String name : sorted.get(key)){
					writer.append(name);
					writer.append(',');
					writer.append(key.toString());
					writer.append(',');
					Float[] fullArray = namesToQueue.get(name).toArray(new Float[10]);
					Float[] partArray = Arrays.copyOfRange(fullArray, 0, lastXGames);
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
