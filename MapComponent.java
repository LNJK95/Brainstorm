package brainstorm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapComponent extends JComponent implements GameListener
{
    private Map map;
    private final static int SQUARE_SIZE = 30;
    private final static int MARGIN = 2;

    private BufferedImage house;
    private BufferedImage playa;

    public MapComponent(Map map) {
	this.map = map;
	this.setPreferredSize(getPreferredSize());

	try {
	    house = ImageIO.read(new File("/home/ellka591/TDDD78/Brainstorm/src/brainstorm/house2.jpg"));
	} catch (IOException e) {
	    System.out.println("hittar ej bild ju");
	}
	try {
	    playa = ImageIO.read(new File("/home/ellka591/TDDD78/Brainstorm/src/brainstorm/protagonist.png"));
	} catch (IOException e) {
	    System.out.println("hittar ej bild ju");
	}
    }


    @Override protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;

	for (int r = 0; r < map.getHeight(); r++) {
	    for (int c = 0; c < map.getWidth(); c++) {
		Shape rectangle = new Rectangle(c * SQUARE_SIZE + c * MARGIN,
						r * SQUARE_SIZE + r * MARGIN,
						SQUARE_SIZE, SQUARE_SIZE);
		g2d.setPaint(mapColor(map.getSquareType(r, c)));
		g2d.fill(rectangle);

		if (map.getSquareType(r,c) == SquareType.HOUSE) {
		    g2d.drawImage(house, c * SQUARE_SIZE + c * MARGIN, r * SQUARE_SIZE + r * MARGIN, null);
		}
	    }
	}

	/*Shape player = new Rectangle(map.getPlayerX()*SQUARE_SIZE + map.getPlayerX()*MARGIN,
				     map.getPlayerY()*SQUARE_SIZE + map.getPlayerY()*MARGIN,
				     SQUARE_SIZE, SQUARE_SIZE);
	g2d.setPaint(Color.GREEN);
	g2d.fill(player);
	*/

	g2d.drawImage(playa, map.getPlayerX() * SQUARE_SIZE + map.getPlayerX() * MARGIN, map.getPlayerY() * SQUARE_SIZE + map.getPlayerY() * MARGIN, null);

	for (Enemy enemy : map.getEnemies()) {
	    Shape thisEnemy = new Rectangle(enemy.getEnemyX()*SQUARE_SIZE + enemy.getEnemyX()*MARGIN,
					 enemy.getEnemyY()*SQUARE_SIZE + enemy.getEnemyY()*MARGIN,
					 SQUARE_SIZE, SQUARE_SIZE);
	    g2d.setPaint(Color.RED);
	    g2d.fill(thisEnemy);
	}

	Shape gear = new Rectangle(map.getGearX()*SQUARE_SIZE + map.getGearX()*MARGIN,
				   map.getGearY()*SQUARE_SIZE + map.getGearY()*MARGIN,
				   SQUARE_SIZE, SQUARE_SIZE);
	g2d.setPaint(Color.PINK);
	g2d.fill(gear);

    }

    public Dimension getPreferredSize() {
	Dimension size = new Dimension(SQUARE_SIZE * map.getWidth() + (map.getWidth() - 1) * MARGIN,
				       SQUARE_SIZE * map.getHeight() + (map.getHeight() - 1) * MARGIN);
	return size;
    }

    private Paint mapColor(SquareType type) {
	switch(type) {
	    case GROUND: return Color.DARK_GRAY;
	    case HOUSE: return Color.WHITE;
	    case DOOR: return Color.BLUE;
	    case OUTSIDE:
	    default: return Color.BLACK;
	}
    }


    public void hasChanged() {
	repaint();
    }

    private class ImageComponent extends JComponent {
   	BufferedImage img;

   	public ImageComponent(BufferedImage img) {
   	    this.img = img;
   	}

   	@Override public void paintComponent(Graphics g) {
   	    super.paintComponent(g);
   	    final Graphics2D g2d = (Graphics2D) g;

   	    g2d.drawImage(img, 0, 0, null);
   	}
    }
}
