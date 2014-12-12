package main;

import java.awt.Color;
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
	int length, width;

	public Map() {
		final Color grass = new Color(0, 166, 81);
		final Color road = new Color(226, 174, 127);
		final Color water = new Color(0, 0, 255);
		final Color bridge = new Color(64, 20, 0);
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(new File("src//tiles//Map1.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tiles = new Tile[bi.getWidth()][bi.getHeight()];
		Color[][] colors = new Color[bi.getWidth()][bi.getHeight()];
		for (int x = 0; x < bi.getWidth(); x++)
			for (int y = 0; y < bi.getHeight(); y++) {
				Color c = new Color(bi.getRGB(x, y));
				colors[x][y] = c;
				if (c.equals(grass)) {
					tiles[x][y] = new Tile(1, x * 48, y * 48, true);
				} else if (c.equals(road)) {
					tiles[x][y] = new Tile(20, x * 48, y * 48, true);
				} else if (c.equals(water)) {
					tiles[x][y] = new Tile(18, x * 48, y * 48, false);
				} else if (c.equals(bridge)) {
					tiles[x][y] = new Tile(19, x * 48, y * 48, true);
				} else {
					tiles[x][y] = new Tile(3, x * 48, y * 48, false);
				}

			}
		for (int x = 1; x < bi.getWidth() - 1; x++) {
			for (int y = 1; y < bi.getHeight() - 1; y++) {
				if (tiles[x][y].getType() == 20) {
					boolean topright = (tiles[x + 1][y - 1].getType() == 1);
					boolean top = (tiles[x][y - 1].getType() == 1);
					boolean topleft = (tiles[x - 1][y - 1].getType() == 1);
					boolean left = (tiles[x - 1][y].getType() == 1);
					boolean bottomleft = (tiles[x - 1][y + 1].getType() == 1);
					boolean bottom = (tiles[x][y + 1].getType() == 1);
					boolean bottomright = (tiles[x + 1][y + 1].getType() == 1);
					boolean right = (tiles[x + 1][y].getType() == 1);
					if (topright && top && right) {
						tiles[x][y] = new Tile(26, x * 48, y * 48, true);
					} else if (right && bottomright && bottom) {
						tiles[x][y] = new Tile(28, x * 48, y * 48, true);
					} else if (top && topleft && left) {
						tiles[x][y] = new Tile(25, x * 48, y * 48, true);
					} else if (left && bottomleft && bottom) {
						tiles[x][y] = new Tile(27, x * 48, y * 48, true);
					} else if (top) {
						tiles[x][y] = new Tile(22, x * 48, y * 48, true);
					} else if (right) {
						tiles[x][y] = new Tile(21, x * 48, y * 48, true);
					} else if (left) {
						tiles[x][y] = new Tile(23, x * 48, y * 48, true);
					} else if (bottom) {
						tiles[x][y] = new Tile(24, x * 48, y * 48, true);
					}
				}
			}
		}
		length = bi.getWidth() * tileSize;
		width = bi.getHeight() * tileSize;
		drawMap();
	}

	public Tile getTile(int x, int y) {
		if (x >= 0 && y >= 0 && x < width && y < length) {
			Tile t = tiles[x/48][y/48];
			return t;
		}
		else
			return null;
	}

	public void drawMap() {
		BufferedImage pic = new BufferedImage(length, width,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = pic.createGraphics();
		Image grass = null;
		Image Road = null;
		Image RoadGT = null;
		Image RoadGB = null;
		Image RoadGR = null;
		Image RoadGL = null;
		Image RoadGTL = null;
		Image RoadGTR = null;
		Image RoadGBL = null;
		Image RoadGBR = null;
		Image Water = null;
		Image Bridge = null;
		Image Bear = null;
		Image Wolf = null;
		Image DirtRoad = null;
		Image DirtRoadGT = null;
		Image DirtRoadGB = null;
		Image DirtRoadGR = null;
		Image DirtRoadGL = null;
		Image DirtRoadGTR = null;
		Image DirtRoadGTL = null;
		Image DirtRoadGBR = null;
		Image DirtRoadGBL = null;
		try {
			grass = ImageIO.read(new File("src//tiles//GrassTile.png"));
			Road = ImageIO.read(new File("src//tiles//RoadTile.png"));
			RoadGT = ImageIO.read(new File(
					"src//tiles//Grass-Road Tile Top.png"));
			RoadGB = ImageIO.read(new File(
					"src//tiles//Grass-Road Tile Bottom.png"));
			RoadGR = ImageIO.read(new File(
					"src//tiles//Grass-Road Tile Right.png"));
			RoadGL = ImageIO.read(new File(
					"src//tiles//Grass-Road Tile Left.png"));
			RoadGTL = ImageIO.read(new File(
					"src//tiles//Grass-Road Tile TopLeft.png"));
			RoadGTR = ImageIO.read(new File(
					"src//tiles//Grass-Road Tile TopRight.png"));
			RoadGBL = ImageIO.read(new File(
					"src//tiles//Grass-Road Tile BottomLeft.png"));
			RoadGBR = ImageIO.read(new File(
					"src//tiles//Grass-Road Tile BottomRight.png"));
			Water = ImageIO.read(new File("src//tiles//Water.png"));
			Bridge = ImageIO.read(new File("src//tiles//Bridge Tile.png"));
			DirtRoad = ImageIO.read(new File("src//tiles//Dirt Road.png"));
			DirtRoadGT = ImageIO.read(new File(
					"src//tiles//Dirt Road Top.png"));
			DirtRoadGB = ImageIO.read(new File(
					"src//tiles//Dirt Road Bottom.png"));
			DirtRoadGR = ImageIO.read(new File(
					"src//tiles//Dirt Road Right.png"));
			DirtRoadGL = ImageIO.read(new File(
					"src//tiles//Dirt Road Left.png"));
			DirtRoadGTL = ImageIO.read(new File(
					"src//tiles//Dirt Road TopLeft.png"));
			DirtRoadGTR = ImageIO.read(new File(
					"src//tiles//Dirt Road TopRight.png"));
			DirtRoadGBL = ImageIO.read(new File(
					"src//tiles//Dirt Road BottomLeft.png"));
			DirtRoadGBR = ImageIO.read(new File(
					"src//tiles//Dirt Road BottomRight.png"));
			Bear = ImageIO.read(new File("src//tiles//Bear.png"));
			Wolf = ImageIO.read(new File("src//tiles//Wolf.png"));

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
				case 2:
					g.drawImage(Road, v.getX(), v.getY(), new JFrame());
					break;
				case 3:
					g.drawImage(RoadGR, v.getX(), v.getY(), new JFrame());
					break;
				case 4:
					g.drawImage(RoadGT, v.getX(), v.getY(), new JFrame());
					break;
				case 5:
					g.drawImage(RoadGL, v.getX(), v.getY(), new JFrame());
					break;
				case 6:
					g.drawImage(RoadGB, v.getX(), v.getY(), new JFrame());
					break;
				case 7:
					g.drawImage(RoadGTL, v.getX(), v.getY(), new JFrame());
					break;
				case 8:
					g.drawImage(RoadGTR, v.getX(), v.getY(), new JFrame());
					break;
				case 9:
					g.drawImage(RoadGBL, v.getX(), v.getY(), new JFrame());
					break;
				case 10:
					g.drawImage(RoadGBR, v.getX(), v.getY(), new JFrame());
					break;
				case 18:
					g.drawImage(Water, v.getX(), v.getY(), new JFrame());
					break;
				case 19:
					g.drawImage(Bridge, v.getX(), v.getY(), new JFrame());
					break;
				case 20:
					g.drawImage(DirtRoad, v.getX(), v.getY(), new JFrame());
					break;
				case 21:
					g.drawImage(DirtRoadGR, v.getX(), v.getY(), new JFrame());
					break;
				case 22:
					g.drawImage(DirtRoadGT, v.getX(), v.getY(), new JFrame());
					break;
				case 23:
					g.drawImage(DirtRoadGL, v.getX(), v.getY(), new JFrame());
					break;
				case 24:
					g.drawImage(DirtRoadGB, v.getX(), v.getY(), new JFrame());
					break;
				case 25:
					g.drawImage(DirtRoadGTL, v.getX(), v.getY(), new JFrame());
					break;
				case 26:
					g.drawImage(DirtRoadGTR, v.getX(), v.getY(), new JFrame());
					break;
				case 27:
					g.drawImage(DirtRoadGBL, v.getX(), v.getY(), new JFrame());
					break;
				case 28:
					g.drawImage(DirtRoadGBR, v.getX(), v.getY(), new JFrame());
					break;
				case 101:
					g.drawImage(Bear, v.getX(), v.getY(), new JFrame());
					break;
				case 102:
					g.drawImage(Wolf, v.getX(), v.getY(), new JFrame());
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
