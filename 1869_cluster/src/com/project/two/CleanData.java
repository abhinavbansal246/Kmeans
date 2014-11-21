package com.project.two;


import java.io.IOException;
import java.io.StringReader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

import com.project.two.GenOut;



public class CleanData {
	
	static boolean bool =false;
	
	public static String cleanUp(String str) throws IOException 
    {
		//System.out.println("cleanup\n");
		String genStr="";
		String docs[]=str.split(" ");
		GenOut.createHeader();
		
	    for(String string:docs) {
	        
			StringBuilder buildstring = new StringBuilder();
			StandardAnalyzer analyser = new  StandardAnalyzer(Version.LUCENE_45);
			// Using Apache Lucene for stemming and lametization
	        TokenStream tkStream = new StandardTokenizer(Version.LUCENE_45, new StringReader(string));
	     /*
	      * 	while(j<8)
		{
		
		for(int i=0;i<k;i++)
		{}x=50;
		j=j+1;
		x=x+f;
		if(x>100){
		x=50;
		f=0;
		}f=f+50;y=y+50;
		}
	}

}
	      * 
	      * */
	        
	        tkStream = new StopFilter(Version.LUCENE_45, tkStream, StandardAnalyzer.STOP_WORDS_SET);
	        CharTermAttribute token = tkStream.getAttribute(CharTermAttribute.class);
	        tkStream.reset();
	        while (tkStream.incrementToken()) 
	        {
	        	bool=true;
	       
	            
	            if(token.length()>1 && !(token.toString().matches(".*\\d.*") && (!token.toString().equals(""))))
	            	buildstring.append(token.toString().trim());
	        }
	        if(tkStream!=null)
	        	tkStream.close();
	        if(bool == true)
	        genStr+=buildstring.toString()+" ";
	        bool=false;
		}   
		return genStr.trim();
    }
	
	
	
	 
	
    public static String cleanDocumentsByStemming(String text) throws IOException{
        StringBuffer out = new StringBuffer();
     //   System.out.println("cleandocument by stemming \n");
        if (text!=null && text.trim().length()>0){
            StringReader tReader = new StringReader(text);
            Analyzer analyzer = new EnglishAnalyzer(Version.LUCENE_45);
            TokenStream tStream = analyzer.tokenStream("contents", tReader);
            CharTermAttribute term = tStream.addAttribute(CharTermAttribute.class);
            tStream.reset();
            try {
                while (tStream.incrementToken()){
                    out.append(term.toString());
                    out.append(" ");
                }
            } catch (IOException ioe){
                System.out.println("Error: "+ioe.getMessage());
            }
        }

        // If, for some reason, the stemming did not happen, return the original text
        if (out.length()==0)
            out.append(text);
        return out.toString().trim();
    }

}
