package main;
import items.*;
import java.util.ArrayList;

public class Player extends Character{
	
	private double strMultiplier;
	private double agiMultiplier;
	private double fortMultiplier;
	private double dexMultiplier;
	private double luckMultiplier;
	
	
	private ArrayList<Item> inventoryW;//Weapons
	private ArrayList<Item> inventoryA;//Armor
	
	private int weaponsEquipped;
	
	public Player(String name) {
		super(name);
		x = 0;
		y = 0;
	}
	
	public void pickUp(Item i) {
		if(i.getType().equals("WEAPON")) {
			inventoryW.add(i);
		}
	}
	
	public void equip(Item i) {
		i.equipped = true;
		
		str += i.getStr();
		agi += i.getAgi();
		dex += i.getDex();
		fort += i.getFort();
		luck += i.getLuck();
		damage += i.getDamage();
		dodge += i.getDodge();
		cdr += i.getCdr();
		crit += i.getCdr();
	}
	
	public void unEquip(Item i) {
		i.equipped = false;
		
		str -= i.getStr();
		agi -= i.getAgi();
		dex -= i.getDex();
		fort -= i.getFort();
		luck -= i.getLuck();
		damage -= i.getDamage();
		dodge -= i.getDodge();
		cdr -= i.getCdr();
		crit -= i.getCdr();
	}
	
	//Stat Accessors
	public double getDmg()
	{
		return str*strMultiplier + damage;
	}
	public double getHealth()
	{
		maxHealth=(fort*fortMultiplier);
		return maxHealth;
	}
	public double getDodge()
	{
		dodge=dex*dexMultiplier;
		return dodge;
	}
	public double getCrit()
	{
		crit=luck*luckMultiplier;
		return crit;
	}
	public double getCDR()
	{
		cdr=agi*agiMultiplier;
		return cdr;
	}
	
	
}
