package item.Equipable;

import item.Item;
import item.ItemType;

public class Equipable extends Item {
	protected double str;
	protected double fort; // health
	protected double damage;
	protected double resil; // energy regeneration
	protected double stam;
	protected int proficiency;
	public boolean equipped;

	public Equipable(String name, ItemType Type, double Str, double Fort,
			double Damage, double Resil, double Stam, int proficiency) {
		super(name, Type);
		str = Str;
		fort = Fort;
		damage = Damage;
		resil = Resil;
		stam = Stam;
		equipped = false;
	}

	public Equipable(String name, ItemType Type, int Str, int Fort, int Damage,
			int Resil, int Stam) {
		super(name, Type);
		str = Str;
		fort = Fort;
		damage = Damage;
		resil = Resil;
		equipped = false;
		stam = Stam;
	}

	public boolean isEquipped() {
		return equipped;
	}

	public double getStr() {
		return str;
	}

	public double getFort() {
		return fort;
	}

	public double getDamage() {
		return damage;
	}

	public double getResil() {
		return resil;
	}

	public double getStam() {
		return stam;
	}
}
