import java.util.*;

public class WorstFit{
    /*
      Worst Fit: Finds the largest segment of memory for swapping a process in.
      Start with 100 chunk of memory.
      First time around, swap one in. Update largest Hole Size and Hole Locations.
      
     */
    ArrayList<String> memory;
    final int RUN_TIME = 60;
    final int MEMORY_SIZE = 100;
    List<Process> allProcesses;
    ArrayList<Process> runningProcesses;
    ArrayList<Hole> holes;
    public WorstFit(List<Process> allProcesses){
	this.allProcesses = allProcesses;
	memory = new ArrayList<String>(100);
	for(int i=0;i<100;i++){
	    memory.add(".");
	}
	//Track processes and holes
	runningProcesses = new ArrayList<Process>();
	holes = new ArrayList<Hole>();
	holes.add(new Hole(0,MEMORY_SIZE-1));

    }

    public void beginSwapping(){
	int time = 0;
	Process readyProcess;
	Iterator<Process> iterRunningProcesses;
	while(time <= RUN_TIME){
	    readyProcess = allProcesses.remove(0);
	    Hole largestHole = holes.get(0);
	    if(readyProcess.getSize()<=largestHole.getSize()){
		runningProcesses.add(readyProcess);
		readyProcess.setMemoryStartIndex(largestHole.getStartIndex());
		readyProcess.setMemoryEndIndex(largestHole.getStartIndex() + readyProcess.getSize() - 1)
		for(int i=readyProcess.getMemoryStartIndex();i<=readyProcess.getMemoryEndIndex;i++){
		    memory.set(i,Integer.toString(readyProcess.getID()));
		}
		largestHole.setStartIndex(largestHole.getStartIndex() + readyProcess.getSize());
	    }
	    Process completedProcess = null;
	    Process evaluateProcess = null;
	    iterRunningProcesses = runningProcesses.iterator();
	    while(iterRunningProcesses.hasNext()){
		evaluateProcess = iterRunningProcesses.next();
		evaluateProcess.decrementTime();//decrement time
		if(evaluateProcess.isDone()){
		    for(int i=evaluateProcess.getMemoryStartIndex();i<=evaluateProcess.getMemoryEndIndex();i++){
			memory.set(i,".")
		    }
		    iterRunningProcesses.remove();//remove completed processes from running process list
		    holes.add(new Hole(evaluateProcess.getMemoryStartIndex(),evaluateProcess.getMemoryEndIndex()))
		}
	    }
	    Collections.sort(holes,new Comparator<Hole>(){
		    public int compare(Object h1, Object h2){
			return ((Integer)((Hole)h1.getStartIndex())).compareTo(((Integer) ((Hole)h1.getStartIndex())));
		    }
		});
	    mergeHoles();
	    time++;
	}
    }
    private void mergeHoles(){
	ListIterator iterHoles = holes.listIterator();
	boolean first = true;
	Hole previous;
	Hole current;
	if(holes.size() < 2){
	    return;
	}
	while(iterHoles.hasNext()){
	    if(first){
		previous = iterHoles.next();
		first = false;
	    }
	    else{
		current = iterHoles.next();
		if(previous.getEndIndex()+1 == current.getStartIndex()){
		    previous.mergeHole(current);
		    iterHoles.remove()
		}
	    }
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
