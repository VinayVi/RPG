package item;

public abstract class Item {

	protected String type; 
	protected String Name;
	
	public Item(String name, String Type)
	{
		Name=name;
		type=Type;
	}
	
	public String getType() {
		return type;
	}
	public String getName() {
		return Name;
	}

}
