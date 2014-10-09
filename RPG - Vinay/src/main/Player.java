package main;
import items.*;
import java.util.ArrayList;

public class Player extends Character{
	
	private ArrayList<Item> items;
	public Player() {
		x = 0;
		y = 0;
	}
	
	public void pickUp(Item i) {
		items.add(i);
		i.setOwner(this);
	}
}
