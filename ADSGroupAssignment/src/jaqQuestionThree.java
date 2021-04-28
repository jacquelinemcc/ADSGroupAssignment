import java.io.File;
import java.util.Scanner;

public class jaqQuestionThree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String filepath = "stop_times.txt";
		String searchTime = "13:05:10";
		readRecords(searchTime, filepath);
	}
	
	private static Scanner txtScan;
	
	public static void readRecords(String searchTime, String filepath) {
		String tripID = "";
		String arrivalTime = "";
		String depTime = "";
		String stopID = "";
		String stopSeq = "";
		String stopHead = "";
		String pickup = "";
		String dropoff = "";
		String shape = "";
			
		try {
			txtScan = new Scanner(new File(filepath));
			txtScan.useDelimiter("[,\n]");
			while(txtScan.hasNext()) {
				tripID = txtScan.next();
				arrivalTime = txtScan.next();
				depTime = txtScan.next();
				stopID = txtScan.next();
				stopSeq = txtScan.next();
				stopHead = txtScan.next();
				pickup = txtScan.next();
				dropoff = txtScan.next();
				shape = txtScan.next();
				
				if(arrivalTime == searchTime) {
					
				}
			}
		}
		catch(Exception e) {
			
		}
	}
	

}
