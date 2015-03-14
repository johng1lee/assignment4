import java.util.*;


public class BestFit 
{
	LinkedList<String> memory;
    final int RUN_TIME = 60;
    final int MEMORY_SIZE = 100;
    List<Process> allProcesses;
    ArrayList<Process> runningProcesses;
    ArrayList<Hole> holes;
    
    
    public BestFit(List<Process> allProcesses)
    {
    	this.allProcesses = allProcesses;
    	memory = new LinkedList<String>();
    	
    	for(int i=0;i<MEMORY_SIZE;i++)
    	{
    		memory.add(".");
    	}

    	runningProcesses = new ArrayList<Process>();
    	//holes = new ArrayList<Hole>();
    	//holes.add(new Hole(0,MEMORY_SIZE-1));

    }

    public void beginSwapping()
    {
    	int time = 0;
    	while(time <= RUN_TIME)
    	{
    		Process p = allProcesses.get(time);
    		int i = p.getMemoryStartIndex();
			int j = p.getMemoryEndIndex();
    		Hole besthole = findBestHole(p,memory);
    		p.setMemoryStartIndex(besthole.getStartIndex());
    		p.setMemoryEndIndex(besthole.getStartIndex()+p.getSize()-1);
    		for(;i<=j;i++)
    		{
    			memory.set(i, Integer.toString(p.getID()));
    		}
    		
    		runningProcesses.add(p);
    		p.decrementTime();
    		if(p.isDone())
    		{
    			for(;i<=j;i++)
    			{
    				memory.set(i, ".");
    			}
    		}
    		time++;
    		
    	}
    }
    public Hole findBestHole(Process currentProcess,LinkedList<String> list) 
    {
    	int counter = 0;
    	int start;
    	int end = 0;
    	ArrayList <Hole> holes = new ArrayList<Hole>();
    	int i = 0;
    	start = i;
    	for( ;i<list.size();i++)
    	{
    		if(list.get(i).equals(".") && list.get(i+1).equals("."))
    		{
    			counter++;
    			end = i+1;
    		}
    		else
    		{
    			if (counter > 0)
    			{
    				holes.add(new Hole(start,end));
    			}
    			counter = 0;
    			start = i+1;
    			
    		}
    		
    	}
    	Collections.sort(holes,new Comparator<Hole>(){
		    public int compare(Hole h1, Hole h2){
			return (h1.getEndIndex()-h1.getStartIndex())-(h2.getEndIndex()-h2.getStartIndex());
		    }
		});
    	
    	for(Hole h:holes)
    	{
    		if(currentProcess.getSize() >= (h.getEndIndex()-h.getStartIndex()))
    		{
    			return h;
    		}
    	}
    	return null;
    	
    }
   
    private void printHoles(){
	System.out.println("------------HOLES----------");
	for(Hole h: holes){
	    System.out.println(h);
	}
    }
    private void printMemory(){
	for(String s:memory){
	    System.out.print(s);
	}
	System.out.println();
    }
}
	
	

