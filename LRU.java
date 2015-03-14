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
		
		 ArrayList<Page> pages = new ArrayList<Page>();
		 pages.add(new Page(0));
		 pages.add(new Page(1));
		 pages.add(new Page(2));
		 pages.add(new Page(3));
		 int index = 0;
		 for(Integer i: pageList)
		 {
			 
			 
			 System.out.println(i);
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
					 pages.get(0).setCount(0);
				 }
				 if(index == 1) 
				 { 
					 pages.get(1).setCount(0);
				 }
				 if(index == 2)
				 { 
					 pages.get(2).setCount(0);
				 }
				 if(index == 3)
				 { 
					 pages.get(3).setCount(0);
				 }
			 }
			 else{
			 if(i == physicalMemory.get(0)) pages.get(0).setCount(0);
			 if(i == physicalMemory.get(1)) pages.get(1).setCount(0);
			 if(i == physicalMemory.get(2)) pages.get(2).setCount(0);
			 if(i == physicalMemory.get(3)) pages.get(3).setCount(0);
			 }
			
			 
			 printMemory();
			 
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
