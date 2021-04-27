import java.util.ArrayList;
import java.util.HashMap;

/*import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Scanner;
import java.io.BufferedReader;*/

public class Question3 {

	public static HashMap<String, ArrayList<String>> stopTimes = new HashMap<String, ArrayList<String>>();
	
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
}
