package items.Equipable;
import item.Item;

public class Equipable extends Item{
	int hands;
	public Equipable(String name, String Type) {
		super(name, Type);
		equipped=false;
	}

	
	public boolean equipped;
	
	public boolean isEquipped() {
		return equipped;
	}
	public boolean isEquipable()
	{
		//If the player's hands are already full
		//**********NOT YET IMPLEMENTED*************
		if(hands>2)
		{
			return false;
		}
		return true;
	}
}
