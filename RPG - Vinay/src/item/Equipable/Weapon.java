package item.Equipable;

import item.ItemType;

public class Weapon extends Equipable{
	protected ItemType type;
	protected Ability ability;
	
	public Weapon(String name, ItemType Type, double Str, double Fort,
			double Damage, double Resil, double Stam, int proficiency, Ability s, ItemType t) {
		super(name, Type, Str, Fort, Damage, Resil, Stam, proficiency);
		ability = s;
		type = t;
	}
	public Ability getAbility() {
		return ability;
	}
	public void setAbility(Ability ability) {
		this.ability = ability;
	}
	
	
	
	
}
