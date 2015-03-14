public class Process{
    private int size;
    private int duration;
    private int timeLeft;
    private String id;
    private int memoryStartIndex;
    private int memoryEndIndex;
    public Process(int size, int duration, String id){
	this.size = size;
	this.duration = duration;
	timeLeft = duration;
	this.id = id;
	memoryEndIndex = 0;
	memoryStartIndex = 0;
    }

    public int getSize(){
	return size;
    }
    public int getDuration(){
	return duration;
    }
    public String getID(){
	return id;
    }
    public void setMemoryStartIndex(int startIndex){
	memoryStartIndex = startIndex;
    }
    public void setMemoryEndIndex(int endIndex){
	memoryEndIndex = endIndex;
    }
    public int getMemoryStartIndex(){
	return memoryStartIndex;
    }
    public int getMemoryEndIndex(){
	return memoryEndIndex;
    }
    public void decrementTime(){
	timeLeft--;
    }
    public boolean isDone(){
	return timeLeft <= 0;
    }
    public String toString(){
	String message = "Process: " + id + " | Size: " + Integer.toString(size) + " | Duration: " + Integer.toString(duration);
	return message;
    }
}
