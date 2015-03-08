import java.util.*;

public class WorstFit{
    int[] memory;
    List<Process> processes;
    int largestHoleSize;
    HashMap<Integer,ArrayList<Integer>> holeLocations;

    public WorstFit(List<Process> processes){
	this.processes = processes;
	memory = new int[100];
	largestHoleSize = memory.length;
	holeLocations = new HashMap<Integer,ArrayList<Integer>>();
    }
    public void printSize(){
	System.out.println(largestHoleSize);
    }
    public static void main(String[] args){
	WorstFit test = new WorstFit(new ArrayList<Process>());
	test.printSize();
    }
}
