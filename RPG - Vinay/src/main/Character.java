package main;

import items.Item;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;

import tiles.Tile;

public class Character implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Info info;

	protected int tileSize;
	protected double tps; // seconds per tile, aka how long it takes to move 1
							// tile

	public Image currSprite;
	public Image BL, BR, BS, FL, FR, FS, LL, LR, LS, RL, RR, RS;
	private Tile newTile;
	int dir;
	private Thread thread;

	public Character(String name) {
		info = new Info();
		tileSize = 48;
		tps = 0.25;
		info.moving = false;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					if (dir == 0) {

					} else if (dir == 1) {
						if (newTile == null || !newTile.walkable() || !currSprite.equals(BS)) {
							currSprite = BS;
							try {
								Thread.sleep((long) (250));
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							for (int i = 0; i < 48; i++) {
								info.y -= 1;
								info.state = (i / 12);

								if (info.state == 0)
									currSprite = BL;
								else if (info.state == 1 || info.state == 3)
									currSprite = BS;
								else
									currSprite = BR;

								try {
									Thread.sleep((long) (tps * 1000 / tileSize));
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						if (!info.mU)
							dir = 0;
					} else if (dir == 2) {
						if (newTile == null || !newTile.walkable()
								|| !currSprite.equals(RS)) {
							currSprite = RS;
							try {
								Thread.sleep((long) (250));
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							for (int i = 0; i < 48; i++) {
								info.x += 1;
								info.state = (i / 12);

								if (info.state == 0)
									currSprite = RL;
								else if (info.state == 1 || info.state == 3)
									currSprite = RS;
								else
									currSprite = RR;

								try {
									Thread.sleep((long) (tps * 1000 / tileSize));
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						if (!info.mR)
							dir = 0;
					} else if (dir == 3) {
						if (newTile == null || !newTile.walkable()
								|| !currSprite.equals(FS)) {
							currSprite = FS;
							try {
								Thread.sleep((long) (250));
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							for (int i = 0; i < 48; i++) {
								info.y += 1;
								info.state = (i / 12);

								if (info.state == 0)
									currSprite = FL;
								else if (info.state == 1 || info.state == 3)
									currSprite = FS;
								else
									currSprite = FR;

								try {
									Thread.sleep((long) (tps * 1000 / tileSize));
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						if (!info.mD)
							dir = 0;
					} else if (dir == 4) {
						if (newTile == null || !newTile.walkable()
								|| !currSprite.equals(LS)) {
							currSprite = LS;
							try {
								Thread.sleep((long) (250));
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							for (int i = 0; i < 48; i++) {
								info.x -= 1;
								info.state = (i / 12);

								if (info.state == 0)
									currSprite = LL;
								else if (info.state == 1 || info.state == 3)
									currSprite = LS;
								else
									currSprite = LR;

								try {
									Thread.sleep((long) (tps * 1000 / tileSize));
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						if (!info.mL)
							dir = 0;
					}
				}
			}

		});
		thread.start();

		info.x = 0;
		info.y = 0;

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

	public void moveUp(final Tile t) {
		if (info.moving)
			return;
		newTile = t;
		dir = 1;
		return;
	}

	public void moveDown(final Tile t) {
		if (info.moving)
			return;
		newTile = t;
		dir = 3;
		return;
	}

	public void moveLeft(final Tile t) {
		if (info.moving)
			return;
		newTile = t;
		dir = 4;
		return;
	}

	public void moveRight(final Tile t) {
		if (info.moving)
			return;
		newTile = t;
		dir = 2;
		return;
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

}
