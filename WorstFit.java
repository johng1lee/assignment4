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
	memory = new ArrayList<String>();
	for(int i=0;i<MEMORY_SIZE;i++){
	    memory.add(".");
	}
	//Track processes and holes
	runningProcesses = new ArrayList<Process>();
	holes = new ArrayList<Hole>();
	holes.add(new Hole(0,MEMORY_SIZE-1));

    }

    public void beginSwapping(){
	int time = 0;
	boolean isProcessServed = true;
	Process readyProcess = null;
	Iterator<Process> iterRunningProcesses;
	while(time <= RUN_TIME){
	    if(allProcesses.size()<=0){
		System.out.println("No more processes");
		return;
	    }
	    if(isProcessServed){
	    readyProcess = allProcesses.remove(0);
	    }
	    if(readyProcess == null){
		return;
	    }
	    System.out.println(readyProcess);
	    Hole largestHole = holes.get(0);
	    if(readyProcess.getSize()<=largestHole.getSize()){
		runningProcesses.add(readyProcess);
		readyProcess.setMemoryStartIndex(largestHole.getStartIndex());
		readyProcess.setMemoryEndIndex(largestHole.getStartIndex() + readyProcess.getSize() - 1);
		for(int i=readyProcess.getMemoryStartIndex();i<=readyProcess.getMemoryEndIndex();i++){
		    memory.set(i,readyProcess.getID());
		}
		largestHole.setStartIndex(largestHole.getStartIndex() + readyProcess.getSize());
		printMemory();
		isProcessServed = true;
	    }
	    else{
		isProcessServed = false;
	    }
	    Process completedProcess = null;
	    Process evaluateProcess = null;
	    iterRunningProcesses = runningProcesses.iterator();
	    while(iterRunningProcesses.hasNext()){
		evaluateProcess = iterRunningProcesses.next();
		if(evaluateProcess.isDone()){
		    for(int i=evaluateProcess.getMemoryStartIndex();i<=evaluateProcess.getMemoryEndIndex();i++){
			memory.set(i,".");
		    }
		    iterRunningProcesses.remove();//remove completed processes from running process list
		    holes.add(new Hole(evaluateProcess.getMemoryStartIndex(),evaluateProcess.getMemoryEndIndex()));
		    printMemory();
		}
		evaluateProcess.decrementTime();//decrement time
	    }
	    Collections.sort(holes,new Comparator<Hole>(){
		    public int compare(Hole h1, Hole h2){
			return ((Integer)h1.getStartIndex()).compareTo(((Integer)h2.getStartIndex()));
		    }
		});
	    mergeHoles();
	    Collections.sort(holes);
	    time++;
	}
    }
    private void mergeHoles(){
	ListIterator<Hole> iterHoles = holes.listIterator();
	boolean first = true;
	Hole previous = null;
	Hole current = null;
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
		if(previous == null || current == null){return;}
		if(previous.getEndIndex()+1 == current.getStartIndex()){
		    previous.mergeHole(current);
		    iterHoles.remove();
		}
	    }
	}
    }
    private void printHoles(){
	System.out.println("------------HOLES----------");
	for(Hole h: holes){
	    System.out.println(h);
	}
    }
    private void printMemory(){
	for(String s:memory){
	    System.out.print(s);
	}
	System.out.println();
    }
}
