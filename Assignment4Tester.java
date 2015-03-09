import java.util.*;

public class Assignment4Tester{
    //SWAPPING SETUP
    private List<Process> processes;
    private int processNumber = 1;
    private Random rGenerator = new Random(1);
    private int numProcesses;
    private final int[] processSizes = new int[]{5,11,17,31};
    private final int[] processDurations = new int[]{1,2,3,4,5};

    //PAGING SETUP
    private final int NUM_PAGE_REFERENCES = 100;
    private List<Integer> pages;
    private Random localityGenerator = new Random(2);
    private Random pageGenerator = new Random(3);
    private final int[] localPagePossibilities = new int[]{-1,0,1};

    public Assignment4Tester(int numProcesses){
	//SWAPPING INIT
	this.numProcesses = numProcesses;
	processes = new ArrayList<Process>();
	generateNewProcesses();

	//PAGING INIT
	pages = new ArrayList<Integer>();
	generateNewPages();
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

    public void generateNewPages(){
	int initialPageValue = localityGenerator.nextInt(10);
	int nextPage = 0;
	int localCheckValue = 0;
	pages.add(initialPageValue);
	for(int i=0; i<NUM_PAGE_REFERENCES-1; i++){
	    localCheckValue = localityGenerator.nextInt(10);
	    if(localCheckValue > 6){
		//not local page
		nextPage = (pages.get(i) + (pageGenerator.nextInt(7)+2)) % 9;
		if(nextPage < 0){
		    nextPage += 9;
		}
		pages.add(nextPage);
	    }
	    else{
		//local page
		nextPage = (pages.get(i) + localPagePossibilities[pageGenerator.nextInt(3)]) % 9;
		if(nextPage < 0){
		    nextPage += 9;
		}
		pages.add(nextPage);
	    }
	}
    }

    private void printProcesses(){
	for(Process p:processes){
	    System.out.println(p);
	}
	System.out.println("---------------------");
    }

    public void printPages(){
	for(Integer i:pages){
	    System.out.print(Integer.toString(i));
	}
	System.out.println();
	System.out.println("---------------------");
    }
    public List<Process> getProcesses(){
	return processes;
    }
    public List<Integer> getPages(){
	return pages;
    }
    public static void main(String[] args){
	Assignment4Tester generator = new Assignment4Tester(40);
	WorstFit wf = new WorstFit(generator.getProcesses());
	wf.beginSwapping();

	Assignment4Tester generator1 = new Assignment4Tester(0);
	RandomPaging rp = new RandomPaging(generator1.getPages());
	rp.beginPaging();
    }
}
