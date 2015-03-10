package item;

public abstract class Item {

	protected ItemType type;
	protected String Name;

	public Item(String name, ItemType type2) {
		Name = name;
		type = type2;
	}

	public ItemType getType() {
		return type;
	}

	public String getName() {
		return Name;
	}

	@Override
	public String toString() {
		return Name;
	}

}
