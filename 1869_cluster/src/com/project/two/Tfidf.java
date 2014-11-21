package com.project.two;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;





//import com.dm.prog.BuildFiles;
import com.project.two.GenOut;
import com.project.two.CleanData;
import com.project.two.PojoTrain;

public class Tfidf {
	public static void main(String[] args) {
		//String trainingFile, testFile;
		int k = 10;
		int p;
String trainingFile;
		
		//Scanner inputtrain = new Scanner(System.in);
		trainingFile = args[0];
		
		//Scanner k = new Scanner(System.in);
		 
		k=Integer.parseInt(args[0]);
		
		try {
		//	System.out.println("");
			int noOfrows = 0;
			@SuppressWarnings("unused")
			int traverse, tomap;
			
			
		//	Map<Integer, String> testData = null;
			Map<Integer, String> trngData = null;
			Map<Integer, Integer> resultmap = null;
			trngData = feedTr(trainingFile);
			//trngData = feedTr("train.csv", tlist);
			
			System.out.println(trngData.toString());
			// trngData = createTrainingMap("E:\\try\\train.csv",tagList);
			//System.out.println("Getting training data \n");
			// System.out.println(trainingMap.toString());
			noOfrows = trngData.size();
			System.out.println("This model is based on = " + noOfrows
					+ " rows of training data");
			//tagData = countLst(tlist);
			//uniqueTags = tagCount(trngData);
		//	int noOfWords = buildVocab(trngData);
		
			GenOut.createHeader();
		resultmap =	firstIteration(k, noOfrows);
		getOutputFile(resultmap);
			 
		/*Iterator it = resultmap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        System.out.println(pairs.getKey() + " = " + pairs.getValue());
	        it.remove();
	        }*/
		
		//System.out.println("result map = " + resultmap);
			 
			GenOut.closeReader();
			
			
		
			System.out.println("Thank you");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// feed train ---------------------------------------------------------------//
	
	public static Map<Integer, String> feedTr(String fileName) {
		Map<Integer, String> map1 = new HashMap<Integer, String>();
		ICsvBeanReader bR = null; //
		try {
			bR = new CsvBeanReader(new FileReader(fileName),
					CsvPreference.STANDARD_PREFERENCE);

		//	System.out.println("feedtrain");
			// the header elements are used to map the values to the bean (names
			// must match)
			final String[] header = bR.getHeader(true);
			final CellProcessor[] processors = trainGetter();

			PojoTrain td;
			while ((td = bR.read(PojoTrain.class, header, processors)) != null) {
			
				map1.put(Integer.parseInt(td.getId().trim()),
						CleanData.cleanDocumentsByStemming(CleanData.cleanUp(td.getTitle().trim())));
				
	//	System.out.println(" previous "+ map1);
			}

		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		} finally {
			if (bR != null) {
				try {
					bR.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return map1;
	}
 //------------- cell processor------------------------------
	
	private static CellProcessor[] trainGetter() {

		final CellProcessor[] processors = new CellProcessor[] { new NotNull(),
				new NotNull(), new NotNull(), new NotNull(), };

		return processors;
		
	}
	
	
	//-----------------------------------------------get output
	
	public static void getOutputFile(Map<Integer, Integer> resultmap)
	{
	
		int documentId=0;
		int cluster=0;
		int del=0;

		Map<String,Double> finalMap = new HashMap<String,Double>();
	//	Map<String,Integer> termListMap =null;
		//System.out.println("result map from output = " + resultmap);
		for(Entry<Integer,Integer> entry:resultmap.entrySet()) 
		 {
			
			for(del=0;del<10000;del++){
				
			}
			
			documentId=entry.getKey();
			cluster=entry.getValue();
		//	System.out.println("\n document =" + documentId + ",  cluster =" + cluster);
			GenOut.printRows(documentId, cluster);
			/*
			 */
			finalMap.clear();
			
			
			//Set<String> termSet = BuildFiles.StringToSet(entry.getValue());
			/*
			 * Map of count of each word
			 */
			//termListMap = BuildFiles.returnFrequency(entry.getValue());
			
		/*	for(String tag:tags)
			{
				for(String term:termSet)
				{
					termTag = term+","+tag;
					if(ttMap.containsKey(termTag))
					{
						termProbability*=ttMap.get(termTag);
						//System.out.println("term: "+term+"---"+termListMap.get(term));
						termProbability=Math.pow(termProbability, termListMap.get(term));
					}
				}
				documentProbability = ((double)tagCount.get(tag)/(double)totalRows);
				documentProbability*=((documentProbability)*termProbability);
				
				finalMap.put(documentId+","+tag, documentProbability);
				termProbability=1;
				documentProbability=1;
			}*/
			
			
		//	finalTags = BuildFiles.getTags(finalMap);
		//	finalTags = finalTags.substring(0,finalTags.length()-1);
			
			//System.out.println(documentId +" "+finalTags );
		
			
		 }
	}
	
	public static Map<Integer, Integer> firstIteration(int k, int n) {
		
		Map<Integer, Integer> resultmap = new HashMap<Integer, Integer>();
		int f=1;
		while (f<n)
		{
			int r=	(int) (1 + (Math.random() * (k)));
		   // resultmap.put(r, cat(resultmap.get(r), f));
			resultmap.put(f, r);
		    f=f+1;
		}
		
	
		
	/*	 Iterator it = resultmap.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        System.out.println(pairs.getKey() + " = " + pairs.getValue());
		        it.remove();
		        }*/
		
		
	//System.out.println("result map = " + resultmap);
		
		return resultmap;
	}
	
	public static double distance(float a[], float b[]){
		double c = 0;
		int i=0;
		int l=0;
		double d=0;
		for (i=0;i<a.length;i++){
			d= a[i] - b[i];
			d=d*d;
			d=Math.sqrt(d);
			c=c+d;
			
		}
		
		return c;}
	
	
	 static String cat(String a, int b) {
	       
		 String c =",";
		 a += c;
		 a += b;
	        return a;
	    }
	
}
