import java.io.File;
import java.io.*;
import java.util.*;

public class Question3 {

	public static String file3 = "Stop_times.txt";
	public static HashMap<String, ArrayList<String>> stopTimesMap = new HashMap<String, ArrayList<String>>();

	public static void main(String[] args) {

		stopTimesMap = HashMapFromTextFile();
		System.out.println("Please enter an arrival time you would like to search for (in the format hh:mm:ss)");
		Scanner input = new Scanner (System.in);
		String userInputTime = input.next();
		ArrayList<String> tripsWithUserTime = findRelevantTimes(userInputTime);

		for(int i = 0; i < tripsWithUserTime.size(); i++) {   
			System.out.println(tripsWithUserTime.get(i));
		}  

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

				if(validTime(time) == true) {
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

				}
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


	public static ArrayList<String> findRelevantTimes(String userInputTime){
		ArrayList<String> relevantTimes = new ArrayList<String>();

		if(validTime(userInputTime)==true) {
			if(stopTimesMap.containsKey(userInputTime)) {
				relevantTimes = stopTimesMap.get(userInputTime);
				printSortedTimes(relevantTimes);
			}
			else {
				relevantTimes.add("The time "+userInputTime+" does not have any trips occurring.");
			}
		}
		else {
			relevantTimes.add("This is not a valid time. Please enter a time in the format hh:mm:ss within 24hrs and minutes and seconds no longer than 60.");
		}
		return relevantTimes;
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










