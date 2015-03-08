import java.util.*;

public class WorstFit{
    /*
      Worst Fit: Finds the largest segment of memory for swapping a process in.
      Start with 100 chunk of memory.
      First time around, swap one in. Update largest Hole Size and Hole Locations.
      
     */
    int[] memory;
    final int RUN_TIME = 60;
    List<Process> processes;
    int largestHoleSize;
    HashMap<Integer,ArrayList<Integer>> holeLocations; //Keeps track of hole sizes and their respective indexes.

    public WorstFit(List<Process> processes){
	this.processes = processes;
	memory = new int[100];
	largestHoleSize = memory.length;
	holeLocations = new HashMap<Integer,ArrayList<Integer>>();
	holeLocations.put(memory.length,new ArrayList<Integer>());
	holeLocations.get(memory.length).add(0);
    }

    public void beginSwapping(){
	
    }
    public void printSize(){
	System.out.println(largestHoleSize);
    }
    public void printHoleLocations(){
	Iterator<Integer> keys = holeLocations.keySet().iterator();
	int key;
	while(keys.hasNext()){
	    key = keys.next();
	    System.out.printf("Key: %d, Value: %s\n",key,holeLocations.get(key).toString());
	}
    }
    public static void main(String[] args){
	WorstFit test = new WorstFit(new ArrayList<Process>());
	test.printHoleLocations();
    }
}
