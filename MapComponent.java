package brainstorm;

import javax.swing.*;
import java.awt.*;

public class MapComponent extends JComponent
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
		Rectangle rect = new Rectangle(c * SQUARE_SIZE + c * MARGIN, r * SQUARE_SIZE + r * MARGIN, SQUARE_SIZE,
					       SQUARE_SIZE);
		g2d.setPaint(mapColor(map.getSquareType(r, c)));
		g2d.fill(rect);
	    }
	}
    }

    public Dimension getPreferredSize() {
	Dimension size = new Dimension(SQUARE_SIZE * map.getWidth() + (map.getWidth() - 1) * MARGIN,
				       SQUARE_SIZE * map.getHeight() + (map.getHeight() - 1) * MARGIN);
	return size;
    }

    private Color mapColor(SquareType type) {
    		switch(type) {
    		case GRASS: return Color.GREEN;
    		case HOUSE: return Color.RED;
    		default: return Color.BLACK;
    		}
    	}
}
