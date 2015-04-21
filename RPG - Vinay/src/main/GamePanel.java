package main;

import item.ItemType;
import item.Equipable.Equipable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tiles.Portal;
import tiles.Tile;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {
	ImageIcon icon = new ImageIcon("Z://git//RPG//RPG - Vinay//src//tiles//Border.png");
	Font myFont = new Font("SansSerif", Font.ITALIC, 18);
	private Image image, loadingImage;
	private Graphics second;
	private Character p;
	private Map map;
	private int leftX, rightX, topY, botY;
	private ArrayList<Integer> drawnMaps;
	JFrame mapFrame, optFrame, invFrame, statsFrame;
	JPanel mapPane, optPane, invPane, statsPane;
	JList<Equipable> invData;
	JButton load, save, exit, resume; // Options Buttons
	JButton equip;
	JLabel str, fort, damage, resil;
	ArrayList<Character> NPCs;
	final String strText, fortText, damageText, resilText;
	private volatile boolean running;
	private boolean loading;
	private Thread mover;
	Toolkit tk = Toolkit.getDefaultToolkit();
	int xSize = ((int) tk.getScreenSize().getWidth());
	int ySize = ((int) tk.getScreenSize().getHeight());
	JPanel dialoguepane;
	JFrame dialogue;
	JPanel shoppane;
	JFrame shop;

	public GamePanel() throws IOException {
		running = true;
		loading = false;
		drawnMaps = new ArrayList<Integer>();
		map = new Map(1, true);
		drawnMaps.add(1);
		p = new Character("Kirito");
		mapPane = new JPanel();
		mapFrame = new JFrame();
		JLabel mapImage = new JLabel(new ImageIcon("src//tiles//minimap.png"));
		mapImage.setBackground(Color.BLACK);
		mapPane.add(mapImage);
		mapFrame.setContentPane(mapPane);
		mapFrame.setSize(300, 300);
		mapFrame.setLocation(xSize - 300, 0);
		mapFrame.setVisible(false);
		mapFrame.addKeyListener(this);
		mapFrame.setUndecorated(true);

		optPane = new JPanel();
		optFrame = new JFrame();
		optFrame.setContentPane(optPane);
		optFrame.setSize(400, 400);
		optFrame.setVisible(false);
		optFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		optFrame.setLocationRelativeTo(this);
		optFrame.setUndecorated(true);
		load = new JButton("Load Game");
		load.addActionListener(new buttonListener());
		save = new JButton("Save Game");
		save.addActionListener(new buttonListener());
		exit = new JButton("Exit Game");
		exit.addActionListener(new buttonListener());
		resume = new JButton("Resume Game");
		resume.addActionListener(new buttonListener());
		optPane.add(load);
		optPane.add(save);
		optPane.add(exit);
		optPane.add(resume);
		optFrame.addKeyListener(new buttonListener());
		optFrame.setAlwaysOnTop(true);

		invPane = new JPanel();
		invPane.setLayout(new BoxLayout(invPane, BoxLayout.Y_AXIS));
		invFrame = new JFrame();
		invFrame.getContentPane().add(invPane);
		invFrame.setSize(250, 300);
		invFrame.setLocation(xSize - invFrame.getWidth(),
				ySize - invFrame.getHeight());
		invFrame.setVisible(false);
		invFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		invFrame.addKeyListener(new invListener());
		invFrame.setUndecorated(true);
		invFrame.setAlwaysOnTop(true);
		invFrame.setFocusable(true);
		equip = new JButton("Equip");
		equip.addActionListener(new buttonListener());
		invPane.add(equip);
		invData = new JList<Equipable>(p.Inventory);
		invPane.add(new JScrollPane(invData));

		statsPane = new JPanel();
		statsPane.setLayout(new BoxLayout(statsPane, BoxLayout.Y_AXIS));
		statsFrame = new JFrame();
		statsFrame.setSize(250, 75);
		statsFrame.setLocation(0, ySize - statsFrame.getHeight());
		statsFrame.setVisible(false);
		statsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		statsFrame.setUndecorated(true);
		statsFrame.setAlwaysOnTop(true);
		statsFrame.setFocusable(false);
		statsFrame.setFocusableWindowState(false);
		strText = new String("  Strength:     	       ");
		fortText = new String("  Fortitude:           ");
		damageText = new String("  Damage:              ");
		resilText = new String("  Resilience:          ");
		Font font = new Font("Courier New", Font.BOLD, 14);
		str = new JLabel(strText);
		str.setFont(font);
		fort = new JLabel(fortText);
		fort.setFont(font);
		damage = new JLabel(damageText);
		damage.setFont(font);
		resil = new JLabel(resilText);
		resil.setFont(font);

		statsPane.add(str);
		statsPane.add(fort);
		statsPane.add(damage);
		statsPane.add(resil);
		statsFrame.setContentPane(statsPane);
		updateStats();

		NPCs = new ArrayList<Character>();
		// Create NPCs here
		Character temp = new Character("oldMan");
		temp.info.setCurrMap(101);
		temp.info.setLoc(new Vector(96, 0));
		NPCs.add(temp);

		mover = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (running) {
						try {
							update(p);
							for (Character c : inScreen()) {
								update(c);
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});
		mover.start();
		p.info.setCurrMap(1);
		loadingImage = ImageIO.read(new File("src//sprites/Loading.png"));
	}

	public void paint(Graphics g) {
		image = createImage(this.getWidth(), this.getHeight());
		second = image.getGraphics();
		second.setColor(Color.BLACK);
		second.fillRect(0, 0, getWidth(), getHeight());
		second.drawImage(map.map, 0, 0, getWidth(), getHeight(), leftX + 24,
				topY + 24, rightX + 24, botY + 24, this);
		second.drawImage(p.currSprite, getWidth() / 2 - 24,
				getHeight() / 2 - 24, this);

		// draw NPCs
		for (Character c : inScreen()) {
			second.drawImage(c.currSprite, c.getX() - leftX - 24, c.getY()
					- topY - 24, this);
		}

		g.drawImage(image, 0, 0, this);
		if (!running) {
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(0, 0, getWidth(), getHeight());
		}
		if (loading) {
			g.setColor(new Color(0, 0, 0));
			g.fillRect(0, 0, getWidth(), getHeight());
			g.drawImage(loadingImage,
					(getWidth() - loadingImage.getWidth(this)) / 2,
					(getHeight() - loadingImage.getHeight(this)) / 2, this);
		}
	}

	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame("RPG");
		GamePanel gui = new GamePanel();
		gui.setPreferredSize(new Dimension(gui.xSize, gui.ySize));
		// frame.setUndecorated(true);
		frame.add(gui);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.addKeyListener(gui);
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		new Thread(gui).start();
	}

	public ArrayList<Character> inScreen() {
		ArrayList<Character> inScreen = new ArrayList<Character>();
		for (Character c : NPCs) {
			if (c.info.getCurrMap() == p.info.getCurrMap()) {
				if (Math.abs(p.info.getLoc().getX() - c.info.getLoc().getX()) <= this
						.getWidth()) {
					if (Math.abs(p.info.getLoc().getY()
							- c.info.getLoc().getY()) <= this.getHeight()) {
						inScreen.add(c);
					}
				}
			}
		}
		return inScreen;
	}

	public class invListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_M) {
				mapFrame.setVisible(!mapFrame.isVisible());
			} else if (e.getKeyCode() == KeyEvent.VK_O) {
				optFrame.setVisible(!optFrame.isVisible());
			} else if (e.getKeyCode() == KeyEvent.VK_I) {
				invFrame.setVisible(false);
			} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				Equipable eq = invData.getSelectedValue();
				JOptionPane.showMessageDialog(new JFrame(), eq.getName());
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}
	}

	public class buttonListener implements ActionListener, KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				optFrame.setVisible(false);
				running = true;
				JFrame frame = (JFrame) GamePanel.this.getTopLevelAncestor();
				frame.setFocusableWindowState(true);
				frame.setFocusable(true);
				frame.toFront();
			}

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Save Game")) {
				try {
					save(p.info);
					JOptionPane.showMessageDialog(new JFrame(),
							"Successfully Saved");
					optFrame.setVisible(!optFrame.isVisible());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (e.getActionCommand().equals("Load Game")) {
				try {
					Info o = load();
					if (o != null) {
						p.info = o;
						JOptionPane.showMessageDialog(new JFrame(),
								"Successfully Loaded");
						optFrame.setVisible(!optFrame.isVisible());
					}
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			} else if (e.getActionCommand().equals("Exit Game")) {
				System.exit(1);
			} else if (e.getActionCommand().equals("Resume Game")) {
				optFrame.setVisible(!optFrame.isVisible());
			} else if (e.getActionCommand().equals("Equip")) {
				ArrayList<Equipable> selected = (ArrayList<Equipable>) invData
						.getSelectedValuesList();
				for (Equipable eq : selected) {
					eq.equipped = true;
				}
				updateStats();
			} else if (e.getActionCommand().equals("No")){
				dialogue.dispose();
			}else if (e.getActionCommand().equals("Yes")){
				dialogue.dispose();
				shoppane = new JPanel();
				shop = new JFrame();
				shop.setContentPane(shoppane);
				JLabel text = new JLabel("Here are my wares!");
				text.setFont(myFont);
				text.setAlignmentY(CENTER_ALIGNMENT);
				shoppane.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, icon));
				shoppane.add(text);
				JLabel wares = new JLabel(new ImageIcon("Z://git//RPG//RPG - Vinay//src//tiles//Asuna.png"));
				shoppane.add(wares);
				shop.pack();
			    shop.setSize(400, 600);
			    shop.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			    shop.setVisible(true);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT
					|| e.getKeyCode() == KeyEvent.VK_D) {
				p.info.mR = false;
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT
					|| e.getKeyCode() == KeyEvent.VK_A) {
				p.info.mL = false;
			} else if (e.getKeyCode() == KeyEvent.VK_UP
					|| e.getKeyCode() == KeyEvent.VK_W) {
				p.info.mU = false;
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN
					|| e.getKeyCode() == KeyEvent.VK_S) {
				p.info.mD = false;
			} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				p.setWait(p.true_wait);
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
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
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}
	}

	/**
	 * Loads a save file
	 * 
	 * @return Info file with saved data in it
	 */
	public Info load() throws ClassNotFoundException, IOException {
		FileInputStream fin = new FileInputStream(p.info.name + ".sav");
		ObjectInputStream ois = new ObjectInputStream(fin);
		Info info = (Info) ois.readObject();
		ois.close();
		return info;
	}

	/**
	 * Saves the Info file to access later
	 * 
	 * @param Info
	 *            file to be saved
	 */
	public void save(Info o) throws IOException {
		FileOutputStream fout = new FileOutputStream(p.info.name + ".sav");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(p.info);
		oos.close();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT
				|| e.getKeyCode() == KeyEvent.VK_D) {
			if (!p.getSpeed().isZero())
				return;
			p.setSpeed(1, 0);
			p.info.mR = true;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT
				|| e.getKeyCode() == KeyEvent.VK_A) {
			if (!p.getSpeed().isZero())
				return;
			p.setSpeed(-1, 0);
			p.info.mL = true;
		} else if (e.getKeyCode() == KeyEvent.VK_UP
				|| e.getKeyCode() == KeyEvent.VK_W) {
			if (!p.getSpeed().isZero())
				return;
			p.setSpeed(0, -1);
			p.info.mU = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN
				|| e.getKeyCode() == KeyEvent.VK_S) {
			if (!p.getSpeed().isZero())
				return;
			p.setSpeed(0, 1);
			p.info.mD = true;
		} else if (e.getKeyCode() == KeyEvent.VK_I) {
			invFrame.setVisible(!invFrame.isVisible());
		} else if (e.getKeyCode() == KeyEvent.VK_M) {
			mapFrame.setVisible(!mapFrame.isVisible());
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			optFrame.setVisible(true);
			optFrame.setFocusable(true);
			running = false;
			JFrame frame = (JFrame) this.getTopLevelAncestor();
			frame.setFocusableWindowState(false);
			repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			p.setWait(0.5);
		} else if (e.getKeyCode() == KeyEvent.VK_N) {
			Equipable weapon = new Equipable("Hermy's Weeny", ItemType.TWO, 1,
					2, 3, 4, 5);
			p.Inventory.addElement(weapon);
		} else if (e.getKeyCode() == KeyEvent.VK_C) {
			statsFrame.setVisible(!statsFrame.isVisible());
		}  else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			if(facing(p)==0)
			{
				
				dialoguepane = new JPanel();
				dialogue = new JFrame();
				dialogue.setContentPane(dialoguepane);
				JLabel text = new JLabel("Greetings weary traveler, may I interest you in my wares?");
				text.setFont(myFont);
				text.setAlignmentY(CENTER_ALIGNMENT);
				dialoguepane.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, icon));
				dialoguepane.add(text);
				JButton yes = new JButton("Yes");
				yes.setFont(myFont);
				yes.addActionListener(new buttonListener());
				dialoguepane.add(yes);
				JButton no = new JButton("No");
				no.setFont(myFont);
				no.addActionListener(new buttonListener());
				dialoguepane.add(no);
				dialogue.pack();
			    dialogue.setSize(xSize, 100);
			    dialogue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			    dialogue.setVisible(true);
			}
		}
	}

	public int facing(Character p) {
		if (p.currSprite.equals(p.sprites[0][0])) {
			return 0;
		} else if (p.currSprite.equals(p.sprites[1][0])) {
			return 1;
		} else if (p.currSprite.equals(p.sprites[2][0])) {
			return 2;
		} else if (p.currSprite.equals(p.sprites[3][0])) {
			return 3;
		}
		return -1;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT
				|| e.getKeyCode() == KeyEvent.VK_D) {
			p.info.mR = false;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT
				|| e.getKeyCode() == KeyEvent.VK_A) {
			p.info.mL = false;
		} else if (e.getKeyCode() == KeyEvent.VK_UP
				|| e.getKeyCode() == KeyEvent.VK_W) {
			p.info.mU = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN
				|| e.getKeyCode() == KeyEvent.VK_S) {
			p.info.mD = false;
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			p.setWait(p.true_wait);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void run() {
		while (true) {
			if (running) {
				repaint();
				leftX = p.getX() - getWidth() / 2;
				rightX = p.getX() + getWidth() / 2;
				topY = p.getY() - getHeight() / 2;
				botY = p.getY() + getHeight() / 2;
			}
		}
	}

	public boolean hasNPC(Vector loc) {
		for (Character c : inScreen())
			if (c.info.getLoc().equals(loc)) {
				System.out.println(c.info.name);
				return true;
			}
		return false;
	}

	public int facing(Vector speed) {
		if (speed.getY() == -1)
			return 0;
		else if (speed.getX() == 1)
			return 1;
		else if (speed.getY() == 1)
			return 2;
		else
			return 3;
	}

	public void randomBattle() {
		Random rand = new Random();
		double chance = (double) (rand.nextInt(1000) + 1) / 10;
		if (chance <= 5) {
			p.setSpeed(0, 0);
			Character bear = createBear();
			Battle b = new Battle(p, bear);
			BattlePanel bp = new BattlePanel(b);
			JFrame frame = new JFrame("Battle");
			bp.setPreferredSize(new Dimension(xSize, ySize));
			// frame.setUndecorated(true);
			frame.add(bp);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setFocusable(true);
			frame.addKeyListener(bp);
			frame.pack();
			frame.setResizable(true);
			frame.setLocationRelativeTo(null);
			new Thread(bp).start();
		}
	}

	public Character createBear() {
		Character c = new Character("bear1");
		// ADD BEAR STATS HERE LATER
		return c;
	}

	public void update(Character c) throws IOException {
		if (c.getSpeed().isZero())
			return;
		c.setCurr(System.nanoTime());
		if (c.getCurr() - c.getWait() * 1000000 > c.getMoveTime()) {
			Vector newLoc = new Vector(c.info.getLoc());
			newLoc.add(c.getSpeed());
			if (c.info.mR) {
				newLoc.add(47, 0);
			}
			if (c.info.mD) {
				newLoc.add(0, 47);
			}
			Tile newTile = map.getTile(newLoc);
			if (newTile == null || !newTile.walkable() || hasNPC(newTile.getLoc())) {
				c.currSprite = c.sprites[facing(c.getSpeed())][0];
				c.setSpeed(0, 0);
				return;
			}

			Vector distanceTo = new Vector(c.info.getLoc());
			distanceTo.sub(newTile.getLoc());
			int state = (int) distanceTo.mag() / 12;
			while (state >= 4) {
				if (state > 4) {
					System.out.println(distanceTo);
					//System.out.println("shit....");
				}
				state--;
			}
			c.currSprite = c.sprites[facing(c.getSpeed())][state];
			c.info.getLoc().add(c.getSpeed());
			c.setMoveTime(c.getCurr());
			if (c.info.getLoc().getX() % 48 == 0 && c.info.getLoc().getY() % 48 == 0 && !c.moving()) {
				c.setSpeed(0, 0);
			}
			if (c.info.getLoc().getX() % 48 == 0 && c.info.getLoc().getY() % 48 == 0 && newTile instanceof Portal) {
				c.info.setLoc(((Portal) newTile).getNewLoc());
				c.info.setCurrMap(((Portal) newTile).getNewMap());
				if (drawnMaps.contains(c.info.getCurrMap())) {
					loading = true;
					map = new Map(c.info.getCurrMap(), false);
					loading = false;
				} else {
					loading = true;
					map = new Map(c.info.getCurrMap(), true);
					drawnMaps.add(c.info.getCurrMap());
					loading = false;
				}
			}
		}		
	}

	public void updateStats() {
		p.info.str = 0;
		p.info.fort = 0;
		p.info.damage = 0;
		p.info.resil = 0;
		for (int i = 0; i < p.Inventory.size(); i++) {
			Equipable e = p.Inventory.get(i);
			if (e.equipped) {
				p.info.str += e.getStr();
				p.info.fort += e.getFort();
				p.info.damage += e.getDamage();
				p.info.resil += e.getResil();
			}
		}

		str.setText(strText + p.info.str);
		fort.setText(fortText + p.info.fort);
		damage.setText(damageText + p.info.damage);
		resil.setText(resilText + p.info.resil);
		statsPane.repaint();
	}
}
