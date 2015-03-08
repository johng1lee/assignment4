import java.util.*;

public class Assignment4Tester{
    private List<Process> processes;
    private int processNumber = 1;
    private Random rGenerator = new Random(1);
    private int numProcesses;
    private final int[] processSizes = new int[]{5,11,17,31};
    private final int[] processDurations = new int[]{1,2,3,4,5};

    public Assignment4Tester(int numProcesses){
	this.numProcesses = numProcesses;
	processes = new ArrayList<Process>();
	
	generateNewProcesses();
    }

    public void generateNewProcesses(){
	int processSizeIndex;
	int processDurationIndex;
	for(int i=0; i<numProcesses; i++){
	    processSizeIndex = rGenerator.nextInt(4);
	    processDurationIndex = rGenerator.nextInt(5);
	    Process process = new Process(processSizes[processSizeIndex], processDurations[processDurationIndex], i);
	    processes.add(process);
	}
	printProcesses();
    }

    private void printProcesses(){
	for(Process p:processes){
	    System.out.println(p);
	}
	System.out.println("---------------------");
    }

    public List<Process> getProcesses(){
	return processes;
    }
    public static void main(String[] args){
	Assignment4Tester generator = new Assignment4Tester(10);
	WorstFit wf = new WorstFit(generator.getProcesses());
	wf.beginSwapping();
    }
}
