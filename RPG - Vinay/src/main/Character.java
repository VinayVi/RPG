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
	public final Image[][] sprites;
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
		sprites = new Image[4][4];
		try {
			//First Number - Up Right Down Left
			//Second Number - Right Straight Left Straight
			sprites[0][3] = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " BR.png"));
			sprites[0][2] = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " BS.png"));
			sprites[0][1] = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " BL.png"));
			sprites[0][0] = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " BS.png"));
			sprites[1][3] = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " RR.png"));
			sprites[1][2] = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " RS.png"));
			sprites[1][1] = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " RL.png"));
			sprites[1][0] = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " RS.png"));
			sprites[2][3] = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " FR.png"));
			sprites[2][2] = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " FS.png"));
			sprites[2][1] = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " FL.png"));
			sprites[2][0] = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " FS.png"));
			sprites[3][3] = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " LR.png"));
			sprites[3][2] = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " LS.png"));
			sprites[3][1] = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " LL.png"));
			sprites[3][0] = ImageIO.read(new File("src//sprites//" + name + "//" + name
					+ " LS.png"));
			currSprite = sprites[2][3];
		} catch (IOException e) {
		}
		setWait((long) (4));
		setMoveTime(0);
		info.setLoc(new Vector(0, 0));
		speed = new Vector();
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
