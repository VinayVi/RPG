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
	
	public Vector(Vector v) {
		x = v.getX();
		y = v.getY();
	}
	
	public double mag() {
		return Math.sqrt(x*x+y*y);
	}

	public boolean isZero() {
		if(x!=0)
			return false;
		if(y!=0)
			return false;
		return true;
	}
	
	public void Scalar(int d) {
		x*=d;
		y*=d;
	}
	
	public void add(Vector v) {
		x = x + v.getX();
		y = y + v.getY();
	}
	
	public void add(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	public void sub(int x, int y) {
		this.x -= x;
		this.y -= y;
	}
	
	public void sub(Vector v) {
		x = x - v.getX();
		y = y - v.getY();
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
