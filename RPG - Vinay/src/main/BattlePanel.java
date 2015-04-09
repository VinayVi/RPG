package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BattlePanel extends JPanel implements Runnable, KeyListener {
	
	protected Battle battle;
	boolean battling;
	private long currTime, prevTime=0, deltaTime = 50000000L;
	private Image image, kiritoBack;
	private Graphics second;
	
	public BattlePanel(Battle b) {
		battle = b;
		battling = true;
		try {
			kiritoBack = ImageIO.read(new File("src//sprites//KiritoBack.png"));
		} catch (IOException e) {
		}
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
		image = createImage(this.getWidth(), this.getHeight());
		second = image.getGraphics();
		second.setColor(Color.LIGHT_GRAY);
		second.fillRect(0, 0, getWidth(), getHeight());
		//second.drawImage(battle.p.sprites[2][0].getScaledInstance(192, 192, Image.SCALE_DEFAULT), getWidth()/4, getHeight()/4, this );
		second.drawImage(kiritoBack, getWidth()/4 - 100, getHeight()/4 - 100, this );
		second.drawImage(battle.enemy.sprites[2][0].getScaledInstance(192, 192, Image.SCALE_DEFAULT), getWidth()*3/4 - 100, getHeight()/4 - 100, this);
		g.drawImage(image, 0, 0, this);
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
