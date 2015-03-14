import java.util.*;

public class FIFO 
{

	 private ArrayList<Integer> physicalMemory;
	 private ArrayList<Integer> disk;
	 private final int DISK_SIZE = 10;
	 private final int MEMORY_SIZE = 4;
	 private List<Integer> pageList;
	 private final int[] randomIndex = new int[MEMORY_SIZE];
	    
	 public FIFO(List<Integer> pageList)
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
	 int index = 0;
	 public void beginPaging()
	 {
		 for(Integer i: pageList)
		 {
			 //System.out.print(i);
			 if(!physicalMemory.contains(i))
			 {
				 physicalMemory.add(index,i); 
				 index++;
				 if(index >3) index = 0;
				 printMemory();
			 }
			 System.out.print("    ");
		 }
	 }
	 
	 public void printMemory()
	 {
		System.out.printf("Memory: ");
		for(int i=0;i<MEMORY_SIZE;i++)
		{
			System.out.print(physicalMemory.get(i));
		}
			System.out.println();
	 }
}
