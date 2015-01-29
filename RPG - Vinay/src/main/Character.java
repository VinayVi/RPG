package main;

import items.Item;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class Character implements Serializable {
	public Info info;
	public Image currSprite;
	public final Image[][] sprites;
	private volatile Vector speed;
	final long true_wait;
	private long moveTime;
	private long curr;
	private long wait;


	public Character(String name) {
		info = new Info();
		info.mU = false;
		info.mR = false;
		info.mD = false;
		info.mL = false;
		info.state = 1;
		this.info.name = name;
		sprites = new Image[4][4];
		try {
			BufferedImage spritesheet = ImageIO.read(new File("src//sprites//"+name+".png"));
			for(int i=0; i<=3; i++) {
				for(int j=0; j<=3; j++) {
					sprites[i][j] = spritesheet.getSubimage(0, (4*i+j)*48, 48, 48);
				}
			}
			currSprite = sprites[2][0];
		} catch (IOException e) {}
		true_wait = 4;
		setWait(true_wait);
		setMoveTime(0);
		info.setLoc(new Vector(0, 0));
		speed = new Vector();
	}
	
	public boolean moving() {
		return info.mD||info.mL||info.mU||info.mR;
	}

	public int getX() {
		return info.getLoc().getX();
	}

	public void setX(int x) {
		this.info.getLoc().setX(x);
	}

	public int getY() {
		return info.getLoc().getY();
	}

	public void setY(int y) {
		this.info.getLoc().setY(y);
	}

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
