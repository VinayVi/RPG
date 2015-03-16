package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JFrame;

import tiles.Portal;
import tiles.Tile;

public class Map {
	int tileSize = 48;
	Tile tiles[][];
	int length, width;
	BufferedImage map;
	public Map(int num, boolean draw) throws IOException {
		final Color grass = new Color(0, 166, 81);
		final Color road = new Color(226, 174, 127);
		final Color water = new Color(0, 0, 255);
		final Color bridge = new Color(64, 20, 0);
		final Color sand = new Color(253, 198, 137);
		final Color cactus = new Color(0, 88, 38);
		final Color treeBot = new Color(96, 57, 19);
		final Color treeTop = new Color(0, 114, 54);
		final Color spookyGrass = new Color(0, 89, 82);
		final Color spookyTree1 = new Color(55, 53, 53);
		final Color spookyTree2 = new Color(112, 51, 9);
		final Color spookyRoad = new Color(91, 82, 81);
		final Color GraveyardFence = new Color(0, 0, 80);
		final Color Gravestone = new Color(79, 79, 79);
		final Color GravestoneVinay = new Color(80, 79, 79);
		final Color GravestoneConnor = new Color(79, 80, 79);
		final Color GravestoneHermy = new Color(79, 79, 80);
		final Color GravestoneDaniel = new Color(80, 80, 80);
		final Color HouseBotLeft = new Color(145, 61, 0);
		final Color HouseBotMid = new Color(145, 62, 0);
		final Color HouseBotRight = new Color(145, 63, 0);
		final Color HouseTopLeft = new Color(145, 64, 0);
		final Color HouseTopMid = new Color(145, 65, 0);
		final Color HouseTopRight = new Color(145, 66, 0);
		final Color MarketTile = new Color(161, 229, 255);
		final Color MarketCarpet = new Color(136, 37, 37);
		final Color MarketPlant = new Color(141, 198, 63);
		final Color MarketCounter1 = new Color(27, 20, 100);
		final Color MarketCounter2 = new Color(48, 37, 171);
		final Color MarketCounter3 = new Color(72, 59, 205);
		final Color MarketCounter4 = new Color(76, 66, 195);
		final Color Snow = new Color(255, 255, 255);
		final Color SnowRoad = new Color(194, 194, 194);
		final Color Ice = new Color(109, 207, 246);
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(new File("src//tiles//Map" + num + ".gif"));
			System.out.println(num);
		} catch (IOException e) {
			System.out.println(num);
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
					tiles[x][y] = new Tile(2, x * 48, y * 48, true);
				} else if (c.equals(water)) {
					tiles[x][y] = new Tile(18, x * 48, y * 48, false);
				} else if (c.equals(bridge)) {
					tiles[x][y] = new Tile(19, x * 48, y * 48, true);
				} else if (c.equals(sand)) {
					tiles[x][y] = new Tile(33, x * 48, y * 48, true);
				} else if (c.equals(cactus)) {
					tiles[x][y] = new Tile(34, x * 48, y * 48, false);
				} else if (c.equals(treeBot)) {
					tiles[x][y] = new Tile(16, x * 48, y * 48, false);
				} else if (c.equals(treeTop)) {
					tiles[x][y] = new Tile(17, x * 48, y * 48, false);
				} else if (c.equals(spookyGrass)) {
					tiles[x][y] = new Tile(35, x * 48, y * 48, true);
				} else if (c.equals(spookyTree1)) {
					tiles[x][y] = new Tile(36, x * 48, y * 48, false);
				} else if (c.equals(spookyTree2)) {
					tiles[x][y] = new Tile(37, x * 48, y * 48, false);
				} else if (c.equals(spookyRoad)) {
					tiles[x][y] = new Tile(38, x * 48, y * 48, true);
				} else if (c.equals(GraveyardFence)) {
					tiles[x][y] = new Tile(39, x * 48, y * 48, false);
				} else if (c.equals(Gravestone)) {
					tiles[x][y] = new Tile(40, x * 48, y * 48, false);
				} else if (c.equals(GravestoneVinay)) {
					tiles[x][y] = new Tile(41, x * 48, y * 48, false);
				} else if (c.equals(GravestoneConnor)) {
					tiles[x][y] = new Tile(42, x * 48, y * 48, false);
				} else if (c.equals(GravestoneHermy)) {
					tiles[x][y] = new Tile(43, x * 48, y * 48, false);
				} else if (c.equals(GravestoneDaniel)) {
					tiles[x][y] = new Tile(44, x * 48, y * 48, false);
				} else if (c.equals(HouseBotLeft)) {
					tiles[x][y] = new Tile(201, x * 48, y * 48, false);
				} else if (c.equals(HouseBotMid)) {
					tiles[x][y] = new Tile(202, x * 48, y * 48, false);
				} else if (c.equals(HouseBotRight)) {
					tiles[x][y] = new Tile(203, x * 48, y * 48, false);
				} else if (c.equals(HouseTopLeft)) {
					tiles[x][y] = new Tile(204, x * 48, y * 48, false);
				} else if (c.equals(HouseTopMid)) {
					tiles[x][y] = new Tile(205, x * 48, y * 48, false);
				} else if (c.equals(HouseTopRight)) {
					tiles[x][y] = new Tile(206, x * 48, y * 48, false);
				} else if (c.equals(MarketTile)) {
					tiles[x][y] = new Tile(207, x * 48, y * 48, true);
				} else if (c.equals(MarketCarpet)) {
					tiles[x][y] = new Tile(208, x * 48, y * 48, true);
				} else if (c.equals(MarketPlant)) {
					tiles[x][y] = new Tile(209, x * 48, y * 48, false);
				} else if (c.equals(MarketCounter1)) {
					tiles[x][y] = new Tile(210, x * 48, y * 48, false);
				} else if (c.equals(MarketCounter2)) {
					tiles[x][y] = new Tile(211, x * 48, y * 48, false);
				} else if (c.equals(MarketCounter3)) {
					tiles[x][y] = new Tile(212, x * 48, y * 48, false);
				} else if (c.equals(MarketCounter4)) {
					tiles[x][y] = new Tile(213, x * 48, y * 48, false);
				} else if (c.equals(Snow)) {
					tiles[x][y] = new Tile(45, x * 48, y * 48, true);
				} else if (c.equals(SnowRoad)) {
					tiles[x][y] = new Tile(46, x * 48, y * 48, true);
				} else if (c.equals(Ice)) {
					tiles[x][y] = new Tile(47, x * 48, y * 48, true);
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
		// Portal Locations
		if (num == 1) {
			// To Desert
			tiles[103][0] = new Portal(103 * 48, 0, true, new Vector(99 * 48,
					149 * 48), 2);
			tiles[102][0] = new Portal(102 * 48, 0, true, new Vector(98 * 48,
					149 * 48), 2);
			tiles[101][0] = new Portal(101 * 48, 0, true, new Vector(97 * 48,
					149 * 48), 2);
			tiles[100][0] = new Portal(100 * 48, 0, true, new Vector(96 * 48,
					149 * 48), 2);
			tiles[103][0].setType(20);
			tiles[102][0].setType(20);
			tiles[101][0].setType(20);
			tiles[100][0].setType(20);
			tiles[3][3] = new Portal(3 * 48, 3 * 48, true, new Vector(4 * 48,
					149 * 48), 5);
			tiles[149][80] = new Portal(149 * 48, 80 * 48, true, new Vector(0,
					125 * 48), 3);
			tiles[149][81] = new Portal(149 * 48, 81 * 48, true, new Vector(0,
					126 * 48), 3);
			tiles[149][82] = new Portal(149 * 48, 82 * 48, true, new Vector(0,
					127 * 48), 3);
			tiles[149][83] = new Portal(149 * 48, 83 * 48, true, new Vector(0,
					128 * 48), 3);
			tiles[15][15] = new Portal(15 * 48, 15 * 48, true, new Vector(
					2 * 48, 5 * 48), 5);

			tiles[149][80] = new Portal(149 * 48, 80 * 48, true, new Vector(0,
					125 * 48), 3);
			tiles[149][81] = new Portal(149 * 48, 81 * 48, true, new Vector(0,
					126 * 48), 3);
			tiles[149][82] = new Portal(149 * 48, 82 * 48, true, new Vector(0,
					127 * 48), 3);
			tiles[149][83] = new Portal(149 * 48, 83 * 48, true, new Vector(0,
					128 * 48), 3);
			tiles[15][15] = new Portal(15 * 48, 15 * 48, true, new Vector(
					2 * 48, 5 * 48), 101);
			tiles[15][15].setType(202);
		} else if (num == 2) {
			// To Grassland
			tiles[99][149] = new Portal(99 * 48, 149 * 48, true, new Vector(
					103 * 48, 0), 1);
			tiles[98][149] = new Portal(98 * 48, 149 * 48, true, new Vector(
					102 * 48, 0), 1);
			tiles[97][149] = new Portal(97 * 48, 149 * 48, true, new Vector(
					101 * 48, 0), 1);
			tiles[96][149] = new Portal(96 * 48, 149 * 48, true, new Vector(
					100 * 48, 0), 1);
			tiles[99][149].setType(20);
			tiles[98][149].setType(20);
			tiles[97][149].setType(20);
			tiles[96][149].setType(20);
		} else if (num == 3) {
			tiles[0][125] = new Portal(0, 125 * 48, true, new Vector(149 * 48,
					80 * 48), 1);
			tiles[0][126] = new Portal(0, 126 * 48, true, new Vector(149 * 48,
					81 * 48), 1);
			tiles[0][127] = new Portal(0, 127 * 48, true, new Vector(149 * 48,
					82 * 48), 1);
			tiles[0][128] = new Portal(0, 128 * 48, true, new Vector(149 * 48,
					83 * 48), 1);
		} else if (num == 101) {
			tiles[2][5] = new Portal(2 * 48, 5 * 48, true, new Vector(15 * 48,
					16 * 48), 1);
			tiles[2][5].setType(208);
			tiles[3][5] = new Portal(3 * 48, 5 * 48, true, new Vector(15 * 48,
					16 * 48), 1);
			tiles[3][5].setType(208);
		}
		length = bi.getWidth() * tileSize;
		width = bi.getHeight() * tileSize;
		long deltaT = System.currentTimeMillis();
		if(draw)
			map = drawMap(num);
		else 
			map = ImageIO.read(new File("src//tiles//map" + num + ".png"));
		System.out.println(deltaT-System.currentTimeMillis());
		System.out.println(System.currentTimeMillis());
	}

	public Tile getTile(Vector v) {
		return getTile(v.getX(), v.getY());
	}

	public Tile getTile(int x, int y) {
		if (x >= 0 && y >= 0 && x < width && y < length) {
			Tile t = tiles[x / 48][y / 48];
			return t;
		} else
			return null;
	}

	public BufferedImage drawMap(int num) {
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
		Image TreeTop = null;
		Image TreeBot = null;
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
		Image Sand = null;
		Image Cactus = null;
		Image SpookyGrass = null;
		Image SpookyTree1 = null;
		Image SpookyTree2 = null;
		Image SpookyRoad = null;
		Image GraveyardFence = null;
		Image Gravestone = null;
		Image GravestoneVinay = null;
		Image GravestoneConnor = null;
		Image GravestoneHermy = null;
		Image GravestoneDaniel = null;
		Image HouseBotLeft = null;
		Image HouseBotMid = null;
		Image HouseBotRight = null;
		Image HouseTopLeft = null;
		Image HouseTopMid = null;
		Image HouseTopRight = null;
		Image MarketTile = null;
		Image MarketCarpet = null;
		Image MarketPlant = null;
		Image MarketCounter1 = null;
		Image MarketCounter2 = null;
		Image MarketCounter3 = null;
		Image MarketCounter4 = null;
		Image Snow = null;
		Image SnowRoad = null;
		Image Ice = null;

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
			TreeTop = ImageIO
					.read(new File("src//tiles//Tree2 Part 1 Tile.png"));
			TreeBot = ImageIO
					.read(new File("src//tiles//Tree2 Part 2 Tile.png"));
			Water = ImageIO.read(new File("src//tiles//Water.png"));
			Bridge = ImageIO.read(new File("src//tiles//Bridge Tile.png"));
			DirtRoad = ImageIO.read(new File("src//tiles//Dirt Road.png"));
			DirtRoadGT = ImageIO
					.read(new File("src//tiles//Dirt Road Top.png"));
			DirtRoadGB = ImageIO.read(new File(
					"src//tiles//Dirt Road Bottom.png"));
			DirtRoadGR = ImageIO.read(new File(
					"src//tiles//Dirt Road Right.png"));
			DirtRoadGL = ImageIO
					.read(new File("src//tiles//Dirt Road Left.png"));
			DirtRoadGTL = ImageIO.read(new File(
					"src//tiles//Dirt Road TopLeft.png"));
			DirtRoadGTR = ImageIO.read(new File(
					"src//tiles//Dirt Road TopRight.png"));
			DirtRoadGBL = ImageIO.read(new File(
					"src//tiles//Dirt Road BottomLeft.png"));
			DirtRoadGBR = ImageIO.read(new File(
					"src//tiles//Dirt Road BottomRight.png"));
			Sand = ImageIO.read(new File("src//tiles//Sand Tile.png"));
			Cactus = ImageIO.read(new File("src//tiles//Cactus.png"));
			Bear = ImageIO.read(new File("src//tiles//Bear.png"));
			Wolf = ImageIO.read(new File("src//tiles//Wolf.png"));
			SpookyGrass = ImageIO
					.read(new File("src//tiles//Spooky Grass.png"));
			SpookyTree1 = ImageIO.read(new File("src//tiles//SpookyTree1.png"));
			SpookyTree2 = ImageIO.read(new File("src//tiles//SpookyTree2.png"));
			SpookyRoad = ImageIO.read(new File("src//tiles//Spooky Road.png"));
			GraveyardFence = ImageIO.read(new File(
					"src//tiles//GraveyardFence.png"));
			Gravestone = ImageIO.read(new File("src//tiles//Gravestone.png"));
			GravestoneVinay = ImageIO.read(new File(
					"src//tiles//Vinay Gravestone.png"));
			GravestoneConnor = ImageIO.read(new File(
					"src//tiles//Connor Gravestone.png"));
			GravestoneHermy = ImageIO.read(new File(
					"src//tiles//Eric Gravestone.png"));
			GravestoneDaniel = ImageIO.read(new File(
					"src//tiles//Daniel Gravestone.png"));
			HouseBotLeft = ImageIO
					.read(new File("src//tiles//HouseBotLeft.png"));
			HouseBotMid = ImageIO.read(new File(
					"src//tiles//HouseBotMiddle.png"));
			HouseBotRight = ImageIO.read(new File(
					"src//tiles//HouseBotRight.png"));
			HouseTopLeft = ImageIO
					.read(new File("src//tiles//HouseTopLeft.png"));
			HouseTopMid = ImageIO.read(new File(
					"src//tiles//HouseTopMiddle.png"));
			HouseTopRight = ImageIO.read(new File(
					"src//tiles//HouseTopRight.png"));
			MarketTile = ImageIO.read(new File("src//tiles//MarketTile.png"));
			MarketCarpet = ImageIO.read(new File("src//tiles//CarpetBGMT.png"));
			MarketPlant = ImageIO.read(new File(
					"src//tiles//PottedBushBGMT.png"));
			MarketCounter1 = ImageIO.read(new File(
					"src//tiles//CounterFront.png"));
			MarketCounter2 = ImageIO.read(new File(
					"src//tiles//CounterCorner.png"));
			MarketCounter3 = ImageIO.read(new File(
					"src//tiles//CounterSide.png"));
			MarketCounter4 = ImageIO.read(new File(
					"src//tiles//CounterCorner2.png"));
			GravestoneVinay = ImageIO.read(new File(
					"src//tiles//Vinay Gravestone.png"));
			GravestoneConnor = ImageIO.read(new File(
					"src//tiles//Connor Gravestone.png"));
			GravestoneHermy = ImageIO.read(new File(
					"src//tiles//Eric Gravestone.png"));
			GravestoneDaniel = ImageIO.read(new File(
					"src//tiles//Daniel Gravestone.png"));
			Snow = ImageIO.read(new File("src//tiles//SnowTile.png"));
			SnowRoad = ImageIO.read(new File("src//tiles//SnowRoad3.png"));
			Ice = ImageIO.read(new File("src//tiles//IceTile.png"));
			MarketPlant = ImageIO.read(new File(
					"src//tiles//PottedBushBGMT.png"));
			MarketCounter1 = ImageIO.read(new File(
					"src//tiles//CounterFront.png"));
			MarketCounter2 = ImageIO.read(new File(
					"src//tiles//CounterCorner.png"));
			MarketCounter3 = ImageIO.read(new File(
					"src//tiles//CounterSide.png"));
			MarketCounter4 = ImageIO.read(new File(
					"src//tiles//CounterCorner2.png"));
			GravestoneVinay = ImageIO.read(new File(
					"src//tiles//Vinay Gravestone.png"));
			GravestoneConnor = ImageIO.read(new File(
					"src//tiles//Connor Gravestone.png"));
			GravestoneHermy = ImageIO.read(new File(
					"src//tiles//Eric Gravestone.png"));
			GravestoneDaniel = ImageIO.read(new File(
					"src//tiles//Daniel Gravestone.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Tile[] t1 : tiles) {
			for (Tile t : t1) {
				int type = t.getType();
				Vector v = t.getLoc();
				switch (type) {
				case 0:
					break;
				case 1:
					g.drawImage(grass, v.getX(), v.getY(), null);
					break;
				case 2:
					g.drawImage(Road, v.getX(), v.getY(), null);
					break;
				case 3:
					g.drawImage(RoadGR, v.getX(), v.getY(), null);
					break;
				case 4:
					g.drawImage(RoadGT, v.getX(), v.getY(), null);
					break;
				case 5:
					g.drawImage(RoadGL, v.getX(), v.getY(), null);
					break;
				case 6:
					g.drawImage(RoadGB, v.getX(), v.getY(), null);
					break;
				case 7:
					g.drawImage(RoadGTL, v.getX(), v.getY(), null);
					break;
				case 8:
					g.drawImage(RoadGTR, v.getX(), v.getY(), null);
					break;
				case 9:
					g.drawImage(RoadGBL, v.getX(), v.getY(), null);
					break;
				case 10:
					g.drawImage(RoadGBR, v.getX(), v.getY(), null);
					break;
				case 16:
					g.drawImage(TreeTop, v.getX(), v.getY(), null);
					break;
				case 17:
					g.drawImage(TreeBot, v.getX(), v.getY(), null);
					break;
				case 18:
					g.drawImage(Water, v.getX(), v.getY(), null);
					break;
				case 19:
					g.drawImage(Bridge, v.getX(), v.getY(), null);
					break;
				case 20:
					g.drawImage(DirtRoad, v.getX(), v.getY(), null);
					break;
				case 21:
					g.drawImage(DirtRoadGR, v.getX(), v.getY(), null);
					break;
				case 22:
					g.drawImage(DirtRoadGT, v.getX(), v.getY(), null);
					break;
				case 23:
					g.drawImage(DirtRoadGL, v.getX(), v.getY(), null);
					break;
				case 24:
					g.drawImage(DirtRoadGB, v.getX(), v.getY(), null);
					break;
				case 25:
					g.drawImage(DirtRoadGTL, v.getX(), v.getY(), null);
					break;
				case 26:
					g.drawImage(DirtRoadGTR, v.getX(), v.getY(), null);
					break;
				case 27:
					g.drawImage(DirtRoadGBL, v.getX(), v.getY(), null);
					break;
				case 28:
					g.drawImage(DirtRoadGBR, v.getX(), v.getY(), null);
					break;
				case 33:
					g.drawImage(Sand, v.getX(), v.getY(), null);
					break;
				case 34:
					g.drawImage(Cactus, v.getX(), v.getY(), null);
					break;
				case 35:
					g.drawImage(SpookyGrass, v.getX(), v.getY(), null);
					break;
				case 36:
					g.drawImage(SpookyTree1, v.getX(), v.getY(), null);
					break;
				case 37:
					g.drawImage(SpookyTree2, v.getX(), v.getY(), null);
					break;
				case 38:
					g.drawImage(SpookyRoad, v.getX(), v.getY(), null);
					break;
				case 39:
					g.drawImage(GraveyardFence, v.getX(), v.getY(), null);
					break;
				case 40:
					g.drawImage(Gravestone, v.getX(), v.getY(), null);
					break;
				case 41:
					g.drawImage(GravestoneVinay, v.getX(), v.getY(), null);
					break;
				case 42:
					g.drawImage(GravestoneConnor, v.getX(), v.getY(), null);
					break;
				case 43:
					g.drawImage(GravestoneHermy, v.getX(), v.getY(), null);
					break;
				case 44:
					g.drawImage(GravestoneDaniel, v.getX(), v.getY(), null);
					break;
				case 45:
					g.drawImage(Snow, v.getX(), v.getY(), null);
					break;
				case 46:
					g.drawImage(SnowRoad, v.getX(), v.getY(), null);
					break;
				case 47:
					g.drawImage(Ice, v.getX(), v.getY(), null);
					break;
				case 101:
					g.drawImage(Bear, v.getX(), v.getY(), null);
					break;
				case 102:
					g.drawImage(Wolf, v.getX(), v.getY(), null);
					break;
				case 201:
					g.drawImage(HouseBotLeft, v.getX(), v.getY(), new JFrame());
					break;
				case 202:
					g.drawImage(HouseBotMid, v.getX(), v.getY(), new JFrame());
					break;
				case 203:
					g.drawImage(HouseBotRight, v.getX(), v.getY(), new JFrame());
					break;
				case 204:
					g.drawImage(HouseTopLeft, v.getX(), v.getY(), new JFrame());
					break;
				case 205:
					g.drawImage(HouseTopMid, v.getX(), v.getY(), new JFrame());
					break;
				case 206:
					g.drawImage(HouseTopRight, v.getX(), v.getY(), new JFrame());
					break;
				case 207:
					g.drawImage(MarketTile, v.getX(), v.getY(), new JFrame());
					break;
				case 208:
					g.drawImage(MarketCarpet, v.getX(), v.getY(), new JFrame());
					break;
				case 209:
					g.drawImage(MarketPlant, v.getX(), v.getY(), new JFrame());
					break;
				case 210:
					g.drawImage(MarketCounter1, v.getX(), v.getY(),
							new JFrame());
					break;
				case 211:
					g.drawImage(MarketCounter2, v.getX(), v.getY(),
							new JFrame());
					break;
				case 212:
					g.drawImage(MarketCounter3, v.getX(), v.getY(),
							new JFrame());
					break;
				case 213:
					g.drawImage(MarketCounter4, v.getX(), v.getY(),
							new JFrame());
					break;
				}
			}
		}
		try {
			java.util.Iterator<ImageWriter> writers = ImageIO
					.getImageWritersByFormatName("png");
			ImageWriter writer = writers.next();
			File f = new File("src//tiles//map" + num + ".png");
			ImageOutputStream ios = ImageIO.createImageOutputStream(f);
			writer.setOutput(ios);
			writer.write(pic);
			ios.close();
		} catch (Exception e) {
			System.out.println("Failed");
		}
		return pic;

	}
}
