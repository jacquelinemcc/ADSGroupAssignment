import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Question3 {

	public static String file3 = "Stop_times.txt";
	public static HashMap<String, ArrayList<String>> stopTimesMap = new HashMap<String, ArrayList<String>>();

	public static int q3Main() {

		stopTimesMap = HashMapFromTextFile();
		boolean validInput = true;
		String userInputTime = "";
		boolean proceedValid = true;
		int userProceed = 0;
		
		do {
			System.out.println("Please enter an arrival time you would like to search for (in the format hh:mm:ss)");
			Scanner input = new Scanner (System.in);
			userInputTime = input.next();
		
			if (!userInputTime.matches("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$")){
				System.out.println( userInputTime+ " is not a valid time. Please enter a time in the format hh:mm:ss within 24hrs and minutes and seconds no longer than 60.");
				validInput = false;
			} 
		
		else validInput = true;
		}while(!validInput);
		
		if(validInput == true){
		
			ArrayList<String> tripsWithUserTime = findRelevantTimes(userInputTime);

			if(tripsWithUserTime!=null) {
				System.out.println("The details for trips arriving at "+userInputTime+" are: ");
				System.out.println("Trip ID\tDeparture Time\tStop ID\t Stop Sequence\tPick Up\tDrop Off\tShape Distance Travelled");
				for(int i = 0; i < tripsWithUserTime.size(); i++) { 
					String [] tripDetails = tripsWithUserTime.get(i).split(",");
				
					if(tripDetails.length==9)
						System.out.println(tripDetails[0]+"\t"+tripDetails[2]+"\t"+tripDetails[3]+"\t\t"+tripDetails[4]+"\t"+tripDetails[6]+"\t"+tripDetails[7]+"\t\t"+tripDetails[8]);
					else System.out.println(tripDetails[0]+"\t"+tripDetails[2]+"\t"+tripDetails[3]+"\t\t"+tripDetails[4]+"\t"+tripDetails[6]+"\t"+tripDetails[7]+"\t\t0");
				} 
			}
			else System.out.println("No trips found for time "+userInputTime+".");
		}
		
		do {
			Scanner scanner = new Scanner (System.in);
			System.out.println("Enter 1 to search for a different time. \n"
				+ "Enter 2 to return to main menu.\n"
				+ "Enter any other key to exit.");
			if(scanner.hasNextInt()) {
			
			userProceed = scanner.nextInt();
			if(userProceed == 1 || userProceed ==2) {
				proceedValid = true;
			}
			else proceedValid = false;
		
		}
		else proceedValid = false;
			
		}while(!proceedValid);
		
		if(proceedValid) {
			if(userProceed == 1) {
				return 1;
			}
		
			else if(userProceed ==2) {
				return 2;
			}
			
		}
		return 0;
	}

	public static HashMap<String, ArrayList<String>> HashMapFromTextFile(){

		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		BufferedReader br = null;


		try {

			File file = new File(file3);
			br = new BufferedReader(new FileReader(file));
			String line = null;
			line = br.readLine();

			while ((line = br.readLine()) != null) {

				String[] parts = line.split(",");
				String time = parts[1].trim();

				//if(validTime(time) == true) {
					if (!time.equals("") && !line.equals("")) {
						if(map.containsKey(time)) {
							map.get(time).add(line);
						}
						else {
							ArrayList<String> tripInfo = new ArrayList<>();
							tripInfo.add(line);
							map.put(time, tripInfo);
						}
					}

				//}
			}
			return map;
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

	


	public static ArrayList<String> findRelevantTimes(String userInputTime){
		ArrayList<String> relevantTimes = new ArrayList<String>();

			if(stopTimesMap.containsKey(userInputTime)) {
				relevantTimes = stopTimesMap.get(userInputTime);
				printSortedTimes(relevantTimes);
				return relevantTimes;
			}
			else {
				
				return null;
			}
			
		}
		
		
	



	public static void printSortedTimes(ArrayList<String> relevantTimes) {

		int[] stopIDs = new int[relevantTimes.size()];

		for(int i=0; i<stopIDs.length; i++) {
			String info = relevantTimes.get(i);
			String[] parts = info.split(",");
			stopIDs[i] = Integer.parseInt(parts[0].trim());
		}

		int temp;
		for (int i=0; i<stopIDs.length; i++) {
			for(int j=i; j>0; j--) {
				if(stopIDs[j] < stopIDs[j-1]) {
					temp = stopIDs[j];
					stopIDs[j] = stopIDs[j-1];
					stopIDs[j-1] = temp;

					String temp1 = relevantTimes.get(j);
					relevantTimes.set(j, relevantTimes.get(j-1));
					relevantTimes.set(j-1, temp1);
				}
			}
		}
	}
}







