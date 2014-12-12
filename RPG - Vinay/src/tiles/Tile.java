package tiles;
import main.*;

public class Tile {
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
	 * 101 = Bear
	 * 102 = Wolf
	 */
	
	public boolean walkable() {
		return walkable;
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
}