package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		actionPanel = new ActionPanel();
		actionPanel.setSize(xSize, 297);
		actionPanel.setLocation(0, ySize - 297);
		this.getParent().add(actionPanel);
		actionPanel.addButtons();
		System.out.println(ySize - 580);
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override 
	public void  keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void paint(Graphics g) {
		image = createImage(this.getWidth(), this.getHeight() - actionPanel.getHeight());;
		second = image.getGraphics();
		second.setColor(Color.LIGHT_GRAY);
		second.fillRect(0, 0, image.getWidth(this), image.getHeight(this));
		second.drawImage(kiritoBack, getWidth()/4 - 300, getHeight()/4 - 125, this );
		second.drawImage(battle.enemy.sprites[2][0].getScaledInstance(192, 192, Image.SCALE_DEFAULT), getWidth()*3/4 +50, getHeight()/4 - 150, this);
		second.setColor(Color.GREEN);
		second.fillRect(getWidth()/4 - 300, getHeight()/4 - 125 + kiritoBack.getHeight(this), kiritoBack.getWidth(this), 23);
		//System.out.println(getHeight()/4 - 125 + kiritoBack.getHeight(this));
		g.drawImage(image, 0, 0, this);
	} 

	@Override
	public void run() {
		while(battling) {
			currTime = System.nanoTime();
			if(currTime-prevTime > deltaTime) {
				//battling = battle.p.info.currentHealth>0 && battle.enemy.info.currentHealth>0;
				repaint();
				prevTime = currTime;
			}
			
		}
	}
 
	public class ActionPanel extends JPanel implements ActionListener {
		private JButton buttonAttack;
		
		public void addButtons() {
			buttonAttack = new JButton("Attack");
			buttonAttack.addActionListener(this);
			add(buttonAttack);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Attack")) {
				System.out.println("TRU");
			}
		}
		
	}
	
}
