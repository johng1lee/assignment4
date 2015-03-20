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
	    System.out.printf("Page needed: %d |",nextPage);
	    if(!physicalMemory.contains(nextPage)){

		if(countUntilFull < MEMORY_SIZE){
		    physicalMemory.set(countUntilFull,nextPage);
		}
		else{
		    evictIndex = randomPagePicker.nextInt(MEMORY_SIZE);
		    physicalMemory.set(evictIndex,nextPage);
		    
		}
		countUntilFull++;
		printMemory();
	    }
	    else{
		numberHits++;
		printMemory();
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
	System.out.println();
    }
    public float getHitCount(){
    	return (float)numberHits/100.0;
    }
}
