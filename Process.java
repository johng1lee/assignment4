public class Process{
    private int size;
    private int duration;
    private int id;
    public Process(int size, int duration, int id){
	this.size = size;
	this.duration = duration;
	this.id = id;
    }

    public int getSize(){
	return size;
    }
    public int getDuration(){
	return duration;
    }
    public int getID(){
	return id;
    }
    public String toString(){
	String message = "Process: " + Integer.toString(id) + " | Size: " + Integer.toString(size) + " | Duration: " + Integer.toString(duration);
	return message;
    }
}
