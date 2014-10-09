package main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JFrame;

import tiles.Tile;


public class Map {
	int tileSize = 48;
	Tile tiles[][];
	int length = 4800, width = 4800;
	
	public Map() {
		tiles = new Tile[length/tileSize][width/tileSize];
		//Grass
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				Tile tile = new Tile(1, i*48, j*48, true);
				tiles[i][j] = tile;
				
			}
		}
		drawMap();
	}
	
	public Tile getTile(int x, int y) {
		if(x>=0 && y>=0 && x<width && y<length)
			return tiles[x/100][y/100];
		else return null;
	}
	
	public void drawMap() {
		BufferedImage pic = new BufferedImage(4800, 4800, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = pic.createGraphics();
		Image grass = null;
		try {
			grass = ImageIO.read(new File("src//tiles//GrassTile.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Tile[] t1 : tiles){ 
			for(Tile t : t1) {
				int type = t.getType();
				Vector v = t.getLoc();
				switch(type) {
				case 1: g.drawImage(grass, v.getX(), v.getY(), new JFrame());
				break;
				}
			}
		}
		try {
			java.util.Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("png");
			ImageWriter writer = writers.next();

			File f = new File("src//tiles//map.png");

			ImageOutputStream ios = ImageIO.createImageOutputStream(f);
			writer.setOutput(ios);
			writer.write(pic);
			ios.close();
		} catch (Exception e) {
			System.out.println("Failed");
		}
			
	}
	
}
