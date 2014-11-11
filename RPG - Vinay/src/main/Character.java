package main;

import items.Item;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import tiles.Tile;

public class Character {
	protected int x;
	protected int y;
	protected int tileSize;
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
	protected double tps; //seconds per tiles
	protected boolean moving;
	protected int state;
	protected String name;
	
	public Image currSprite;
	public Image BL, BR, BS, FL, FR, FS, LL, LR, LS, RL, RR, RS;
	
	private double strMultiplier;
	private double agiMultiplier;
	private double fortMultiplier;
	private double dexMultiplier;
	private double luckMultiplier;
	
	
	private ArrayList<Item> inventoryW;//Weapons
	private ArrayList<Item> inventoryA;//Armor
	
	private int weaponsEquipped;
	
	
	public Character(String name) {
		tileSize = 48;
		tps = 0.25;
		moving = false;
		state = 1;
		this.name = name;
		try {
			BL = ImageIO.read(new File("src//sprites//" + name + "//" + name + " BL.png"));
			BR = ImageIO.read(new File("src//sprites//" + name + "//" + name + " BR.png"));
			BS = ImageIO.read(new File("src//sprites//" + name + "//" + name + " BS.png"));
			FL = ImageIO.read(new File("src//sprites//" + name + "//" + name + " FL.png"));
			FR = ImageIO.read(new File("src//sprites//" + name + "//" + name + " FR.png"));
			FS = ImageIO.read(new File("src//sprites//" + name + "//" + name + " FS.png"));
			LL = ImageIO.read(new File("src//sprites//" + name + "//" + name + " LL.png"));
			LR = ImageIO.read(new File("src//sprites//" + name + "//" + name + " LR.png"));
			LS = ImageIO.read(new File("src//sprites//" + name + "//" + name + " LS.png"));
			RL = ImageIO.read(new File("src//sprites//" + name + "//" + name + " RL.png"));
			RR = ImageIO.read(new File("src//sprites//" + name + "//" + name + " RR.png"));
			RS = ImageIO.read(new File("src//sprites//" + name + "//" + name + " RS.png"));

			
			currSprite = FS;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		x = 0;
		y = 0;
			
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void moveUp(final Tile t) {
		if(moving)
			return;
		new Thread(new Runnable() {
			@Override
			public void run() {
				if(t == null || !t.walkable()) {
					currSprite = BS;
					return;
				}
				
				moving = true;
				for(int i=0; i<48; i++) {
					y-=1;
					state = (i/12);
					
					if (state == 0)
						currSprite = BL;
					else if (state == 1 || state == 3)
						currSprite = BS;
					else
						currSprite = BR;
					
					try {
						Thread.sleep((long) (tps*1000/tileSize));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				moving = false;
			}			
		}).start();
		return;
	}
	
	public void moveDown(final Tile t) {
		if(moving)
			return;
		new Thread(new Runnable() {
			@Override
			public void run() {	
				if(t == null || !t.walkable()) {
					currSprite = FS;
					return;
				}
				
				moving = true;
				for(int i=0; i<48; i++) {
					y++;
					state = (i/12);

					if (state == 0)
						currSprite = FL;
					else if (state == 1 || state == 3)
						currSprite = FS;
					else
						currSprite = FR;
					
					try {
						Thread.sleep((long) (tps*1000/tileSize));
					} catch (InterruptedException e) {
						System.out.println(1);
					}
				}
				moving = false;					
			}			
		}).start();
		return;
	}
	
	public void moveLeft(final Tile t) {
		if(moving)
			return;
		new Thread(new Runnable() {
			@Override
			public void run() {
				if(t == null || !t.walkable()) {
					currSprite = LS;
					return;
				}
				
				moving = true;
				for(int i=0; i<48; i++) {
					x-=1;
					state = (i/12);

					if (state == 0)
						currSprite = LL;
					else if (state == 1 || state == 3)
						currSprite = LS;
					else
						currSprite = LR;
					
					try {
						Thread.sleep((long) (tps*1000/tileSize));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				moving = false;					
			}
		}).start();
		return;
	}
	
	public void moveRight(final Tile t) {
		if(moving)
			return;
		new Thread(new Runnable() {
			@Override
			public void run() {
				if(t == null || !t.walkable()) {
					currSprite = RS;
					return;
				}
				
				moving = true;
				for(int i=0; i<48; i++) {
					x+=1;
					state = (i/12);

					if (state == 0)
						currSprite = RL;
					else if (state == 1 || state == 3)
						currSprite = RS;
					else
						currSprite = RR;
					
					try {
						Thread.sleep((long) (tps*1000/tileSize));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				moving = false;
			}
			
		}).start();
		return;
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
