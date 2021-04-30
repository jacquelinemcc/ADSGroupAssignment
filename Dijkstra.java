import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Dijkstra {
	public double[] distanceTo;
	public dirEdge[] edgeTo;
	public boolean[] visited;
	public String shortestRoute;
	
	public Dijkstra(wGraph map, int startPoint, int endPoint) {
        this.distanceTo = new double[20000];
        this.edgeTo = new dirEdge[20000];
        this.visited = new boolean[20000];
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
        	System.out.println(stop + " " + nextStop);
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

    public static void main(String args[]){
    	int stop1 = 646;
        int stop2 = 68;
        wGraph map = new wGraph();
        Dijkstra s = new Dijkstra(map, stop1, stop2);

        System.out.println(s.shortestRoute);
        System.out.println(s.distanceTo[stop2]);        
    }
	
}
