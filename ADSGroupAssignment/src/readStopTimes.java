import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class readStopTimes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<stopTimes> timesList = readFromText("stop_times.txt");
		
		for(stopTimes a: timesList) {
            System.out.println(a);
        }
		
	}

	public static List<stopTimes> readFromText(String filename){
		List<stopTimes> timesList = new ArrayList <> (); 
        Path pathToFile = Paths.get(filename);

        try(BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)){
            br.readLine();
            String line = br.readLine();

            while (line != null) {
                String [] variable = line.split("[,\n]");

                //convert string array to list
                List<String> list = Arrays.asList(variable);
                
                    stopTimes dataset = createStopTimes(variable);
                    timesList.add(dataset);
                }
                line = br.readLine();
            
        }	catch (Exception e) {
        	}

        return timesList;
    }
	
	private static stopTimes createStopTimes (String [] metadata) {
        int tripID = Integer.parseInt(metadata[1]); //convert string into int
        String arrivalTime = metadata[2]; //convert string into int
        String depTime = metadata[3]; //convert string into int
        int stopID = Integer.parseInt(metadata[4]); //convert string into int
        int stopSeq = Integer.parseInt(metadata[10]);
        String stopHead = metadata[11];
        int pickup = Integer.parseInt(metadata[13]); //convert string into int
        int dropoff = Integer.parseInt(metadata[14]);
        double shape = Double.parseDouble(metadata[15]);
        return new stopTimes(tripID, arrivalTime, depTime, stopID,stopSeq, stopHead, pickup, dropoff, shape);
    }

	
}
