package main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class BattlePanel extends JPanel implements Runnable, KeyListener {

	protected Battle battle;
	protected ActionPanel actionPanel;
	boolean battling;
	private long currTime, prevTime = 0, deltaTime = 50000000L;
	private Image image, kiritoBack;
	private Graphics second;
	volatile double enemyHealthValue = 100;
	boolean playerWin;
	Random r = new Random();

	Toolkit tk = Toolkit.getDefaultToolkit();
	int xSize = ((int) tk.getScreenSize().getWidth());
	int ySize = ((int) tk.getScreenSize().getHeight());

	public BattlePanel(Battle b) {
		battle = b;
		try {
			kiritoBack = ImageIO.read(new File("src//sprites//KiritoBack.png"));
		} catch (IOException e) {}
		battling = true;
	}

	public void createActionPanel() {
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		actionPanel = new ActionPanel();
		actionPanel.setSize(xSize, 297);
		actionPanel.setLocation(0, ySize - 297);
		this.getParent().setLayout(null);
		this.getParent().add(actionPanel);
		actionPanel.setVisible(true); 
		topFrame.getContentPane().revalidate();
		topFrame.revalidate();
		actionPanel.repaint();
		topFrame.getContentPane().revalidate();
		topFrame.revalidate();
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
	public void paintComponent(Graphics g) {
		/*actionPanel.strongAttack.requestFocus();
		actionPanel.mediumAttack.requestFocus();
		actionPanel.lightAttack.requestFocus();*/
		if(actionPanel == null)
			return;
		image = createImage(xSize,
				ySize - actionPanel.getHeight() - 1);
		second = image.getGraphics();
		second.setColor(Color.LIGHT_GRAY);
		second.fillRect(0, 0, image .getWidth(this), image.getHeight(this));
		second.drawImage(kiritoBack, 0, this.getHeight() - actionPanel.getHeight()
						- kiritoBack.getHeight(this) - 15, this);
		second.drawImage(battle.enemy.sprites[2][0].getScaledInstance(192, 192,
				Image.SCALE_DEFAULT), getWidth() * 3 / 4 + 50,
				getHeight() / 4 - 150, this);
		second.setColor(Color.BLACK);
		second.setFont(new Font("default", Font.BOLD, 16));
		second.drawString("HEALTH", 0,
				this.getHeight() - actionPanel.getHeight());
		second.drawString("HEALTH", getWidth()*3/4+50, getHeight() / 4 - 150 + 192 + 15);
		FontMetrics fm = second.getFontMetrics();
		int width = fm.stringWidth("HEALTH") + 5;
		second.setColor(Color.GREEN);
		int rectWidth = kiritoBack.getWidth(this) - width;
		int enemyHealth = (int) (rectWidth * (battle.enemy.info.visibleHealth/ battle.enemy.info.maxHealth));
		int playerHealth = (int) (rectWidth * (battle.p.info.visibleHealth/battle.p.info.maxHealth));
		second.fillRect(width, this.getHeight() - actionPanel.getHeight() - 15,
				playerHealth, 15);
		second.fillRect(getWidth()*3/4+50, getHeight() / 4 - 150 + 192 + 15, enemyHealth, 15);
		g.drawImage(image, 0,  0, this);
	}

	@Override
	public void run() {
		while (battling) {
			currTime = System.nanoTime();
			if (currTime - prevTime > deltaTime) {
				// battling = battle.p.info.currentHealth>0 &&
				// battle.enemy.info.currentHealth>0;
				repaint();
				//actionPanel.repaint();
				prevTime = currTime;
				
				//Calculate player health
				if (battle.p.info.currentHealth < battle.p.info.visibleHealth) {
					battle.p.info.visibleHealth -= 1;
				}
				else if (battle.p.info.currentHealth != battle.p.info.visibleHealth) {
				   // Make sure we don't go over
					battle.p.info.visibleHealth = battle.p.info.currentHealth;
				}
				//Calculate enemy health;
				if (battle.enemy.info.currentHealth < battle.enemy.info.visibleHealth) {
					battle.enemy.info.visibleHealth -= 1;
				}
				else if (battle.enemy.info.currentHealth != battle.enemy.info.visibleHealth) {
				   // Make sure we don't go over
					battle.enemy.info.visibleHealth = battle.enemy.info.currentHealth;
				}
				
				if(!battle.playerTurn) {
					int damage = r.nextInt(10)+10;
					Attack attack = new Attack(damage, 0);
					int result = battle.attack(attack);
					try {
						Thread.sleep(damage*16);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (result == 1) {
						JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
						battling = false;
						topFrame.dispose();
						playerWin = false;
					}
					battle.playerTurn = true;
					actionPanel.strongAttack.setEnabled(true);
					actionPanel.mediumAttack.setEnabled(true);
					actionPanel.lightAttack.setEnabled(true);

				}
			} 
		}
	}
	
	public int playerAttack(double damage) {
		int a = 0;
		while(damage > 0) {
			a = battle.attack(new Attack(1, 0));
			enemyHealthValue = (battle.enemy.info.currentHealth);
			damage--;
			repaint();
		}
		return a;
	}

	public class ActionPanel extends JPanel implements ActionListener {
		private JButton strongAttack;
		private JButton mediumAttack;
		private JButton lightAttack;
		private JLabel damageMessage;
		
		public ActionPanel() {
			addButtons();
		}

		public void addButtons() {
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			strongAttack = new JButton("Strong Attack");
			strongAttack.addActionListener(this);
			add(strongAttack);
			mediumAttack = new JButton("Medium Attack");
			mediumAttack.addActionListener(this);
			add(mediumAttack);
			lightAttack = new JButton("Light Attack");
			lightAttack.addActionListener(this);
			add(lightAttack);
			damageMessage = new JLabel("aaaaaaaaaaa"); 
			add(damageMessage); 
			validate();
		}
		

		@Override
		public void actionPerformed(ActionEvent e) {
			String str = e.getActionCommand();
			if(str.contains("Attack")) {
				int damage = 0;
				if (e.getActionCommand().equals("Strong Attack")) {
					damage = r.nextInt(50)+51;
				} else if (e.getActionCommand().equals("Medium Attack")) {
					damage = r.nextInt(50)+26;
				} else if (e.getActionCommand().equals("Light Attack")) {
					damage = r.nextInt(50)+1;
				}
				int result = playerAttack(damage);
				battle.playerTurn = false;
				damageMessage.setText(battle.damageMessage);
				actionPanel.strongAttack.setEnabled(false);
				actionPanel.mediumAttack.setEnabled(false);
				actionPanel.lightAttack.setEnabled(false);
				actionPanel.repaint(); 
				try {
					Thread.sleep(damage*16);
				} catch (InterruptedException IE) {
					// TODO Auto-generated catch block
					IE.printStackTrace();
				}
				if(result == 1) {
					playerWin = true;
					JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
					battling = false;
					topFrame.dispose();
				} 				
			}
		
		} 

	}

}
