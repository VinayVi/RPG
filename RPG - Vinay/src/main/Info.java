package main;

import items.Item;

import java.io.Serializable;
import java.util.ArrayList;

public class Info implements Serializable{
	private static final long serialVersionUID = 1L;
	protected long lastHealed;
	protected double currentHealth;
	protected double maxHealth;
	protected double str;
	protected double agi;
	protected double dex;
	protected double fort;
	protected double luck;
	protected double damage;
	protected double dodge;
	protected double cdr;
	protected double crit;
	/**Directional Moving Booleans*/
	protected boolean mU, mR, mD, mL;
	protected int state;
	protected String name;
	private double strMultiplier;
	private double agiMultiplier;
	private double fortMultiplier;
	private double dexMultiplier;
	private double luckMultiplier;
	private ArrayList<Item> inventoryW;//Weapons
	private ArrayList<Item> inventoryA;//Armor
	private int weaponsEquipped;
	private volatile Vector loc;
	
	public double getStrMultiplier() {
		return strMultiplier;
	}
	
	public void setStrMultiplier(double strMultiplier) {
		this.strMultiplier = strMultiplier;
	}
	
	public double getAgiMultiplier() {
		return agiMultiplier;
	}
	
	public void setAgiMultiplier(double agiMultiplier) {
		this.agiMultiplier = agiMultiplier;
	}
	
	public double getFortMultiplier() {
		return fortMultiplier;
	}
	
	public void setFortMultiplier(double fortMultiplier) {
		this.fortMultiplier = fortMultiplier;
	}
	
	public double getDexMultiplier() {
		return dexMultiplier;
	}
	
	public void setDexMultiplier(double dexMultiplier) {
		this.dexMultiplier = dexMultiplier;
	}
	
	public double getLuckMultiplier() {
		return luckMultiplier;
	}
	
	public void setLuckMultiplier(double luckMultiplier) {
		this.luckMultiplier = luckMultiplier;
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
}
