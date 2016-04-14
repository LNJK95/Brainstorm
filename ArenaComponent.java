package brainstorm;

import javax.swing.*;
import java.awt.*;

public class ArenaComponent extends JComponent implements GameListener
{
    private Arena arena;

    public ArenaComponent(final Arena arena) {
	this.arena = arena;
	this.setPreferredSize(getPreferredSize());
    }

    @Override protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;

	//ugliest shit i ever did see
	String us = arena.getPlayer().toString();
	g2d.setPaint(Color.RED);
	g2d.drawString(us, 100, 100);
	String them = arena.getEnemy().toString();
	g2d.setPaint(Color.BLACK);
	g2d.drawString(them, 500, 100);

	/*Shape rectangle = new Rectangle(0, 0, 200, 300);
	g2d.setPaint(Color.BLACK);
	g2d.fill(rectangle);*/
    }

    public Dimension getPreferredSize() {
	Dimension size = new Dimension(1000, 1000);
	return size;
    }

    public void hasChanged() {
	repaint();
    }
}
