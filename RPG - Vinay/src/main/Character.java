package main;

import items.Item;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;

import tiles.Tile;

public class Character implements Serializable {
	private static final long serialVersionUID = 1L;
	public Info info;
	protected int tileSize;
	protected double tps; // seconds per tile, aka how long it takes to move 1
							// tile
	public Image currSprite;
	public Image BL, BR, BS, FL, FR, FS, LL, LR, LS, RL, RR, RS;
	volatile int dir;
	private volatile Vector speed;
	private long moveTime;
	private long curr;
	private long wait;

	
	
	
	
	public boolean moving() {
		return info.mD||info.mL||info.mU||info.mR;
	}



	public Character(String name) {
		info = new Info();
		tileSize = 48;
		tps = 0.25;
		info.mU = false;
		info.mR = false;
		info.mD = false;
		info.mL = false;
		info.state = 1;
		this.info.name = name;
		try {
			BL = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " BL.png"));
			BR = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " BR.png"));
			BS = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " BS.png"));
			FL = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " FL.png"));
			FR = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " FR.png"));
			FS = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " FS.png"));
			LL = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " LL.png"));
			LR = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " LR.png"));
			LS = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " LS.png"));
			RL = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " RL.png"));
			RR = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " RR.png"));
			RS = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " RS.png"));
			currSprite = FS;
		} catch (IOException e) {
		}
		setWait((long) (1));
		setMoveTime(0);
		info.x = 0;
		info.y = 0;
		speed = new Vector();
	}

	public int getX() {
		return info.x;
	}

	public void setX(int x) {
		this.info.x = x;
	}

	public int getY() {
		return info.y;
	}

	public void setY(int y) {
		this.info.y = y;
	}

	/*public void move(final Tile t) {
		newTile = t;
	}
*/
	public void pickUp(Item i) {
		if (i.getType().equals("WEAPON")) {
			info.getInventoryW().add(i);
		}
	}

	public void equip(Item i) {
		i.equipped = true;
		info.str += i.getStr();
		info.agi += i.getAgi();
		info.dex += i.getDex();
		info.fort += i.getFort();
		info.luck += i.getLuck();
		info.damage += i.getDamage();
		info.dodge += i.getDodge();
		info.cdr += i.getCdr();
		info.crit += i.getCdr();
	}

	public void unEquip(Item i) {
		i.equipped = false;
		info.str -= i.getStr();
		info.agi -= i.getAgi();
		info.dex -= i.getDex();
		info.fort -= i.getFort();
		info.luck -= i.getLuck();
		info.damage -= i.getDamage();
		info.dodge -= i.getDodge();
		info.cdr -= i.getCdr();
		info.crit -= i.getCdr();
	}
	
	public Vector getSpeed() {
		return speed;
	}
	
	public void setSpeed(Vector v) {
		speed = v;
	}
	
	public void setSpeed(int x, int y) {
		speed.setX(x);
		speed.setY(y);
	}

	// Stat Accessors
	public double getDmg() {
		return info.str * info.getStrMultiplier() + info.damage;
	}

	public double getHealth() {
		info.maxHealth = (info.fort * info.getFortMultiplier());
		return info.maxHealth;
	}

	public double getDodge() {
		info.dodge = info.dex * info.getDexMultiplier();
		return info.dodge;
	}

	public double getCrit() {
		info.crit = info.luck * info.getLuckMultiplier();
		return info.crit;
	}

	public double getCDR() {
		info.cdr = info.agi * info.getAgiMultiplier();
		return info.cdr;
	}



	public long getCurr() {
		return curr;
	}



	public void setCurr(long curr) {
		this.curr = curr;
	}



	public long getWait() {
		return wait;
	}



	public void setWait(long wait) {
		this.wait = wait;
	}



	public long getMoveTime() {
		return moveTime;
	}



	public void setMoveTime(long moveTime) {
		this.moveTime = moveTime;
	}

}
