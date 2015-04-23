package item.Equipable;

public class Ability {
	protected String name;
	protected double damage;
	protected int energy;

	public Ability(String n, double d, int e) {
		name = n;
		damage = d;
		energy = e;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}
}
