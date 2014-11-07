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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tiles.Tile;

public class GUI extends JPanel implements Runnable, KeyListener {
	private Image image, bg, player_sprite;
	private Image player_fs, player_fr, player_fl;
	private Image player_bs, player_br, player_bl;
	private Image player_rs, player_rr, player_rl;
	private Image player_ls, player_lr, player_ll;
	private Graphics second;
	private Player p;
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
			player_fs = ImageIO.read(new File(
					"src//sprites//Kirito//Kirito FS.png"));
			player_fr = ImageIO.read(new File(
					"src//sprites//Kirito//Kirito FR.png"));
			player_fl = ImageIO.read(new File(
					"src//sprites//Kirito//Kirito FL.png"));

			player_bs = ImageIO.read(new File(
					"src//sprites//Kirito//Kirito BS.png"));
			player_br = ImageIO.read(new File(
					"src//sprites//Kirito//Kirito BR.png"));
			player_bl = ImageIO.read(new File(
					"src//sprites//Kirito//Kirito BL.png"));

			player_rs = ImageIO.read(new File(
					"src//sprites//Kirito//Kirito RS.png"));
			player_rr = ImageIO.read(new File(
					"src//sprites//Kirito//Kirito RR.png"));
			player_rl = ImageIO.read(new File(
					"src//sprites//Kirito//Kirito RL.png"));

			player_ls = ImageIO.read(new File(
					"src//sprites//Kirito//Kirito LS.png"));
			player_lr = ImageIO.read(new File(
					"src//sprites//Kirito//Kirito LR.png"));
			player_ll = ImageIO.read(new File(
					"src//sprites//Kirito//Kirito LL.png"));

			player_sprite = player_fs;
			bg = ImageIO.read(new File("src//tiles//map.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p = new Player();

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
		//mapFrame.addKeyListener(new mapListener());
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
		second.drawImage(player_sprite, getWidth() / 2 - 24,
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
		frame.add(gui);
		frame.setSize(xSize, ySize);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.addKeyListener(gui);
		frame.setResizable(false);
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
			else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				System.out.println(optPane.getSize());
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
		if (e.getKeyCode() == KeyEvent.VK_RIGHT
				|| e.getKeyCode() == KeyEvent.VK_D) {
			int x = p.getX() + 48;
			int y = p.getY();
			Tile t = map.getTile(x, y);
			if (t != null && t.walkable()) {
				if (p.moveRight()) {
					player_sprite = player_rs;
					new Thread(new Runnable() {
						@Override
						public void run() {
							while (p.moving) {
								if (p.state == 0)
									player_sprite = player_rl;
								else if (p.state == 1 || p.state == 3)
									player_sprite = player_rs;
								else
									player_sprite = player_rr;
							}
						}

					}).start();
				}
			}

		} else if (e.getKeyCode() == KeyEvent.VK_LEFT
				|| e.getKeyCode() == KeyEvent.VK_A) {
			int x = p.getX() - 48;
			int y = p.getY();
			Tile t = map.getTile(x, y);
			if (t != null && t.walkable()) {
				if (p.moveLeft()) {
					player_sprite = player_ls;
					new Thread(new Runnable() {
						@Override
						public void run() {
							while (p.moving) {
								if (p.state == 0)
									player_sprite = player_ll;
								else if (p.state == 1 || p.state == 3)
									player_sprite = player_ls;
								else
									player_sprite = player_lr;
							}
						}

					}).start();
				}
			}

		} else if (e.getKeyCode() == KeyEvent.VK_UP
				|| e.getKeyCode() == KeyEvent.VK_W) {
			int x = p.getX();
			int y = p.getY() - 48;
			Tile t = map.getTile(x, y);
			if (t != null && t.walkable()) {
				if (p.moveUp()) {
					player_sprite = player_bs;
					new Thread(new Runnable() {
						@Override
						public void run() {
							while (p.moving) {
								if (p.state == 0)
									player_sprite = player_bl;
								else if (p.state == 1 || p.state == 3)
									player_sprite = player_bs;
								else
									player_sprite = player_br;
							}
						}

					}).start();
				}
			}
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN
				|| e.getKeyCode() == KeyEvent.VK_S) {
			int x = p.getX();
			int y = p.getY() + 48;
			Tile t = map.getTile(x, y);
			if (t != null && t.walkable()) {
				if (p.moveDown()) {
					player_sprite = player_fs;
					new Thread(new Runnable() {
						@Override
						public void run() {
							while (p.moving) {
								if (p.state == 0)
									player_sprite = player_fl;
								else if (p.state == 1 || p.state == 3)
									player_sprite = player_fs;
								else
									player_sprite = player_fr;
							}
						}

					}).start();
				}
			}
		} else if (e.getKeyCode() == KeyEvent.VK_I) {
			invFrame.setVisible(!invFrame.isVisible());
		} else if (e.getKeyCode() == KeyEvent.VK_M) {
			mapFrame.setVisible(!mapFrame.isVisible());
		} else if (e.getKeyCode() == KeyEvent.VK_O) {
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
				e.printStackTrace();
			}
		}
	}
}
