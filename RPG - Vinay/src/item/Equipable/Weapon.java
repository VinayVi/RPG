package item.Equipable;

import item.ItemType;

public class Weapon extends Equipable{
	protected ItemType type;
	protected Special special;
	
	public Weapon(String name, ItemType Type, double Str, double Fort,
			double Damage, double Resil, double Stam, int proficiency, Special s, ItemType t) {
		super(name, Type, Str, Fort, Damage, Resil, Stam, proficiency);
		special = s;
		type = t;
	}
	public Special getSpecial() {
		return special;
	}
	public void setSpecial(Special special) {
		this.special = special;
	}
	
	
	
	
}
