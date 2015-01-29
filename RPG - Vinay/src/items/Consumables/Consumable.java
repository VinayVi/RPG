package items.Consumables;

import item.Item;

public abstract class Consumable extends Item{

	public Consumable(String name, String Type) {
		super(name, Type);
		// TODO Auto-generated constructor stub
	}

	public abstract boolean consume();
	
}
