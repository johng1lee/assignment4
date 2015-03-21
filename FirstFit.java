import java.util.*;

public class FirstFit{
    
    ArrayList<String> memory;
    final int RUN_TIME = 60;
    final int MEMORY_SIZE = 100;
    List<Process> allProcesses;
    ArrayList<Process> runningProcesses;
    ArrayList<Hole> holes;
    private int processCompleted;
    
    public FirstFit(List<Process> allProcesses){
    	this.allProcesses = allProcesses;
    	memory = new ArrayList<String>();
    	for(int i=0;i<MEMORY_SIZE;i++){
    		memory.add(".");
    	}
    	//Track processes and holes
    	runningProcesses = new ArrayList<Process>();
    	holes = new ArrayList<Hole>();
    	holes.add(new Hole(0,MEMORY_SIZE-1));
    	processCompleted = 0;
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
    		System.out.println(readyProcess + "| Entered");
    		for (int j = 0; j < holes.size(); j++){ // added for loop to check if there are multiples holes, and traverse them and find correct sized hole 
    		Hole hole = holes.get(j);
    		if(readyProcess.getSize()<=hole.getSize()){
    			runningProcesses.add(readyProcess);
    			readyProcess.setMemoryStartIndex(hole.getStartIndex());
    			readyProcess.setMemoryEndIndex(hole.getStartIndex() + readyProcess.getSize() - 1);
    			for(int i=readyProcess.getMemoryStartIndex();i<=readyProcess.getMemoryEndIndex();i++){
    				memory.set(i,readyProcess.getID());
    			}
    			hole.setStartIndex(hole.getStartIndex() + readyProcess.getSize());
    			if (hole.getSize() <= 0) // testing to see if fixes error *it does fix the error with a hole of size 0 I was getting
    				holes.remove(j);
			if(holes.size() <= 0){
			    holes.add(new Hole(0,0));
			}
    			printMemory();
    			isProcessServed = true;
    			break;
    		}
    		else{
    			isProcessServed = false;
    		}
    		}
    		
    		if (!isProcessServed)
    			System.out.println("Not enough memory for Process " + readyProcess.getID() + " to be placed---------------------");
    		Process evaluateProcess = null;
    		iterRunningProcesses = runningProcesses.iterator();
    		while(iterRunningProcesses.hasNext()){
    			evaluateProcess = iterRunningProcesses.next();
    			if(evaluateProcess.isDone()){
    				for(int i=evaluateProcess.getMemoryStartIndex();i<=evaluateProcess.getMemoryEndIndex();i++){
    					memory.set(i,".");
    				}
				System.out.println(evaluateProcess + "| Exited");

    				iterRunningProcesses.remove();//remove completed processes from running process list
    				holes.add(new Hole(evaluateProcess.getMemoryStartIndex(),evaluateProcess.getMemoryEndIndex()));
    				printMemory();
    				processCompleted++;
    			}
    			evaluateProcess.decrementTime();//decrement time
    		}
    		Collections.sort(holes,new Comparator<Hole>(){
    		public int compare(Hole h1, Hole h2){
    		return ((Integer)h1.getStartIndex()).compareTo(((Integer)h2.getStartIndex()));
    		}
    		});
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
    
    public int getProcessCompleted(){
    	return processCompleted;
    }
}
