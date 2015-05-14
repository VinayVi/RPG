package main;

import java.util.Random;

public class Battle{
	
	volatile Character p;
	volatile Character enemy;
	boolean playerTurn;
	Random r = new Random();
	String damageMessage;
	
	public Battle(Character p, Character enemy) {
		this.p = p;
		this.enemy = enemy;
		initialize();
	}
	

	private void initialize() {
		p.info.currentEnergy = p.info.maxEnergy/2;
		enemy.info.currentEnergy = enemy.info.maxEnergy/2;
		playerTurn = true;
	}
	
	/** 
	 * @param a
	 * @return -1 if not enough energy; 
	 * 			0 if attack is successful; 
	 * 			1 if enemy dies from attack
	 */
	public int attack (Attack a) {

		Character c1 = null, c2 = null;
		if(playerTurn) {
			c1 = p;
			c2 = enemy;
		} else {
			c1 = enemy;
			c2 = p;
		}
		updateMessage(a.damage);
		if(c1.info.currentEnergy - a.energy < 0) {
			return -1;
		}
		c1.info.currentEnergy -= a.energy;
		double damage = a.damage;
		c2.info.currentHealth -= damage;
		if(c2.info.currentHealth < 0) {
			return 1;
		}
		return 0;
	}
	
	private void updateMessage(double damage2) {
		if(playerTurn) {
			damageMessage = "You have dealt " + damage2 + " damage to the enemy";
		} else {
			damageMessage = "The Enemy has dealt " + damage2 + " damage to you";
		}
		
	}


	public int bearAttack() {
		int attack = r.nextInt(10)+10;
		Attack a = new Attack(attack, 0);
		attack = this.attack(a);
		return attack;
	}
}
