import java.util.*;


public class LRU 
{

	 private ArrayList<Integer> physicalMemory;
	 private ArrayList<Integer> disk;
	 private final int DISK_SIZE = 10;
	 private final int MEMORY_SIZE = 4;
	 private List<Integer> pageList;
	 private final int[] randomIndex = new int[MEMORY_SIZE];
	 private int numberHits;
	 
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
			numberHits = 0;
	 }
	 
	 public void beginPaging()
	 {
		
		 ArrayList<Page> pages = new ArrayList<Page>();
		 pages.add(new Page(0));
		 pages.add(new Page(1));
		 pages.add(new Page(2));
		 pages.add(new Page(3));
		 int index = 0;
		 for(Integer i: pageList)
		 {
			 
		     printMemory();			 
		     System.out.printf(" | Page needed: %d |",i);
			 pages.get(0).incrementCount();
			 pages.get(1).incrementCount();
			 pages.get(2).incrementCount();
			 pages.get(3).incrementCount();
			 if(!physicalMemory.contains(i))
			 {
				

				 ArrayList<Page> sorted = new ArrayList<Page>(pages);

				 Comparator<Page> comp1 = new
						 Comparator<Page>()
						 {
					 		public int compare(Page p1,Page p2)
					 		{
					 			return p2.getcount()-p1.getcount();
					 		}
						 };
				 Collections.sort(sorted,comp1);
				 index = sorted.get(0).getid();
				 physicalMemory.set(index, i);
				 
				 if(index == 0) 
				 { 
				     if(physicalMemory.get(index) == null){
					 System.out.printf("Page evicted: None\n");					 
				     }
				     else{
					 System.out.printf("Page evicted: %d\n",physicalMemory.get(index));
				     }
					 pages.get(0).setCount(0);
				 }
				 if(index == 1) 
				     { 	
					 if(physicalMemory.get(index) == null){
					     System.out.printf("Page evicted: None\n");					 
					 }
					 else{
					     System.out.printf("Page evicted: %d\n",physicalMemory.get(index));
					 }
					 pages.get(1).setCount(0);
				 }
				 if(index == 2)
				 { 
					 if(physicalMemory.get(index) == null){
					     System.out.printf("Page evicted: None\n");					 
					 }
					 else{
					     System.out.printf("Page evicted: %d\n",physicalMemory.get(index));
					 }

					 pages.get(2).setCount(0);
				 }
				 if(index == 3)
				 { 
					 if(physicalMemory.get(index) == null){
					     System.out.printf("Page evicted: None\n");					 
					 }
					 else{
					     System.out.printf("Page evicted: %d\n",physicalMemory.get(index));
					 }
					 pages.get(3).setCount(0);
				 }
			 }
			 else{
			     System.out.printf("Page evicted: None\n");
				 numberHits++;
			 if(i == physicalMemory.get(0)) pages.get(0).setCount(0);
			 if(i == physicalMemory.get(1)) pages.get(1).setCount(0);
			 if(i == physicalMemory.get(2)) pages.get(2).setCount(0);
			 if(i == physicalMemory.get(3)) pages.get(3).setCount(0);
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
	     return (float)numberHits/(float)100;
		 }
	 
}
