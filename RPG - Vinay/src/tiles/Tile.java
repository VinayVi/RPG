package tiles;
import main.*;

public class Tile {
	private Vector loc;
	private int type;
	private boolean walkable;
	/*
	 * 1 = Grass
	 * 2 = Road
	 * 3 = Road with Grass on right
	 * 4 = Road with Grass on top
	 * 5 = Road with Grass on left
	 * 6 = Road with Grass on bottom
	 * 7 = Road with Grass on top-left corner
	 * 8 = Road with Grass on top-right corner
	 * 9 = Road with Grass on bottom-left corner
	 * 10 = Road with Grass on bottom-right corner
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