package items;
import main.*;

public abstract class Item {

	public Player owner;
	
	public void setOwner(Player p) {
		owner = p;
	}
	
}
