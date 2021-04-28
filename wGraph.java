import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class wGraph {
    public static ArrayList<ArrayList<dirEdge>> edges;

    	
    	
    private static void stopTimesToArrayList() {
    try {
    	
    	 int route, stop, nextStop ;
    	 int nextRoute;
    	 
    	String fileName = "stop_times.txt";
    	File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(",");
        scanner.nextLine();
        route = scanner.nextInt();
        scanner.next();
        scanner.next();
        stop = scanner.nextInt();

		while (scanner.hasNext()) {
			scanner.nextLine();
			nextRoute = scanner.nextInt();
			scanner.next();
	        scanner.next();
	        nextStop = scanner.nextInt();
			if(route == nextRoute) {
		        
				edges.get(stop).add(new dirEdge(stop, nextStop, 1));
			}
			else {
				route = nextRoute;
				
			}
			stop = nextStop;
			}
		
		scanner.close();
	}
    catch(FileNotFoundException e){edges = null;System.out.println("file 'stop_times.txt' not found");}
    }
    
    private static void transfersToArrayList() {
        try {
        	
        	 int stop, nextStop, weightType,weight;
        	 
        	String fileName = "transfers.txt";
        	File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(",");
            scanner.nextLine();
            

    		while (scanner.hasNext()) {
    			stop = scanner.nextInt();
                nextStop = scanner.nextInt();
                weightType = scanner.nextInt();
                if(weightType==0) { weight = 2;}
                else { weight = (scanner.nextInt())/100;}
              
    			edges.get(stop).add(new dirEdge(stop, nextStop, weight));
    			stop = nextStop;
    			}
    		
    		scanner.close();
    	}
        catch(FileNotFoundException e){edges = null; System.out.println("file 'transfers.txt' not found");}
        }


    

    public static void main(String[] args) {
  
        edges = new ArrayList<>(10000);
        for (int i = 0; i < (10000); i++) {
            edges.add(new ArrayList<>());
        }
        
        stopTimesToArrayList();
        transfersToArrayList();
        for (int i = 0; i < (10000); i++) {
            System.out.println(edges.get(i));
        }
    }
}

