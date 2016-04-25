package brainstorm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ArenaComponent extends JComponent implements GameListener
{
    private Arena arena;

    private BufferedImage vampire;
    private BufferedImage mermaid;
    private BufferedImage playa;

    public ArenaComponent(final Arena arena) {
	this.arena = arena;
	this.setPreferredSize(getPreferredSize());

	try {
	    playa = ImageIO.read(new File("/home/ellka591/TDDD78/Brainstorm/src/brainstorm/playerArena.png"));
	    vampire = ImageIO.read(new File("/home/ellka591/TDDD78/Brainstorm/src/brainstorm/vampire.png"));
	    mermaid = ImageIO.read(new File("/home/ellka591/TDDD78/Brainstorm/src/brainstorm/mermaid.png"));
	} catch (IOException e) {
	    System.out.println("hittar ej bild ju");
	}
    }

    @Override protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;

	g2d.drawImage(playa, 0,0, null);

	if (arena.getEnemy().toString() == "Vampire") {
	    //g2d.drawImage(vampire, 0, 0, null);
	    g2d.drawImage(vampire, 100, 0, null);
	}
	else if (arena.getEnemy().toString() == "Mermaid") {
	    g2d.drawImage(mermaid, 100, 0, null);
	}
    }

    public Dimension getPreferredSize() {
	Dimension size = new Dimension(200, 200);
	return size;
    }

    public void hasChanged() {
	repaint();
    }
}
