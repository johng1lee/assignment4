import java.util.*;
public class Hole implements Comparable{
    private int size;
    private int startIndex;
    private int endIndex;
    public Hole(int startIndex, int endIndex){
	this.startIndex = startIndex;
	this.endIndex = endIndex;
	size = endIndex - startIndex + 1;
    }

    public int getSize(){
	return size;
    }
    public int getStartIndex(){
	return startIndex;
    }
    public void setStartIndex(int newStartIndex){
	size = endIndex - newStartIndex + 1;
	startIndex = newStartIndex;
    }
    public int getEndIndex(){
	return endIndex;
    }
    public void setEndIndex(int newEndIndex){
	size = newEndIndex - startIndex + 1;
	endIndex = newEndIndex;
    }
    public void mergeHole(Hole otherHole){
	int difference;
	if(startIndex<otherHole.getStartIndex()){
	    difference = otherHole.getEndIndex() - endIndex;
	    endIndex = otherHole.getEndIndex();
	    System.out.println("Old size is: " + size);
	    size = size + difference;
	    System.out.println("New size is: " + size);
	    System.out.printf("Start Index: %d | End Index: %d\n", startIndex, endIndex);
	}
	else{
	    difference = startIndex - otherHole.getStartIndex();
	    startIndex = otherHole.getStartIndex();
	    System.out.println("Old size is: " + size);
	    size = size + difference;
	    System.out.println("New size is: " + size);
	    System.out.printf("Start Index: %d | End Index: %d\n", startIndex, endIndex);
	}
    }
    public int compareTo(Object hole){
	return (((Integer)((Hole)hole).getSize())).compareTo((Integer)size);
    }
    public String toString(){
	return "start: " + Integer.toString(startIndex) + " end: " + Integer.toString(endIndex) + " size: " + Integer.toString(size);
    }
    public static void main(String[] args){
    	// ArrayList<Hole> listH = new ArrayList<Hole>();
    	// listH.add(new Hole(0,2));
    	// listH.add(new Hole(77,79));
    	// listH.add(new Hole(100,105));
    	// listH.add(new Hole(85,90));
    	// listH.add(new Hole(10,15));
    	// System.out.println(listH.toString());
    	// Collections.sort(listH);
    	// System.out.println(listH.toString());
    }
}
