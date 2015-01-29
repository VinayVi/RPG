 package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tiles.Tile;

public class GUI extends JPanel implements Runnable, KeyListener {
	private Image image, bg;
	private Graphics second;
	private Character p;
	private Map map;
	private int leftX, rightX, topY, botY, length, width;
	JFrame mapFrame, optFrame, invFrame;
	JButton inventory;
	JPanel mapPane, optPane, invPane;
	GridLayout invLayout=new GridLayout(4, 8, 5, 5);
	boolean close = false;
	JButton load, save, exit, resume; //Options Buttons

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
		} catch (IOException e) {}
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
		invFrame.setLayout(invLayout);
		//Displaying Inventory
		
		inventory=new JButton();
		JButton inventoryyy = new JButton();
		inventoryyy.setText("Sharp Twig");
		String temp = "";
		for(int x=0;x<p.Inventory.size();x++)
		{
			temp+=p.Inventory.get(x).getName()+"\n";
		}
		inventory.setText(temp);
		invFrame.add(inventory);

		
	}

	public void paint(Graphics g) {
		image = createImage(this.getWidth(), this.getHeight());
		second = image.getGraphics();
		second.drawImage(bg, 0, 0, getWidth(), getHeight(), leftX + 1024,
				topY + 1024, rightX + 1024, botY + 1024, this);
		second.drawImage(p.currSprite, getWidth() / 2 - 24,
				getHeight() / 2 - 24, this);

		second.setColor(Color.black);
		
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
			int x = p.getX() + 48;
			int y = p.getY();
			if(x%48!=0) {
				x+=48;
			}
			Tile t = map.getTile(x, y);
			p.move(t);
			p.dir=2;
			p.info.mR = true;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			int x = p.getX() - 48;
			int y = p.getY();
			Tile t = map.getTile(x, y);
			p.move(t);
			p.dir=4;
			p.info.mL = true;
		} else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			int x = p.getX();
			int y = p.getY() - 48;
			Tile t = map.getTile(x, y);
			p.move(t);
			p.dir=1;
			p.info.mU = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			int x = p.getX();
			int y = p.getY() + 48;
			if(y%48!=0) {
				y+=48;
			}
			Tile t = map.getTile(x, y);
			p.move(t);
			p.dir=3;
			p.info.mD = true;
		} else if (e.getKeyCode() == KeyEvent.VK_I) {
			invFrame.setVisible(!invFrame.isVisible());
		} else if (e.getKeyCode() == KeyEvent.VK_M) {
			mapFrame.setVisible(!mapFrame.isVisible());
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			optFrame.setVisible(!optFrame.isVisible());
		} 
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			p.dir=0;
			p.info.mR = false;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			p.dir=0;
			p.info.mL = false;
		} else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			p.dir=0;
			p.info.mU = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			p.dir=0;
			p.info.mD = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}

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
			} catch (InterruptedException e) {}
		}
	}

}
