import java.util.*;

public class NextFit{
    
    ArrayList<String> memory;
    final int RUN_TIME = 60;
    final int MEMORY_SIZE = 100;
    List<Process> allProcesses;
    ArrayList<Process> runningProcesses;
    ArrayList<Hole> holes;
    private int lastAccessedHoleIndex = 0;
    private Hole currentStartingHole = null;
    
    public NextFit(List<Process> allProcesses){
    	this.allProcesses = allProcesses;
    	memory = new ArrayList<String>();
    	for(int i=0;i<MEMORY_SIZE;i++){
	    memory.add(".");
    	}
    	//Track processes and holes
    	runningProcesses = new ArrayList<Process>();
    	holes = new ArrayList<Hole>();
    	holes.add(new Hole(0,MEMORY_SIZE-1));
	currentStartingHole = holes.get(0);

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
	    lastAccessedHoleIndex = holes.indexOf(currentStartingHole);
	    if( lastAccessedHoleIndex == -1){
		lastAccessedHoleIndex = 0;//check later
	    }
	    // - - - - - - -
	    //System.out.printf("\nPrior to loop, currentStartingHole: %s | lastAccessedHoleIndex: %d | number of holes: %d\n",currentStartingHole.toString(),lastAccessedHoleIndex,holes.size());
	    for (int j = lastAccessedHoleIndex; j < holes.size(); j++){ 
		// added for loop to check if there are multiples holes,
		// and traverse them and find correct sized hole 
		Hole hole = holes.get(j);
		//System.out.printf("First loop: Index %d | Hole info: %s\n",j,hole.toString());
		if(readyProcess.getSize()<=hole.getSize()){
		    // - - - - - - -
		    // Adds process to runningProcesses, then updates the process with correct start and end indexes
		    // - - - - - - -
		    runningProcesses.add(readyProcess);
		    readyProcess.setMemoryStartIndex(hole.getStartIndex());
		    readyProcess.setMemoryEndIndex(hole.getStartIndex() + readyProcess.getSize() - 1);
		    // - - - - - - -
		    // Replaces holes with process for memory print out
		    // - - - - - - -
		    for(int i=readyProcess.getMemoryStartIndex();i<=readyProcess.getMemoryEndIndex();i++){
			memory.set(i,readyProcess.getID());
		    }
		    // - - - - - - -
		    // Update the hole starting index, remove holes that are filled
		    // - - - - - - -
		    hole.setStartIndex(hole.getStartIndex() + readyProcess.getSize());
		    currentStartingHole = hole;
		    if (hole.getSize() <= 0){ // testing to see if fixes error *it does fix the error with a hole of size 0 I was getting
			holes.remove(j);
			    if(holes.size()<= j){
				currentStartingHole = holes.get(0);
			    }
			    else{

				currentStartingHole = holes.get(j); // gets the next hole that has shifted over
			    }
		    }
		    // - - - - - - -
		    // print memory and exit loop
		    // - - - - - - -
		    printMemory();
		    isProcessServed = true;
		    break;
		}
		else{
		    isProcessServed = false;
		}
	    }
	    if(!isProcessServed){
		for (int j = 0; j < lastAccessedHoleIndex; j++){ // added for loop to check if there are multiples holes, and traverse them and find correct sized hole 
		    Hole hole = holes.get(j);
		    //System.out.printf("Second loop: Index %d | Hole info: %s\n",j,hole.toString());
		    if(readyProcess.getSize()<=hole.getSize()){
			// - - - - - - -
			// Adds process to runningProcesses, then updates the process with correct start and end indexes
			// - - - - - - -
			runningProcesses.add(readyProcess);
			readyProcess.setMemoryStartIndex(hole.getStartIndex());
			readyProcess.setMemoryEndIndex(hole.getStartIndex() + readyProcess.getSize() - 1);
			// - - - - - - -
			// Replaces holes with process for memory print out
			// - - - - - - -
			for(int i=readyProcess.getMemoryStartIndex();i<=readyProcess.getMemoryEndIndex();i++){
			    memory.set(i,readyProcess.getID());
			}
			// - - - - - - -
			// Update the hole starting index, remove holes that are filled
			// - - - - - - -
			hole.setStartIndex(hole.getStartIndex() + readyProcess.getSize());
			currentStartingHole = hole;
			if (hole.getSize() <= 0){ // testing to see if fixes error *it does fix the error with a hole of size 0 I was getting
			    holes.remove(j);
			    if(holes.size()<= j){
				currentStartingHole = holes.get(0);
			    }
			    else{
				currentStartingHole = holes.get(j); // gets the next hole that has shifted over
			    }
			}
			// - - - - - - -
			// print memory and exit loop
			// - - - - - - -
			printMemory();
			isProcessServed = true;
			break;
		    }
		    else{
			isProcessServed = false;
		    }
		}
	    }
    		
	    if (!isProcessServed)
		System.out.println("Not enough memory for Process " + readyProcess.getID() + " to be placed---------------------");
	    Process completedProcess = null;
	    Process evaluateProcess = null;
	    iterRunningProcesses = runningProcesses.iterator();
	    while(iterRunningProcesses.hasNext()){
		evaluateProcess = iterRunningProcesses.next();

		if(evaluateProcess.isDone()){
		    for(int i=evaluateProcess.getMemoryStartIndex();i<=evaluateProcess.getMemoryEndIndex();i++){
			memory.set(i,".");
		    }
		    System.out.println("Process Removed---------------------");
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
	    // After sorting, we have the new starting index.
	    lastAccessedHoleIndex = holes.indexOf(currentStartingHole);
	    mergeHoles();
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
		    // - - - - - - -
		    // Based on the professor's response, if the hole is merged to the one before, then we go to the next one.
		    // If the hole is merged to ones after it, then its okay
		    // If the hole is merged to both before and after, then we take the one following I guess.
		    // Now if the hole that we merge together is at the end or goes all the way to the end, we then make the hole to start from the first hole in the list.
		    //
		    // I think if we set up conditions where we obtain the hole position by calling holes.indexOf(currentStartingHole) and see where its at in the list,
		    // we can then figure out whether or not we set the currentStartingHole to be the first element in the list... simple solution?
		    // - - - - - - -
		    if(currentStartingHole.equals(current)){
			currentStartingHole = previous;
		    }
		    previous.mergeHole(current);
		    iterHoles.remove();
		}
		else { 	// needed an else statement because if the previous and current if statement didn't match it will keep checking same previous with every other hole thats become current
		    // which could lead to later holes that are next to each other not merging because they are never compared
		    previous = current;
		}
	    }
    	}
    }
   
    private void printMemory(){
    	for(String s:memory){
	    System.out.print(s);
    	}
    	System.out.println();
    }
}
