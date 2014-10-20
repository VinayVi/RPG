package items;
import main.*;

public abstract class Item {

	protected String type; 
	
	public String getType() {
		return type;
	}
	
	protected double str;

	protected double agi;
	protected double dex;
	protected double fort;
	protected double luck;
	protected double damage;
	protected double dodge;
	protected double cdr;
	protected double crit;
	
	public boolean equipped;
	
	
	public double getStr() {
		return str;
	}
	
	public double getAgi() {
		return agi;
	}
	
	public double getDex() {
		return dex;
	}
	
	public double getFort() {
		return fort;
	}
	
	public double getLuck() {
		return luck;
	}
	
	public double getDamage() {
		return damage;
	}
	
	public double getDodge() {
		return dodge;
	}
	
	public double getCdr() {
		return cdr;
	}
	
	public double getCrit() {
		return crit;
	}
	
	public boolean isEquipped() {
		return equipped;
	}
}
