package main;

import java.awt.Color;
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

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
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
		//actionPanel.setBackground(Color.DARK_GRAY);
		actionPanel.addButtons();
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
		//super.paintComponent(g);
		actionPanel.buttonAttack.requestFocus();
		image = createImage(this.getWidth(),
				this.getHeight() - actionPanel.getHeight() - 1);
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
		second.fillRect(width, this.getHeight() - actionPanel.getHeight() - 15,
				kiritoBack.getWidth(this) - width, 15);
		g.drawImage(image, 0, 0, this);
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
				if(!battle.playerTurn)
					actionPanel.buttonAttack.setEnabled(false);
				else
					actionPanel.buttonAttack.setEnabled(true);
			}

		}
	}

	public class ActionPanel extends JPanel implements ActionListener {
		private JButton buttonAttack;

		public void addButtons() {
			buttonAttack = new JButton("Attack");
			buttonAttack.addActionListener(this);
			add(buttonAttack);
			validate();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Attack")) {
				int attack = battle.attack(new Attack(10, 10));
			}
		}

	}

}
