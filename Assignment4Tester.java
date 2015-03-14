import java.util.*;

public class Assignment4Tester{
    //SWAPPING SETUP
    private List<Process> processes;
    private int processNumber = 1;
    private Random rGenerator = new Random();
    private int numProcesses;
    private final int[] processSizes = new int[]{5,11,17,31};
    private final int[] processDurations = new int[]{1,2,3,4,5};
    private String processNames = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final int NUM_MAX_PROCESS = 62;

    //PAGING SETUP
    private final int NUM_PAGE_REFERENCES = 100;
    private List<Integer> pages;
    private Random localityGenerator = new Random();
    private Random pageGenerator = new Random();
    private final int[] localPagePossibilities = new int[]{-1,0,1};

    public Assignment4Tester(int numProcesses){
	//SWAPPING INIT
	this.numProcesses = numProcesses;
	if(numProcesses > NUM_MAX_PROCESS){
	    numProcesses = NUM_MAX_PROCESS;
	}
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
	    Process process = new Process(processSizes[processSizeIndex], processDurations[processDurationIndex], processNames.substring(i,i+1));
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

	//Assignment4Tester generator = new Assignment4Tester(60);
	//WorstFit wf = new WorstFit(generator.getProcesses());
	//wf.beginSwapping();
	//BestFit bf = new BestFit(generator.getProcesses());
	//bf.beginSwapping();
	//Assignment4Tester generator1 = new Assignment4Tester(0);
	//RandomPaging rp = new RandomPaging(generator1.getPages());
	//rp.beginPaging();
	//FIFO fifo = new FIFO(generator1.getPages());
	//fifo.beginPaging();
	//LRU lru = new LRU(generator1.getPages());
	//lru.beginPaging();

	for(int i = 0;i<5;i++){
	    Assignment4Tester generator = new Assignment4Tester(5);
	    WorstFit wf = new WorstFit(generator.getProcesses());
	    wf.beginSwapping();
	}

	// Assignment4Tester generator1 = new Assignment4Tester(0);
	// RandomPaging rp = new RandomPaging(generator1.getPages());
	// rp.beginPaging();

	// Assignment4Tester generator2 = new Assignment4Tester(53);
	// FirstFit ff = new FirstFit(generator2.getProcesses());
	// ff.beginSwapping();

	// Assignment4Tester generator3 = new Assignment4Tester(53);
	// NextFit nf = new NextFit(generator3.getProcesses());
	// nf.beginSwapping();

	// Assignment4Tester generator4 = new Assignment4Tester(0);
	// MFU mfu = new MFU(generator4.getPages());
	// mfu.beginPaging();

	// Assignment4Tester generator5 = new Assignment4Tester(0);
	// LFU lfu = new LFU(generator5.getPages());
	// lfu.beginPaging();

    }
}
