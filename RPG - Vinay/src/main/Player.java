package main;
import items.*;
import java.util.ArrayList;

public class Player extends Character{
	
	private int strMultiplier;
	private int agiMultiplier;
	private int fortMultiplier;
	private int dexMultiplier;
	private int luckMultiplier;
	
	
	private ArrayList<Item> inventory;
	
	public Player() {
		x = 0;
		y = 0;
	}
	
	public void pickUp(Item i) {
		inventory.add(i);
		i.setOwner(this);
	}
	
	//Stat Accessors
	public int getDmg()
	{
		damage=(str*strMultiplier)+weaponDmg;
		return damage;
	}
	public int getHealth()
	{
		health=(fort*fortMultiplier);
		return health;
	}
	public int getDodge()
	{
		dodge=dex*dexMultiplier;
		return dex;
	}
	public int getCrit()
	{
		crit=luck*luckMultiplier;
		return crit;
	}
	public int getCDR()
	{
		cdr=agi*agiMultiplier;
		return cdr;
	}
	
	
}
