package tiles;

import main.Vector;

public class Portal extends Tile {
	private Vector newLoc;
	private int newMap;

	public Portal(Vector v, boolean b, Vector l, int m) {
		super(0, v, b);
		setNewLoc(new Vector(l));
		setNewMap(m);
		// TODO Auto-generated constructor stub
	}

	public Portal(int x, int y, boolean b, Vector l, int m) {
		super(0, x, y, b);
		setNewLoc(new Vector(l));
		setNewMap(m);
	}

	public int getNewMap() {
		return newMap;
	}

	public void setNewMap(int newMap) {
		this.newMap = newMap;
	}

	public Vector getNewLoc() {
		return newLoc;
	}

	public void setNewLoc(Vector newLoc) {
		this.newLoc = newLoc;
	}

}
