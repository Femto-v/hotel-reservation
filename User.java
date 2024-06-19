public abstract class User implements Printable {
    protected int id;
    protected String name;


    public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
    public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	// Abstract method to be implemented by subclasses
	public abstract void print();

}
