
public class stopTimes {

	private int tripID;
	private String arrivalTime;
	private String depTime;
	private int stopID;
	private int stopSeq;
	private String stopHead;
	private int pickup;
	private int dropoff;
	private double shape;
	
	public stopTimes(int tripID, String arrivalTime,String depTime, 
			int stopID, int stopSeq, String stopHead, int pickup, int dropoff,
			double shape) {
		this.tripID = tripID;
		this.arrivalTime = arrivalTime;
		this.depTime = depTime;
		this.stopID = stopID;
		this.stopSeq = stopSeq;
		this.stopHead = stopHead;
		this.pickup = pickup;
		this.dropoff = dropoff;
		this.shape = shape;
	}
	
	public int getTrip() {return tripID;}
    public void setTripID(int tripID) {this.tripID = tripID;}
    
    public String getArrival() {return arrivalTime;}
    public void setArrival(String arrivalTime) {this.arrivalTime= arrivalTime;}

    public String getDep() {return depTime;}
    public void setDep(String depTime) {this.depTime= depTime;}

    public int getStopID() {return stopID;}
    public void setStopID(int stopID) {this.stopID = stopID;}
    
    public int getStopSeq() {return stopSeq;}
    public void setStopSeq(int stopSeq) {this.stopSeq = stopSeq;}

    public String getStopHead() {return stopHead;}
    public void setStopHead(String stopHead) {this.stopHead = stopHead;}

    public int getPickup() {return pickup;}
    public void setPickup(int pickup) {this.pickup = pickup;}
    
    public int getDropoff() {return dropoff;}
    public void setDropoff(int dropoff) {this.dropoff = dropoff;}
    
    public double getShape() {return shape;}
    public void setShape(double shape) {this.shape = shape; }

    @Override
    public String toString() {
       return "Flights "+tripID;
	}
	
}
