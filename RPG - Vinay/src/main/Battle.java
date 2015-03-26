package main;

public class Battle{
	
	Character p;
	Character enemy;
	boolean playerTurn;
	
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
	 * 
	 * @param a
	 * @return -1 if not enough energy
	 * @return 0 if attack is successful
	 * @return 1 if enemy dies from attack
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
		if(c1.info.currentEnergy - a.energy < 0) {
			return -1;
		}
		c1.info.currentEnergy -= a.energy;
		c2.info.currentHealth -= a.damage;
		if(c2.info.currentHealth < 0) {
			return 1;
		}
		return 0;
	}
	
	
	
}
