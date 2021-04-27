import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import java.io.*;
import java.util.*;

public class Question3 {

	//public static HashMap<String, ArrayList<String>> stopTimes = new HashMap<String, ArrayList<String>>();
	public static String file3 = "Stop_times.txt";

	public static void main(String[] args) {

		Map<String, String> stopTimesMap = HashMapFromTextFile();

		for (Map.Entry<String, String> entry:stopTimesMap.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}

	public static Map<String, String> HashMapFromTextFile(){

		Map<String, String> map = new HashMap<String, String>();
		BufferedReader br = null;

		try {

			File file = new File(file3);
			br = new BufferedReader(new FileReader(file));
			String line = null;
			line = br.readLine();
			
			while ((line = br.readLine()) != null) {

				String stopDetails = line;
				String[] parts = line.split(",");
				String time = parts[1].trim();

				if(validTime(time) == true) {
					if (!time.equals("") && !stopDetails.equals(""))
						map.put(time, stopDetails);
				}
			}

		}
		catch (Exception e) {e.printStackTrace();}

		finally {

			if (br != null) {
				try {
					br.close();
				}
				catch (Exception e) {System.out.println("Error");}
			}
		}

		return map;
	}

	public static boolean validTime(String time) {
		boolean isValidTime = false;
		boolean isValidHour = false;
		boolean isValidMinute = false;
		boolean isValidSecond = false;


		String[] timeP1 = time.split(":");
		int hour = Integer.parseInt(timeP1[0].trim());  

		String[] timeP2 = time.split(":");
		int minute = Integer.parseInt(timeP2[1].trim());  

		String[] timeP3 = time.split(":");
		int second = Integer.parseInt(timeP3[2].trim());  


		if(hour<24 && hour>=0) {
			isValidHour = true;
		}
		if(minute<60 && minute>=0) {
			isValidMinute = true;
		}
		if(second<60 && second>=0) {
			isValidSecond = true;
		}
		if(isValidHour == true && isValidMinute == true && isValidSecond == true) {
			isValidTime = true;
		}

		return isValidTime;
	}


}

// Java program to reading
//text file to HashMap
/*
class GFG {
	final static String filePath
	= "F:/Serialisation/write.txt";
	/*public static void main(String[] args)
 {

     // read text file to HashMap
     Map<String, String> mapFromFile
         = HashMapFromTextFile();

     // iterate over HashMap entries
     for (Map.Entry<String, String> entry :mapFromFile.entrySet()) {
         System.out.println(entry.getKey() + " : " + entry.getValue());
     }
 }*/
/*
	public static Map<String, String> HashMapFromTextFile()
	{

		Map<String, String> map
		= new HashMap<String, String>();
		BufferedReader br = null;

		try {

			// create file object
			File file = new File(filePath);

			// create BufferedReader object from the File
			br = new BufferedReader(new FileReader(file));

			String line = null;

			// read file line by line
			while ((line = br.readLine()) != null) {

				// split the line by :
				String[] parts = line.split(":");

				// first part is name, second is number
				String name = parts[0].trim();
				String number = parts[1].trim();

				// put name, number in HashMap if they are
				// not empty
				if (!name.equals("") && !number.equals(""))
					map.put(name, number);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {

			// Always close the BufferedReader
			if (br != null) {
				try {
					br.close();
				}
				catch (Exception e) {
				};
			}
		}

		return map;
	}
}
 */

/*public String file3 = "Stop_times.txt";
	public static double stopDetails [][];



	public static double[] fileReading(String file3){

		try
    	{
    		File file = new File(file3);
        	Scanner scanner = new Scanner(file);
        	int i = 0;

        	while(scanner.hasNextLine())
        	{
        		String [] line = scanner.nextLine().trim().split("\\s+");
        		if(i == 0)	
        		{
        			stopDetails = new double[Integer.parseInt(line[i])][Integer.parseInt(line[i])];
            		//edgeTo = new int[Integer.parseInt(line[i])][Integer.parseInt(line[i])];

            		for(int j = 0; j < distanceTo.length; j++)
            		{
            			for(int k = 0; k < distanceTo[j].length; k++)
            			{
            				distanceTo[j][k] = Integer.MAX_VALUE;
            				if(j == k)
            				{
            					distanceTo[j][k] = 0;
            				}
            			}	
            		}
            	}
        		else if(i == 1)
        		{
        			edgeCount = Integer.parseInt(line[i - 1]);
        		}
        		else
        		{
        			distanceTo[Integer.parseInt(line[0])][Integer.parseInt(line[1])] = Double.parseDouble(line[2]);
        			edgeTo[Integer.parseInt(line[0])][Integer.parseInt(line[1])] = Integer.parseInt(line[0]);

        		}
        		i++;	
        	}

        	for(int j = 0; j < distanceTo.length; j++)
        	{
        		shortestPath(j);
        	}
        	scanner.close();
    	}
    	catch(Exception x)
    	{
    		distanceTo = new double[0][0];
    		edgeTo = new int[0][0];
    		return;
    	}
 */

