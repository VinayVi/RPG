package main;

import java.awt.Color;
import java.awt.Dimension;
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
	protected JPanel actionPanel;
	boolean battling;
	private long currTime, prevTime=0, deltaTime = 50000000L;
	private Image image, kiritoBack;
	private Graphics second;
	
	public BattlePanel(Battle b) {
		battle = b;
		try {
			kiritoBack = ImageIO.read(new File("src//sprites//KiritoBack.png"));
		} catch (IOException e) {
		}
	}
	
	public void createActionPanel() {
		actionPanel = new JPanel();
		actionPanel.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()/4));
		this.add(actionPanel);
		actionPanel.setLocation(0, this.getHeight() - actionPanel.getHeight());
		actionPanel.setVisible(true);
		Graphics g = actionPanel.getGraphics();
		g.setColor(Color.blue);
		g.drawRect(0, 0, 100, 100);
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
		second.drawImage(kiritoBack, getWidth()/4 - 300, getHeight()/4 - 125, this );
		second.drawImage(battle.enemy.sprites[2][0].getScaledInstance(192, 192, Image.SCALE_DEFAULT), getWidth()*3/4 +50, getHeight()/4 - 150, this);
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
