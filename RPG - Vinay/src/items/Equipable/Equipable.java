package items.Equipable;

import item.Item;
import item.ItemType;

public class Equipable extends Item {
	protected double str;
	protected double agi;
	protected double dex;
	protected double fort;
	protected double luck;
	protected double damage;
	protected double dodge;
	protected double cdr;
	protected double crit;
	public boolean equipped;

	public Equipable(String name, ItemType Type, double Str, double Agi, double Dex, double Fort, double Luck, double Damage, double Dodge, double CDR, double Crit) {
		super(name, Type);
		str = Str;
		agi = Agi;
		dex = Dex;
		fort = Fort;
		luck = Luck;
		damage = Damage;
		dodge = Dodge;
		cdr = CDR;
		crit = Crit;
		equipped = false;
	}

	public Equipable(String name, ItemType Type, int Str, int Agi, int Dex, int Fort, int Luck, int Damage, int Dodge, int CDR, int Crit) {
		super(name, Type);
		str = Str;
		agi = Agi;
		dex = Dex;
		fort = Fort;
		luck = Luck;
		damage = Damage;
		dodge = Dodge;
		cdr = CDR;
		crit = Crit;
		equipped = false;
	}

	public boolean isEquipped() {
		return equipped;
	}

	public double getStr() {
		return str;
	}

	public double getAgi() {
		return agi;
	}

	public double getDex() {
		return dex;
	}

	public double getFort() {
		return fort;
	}

	public double getLuck() {
		return luck;
	}

	public double getDamage() {
		return damage;
	}

	public double getDodge() {
		return dodge;
	}

	public double getCdr() {
		return cdr;
	}

	public double getCrit() {
		return crit;
	}

}
