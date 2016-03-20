package mapgame;

import java.awt.*;
import javax.swing.*;

public class MapComponent extends JComponent implements MapListener {
	
	private Map map;
	private final static int SQUARE_WIDTH = 30;
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
				if ((r == map.getPlayerY()) && (c == map.getPlayerX())) {
					Rectangle rect = new Rectangle(c*SQUARE_WIDTH + c*MARGIN, r*SQUARE_WIDTH + r*MARGIN, SQUARE_WIDTH, SQUARE_WIDTH);
					g2d.setPaint(Color.MAGENTA);
					g2d.fill(rect);
				}
				else {
					Rectangle rect = new Rectangle(c*SQUARE_WIDTH + c*MARGIN, r*SQUARE_WIDTH + r*MARGIN, SQUARE_WIDTH, SQUARE_WIDTH);
					g2d.setPaint(mapColor(map.getMapType(c, r)));
					g2d.fill(rect);
				}
			}
		}
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(SQUARE_WIDTH*map.getWidth() + (map.getWidth()-1)*MARGIN, SQUARE_WIDTH*map.getHeight() + (map.getHeight()-1)*MARGIN);
		return size;
	}
	
	private Color mapColor(MapType type) {
		switch(type) {
		case GRASS: return Color.GREEN;
		case ROAD: return Color.GRAY;
		case HOUSE: return Color.RED;
		default: return Color.BLACK;
		}
	}
	
	public void mapChanged() {
		repaint();
	}
}
