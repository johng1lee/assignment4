import java.util.*;

public class LFU{
    private ArrayList<Integer> physicalMemory;
    private ArrayList<Integer> disk;
    private final int DISK_SIZE = 10;
    private final int MEMORY_SIZE = 4;
    private List<Integer> pageList;
    private HashMap<Integer, Integer> processToCount = new HashMap<Integer,Integer>();

    
    public LFU(List<Integer> pageList){
	this.pageList = pageList;
	physicalMemory = new ArrayList<Integer>();
	for(int i=0;i<MEMORY_SIZE;i++){
	    physicalMemory.add(null);
	}
	disk = new ArrayList<Integer>();
	for(int i=0;i<DISK_SIZE;i++){
	    disk.add(i);
	}
    }

    private int leastFrequentlyUsed(){
	Iterator<Integer> iter = processToCount.keySet().iterator();
	int minCount = processToCount.get(physicalMemory.get(0));
	Integer chosenKey = null;
	Integer tempKey = null;
	while(iter.hasNext()){
	    tempKey = iter.next();
	    if(physicalMemory.contains(tempKey) && processToCount.get(tempKey) <= minCount){
		chosenKey = tempKey;
		minCount = processToCount.get(tempKey);
		break;
	    }
	}
	return physicalMemory.indexOf(chosenKey);
    }
    public void beginPaging(){
	ListIterator<Integer> iterList = pageList.listIterator();
	Integer nextPage;
	Integer evictIndex;
	int countUntilFull = 0;
	while(iterList.hasNext()){
	    nextPage = iterList.next();
	    System.out.printf("Page needed: %d | ",nextPage);
	    if(!physicalMemory.contains(nextPage)){
		if(countUntilFull < MEMORY_SIZE){
		    physicalMemory.set(countUntilFull,nextPage);
		    if(processToCount.get(nextPage) == null){
			processToCount.put(nextPage,1);
		    }
		    else{
			processToCount.put(nextPage,processToCount.get(nextPage)+1);
		    }
		}
		else{
		    evictIndex = leastFrequentlyUsed();
		    if(evictIndex == null){
			System.out.println("Should not happen");
		    }
		    else{
			processToCount.put(physicalMemory.get(evictIndex.intValue()),0);
			physicalMemory.set(evictIndex.intValue(),nextPage);
			if(processToCount.get(nextPage) == null){
			    processToCount.put(nextPage,1);
			}
			else{
			    processToCount.put(nextPage,processToCount.get(nextPage)+1);
			}
		    }
		}
		countUntilFull++;
		printMemory();
	    }
	    else{
		Integer counter = processToCount.get(nextPage);
		processToCount.put(nextPage,counter + 1);
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
}
