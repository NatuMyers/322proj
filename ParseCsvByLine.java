import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
 
 
public class ParseCsvByLine
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
     
      //Reverse Rows
      reverseRows(CSV);
      
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
	private static void writeToCSVFile(ArrayList<String[]> elements, String file) {
		CSVWriter writer;
		try {
			writer = new CSVWriter(new FileWriter(file), ',');
		     writer.writeAll(elements);
			 writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	} // writeToCSVFile end

	// reverseRows takes in ArrayList containing all rows, and reverses them one row
	// at a time
	private static void reverseRows(ArrayList<String[]> elements) {
		
		for (int i = 0 ; i<elements.size(); i++){
			String[] newLine = elements.get(i);
			for (int j = 0; j<newLine.length/2; j++){
				String temp = newLine[j];
				newLine[j] = newLine[newLine.length-1-j];
				newLine[newLine.length-1-j] = temp;
			}
	    }
	} // reverseRows end

	
} // ParseCsvLineByLine end
