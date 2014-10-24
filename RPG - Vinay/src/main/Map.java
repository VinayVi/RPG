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
	int length = 1200, width = 1200;

	public Map() {
		tiles = new Tile[length / tileSize][width / tileSize];
		for (int x = 0; x < 25; x++) {
			for (int y = 0; y < 25; y++) {
				if (x == 12) {
					tiles[x][y] = new Tile(5, x * 48, y * 48, true);
				} else if (x == 13) {
					tiles[x][y] = new Tile(3, x * 48, y * 48, true);
				} else
					tiles[x][y] = new Tile(1, x * 48, y * 48, true);
			}
		}

		drawMap();
	}

	public Tile getTile(int x, int y) {
		if (x >= 0 && y >= 0 && x < width && y < length)
			return tiles[x / 48][y / 48];
		else
			return null;
	}

	public void drawMap() {
		BufferedImage pic = new BufferedImage(4800, 4800,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = pic.createGraphics();
		Image grass = null;
		Image RoadGR = null;
		Image RoadGL = null;
		try {
			grass = ImageIO.read(new File("src//tiles//GrassTile.png"));
			RoadGR = ImageIO.read(new File(
					"src//tiles//Grass-Road Tile Right.png"));
			RoadGL = ImageIO.read(new File(
					"src//tiles//Grass-Road Tile Left.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Tile[] t1 : tiles) {
			for (Tile t : t1) {
				int type = t.getType();
				Vector v = t.getLoc();
				switch (type) {
				case 1:
					g.drawImage(grass, v.getX(), v.getY(), new JFrame());
					break;
				case 3:
					g.drawImage(RoadGR, v.getX(), v.getY(), new JFrame());
					break;
				case 5:
					g.drawImage(RoadGL, v.getX(), v.getY(), new JFrame());
					break;
				}
			}
		}
		try {
			java.util.Iterator<ImageWriter> writers = ImageIO
					.getImageWritersByFormatName("png");
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
