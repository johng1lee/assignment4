
public class Page {
    private int id;
    private int count;
    
    public Page(int id){
	this.id = id;
	this.count = 0;
    }

    public int getid(){
	return id;
    }
    public int getcount(){
	return count;
    }
    public void incrementCount(){
	count++;
    }
}
