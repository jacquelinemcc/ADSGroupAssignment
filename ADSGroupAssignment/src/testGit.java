import java.util.Scanner;

public class testGit {


	public static void main(String[] args) {

		boolean validSelection = true;
		int userSelection =0;
		System.out.println("Welcome to the Vancouver Bus Data System.");
		
		do {
			Scanner scan = new Scanner (System.in);
			System.out.println("Enter 1 to search for shortest path between two stops. \n"
					+ "Enter 2 to search for bus stops by stop name. \n"
					+ "Enter 3 to search for trips by arrival time.");
			
			if(scan.hasNextInt()) {
				
				userSelection = scan.nextInt();
				if(userSelection == 1 || userSelection ==2 || userSelection ==3) {
					validSelection = true;
				}
				else {
					System.out.println("Please enter 1,2 or 3.");
					validSelection = false;
				}
			
			}
			else {
				System.out.println("Please enter 1,2 or 3.");
				validSelection = false;
			}
			
			if(validSelection) {
				if(userSelection == 1) {
					Dijkstra.DijkstraMain();
				}
			
				else if(userSelection ==2) {
				//question2
				}
				else if(userSelection == 3) {
					System.out.println("Loading. Please Wait.");
					int tryAgainOrExit = Question3.q3Main();
					if(tryAgainOrExit==1) {
						validSelection = false;
						Question3.q3Main();
					}
					else if(tryAgainOrExit==2) {
						System.out.print("Welcome back to the main menu");
						validSelection = false;
					}
					else validSelection = true;
					System.out.println("Thank you. Goodbye");
				}
			}
			
			
		}while(!validSelection);
		
		
		
	}
}


