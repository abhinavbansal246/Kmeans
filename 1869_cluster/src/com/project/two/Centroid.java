package com.project.two;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

//import maCountWords;

public class Centroid {
	
	
	
	
	public static void centroidMap(Map<String,Double> finalMap,int totalWords,String distance,Map<Integer,String> tagDocMap)
	{
		Map<String,Integer> termCountMap = null;
		int allTerms=0;
		String term="";
		int numerator=0;
		int denominator=0;
		double centroid=1;
		//System.out.println(tagDocMap.toString());
		allTerms = getCount(tagDocMap);
		termCountMap=getDistance(tagDocMap);
		for(Entry<String,Integer> entry:termCountMap.entrySet())
		{
			 
			//System.out.println(entry.getKey()+"----"+entry.getValue());
			numerator=entry.getValue()+1;
			denominator = allTerms+totalWords;
			centroid = ((double)numerator/(double)denominator);
			finalMap.put(entry.getKey()+","+distance, centroid) ;
			
		}
	}
	
	
	public static Map<String,Integer> getDistance(Map<Integer,String> distance)
	{
		Map<String,Integer> mapGd = new HashMap<String,Integer>();
		int count=0;
		String tempValue="";
		String term="";
		try{
			for(Entry<Integer,String> entry:distance.entrySet())
			{
				String arr[] = entry.getValue().split(" ");
				for(String st:arr)
				{
					//System.out.println("counting the tags");
					/*
					 if (dtag[j][i] == 1) {
									
										System.out.println("\n flag3 \n");
										
									term[i]=term[i] + dterm[j][i];
									//counter= counter+1;
									
								//	System.out.println("\t" + i + "," + j + "-->" + dterm[j][i]);
									//System.out.println("\t test["+i+ "]\t***"+ test[i]);
							//	}
									
									prob[i]= (int) (tag[i]*term[i]);
							}
					 * */
					String str[] = st.split("::::");
				 term=str[0];
				 if(mapGd.containsKey(term))
				 {
					 
					 count=mapGd.get(term)+defParser(str[1],0);
					 mapGd.put(term,count);
				 }
				 else
				 {
					 count  =defParser(str[1],0);
					 //count  =Integer.parseInt(str[1]);
				 }
				 
				 mapGd.put(term,count);
				 tempValue =term;
				}
			}
		}catch(Exception e)
		{
			mapGd.put(term,count);
			 tempValue =term;
		}
		return mapGd;
	}
	
	
	public static int getCount(Map<Integer,String> ccDistance)
	{
		int count=0;
		try{
			
			for(Entry<Integer,String> entry:ccDistance.entrySet())
			{
				if((entry.getValue()!=null) ||(!entry.getValue().equals(""))){
					String arr[]=entry.getValue().split(" ");
					/*
					 HashMap<String, Integer> display = new HashMap<String, Integer>();
					for(int i=1;i<3;i++){
						String present = "map" +i;
						display = (HashMap<String, Integer>) submap.get(i);
					System.out.println("\n"+ present +"\t"+ submap.get(i));
					 * */
					for(String tempValue:arr)
					{
						String arr1[]=tempValue.split("::::");
						//String arr1[]=tempValue.split(":");
						//totalTerms+=Integer.parseInt(arr1[1]);
						count+=defParser(arr1[1], 0);
					}
				}
			}
		}catch(Exception e)
		{
			e.getMessage();
			count+=0;
		}
		return count;
	}
	
	
	public static int defParser(String number, int valueDef) {
		  try {
		    return Integer.parseInt(number);
		  } catch (Exception e) {
		    return valueDef;
		  }
		}
	
	
	
	public static int allWords(Map<Integer,String> mapTrain)
	{
		int termAll=0;
		Set<String> wordAll =new HashSet<String>();
		for(Entry<Integer,String> entry:mapTrain.entrySet())
		{
			String arr[]=entry.getValue().split(",");
			String arr1[] = arr[0].split(" ");
			/*
 			* try {
			 * 
			 * // System.out.print("\n iii" + dtag[1][3] + "\t");
			 * 
			 * for (i = 0; i <= 3; i++) {
			 * 
			 * for (j = 0; j <= 3; j++) {
			 * 
			 * if (dtag[j][i] == 1) { tag[i] = tag[i] + 1;
			 * 
			 * }
			 * 
			 * else {
			 * 
			 * }
			 * */
			
			for(String term:arr1)
			{
				if(!term.equals(""))
					wordAll.add(term.trim());
			}
		}
		
		termAll= wordAll.size();
		return termAll;
	}
	
	
	
	
	public static Map<Integer,String> gKey(String value, Map<Integer, String> mapGk) {
		  
		  List<Integer> keys = new ArrayList<Integer>();
		  Map<Integer,String> freqMap = new HashMap<Integer,String>();
		//  CountWords cw = new CountWords();
		  
		  
		  for(Entry<Integer, String> entry:mapGk.entrySet()) {
			  String str=entry.getValue();
			  String arr[] = str.split(",");
			  String titleTags[] = arr[1].split(" ");
			  for(String s: titleTags){
				  if(s.equals(value))
				  {
					  if(!entry.getKey().equals(""))
						  keys.add(entry.getKey());
					  break;
				  }
				  
					/*
					 * BufferedReader br = new BufferedReader(new
					 * InputStreamReader(System.in) ); String inputURL = br.readLine(); try
					 * { int port = (); String response; while((response = .readLine()) !=
					 * null) System.out.println(response); catch( e){
					 * System.out.println("+e.getMessage()); } catch ( e){
					 * System.out.println("file out of range" + e.getMessage()); } catch
					 * (IOException e) { System.out.println("IO Error:"+e.getMessage()); }
					 * catch (Exception e){ System.out.println("Error:"+e.getMessage()); } }
					 */
			 }
		  }
		  
		  for(Integer key:keys){
			  String arr[]=mapGk.get(key).toString().split(",");
		  }
		  keys.clear();
		  keys=null;
		  
		  return freqMap;
	}
	
}
