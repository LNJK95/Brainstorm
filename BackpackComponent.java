package brainstorm;

import javax.swing.*;
import java.awt.*;

public class BackpackComponent extends JComponent implements GameListener
{
    private static final int SQUARE_SIZE = 30;
    private static final int MARGIN = 2;

    private Backpack backpack;

    public BackpackComponent(Backpack backpack) {
	this.backpack = backpack;
	this.setPreferredSize(getPreferredSize());
    }

    public Dimension getPreferredSize() {
	Dimension dim = new Dimension(SQUARE_SIZE * backpack.getSize() + MARGIN * backpack.getSize(),
				      SQUARE_SIZE * backpack.getSize() + MARGIN * backpack.getSize());
	return dim;
    }

    @Override protected void paintComponent(Graphics g) {
   	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;

	for (int r = 0; r<backpack.getSize(); r++) {
	    for (int c = 0; c<backpack.getSize(); c++) {
		Shape rectangle = new Rectangle(c * SQUARE_SIZE + c * MARGIN,
						r * SQUARE_SIZE + r * MARGIN,
						SQUARE_SIZE, SQUARE_SIZE);
		g2d.setPaint(palette(backpack.getGear(r, c)));
		g2d.fill(rectangle);
	    }
	}
    }

    public Paint palette(Gear gear) {

	switch(gear) {
	    case BRAINS:
		return Color.PINK;
	    case BOMB:
		return Color.RED;
	    case HEADPHONES:
	    case PANCAKES:
	    case LOST_ARM:
	    case ROLLER_BLADES:
	    case PIZZA_SLICER:
	    case SKATEBOARD:
	    case GUN:
	    case CHAINSAW:
	    case EGG_CARTON:
	    case UGGS:
	    case LEATHER_JACKET:
		return Color.BLUE;
	    case KITTY_SLIPPERS:
	    case LIPSTICK:
	    case OVEN_MITT:
	    case HELMET:
	    case GLITTER_LOTION:
		return Color.YELLOW;
	    case NOTHING:
		return Color.GREEN;
	    default:
		return Color.BLACK;
	}
    }

    public void hasChanged() {
	repaint();
    }
}