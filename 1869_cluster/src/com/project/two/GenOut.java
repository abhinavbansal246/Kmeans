package com.project.two;

import java.io.FileWriter;
import java.io.IOException;

import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;


public class GenOut {

	 private static ICsvBeanWriter beanWriter = null;
	 private static PojoOut fb = null;
	 private static String[] header=null;
	 private static CellProcessor[] processors =null;
	
private static CellProcessor[] getProcessors() {
        
        final CellProcessor[] processors = new CellProcessor[] { 
                new NotNull(), // firstName
                new NotNull(), // lastName
        };
        
        return processors;
}


public static void createHeader()
{
     try {
    	 	 fb = new PojoOut();
             beanWriter = new CsvBeanWriter(new FileWriter("e:\\try\\file1.csv"),
                     CsvPreference.STANDARD_PREFERENCE);
             
             // the header elements are used to map the bean values to each column (names must match)
             /*header = new String[] { "cluster", "ids"};*/
             header = new String[] { "id", "cluster"};
             processors = getProcessors();
             
             // write the header
             beanWriter.writeHeader(header);
     }catch(Exception e)
     {
    	 e.printStackTrace();
     }
	
}


public static void printRows(int id, int cluster)
{
	try
	{
		fb.setId(id);
		fb.setCluster(cluster);
	  //  fb.setTags(tags);
	    beanWriter.write(fb, header, processors);
	}catch(Exception e)
	{
		e.printStackTrace();
	}
}

public static void closeReader()
{
	 if( beanWriter != null ) {
         try {
				beanWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
}

}
