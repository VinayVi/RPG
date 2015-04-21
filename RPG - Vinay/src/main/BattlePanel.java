package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BattlePanel extends JPanel implements Runnable, KeyListener {
	
	protected Battle battle;
	protected ActionPanel actionPanel;
	boolean battling;
	private long currTime, prevTime=0, deltaTime = 50000000L;
	private Image image, kiritoBack;
	private Graphics second;
	Toolkit tk = Toolkit.getDefaultToolkit();
	int xSize = ((int) tk.getScreenSize().getWidth());
	int ySize = ((int) tk.getScreenSize().getHeight());

	public BattlePanel(Battle b) {
		battle = b;
		try {
			kiritoBack = ImageIO.read(new File("src//sprites//KiritoBack.png"));
		} catch (IOException e) {
		}
		battling = true;
	}
	
	public void createActionPanel() {
		this.setLayout(null);
		actionPanel = new ActionPanel();
		actionPanel.setLayout(null);
		actionPanel.setSize(xSize, ySize/4);
		actionPanel.setLocation(0, 3*ySize/4);
		this.getParent().add(actionPanel);
		actionPanel.addButtons();
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
	
	@Override
	public void paint(Graphics g) {
		image = createImage(this.getWidth(), this.getHeight()*3/4);
		second = image.getGraphics();
		second.setColor(Color.LIGHT_GRAY);
		second.fillRect(0, 0, image.getWidth(this), image.getHeight(this));
		second.drawImage(kiritoBack, getWidth()/4 - 300, getHeight()/4 - 125, this );
		second.drawImage(battle.enemy.sprites[2][0].getScaledInstance(192, 192, Image.SCALE_DEFAULT), getWidth()*3/4 +50, getHeight()/4 - 150, this);
		g.drawImage(image, 0, 0, this);
		actionPanel.repaint();
	} 

	@Override
	public void run() {
		while(battling) {
			currTime = System.nanoTime();
			if(currTime-prevTime > deltaTime) {
				//battling = battle.p.info.currentHealth>0 && battle.enemy.info.currentHealth>0;
				repaint();
				//actionPanel.repaint();
				prevTime = currTime;
			}
			
		}
	}

	public class ActionPanel extends JPanel {
		
		private Image image2;
		private Graphics third;
		private JButton button;
		
		public void addButtons() {
			button = new JButton("Attack");
			button.setBounds(100, 700, 100, 100);
			add(button);
			button.setVisible(true);
		}
		
	    @Override
		public void paint(Graphics g) {
	    	System.out.println(this.getLocation());
	    	System.out.println(this.getSize());
			System.out.println(button.getLocation());
	    	super.paint(g);
			image2 = createImage(this.getWidth(), this.getHeight());
			third = image2.getGraphics();
			g.drawImage(image2, 0, 0, this);
		}
		
	}
	
}
