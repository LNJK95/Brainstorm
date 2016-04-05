package brainstorm;

import javax.swing.*;
import java.awt.*;

public class ArenaComponent extends JComponent
{
    public ArenaComponent(final Arena arena) {
	this.setPreferredSize(getPreferredSize());
    }

    @Override protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;
	Shape rectangle = new Rectangle(0, 0, 200, 300);
	g2d.setPaint(Color.BLACK);
	g2d.fill(rectangle);
    }

    public Dimension getPreferredSize() {
	Dimension size = new Dimension(200, 300);
	return size;
    }
}
