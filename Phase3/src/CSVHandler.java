import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
 
 
public class CSVHandler
{
	
   public static String[][] read(InputStream in) throws Exception
   {
	   
	   //Takes the file name
	   InputStream csvToRead = in;
	   
	  
      //Build reader instance
      //Read csvToRead
	  CSVReader reader = new CSVReader(new InputStreamReader(csvToRead), ',', '"');
      
      //Read CSV line by line and store each line in an ArrayList<String[]>
      String[] nextLine;
      ArrayList<String[]> CSV = new ArrayList<String[]>();
      while ((nextLine = reader.readNext()) != null) {
    	  if (nextLine != null) {
    		  //Verifying the read data here
    		  CSV.add(nextLine);
    	  }
      }
     
      
      //Convert Array List into an Array
      String[][] csvArray =  new String[CSV.size()][];
      for (int i = 0; i < CSV.size(); i++){
    	  String[] row = CSV.get(i);
    	  csvArray[i] = row;
      }
      return csvArray;
      
	       //TODO: Add more functionality to rows, JTable as well....
	      
	       // Write to CSV file defined in the command line
	       // This call is not used in the Table Model 
	       //writeToCSVFile(CSV, "file.csv");
	}

   	// writeToCSVFile 
	public static void writeToCSVFile(String[][] elements, OutputStream out) {
		CSVWriter writer;
		try {
			writer = new CSVWriter(new OutputStreamWriter(out));
			for (int i=0; i < elements.length; i++){
				String[] nextLine = elements[i];
		                writer.writeNext(nextLine);
			}
			 writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	} // writeToCSVFile end

	// reverseRows takes in ArrayList containing all rows, and reverses them one row
	// at a time
	private static void reverseRows(String[][] elements) {
		
		for (int i = 0 ; i<elements.length; i++){
			String[] newLine = elements[i];
			for (int j = 0; j<newLine.length/2; j++){
				String temp = newLine[j];
				newLine[j] = newLine[newLine.length-1-j];
				newLine[newLine.length-1-j] = temp;
			}
	    }
	} // reverseRows end

	
} // ParseCsvLineByLine end
