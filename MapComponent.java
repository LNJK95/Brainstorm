package brainstorm;

import javax.swing.*;
import java.awt.*;

public class MapComponent extends JComponent implements GameListener
{
    private Map map;
    private final static int SQUARE_SIZE = 30;
    private final static int MARGIN = 2;

    public MapComponent(Map map) {
	this.map = map;
	this.setPreferredSize(getPreferredSize());
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
	    }
	}

	/*String player = "\tU+1F617";
	g2d.setPaint(Color.MAGENTA);
	g2d.drawString(player, map.getPlayerX()*SQUARE_SIZE + map.getPlayerX()*MARGIN,
		       map.getPlayerY()*SQUARE_SIZE + map.getPlayerY()*MARGIN);*/
	Shape player = new Rectangle(map.getPlayerX()*SQUARE_SIZE + map.getPlayerX()*MARGIN,
				     map.getPlayerY()*SQUARE_SIZE + map.getPlayerY()*MARGIN,
				     SQUARE_SIZE, SQUARE_SIZE);
	g2d.setPaint(Color.GREEN);
	g2d.fill(player);

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
	    case GRASS: return Color.DARK_GRAY;
	    case HOUSE: return Color.RED;
	    case OUTSIDE:
	    default: return Color.BLACK;
	}
    }

    public void hasChanged() {
	repaint();
    }
}
