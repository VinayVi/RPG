package main;

public class Vector{

	private int x, y;

	public Vector() {
		x = 0;
		y = 0;
	}
	
	public Vector(int i, int j) {
		x = i;
		y = j;
	}
	
	public void Scalar(int d) {
		x*=d;
		y*=d;
	}
	
	public void add(Vector v) {
		x = x + v.getX();
		y = y + v.getY();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public String toString() {
		return x + ", " + y;
	}
}
