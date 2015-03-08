import java.util.*;

public class RandomPaging{
    private ArrayList<Integer> physicalMemory;
    private ArrayList<Integer> disk;
    private final int DISK_SIZE = 10;
    private final int MEMORY_SIZE = 4;
    private List<Integer> pageList;
    
    public RandomPaging(List<Integer> pageList){
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
    
}
