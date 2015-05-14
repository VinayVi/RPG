package tiles;
import java.io.Serializable;

import main.*;

public class Tile implements Serializable{
	private static final long serialVersionUID = 1L;
	private Vector loc;
	private int type;
	private boolean walkable;
	/* 1 = Grass
	 * 2 = Road
	 * 3 = Road with Grass on right
	 * 4 = Road with Grass on top
	 * 5 = Road with Grass on left
	 * 6 = Road with Grass on bottom
	 * 7 = Road with Road on bottom-right corner
	 * 8 = Road with Road on bottom-left corner
	 * 9 = Road with Road on top right corner
	 * 10 = Road with Road on top left corner
	 * 11 = Road with Grass on bottom right corner
	 * 12 = Road with Grass on bottom left corner
	 * 13 = Road with Grass on top right corner
	 * 14 = Road with Grass on top left corner
	 * 15 = Rock
	 * 16 = Tree Bottom
	 * 17 = Tree Top
	 * 18 = Water
	 * 19 = Bridge
	 * 20 = Dirt Road
	 * 21 = Dirt Road with Grass on right
	 * 22 = Dirt Road with Grass on top
	 * 23 = Dirt Road with Grass on left
	 * 24 = Dirt Road with Grass on bottom
	 * 25 = Dirt Road with Road on bottom-right corner
	 * 26 = Dirt Road with Road on bottom-left corner
	 * 27 = Dirt Road with Road on top right corner
	 * 28 = Dirt Road with Road on top left corner
	 * 29 = Dirt Road with Grass on bottom right corner
	 * 30 = Dirt Road with Grass on bottom left corner
	 * 31 = Dirt Road with Grass on top right corner
	 * 32 = Dirt Road with Grass on top left corner
	 * 33 = Sand
	 * 34 = Cactus
	 * 35 = Spooky Grass
	 * 36 = Spooky Tree 1
	 * 37 = Spooky Tree 2
	 * 38 = Spooky Road
	 * 39 = Graveyard Fence
	 * 40 = Gravestone
	 * 41 = Vinay Gravestone
	 * 42 = Connor Gravestone
	 * 43 = Eric Gravestone
	 * 44 = Daniel Gravestone
	 * 45 = Snow
	 * 46 = SnowRoad
	 * 47 = Ice
	 * 48 = Boat
	 * 49 = Marble Road
	 * 50 = Lava
	 * 51 = Volcano
	 * 52 = LavaBridge
	 * 53 = LastTile
	 * 54 = CrystalBot
	 * 55 = CrystalTop
	 * 101 = Bear
	 * 102 = Wolf
	 * 201 = HouseBotLeft
	 * 202 = HouseBotMid
	 * 203 = HouseBotRight
	 * 204 = HouseTopLeft
	 * 205 = HouseTopMid
	 * 206 = HouseTopRight
	 * 207 = MarketTile
	 * 208 = MarketCarpet
	 * 209 = MarketPlant
	 * 210 = MarketCounter1
	 * 211 = MarketCounter2
	 * 212 = MarketCounter3
	 * 213 = MarketCounter4
	 */
	
	public boolean walkable() {
		return walkable;
	}
	
	public void setWalkable(boolean b) {
		walkable = b;
	}
	
	
	public Tile(int type, Vector v, boolean b) {
		loc = new Vector();
		this.type = type;
		loc.setX(v.getX());
		loc.setY(v.getY());
		walkable = b;
	}
	
	public Tile(int type, int x, int y, boolean b) {
		loc = new Vector();
		this.type = type;
		loc.setX(x);
		loc.setY(y);
		walkable = b;
	}
	
	public Vector getLoc() {
		return loc;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int t) {
		type = t;
	}
}