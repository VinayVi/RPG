package main;

import item.Item;
import item.ItemType;
import item.Equipable.Equipable;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;

@SuppressWarnings("serial")
public class Character implements Serializable {
	public Info info;
	protected double tps; // seconds per tile, aka how long it takes to move 1
							// tile
	public Image currSprite;
	volatile int dir;
	public DefaultListModel<Equipable> Inventory = new DefaultListModel<Equipable>();

	public final Image[][] sprites;
	private volatile Vector speed;
	final double true_wait;
	private long moveTime;
	private long curr;
	private double wait;

	public Character(String name) {
		info = new Info();
		info.mU = false;
		info.mR = false;
		info.mD = false;
		info.mL = false;
		info.state = 1;
		this.info.name = name;
		sprites = new Image[4][4];
		BufferedImage spritesheet = null;
		try {
			spritesheet = ImageIO.read(new File("src//sprites//" + name
					+ ".png"));
			spritesheet = ImageIO.read(new File("src//sprites//" + name
					+ ".png"));
		} catch (IOException e) {
		}
		if (spritesheet.getHeight() == 768) {
			for (int i = 0; i <= 3; i++) {
				for (int j = 0; j <= 3; j++) {
					sprites[i][j] = spritesheet.getSubimage(0,
							(4 * i + j) * 48, 48, 48);
				}
			}
		} else {
			for (int i = 0; i <= 3; i++) {
				for (int j = 0; j <= 3; j++) {
					sprites[i][j] = spritesheet;
				}
			}
		}
		currSprite = sprites[2][0];
		true_wait = 4;
		setWait(true_wait);
		setMoveTime(0);
		info.setLoc(new Vector(0, 0));
		speed = new Vector();
	}

	public boolean moving() {
		return info.mD || info.mL || info.mU || info.mR;
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

	// Item Equipment
	public Boolean canEquipR(Equipable item) {
		boolean out = false;
		switch (item.getType()) {
		case ONE:
			if (info.getRweaponEquipped() == null
					&& (info.getLweaponEquipped() == null || info
							.getLweaponEquipped().getType()
							.equals(ItemType.SHIELD))) {
				out = true;
			}
			break;
		case TWO:
			if (info.getRweaponEquipped() == null
					&& info.getLweaponEquipped() == null) {
				out = true;
			}
			break;
		case DAGGER:
			if (info.getRweaponEquipped() == null
					&& (info.getLweaponEquipped() == null || info
							.getLweaponEquipped().getType()
							.equals(ItemType.DAGGER))) {
				out = true;
			}
			break;
		case RAPIER:
			if (info.getRweaponEquipped() == null
					&& info.getLweaponEquipped() == null) {
				out = true;
			}
			break;
		case SHIELD:
			out = false;
			break;
		}
		return out;

	}

	public Boolean canEquipL(Equipable item) {
		boolean out = false;
		switch (item.getType()) {
		case DAGGER:
			if (info.getRweaponEquipped() == null) {
				out = false;
			} else if (info.getRweaponEquipped().getType()
					.equals(ItemType.DAGGER)) {
				out = true;
			}
			break;
		case SHIELD:
			if (info.getRweaponEquipped() == null
					&& info.getLweaponEquipped() == null) {
				out = true;
			} else if (info.getRweaponEquipped().getType().equals(ItemType.ONE)) {
				out = true;
			}
			break;
		}
		return out;
	}

	// Stat Accessors
	public double getDmg() {
		return info.str * info.getStrMultiplier() + info.damage;
	}

	public double getHealth() {
		info.maxHealth = (info.fort * info.getFortMultiplier());
		return info.maxHealth;
	}

	public double getRes() {
		return info.resil * info.getResMultiplier();
	}

	public double getStam() {
		return info.stam * info.getStamMultiplier();
	}

	public long getCurr() {
		return curr;
	}

	public void setCurr(long curr) {
		this.curr = curr;
	}

	public double getWait() {
		return wait;
	}

	public void setWait(double true_wait2) {
		this.wait = true_wait2;
	}

	public long getMoveTime() {
		return moveTime;
	}

	public void setMoveTime(long moveTime) {
		this.moveTime = moveTime;
	}

}
