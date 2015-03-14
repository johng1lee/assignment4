
public class Page{
    private int id;
    private int count;
    private int usedTime;
    
    public Page(int id){
	this.id = id;
	this.count = 0;
	usedTime = 0;
    }

    public int getid(){
	return id;
    }
    
    public int getUsedTime()
    {
    	return usedTime;
    }
    public void setUsedTime(int times)
    {
    	usedTime = times;
    }
    public void setCount(int c)
    {
    	count = c;
    }
    public void incrementUsedTime()
    {
    	usedTime++;
    }
    public int getcount(){
	return count;
    }
    public void incrementCount(){
	count++;
    }
 
}
