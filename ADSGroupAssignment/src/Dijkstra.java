
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Dijkstra {
	public double[] distanceTo;
	public dirEdge[] edgeTo;
	public boolean[] visited;
	public String shortestRoute;
	
	public Dijkstra(wGraph map, int startPoint, int endPoint) {
        this.distanceTo = new double[wGraph.stopCount];
        this.edgeTo = new dirEdge[wGraph.stopCount];
        this.visited = new boolean[wGraph.stopCount];
        for (int i = 0; i < distanceTo.length; i++) {
            distanceTo[i] = Double.POSITIVE_INFINITY;
            visited[i] = false;		
        }
        distanceTo[startPoint] = 1;
    	int v = shortestPath();
    	visited[v] = true;
    	relax(map, startPoint,endPoint);
    	
        this.shortestRoute = findShortestRoute(startPoint, endPoint, map);
    }


    public int shortestPath()
    {
    	int x = -1;
    	for(int i = 0; i < distanceTo.length; i ++) {
    		if((visited[i] == false) && (distanceTo[i] != Double.POSITIVE_INFINITY)){return i;}
    	}
       	return x;	
    }
    
    public void relax(wGraph map,int startPoint, int endPoint) {
    	ArrayList<dirEdge> edge = map.edges.get(startPoint);
        for(int i =0; i < edge.size();i++) {
        	int stop = edge.get(i).stop;
        	int nextStop = edge.get(i).nextStop;
        	double weight = edge.get(i).weight;
        if (distanceTo[nextStop] > (distanceTo[stop] + weight)) {
            distanceTo[nextStop] = distanceTo[stop] + weight;
            edgeTo[nextStop] = edge.get(i);
        }
    }
    }

    public String findShortestRoute(int stop1, int stop2, wGraph map) {
        Queue<Integer> stops = new LinkedList<Integer>();
        stops.add(stop1);
        int temp1 = stop1;
        for(int i =0;i<distanceTo.length;i++) {
        	
            int temp = map.edges.get(temp1).get(i).nextStop;
            stops.add(temp);
            if (temp == stop2) {
                break;
                
            }
            temp1 = temp;
        }
        shortestRoute = ("The shortest route from " + stop1 + " to " + stop2 + " is: ");
        while (!stops.isEmpty()){
            shortestRoute += ("\n" + stops.remove());
        }

        return shortestRoute;
    }

    public static void DijkstraMain(){
    	wGraph map = new wGraph();
    	boolean validStop1 = false;
    	int stop1 = 11546;
    	boolean validStop2 = true;
    	int stop2 = 0;
    	
    	for(int i =0;i<=wGraph.stopCount-1;i++) {
    		int temp = map.edges.get(stop1).get(0).stop;
    		if (temp == stop1) {
    			validStop1 = true;
    		}
    		System.out.println(validStop1);
    	}
    		
    	System.out.println(validStop1);
    	
    	/*
    	do {
    			Scanner input1 = new Scanner (System.in);
    			System.out.println("Please enter your starting bus stop");
    			if(input1.hasNextInt()) {
    				stop1 = input1.nextInt();
    				if(map.edges.contains(stop1)) {
    					validStop1=true;
    				}
    				else {
    					System.out.println("This stop does not exist.");
    					validStop1=false;
    				}
    			}
    			else {
    				System.out.println("Invalid input.");
    				validStop1=false;
    			}
    	}while(!validStop1);
    	
    	do {
			Scanner input2 = new Scanner (System.in);
			System.out.println("Please enter your destination bus stop");
			if(input2.hasNextInt()) {
				stop2 = input2.nextInt();
				if(map.edges.contains(stop2)) {
					validStop2=true;
				}
				else {
					System.out.println("This stop does not exist.");
					validStop2=false;
				}
			}
			else {
				System.out.println("Invalid input.");
				validStop2=false;
			}
	}while(!validStop2);
	
			
        
        Dijkstra s = new Dijkstra(map, stop1, stop2);

        System.out.println(s.shortestRoute);
        System.out.println(s.distanceTo[stop2]); 
        */       
    }
    
}
    
	
    
