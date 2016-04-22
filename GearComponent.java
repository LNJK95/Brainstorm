package brainstorm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GearComponent extends JComponent implements GameListener
{
    private static final int SQUARE_SIZE = 20;
    private static final int MARGIN = 2;

    private Gear gear;
    private int y;
    private int x;

    public GearComponent(Gear gear, int y, int x) {
	this.gear = gear;
	this.y = y;
	this.x = x;

	this.addMouseListener(new ClickPrinter());

    }

    @Override protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;

	Shape rectangle = new Rectangle(y, x, SQUARE_SIZE, SQUARE_SIZE);
	g2d.setPaint(palette(gear));
	g2d.fill(rectangle);

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

    private class ClickPrinter implements MouseListener {
    	public void mouseClicked (MouseEvent e) {
    	    System.out.println(gear.toString());
    	}

    	public void mousePressed(MouseEvent e) {}

    	public void mouseEntered(MouseEvent e){}

    	public void mouseReleased(MouseEvent e) {}

    	public void mouseExited(MouseEvent e) {}
    }

    public void hasChanged() {
	repaint();
    }
}
