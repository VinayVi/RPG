package main;

import item.Item;

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
	private int weaponsEquipped;
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

	public int getWeaponsEquipped() {
		return weaponsEquipped;
	}

	public void setWeaponsEquipped(int weaponsEquipped) {
		this.weaponsEquipped = weaponsEquipped;
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
}
