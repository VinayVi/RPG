package items.Equipable;

import item.Item;


public class Weapon extends Equipable{
	protected double str;
	protected double agi;
	protected double dex;
	protected double fort;
	protected double luck;
	protected double damage;
	protected double dodge;
	protected double cdr;
	protected double crit;
	public Weapon(String name, String Type, int Str, int Agi, int Dex, int Fort, int Luck, int Damage, int CDR, int Crit) {
		super(name, Type);
		// TODO Auto-generated constructor stub
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
