package main;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import tiles.Tile;


public class tester {
	public static void main(String[] args) {
		final Color grass = new Color(0, 166, 82);
		BufferedImage bi = null;
		try {
			bi=ImageIO.read(new File("Map1.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Tile[][] tiles = new Tile[bi.getWidth()][bi.getHeight()];
		Color[][] colors = new Color[bi.getWidth()][bi.getHeight()];
		for (int x = 0; x <bi.getWidth(); x++) 
		    for (int y = 0; y < bi.getHeight(); y++) {
		    	Color c = new Color(bi.getRGB(x, y));
		        colors[x][y] = c;
		        if(c.equals(grass)) {
		        	tiles[x][y] = new Tile(1, x*48, y*48, true);
		        }
		    }
		System.out.println(new Color(bi.getRGB(0, 0)));
	}
}
