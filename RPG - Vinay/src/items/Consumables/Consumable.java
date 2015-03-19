package items.Consumables;

import item.Item;
import item.ItemType;

public abstract class Consumable extends Item{

	public Consumable(String name, ItemType Type) {
		super(name, Type);
		// TODO Auto-generated constructor stub
	}

	public abstract boolean consume();
	
}
