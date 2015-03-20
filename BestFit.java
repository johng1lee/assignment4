import java.util.*;


public class BestFit 
{
	LinkedList<String> memory;
    final int RUN_TIME = 60;
    final int MEMORY_SIZE = 100;
    List<Process> allProcesses;
    ArrayList<Process> runningProcesses;
    ArrayList<Hole> holes;
    private int processCompleted;
    
    
    public BestFit(List<Process> allProcesses)
    {
    	this.allProcesses = allProcesses;
    	memory = new LinkedList<String>();
    	
    	for(int i=0;i<MEMORY_SIZE;i++)
    	{
    		memory.add(".");
    	}

    	runningProcesses = new ArrayList<Process>();
    	//processCompleted = 0;

    }

    public void beginSwapping()
    {
    	int time = 0;
    	int counter = 0;
    	
    	while(time <= RUN_TIME)
    	{
    		for(int k = 0;k<runningProcesses.size();k++)
    		{
    			runningProcesses.get(k).decrementTime();
    			if(runningProcesses.get(k).isDone())
        		{
    				int start = runningProcesses.get(k).getMemoryStartIndex();
    				int end = runningProcesses.get(k).getMemoryEndIndex();
        			for(;start<=end;start++)
        			{
        				memory.set(start, ".");
        			}
        			System.out.println(runningProcesses.get(k)+"| Exit ");
        			runningProcesses.remove(runningProcesses.get(k));
                    
        		}
    		}
    		//System.out.println(counter);
    		Process p = allProcesses.get(counter);
    		Hole besthole = findBestHole(p,memory);
    		if(besthole == null)
    		{
    			time++;
    			printMemory();
    		}
    		else{
    		//System.out.println(besthole);
    		System.out.println(p+"| Enter ");
    		p.setMemoryStartIndex(besthole.getStartIndex());
    		p.setMemoryEndIndex(besthole.getStartIndex()+p.getSize()-1);
    		int i = p.getMemoryStartIndex();
			int j = p.getMemoryEndIndex();
    		for(;i<=j;i++)
    		{
    			//System.out.println(i);
    			memory.set(i, p.getID());
    		}
    		//System.out.println(p);
    		runningProcesses.add(p);
    		processCompleted++;
    		time++;
    		counter++;
    		printMemory();
    		}
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
    	//System.out.println(currentProcess);
    	//System.out.println(list);
    	for( ;i<list.size()-1;i++)
    	{
    		if(list.get(i).equals(".") && list.get(i+1).equals("."))
    		{
    			counter++;
    			end = i+1;
    			//System.out.println(counter);
    		}
    		else
    		{
    			if (counter > 0)
    			{
    				//System.out.println(start);
    				//System.out.println(end);
    				holes.add(new Hole(start,end));
    			}
    			counter = 0;
    			start = i+1;
    			
    		}
    		//System.out.println(i);
    		//System.out.println(counter);
    		if(i == 98 && counter >0) 
    		{
    			//System.out.println(start);
    			//System.out.println(end);
    			holes.add(new Hole(start,end));
    		}
    		
    	}
    	//System.out.println(holes);
    	Collections.sort(holes,new Comparator<Hole>(){
		    public int compare(Hole h1, Hole h2){
			return (h1.getEndIndex()-h1.getStartIndex())-(h2.getEndIndex()-h2.getStartIndex());
		    }
		});
    	
    	for(Hole h:holes)
    	{
    		//System.out.println(h);
    		if(currentProcess.getSize() <= (h.getEndIndex()-h.getStartIndex()))
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
    
    public int getProcessCompleted(){
    	return processCompleted;
    }
}
	
	

