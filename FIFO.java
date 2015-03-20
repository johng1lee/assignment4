import java.util.*;

public class FIFO 
{

	 private ArrayList<Integer> physicalMemory;
	 private ArrayList<Integer> disk;
	 private final int DISK_SIZE = 10;
	 private final int MEMORY_SIZE = 4;
	 private List<Integer> pageList;
	 private final int[] randomIndex = new int[MEMORY_SIZE];
	 private int numberHits;
	    
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
		numberHits = 0;
     }
	 int index = 0;
	 public void beginPaging()
	 {
		 for(Integer i: pageList)
		 {
		     printMemory();
		     System.out.printf(" | Page needed: %d |",i);
			 if(!physicalMemory.contains(i))
			 {
			     if(physicalMemory.get(index) == null){
				 System.out.printf("Page evicted: None\n");					 
			     }
			     else{
				 System.out.printf("Page evicted: %d\n",physicalMemory.get(index));
			     }
				 physicalMemory.set(index,i); 
				 index++;

				 if(index >3) index = 0;
				 //printMemory();
			 }
			 else{
			     System.out.printf("Page evicted: None\n");
			     numberHits++;
			 }

		 }
	 }
	 
	 public void printMemory()
	 {
		System.out.printf("Memory: ");
		for(int i=0;i<MEMORY_SIZE;i++)
		{
		    if(physicalMemory.get(i) == null){
			System.out.print("-");
		    }
		    else{
			System.out.print(physicalMemory.get(i));
		    }
		}
	 }
	 public float getHitCount(){
	     return (float)numberHits/(float)100;
	 }
}
