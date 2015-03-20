import java.util.*;

public class RandomPaging{
    private ArrayList<Integer> physicalMemory;
    private ArrayList<Integer> disk;
    private final int DISK_SIZE = 10;
    private final int MEMORY_SIZE = 4;
    private List<Integer> pageList;
    private final int[] randomIndex = new int[MEMORY_SIZE];
    private int numberHits;
    
    public RandomPaging(List<Integer> pageList){
	this.pageList = pageList;
	physicalMemory = new ArrayList<Integer>();
	for(int i=0;i<MEMORY_SIZE;i++){
	    physicalMemory.add(null);
	    randomIndex[i] = i;
	}
	disk = new ArrayList<Integer>();
	for(int i=0;i<DISK_SIZE;i++){
	    disk.add(i);
	}
	numberHits = 0;
    }

    public void beginPaging(){
	Random randomPagePicker = new Random();
	ListIterator<Integer> iterList = pageList.listIterator();
	Integer nextPage;
	int evictIndex;
	int countUntilFull = 0;
	while(iterList.hasNext()){
	    nextPage = iterList.next();
	    printMemory();
	    System.out.printf(" | Page needed: %d |",nextPage);
	    if(!physicalMemory.contains(nextPage)){

		if(countUntilFull < MEMORY_SIZE){
		    physicalMemory.set(countUntilFull,nextPage);
		    System.out.printf("Page evicted: None\n");
		}
		else{
		    evictIndex = randomPagePicker.nextInt(MEMORY_SIZE);
		    System.out.printf("Page evicted: %d\n",physicalMemory.get(evictIndex));		    
		    physicalMemory.set(evictIndex,nextPage);
		    
		}
		countUntilFull++;

	    }
	    else{
		numberHits++;
		System.out.printf("Page evicted: None\n");
	    }
	}
    }
    public void printMemory(){
	System.out.printf("Memory: ");
	for(int i=0;i<MEMORY_SIZE;i++){
		if(physicalMemory.get(i)==null){
		System.out.print("-");
		}
		else{
		System.out.print(physicalMemory.get(i));
		}
	}
    }
    public float getHitCount(){
    	return (float)numberHits/(float)100.0;
    }
}
