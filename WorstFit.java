import java.util.*;

public class WorstFit{
    /*
      Worst Fit: Finds the largest segment of memory for swapping a process in.
      Start with 100 chunk of memory.
      First time around, swap one in. Update largest Hole Size and Hole Locations.
      
     */
    Process[] memory;
    final int RUN_TIME = 60;
    final int MEMORY_SIZE = 100;
    List<Process> allProcesses;
    ArrayList<Process> runningProcesses;
    ArrayList<Hole> holes;
    public WorstFit(List<Process> allProcesses){
	this.allProcesses = allProcesses;
	memory = new Process[MEMORY_SIZE];
	//Track processes and holes
	runningProcesses = new ArrayList<Process>();
	holes = new ArrayList<Hole>();
	holes.add(new Hole(0,MEMORY_SIZE-1));

    }

    public void beginSwapping(){
	int time = 0;
	while(time <= RUN_TIME){
	    //get largest chunk position
	    //check if process fits
	    //    if fits, put process in first available slot
	    //        then update holeLocations:
	    //            1.check if hole size has multiple slots. 
	    //                if not, remove and add new hole size
	    //                       add new hole size:
	    //                           check if new hole size exists
	    //                               if yes, append new location
	    //                               if no, create new entry
	    //                if yes, delete the first slot.
	    //            2. add process to runningProcesses
	    //decrement timer across all running processes
	    //remove completed process then update holeLocations for each process removed
	    //    retrieve process location and empty the array of that process
	    //    retrieve 
	    

	    time++;
	}
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
