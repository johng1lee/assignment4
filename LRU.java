import java.util.*;


public class LRU 
{

	 private ArrayList<Integer> physicalMemory;
	 private ArrayList<Integer> disk;
	 private final int DISK_SIZE = 10;
	 private final int MEMORY_SIZE = 4;
	 private List<Integer> pageList;
	 private final int[] randomIndex = new int[MEMORY_SIZE];
	 
	 public LRU(List<Integer> pageList)
	 {
	       this.pageList = pageList;
			physicalMemory = new ArrayList<Integer>();
			for(int i=0;i<MEMORY_SIZE;i++)
			{
			    physicalMemory.add(null);
			    randomIndex[i] = i;
			}
			disk = new ArrayList<Integer>();
			for(int i=0;i<DISK_SIZE;i++)
			{
			    disk.add(i);
			}
	 }
	 
	 public void beginPaging()
	 {
		 int counter0 = 0;
		 int counter1 = 0;
		 int counter2 = 0;
		 int counter3 = 0;
		 
		 for(Integer i: pageList)
		 {
			 if(!physicalMemory.contains(i))
			 {
				 physicalMemory.add(index, element);
			 }
		 }
	 }
	 
	 
	 
	 
}
