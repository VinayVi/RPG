package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tiles.Tile;

public class GUI extends JPanel implements Runnable, KeyListener {
	private Image image, bg;
	private Graphics second;
	private Character p;
	private Map map;
	private int leftX, rightX, topY, botY, length, width;
	JFrame mapFrame, optFrame, invFrame;
	JPanel mapPane, optPane, invPane;
	boolean close = false;

	public GUI() {
		
		Toolkit tk = Toolkit.getDefaultToolkit();  
		int xSize = ((int) tk.getScreenSize().getWidth());  
		int ySize = ((int) tk.getScreenSize().getHeight());  

		bg = null;
		map = new Map();
		length = map.length;
		width = map.width;
		
		try {
			bg = ImageIO.read(new File("src//tiles//map.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p = new Character("Kirito");

		// Set up the minimap
		mapPane = new JPanel();
		mapFrame = new JFrame("Map");
		JLabel mapImage = new JLabel(new ImageIcon("src//tiles//minimap.png"));
		mapImage.setBackground(Color.BLACK);
		mapPane.add(mapImage);
		mapFrame.setContentPane(mapPane);
		mapFrame.setSize(300, 300);
		mapFrame.setLocation(xSize - 300, 0);
		mapFrame.setVisible(false);
		mapFrame.addKeyListener(this);
		mapFrame.setUndecorated(true);

		// Set up the Options
		optPane = new JPanel();
		optFrame = new JFrame("Options");
		optFrame.setContentPane(optPane);
		optFrame.setSize(400, 400);
		optFrame.setVisible(false);
		optFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		optFrame.addKeyListener(new optListener());
		optFrame.setLocationRelativeTo(null);
		optFrame.setUndecorated(true);
		

		// Set up the Inventory
		invPane = new JPanel();
		invFrame = new JFrame("Inventory");
		invFrame.setContentPane(invPane);
		invFrame.setSize(300, 500);
		invFrame.setLocation(xSize - 300, ySize - 500);
		invFrame.setVisible(false);
		invFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		invFrame.addKeyListener(new invListener());
		invFrame.setUndecorated(true);


	}

	public void paint(Graphics g) {
		image = createImage(this.getWidth(), this.getHeight());
		second = image.getGraphics();
		second.drawImage(bg, 0, 0, getWidth(), getHeight(), leftX + 24,
				topY + 24, rightX + 24, botY + 24, this);
		second.drawImage(p.currSprite, getWidth() / 2 - 24,
				getHeight() / 2 - 24, this);

		second.setColor(Color.black);
		if (leftX + 24 < 0)
			second.fillRect(0, 0, -(24 + leftX), getHeight());
		if (rightX - 24 > width - 48)
			second.fillRect(width - 24 - leftX, 0, getWidth(), getHeight());
		if (topY + 24 < 0)
			second.fillRect(0, 0, getWidth(), -(24 + topY));
		if (botY - 24 > length - 48)
			second.fillRect(0, length - 24 - topY, getWidth(), getHeight());

		g.drawImage(image, 0, 0, this);
	}

	public static void main(String[] args) {
		
		Toolkit tk = Toolkit.getDefaultToolkit();  
		int xSize = ((int) tk.getScreenSize().getWidth());  
		int ySize = ((int) tk.getScreenSize().getHeight());  
		
		JFrame frame = new JFrame("RPG");
		GUI gui = new GUI();
		gui.setPreferredSize(new Dimension(xSize, ySize));
		frame.setUndecorated(true);
		frame.add(gui);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.addKeyListener(gui);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		new Thread(gui).start();
	}

	public class invListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_M) {
				mapFrame.setVisible(!mapFrame.isVisible());
			} else if (e.getKeyCode() == KeyEvent.VK_O) {
				optFrame.setVisible(!optFrame.isVisible());
			} else if (e.getKeyCode() == KeyEvent.VK_I) {
				invFrame.setVisible(!invFrame.isVisible());
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	public class optListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_O) {
				optFrame.setVisible(!optFrame.isVisible());
			}

		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	public class mapListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_M) {
				mapFrame.setVisible(!mapFrame.isVisible());
			} else if (e.getKeyCode() == KeyEvent.VK_O) {
				optFrame.setVisible(!optFrame.isVisible());
			} else if (e.getKeyCode() == KeyEvent.VK_I) {
				invFrame.setVisible(!invFrame.isVisible());
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			int x = p.getX() + 48;
			int y = p.getY();
			Tile t = map.getTile(x, y);
			p.moveRight(t);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			int x = p.getX() - 48;
			int y = p.getY();
			Tile t = map.getTile(x, y);
			p.moveLeft(t);
		} else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			int x = p.getX();
			int y = p.getY() - 48;
			Tile t = map.getTile(x, y);
			p.moveUp(t);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			int x = p.getX();
			int y = p.getY() + 48;
			Tile t = map.getTile(x, y);
			p.moveDown(t);
			
		} else if (e.getKeyCode() == KeyEvent.VK_I) {
			invFrame.setVisible(!invFrame.isVisible());
		} else if (e.getKeyCode() == KeyEvent.VK_M) {
			mapFrame.setVisible(!mapFrame.isVisible());
		} else if (e.getKeyCode() == KeyEvent.VK_O) {
			optFrame.setVisible(!optFrame.isVisible());
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		while (true) {
			repaint();
			leftX = p.getX() - getWidth() / 2;
			rightX = p.getX() + getWidth() / 2;
			topY = p.getY() - getHeight() / 2;
			botY = p.getY() + getHeight() / 2;
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(2);

			}
		}
	}
}
