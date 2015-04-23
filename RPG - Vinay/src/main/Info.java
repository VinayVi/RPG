package main;

import item.Item;
import item.Equipable.Ability;
import item.Equipable.Equipable;

import java.io.Serializable;
import java.util.ArrayList;

public class Info implements Serializable {
	private static final long serialVersionUID = 1L;
	protected long lastHealed;
	protected double currentHealth;
	protected double maxHealth;
	protected double currentEnergy;
	protected double maxEnergy;
	protected double str = 0;
	protected double fort = 0;
	protected double damage = 0;
	protected double resil = 0;
	protected double stam = 0;
	protected ArrayList<Ability> moves = new ArrayList();
	/** Directional Moving Booleans */
	protected boolean mU;
	protected boolean mR;
	protected boolean mD;
	protected boolean mL;
	protected int state;
	protected String name;
	private double strMultiplier;
	private double fortMultiplier;
	private ArrayList<Item> inventoryW; // Weapons
	private ArrayList<Item> inventoryA; // Armor
	private double resMultiplier;
	private double stamMultiplier;
	protected Equipable RweaponEquipped;
	protected Equipable LweaponEquipped;
	private volatile Vector loc;
	private volatile int currMap = 1;

	public double getStrMultiplier() {
		return strMultiplier;
	}

	public void setStrMultiplier(double strMultiplier) {
		this.strMultiplier = strMultiplier;
	}

	public double getFortMultiplier() {
		return fortMultiplier;
	}

	public void setFortMultiplier(double fortMultiplier) {
		this.fortMultiplier = fortMultiplier;
	}

	public double getResMultiplier() {
		return resMultiplier;
	}

	public void setResMultiplier(double resMultiplier) {
		this.resMultiplier = resMultiplier;
	}

	public double getStamMultiplier() {
		return stamMultiplier;
	}

	public void setStamMultiplier(double stamMultiplier) {
		this.stamMultiplier = stamMultiplier;
	}

	public ArrayList<Item> getInventoryW() {
		return inventoryW;
	}

	public void setInventoryW(ArrayList<Item> inventoryW) {
		this.inventoryW = inventoryW;
	}

	public ArrayList<Item> getInventoryA() {
		return inventoryA;
	}

	public void setInventoryA(ArrayList<Item> inventoryA) {
		this.inventoryA = inventoryA;
	}

	public Equipable getLweaponEquipped() {
		return LweaponEquipped;
	}

	public void setLweaponEquipped(Equipable weaponsEquipped) {
		this.LweaponEquipped = weaponsEquipped;
	}

	public Vector getLoc() {
		return loc;
	}

	public void setLoc(Vector loc) {
		this.loc = loc;
	}

	public int getCurrMap() {
		return currMap;
	}

	public void setCurrMap(int currMap) {
		this.currMap = currMap;
	}

	public Equipable getRweaponEquipped() {
		return RweaponEquipped;
	}

	public void setRweaponEquipped(Equipable rweaponEquipped) {
		RweaponEquipped = rweaponEquipped;
	}

}
