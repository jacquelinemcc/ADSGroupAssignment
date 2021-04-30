import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class wGraph {
    public static ArrayList<ArrayList<dirEdge>> edges;
    public static int stopCount;

    public wGraph() {
    	stopCount = countStops();
        edges = new ArrayList<>(stopCount*17);
        for (int i = 0; i < (stopCount*17); i++) {
            edges.add(new ArrayList<dirEdge>());
        }
        stopTimesToArrayList();
        transfersToArrayList();
    }
    private static int countStops() {
        try{
            String filename = "stops.txt";
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(",");
            scanner.nextLine();
            int i =0;
            while(scanner.hasNext()) {
                    scanner.nextLine();
                    i++;
                
            }
            scanner.close();
            return i;
        }   
        catch(FileNotFoundException e){System.out.println("File 'stops.txt' not found");return 0;}
    }
    private static void stopTimesToArrayList() {
    try {
    	
    	 int route, stop, nextStop =0;
    	 int nextRoute = 0;
    	 
    	String fileName = "stop_times.txt";
    	File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(",");
        scanner.nextLine();
        route = scanner.nextInt();
        scanner.next();
        scanner.next();
        stop = scanner.nextInt();
        scanner.nextLine();
		while (scanner.hasNext()) {
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
			scanner.nextLine();
		}
		
		scanner.close();
	}
    catch(FileNotFoundException e){edges = null;System.out.println("file 'stop_times.txt' not found");}
    }
    
    private static void transfersToArrayList() {
        try {
        	
        	 int stop, nextStop, weightType = 0;
        	 double weight = 0;
        	 
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
                else { 
                	scanner.useDelimiter("\n");
                	String line = scanner.next();
                	String[] parts = line.split(",");
                	weight = (Double.parseDouble(parts[1].trim()))/100;
                	scanner.useDelimiter(",");

                }
              
    			edges.get(stop).add(new dirEdge(stop, nextStop, weight));
    			scanner.nextLine();
    			}
    		
    		scanner.close();
    	}
        catch(FileNotFoundException e){edges = null; System.out.println("file 'transfers.txt' not found");}
        }


    

    
    
}

