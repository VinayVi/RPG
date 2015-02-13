 package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tiles.Portal;
import tiles.Tile;

@SuppressWarnings("serial")
public class GUI extends JPanel implements Runnable, KeyListener {
	private Image image, bg;
	private Graphics second;
	private Character p;
	private ArrayList<Map> maps;
	private int leftX, rightX, topY, botY;
	JFrame mapFrame, optFrame, invFrame;
	JPanel mapPane, optPane, invPane;
	JButton load, save, exit, resume; //Options Buttons
	private Thread mover;

	public GUI() {
		Toolkit tk = Toolkit.getDefaultToolkit();  
		int xSize = ((int) tk.getScreenSize().getWidth());  
		int ySize = ((int) tk.getScreenSize().getHeight());  
		bg = null;
		maps = new ArrayList<Map>();
		maps.add(new Map(1));
		maps.add(new Map(2));
		maps.add(new Map(3));
		bg = maps.get(2).map;
		p = new Character("Kirito");
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
		optPane = new JPanel();		
		optFrame = new JFrame("Options");
		optFrame.setContentPane(optPane);
		optFrame.setSize(400, 400);
		optFrame.setVisible(false);
		optFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		optFrame.setLocationRelativeTo(null);
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
		invPane = new JPanel();
		invFrame = new JFrame("Inventory");
		invFrame.setContentPane(invPane);
		invFrame.setSize(300, 500);
		invFrame.setLocation(xSize - 300, ySize - 500);
		invFrame.setVisible(false);
		invFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		invFrame.addKeyListener(new invListener());
		invFrame.setUndecorated(true);
		mover = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					update(p);
				}
			}
		});
		mover.start();
		p.info.setCurrMap(3);
	}

	public void paint(Graphics g) {
		image = createImage(this.getWidth(), this.getHeight());
		second = image.getGraphics();
		second.setColor(Color.BLACK);
	    second.fillRect(0, 0, getWidth(), getHeight());
		second.drawImage(bg, 0, 0, getWidth(), getHeight(), leftX + 24,
				topY + 24, rightX + 24, botY + 24, this);
		second.drawImage(p.currSprite, getWidth() / 2 - 24,
				getHeight() / 2 - 24, this);		
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
		frame.setResizable(true);
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
		public void keyReleased(KeyEvent arg0) {}
		@Override
		public void keyTyped(KeyEvent arg0) {}
	}
	
	public class buttonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Save Game")) {
				try {
					save(p.info);
					JOptionPane.showMessageDialog(new JFrame(), "Successfully Saved");
					optFrame.setVisible(!optFrame.isVisible());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if(e.getActionCommand().equals("Load Game")) {
				try {
					Info o = load();
					if(o!=null) {
						p.info = o;
						JOptionPane.showMessageDialog(new JFrame(), "Successfully Loaded");
						optFrame.setVisible(!optFrame.isVisible());
						bg = maps.get(p.info.getCurrMap()-1).map;
					}
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			} else if(e.getActionCommand().equals("Exit Game")) {
				System.exit(1);
			} else if(e.getActionCommand().equals("Resume Game")) {
				optFrame.setVisible(!optFrame.isVisible());
			}	
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
		public void keyReleased(KeyEvent arg0) {}
		@Override
		public void keyTyped(KeyEvent arg0) {}
	}
	
	/**
	 * Loads a save file
	 * @return Info file with saved data in it
	 */
	public Info load() throws ClassNotFoundException, IOException {
		FileInputStream fin = new FileInputStream(p.info.name+".sav");
		ObjectInputStream ois = new ObjectInputStream(fin);
		Info info = (Info) ois.readObject();
		ois.close();
		return info;
	}

	/**
	 * Saves the Info file to access later
	 * @param Info file to be saved
	 */
	public void save(Info o) throws IOException {
		FileOutputStream fout = new FileOutputStream(p.info.name+".sav");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(p.info);
		oos.close();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			if(!p.getSpeed().isZero())
				return;
			p.setSpeed(1, 0);
			p.info.mR = true;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			if(!p.getSpeed().isZero())
				return;
			p.setSpeed(-1, 0);
			p.info.mL = true;
		} else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			if(!p.getSpeed().isZero())
				return;
			p.setSpeed(0, -1);
			p.info.mU = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			if(!p.getSpeed().isZero())
				return;
			p.setSpeed(0, 1);
			p.info.mD = true;
		} else if (e.getKeyCode() == KeyEvent.VK_I) {
			invFrame.setVisible(!invFrame.isVisible());
		} else if (e.getKeyCode() == KeyEvent.VK_M) {
			mapFrame.setVisible(!mapFrame.isVisible());
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			optFrame.setVisible(!optFrame.isVisible());
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			p.setWait(0.5);
		} else if (e.getKeyCode() == KeyEvent.VK_N){
			System.out.println(true);
			if(p.info.getCurrMap()==1)
				p.info.setCurrMap(2);
			else
				p.info.setCurrMap(1);
			p.info.setLoc(new Vector(0, 0));
			bg = maps.get(p.info.getCurrMap()-1).map;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			p.info.mR=false;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			p.info.mL=false;
		} else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			p.info.mU=false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			p.info.mD=false;
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
			repaint();
			leftX = p.getX() - getWidth() / 2;
			rightX = p.getX() + getWidth() / 2;
			topY = p.getY() - getHeight() / 2;
			botY = p.getY() + getHeight() / 2;
		}
	}
	
	public int facing(Vector speed) {
		if(speed.getY() == -1)
			return 0;
		else if(speed.getX() == 1)
			return 1;
		else if(speed.getY() == 1)
			return 2;
		else 
			return 3;  
	} 
	
	public void update(Character c) {
		if(c.getSpeed().isZero())
			return;
		c.setCurr(System.nanoTime());
		if(c.getCurr()-c.getWait()*1000000>c.getMoveTime()) {
			Vector newLoc = new Vector(c.info.getLoc());
			newLoc.add(c.getSpeed());
			if(c.info.mR) {
				newLoc.add(47, 0);
			}
			if(c.info.mD) {
				newLoc.add(0, 47);
			}
			Tile newTile = maps.get(c.info.getCurrMap()-1).getTile(newLoc);
			if(newTile == null || !newTile.walkable()) {
				c.currSprite = c.sprites[facing(c.getSpeed())][0];
				c.setSpeed(0, 0);
				return;
			}
			
			Vector distanceTo = new Vector(c.info.getLoc());
			distanceTo.sub(newTile.getLoc());
			int state = (int)distanceTo.mag()/12;
			while(state>=4) {
				if(state>4)
					System.out.println("shit....");
				state--;
			}
			c.currSprite = c.sprites[facing(c.getSpeed())][state];
			c.info.getLoc().add(c.getSpeed());
			c.setMoveTime(c.getCurr());
			if(c.info.getLoc().getX()%48==0&&c.info.getLoc().getY()%48==0&&!c.moving()) {
				c.setSpeed(0, 0);
			}
			if(c.info.getLoc().getX()%48==0&&c.info.getLoc().getY()%48==0&&newTile instanceof Portal) {
				c.info.setLoc(((Portal) newTile).getNewLoc());
				c.info.setCurrMap(((Portal) newTile).getNewMap());
				bg = maps.get(p.info.getCurrMap()-1).map;
			}
		}
	}
}
