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

    	float avgFFProcess = 0;
    	for(int i = 0;i<5;i++){
    		Assignment4Tester generator1 = new Assignment4Tester(62);
    		FirstFit ff = new FirstFit(generator1.getProcesses());
    		ff.beginSwapping();
    		avgFFProcess += (float)ff.getProcessCompleted();
    	}
    	float avgBFProcess = 0;
    	for(int i = 0;i<5;i++){
    		Assignment4Tester generator1 = new Assignment4Tester(62);
    		BestFit bf = new BestFit(generator1.getProcesses());
    		bf.beginSwapping();
    		avgBFProcess += (float)bf.getProcessCompleted();
    	}
    	
    	float avgNFProcess = 0;
    	for(int i = 0;i<5;i++){
    		Assignment4Tester generator2 = new Assignment4Tester(62);
    		NextFit nf = new NextFit(generator2.getProcesses());
    		nf.beginSwapping();
    		avgNFProcess += (float)nf.getProcessCompleted();
    	}
    	
    	float avgWFProcess = 0;
    	for(int i = 0;i<5;i++){
    		Assignment4Tester generator3 = new Assignment4Tester(62);
    		WorstFit wf = new WorstFit(generator3.getProcesses());
    		wf.beginSwapping();
    		avgWFProcess += (float)wf.getProcessCompleted();
    	}
    	
    	float avgFIFOHitRatio = 0;
    	for (int i = 0;i<5;i++){
    		Assignment4Tester generator4 = new Assignment4Tester(0);
    		FIFO fifo = new FIFO(generator4.getPages());
    		fifo.beginPaging();
    		avgFIFOHitRatio += fifo.getHitCount();
    	}
     
    	float avgLFUHitRatio = 0;
    	for (int i = 0;i<5;i++){
    		Assignment4Tester generator4 = new Assignment4Tester(0);
    		LFU lfu = new LFU(generator4.getPages());
    		lfu.beginPaging();
    		avgLFUHitRatio += lfu.getHitCount();
    	}
    	float avgLRUHitRatio = 0;
    	for (int i = 0;i<5;i++){
    		Assignment4Tester generator4 = new Assignment4Tester(0);
    		LRU lru = new LRU(generator4.getPages());
    		lru.beginPaging();
    		avgLRUHitRatio += lru.getHitCount();
    	}
   
    	float avgMFUHitRatio = 0;
    	for (int i = 0;i<5;i++){
    		Assignment4Tester generator5 = new Assignment4Tester(0);
    		MFU mfu = new MFU(generator5.getPages());
    		mfu.beginPaging();
    		avgMFUHitRatio += mfu.getHitCount();
    	}
    	
    	float avgRPHitRatio = 0;
    	for (int i = 0;i<5;i++){
    		Assignment4Tester generator6 = new Assignment4Tester(0);
    		RandomPaging rp = new RandomPaging(generator6.getPages());
    		rp.beginPaging();
    		avgRPHitRatio += rp.getHitCount();
    	}
   
    	
    	System.out.println("\nProcesses:\nAverage First Fit: " + avgFFProcess/5);
    	System.out.println("Average Next Fit: " + avgNFProcess/5);
    	System.out.println("Average Best Fit: " + avgBFProcess/5);
    	System.out.println("Average Worst Fit: " + avgWFProcess/5);
   
    	System.out.println("\nPages:\nAverage LFU hits: " + avgLFUHitRatio/5);
    	System.out.println("Average FIFO hits: " + avgFIFOHitRatio/5);
    	System.out.println("Average LRU hits: " + avgLRUHitRatio/5);
    	System.out.println("Average MFU hits: " + avgMFUHitRatio/5);
    	System.out.println("Average RandomPick hits: " + avgRPHitRatio/5);
    }
}
