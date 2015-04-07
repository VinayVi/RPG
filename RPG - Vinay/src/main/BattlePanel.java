package main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BattlePanel extends JPanel implements Runnable, KeyListener {
	
	protected Battle battle;
	boolean battling;
	private long currTime, prevTime=0, deltaTime = 50000000L;
	
	public BattlePanel(Battle b) {
		battle = b;
		battling = true;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void paint(Graphics g) {
		
	}

	@Override
	public void run() {
		while(battling) {
			currTime = System.nanoTime();
			if(currTime-prevTime > deltaTime) {
				battling = battle.p.info.currentHealth>0 && battle.enemy.info.currentHealth>0;
				repaint();
				prevTime = currTime;
			}
			
		}
	}

}
