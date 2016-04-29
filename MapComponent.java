package brainstorm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/** MapComponent draws the Map. */

public class MapComponent extends JComponent implements GameListener
{
    private Map map;

    //Graphical representation of the squares on the map.
    private final static int SQUARE_SIZE = 30;
    private final static int MARGIN = 2;

    private BufferedImage house = null;
    private BufferedImage player = null;
    private BufferedImage mysteryBox = null;
    private BufferedImage grass = null;
    private BufferedImage door = null;
    private BufferedImage floor = null;

    private BufferedImage human = null;
    private BufferedImage vampire = null;
    private BufferedImage werewolf = null;
    private BufferedImage mermaid = null;
    private BufferedImage alien = null;

    public MapComponent(Map map) {
	this.map = map;
	this.setPreferredSize(getPreferredSize());

	try {
	    house = ImageIO.read(new File(getClass().getResource("resources/house2.1.png").getPath()));
	    player = ImageIO.read(new File(getClass().getResource("resources/protagonist.png").getPath()));
	    mysteryBox = ImageIO.read(new File(getClass().getResource("resources/mystery_box.png").getPath()));
	    grass = ImageIO.read(new File(getClass().getResource("resources/grass.png").getPath()));
	    door = ImageIO.read(new File(getClass().getResource("resources/door.png").getPath()));
	    floor = ImageIO.read(new File(getClass().getResource("resources/floor.png").getPath()));

	    human = ImageIO.read(new File(getClass().getResource("resources/human_mini.png").getPath()));
	    vampire = ImageIO.read(new File(getClass().getResource("resources/vampire_mini.png").getPath()));
	    werewolf = ImageIO.read(new File(getClass().getResource("resources/werewolf_mini.png").getPath()));
	    mermaid = ImageIO.read(new File(getClass().getResource("resources/mermaid_mini.png").getPath()));
	    alien = ImageIO.read(new File(getClass().getResource("resources/alien_mini.png").getPath()));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @Override protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;

	for (int r = 0; r < map.getHeight(); r++) {
	    for (int c = 0; c < map.getWidth(); c++) {
		if (!map.isInHouse()) {
		    g2d.drawImage(grass, c * SQUARE_SIZE + c * MARGIN, r * SQUARE_SIZE + r * MARGIN, null);
		}
		else if (map.isInHouse()) {
		    g2d.drawImage(floor, c * SQUARE_SIZE + c * MARGIN, r * SQUARE_SIZE + r * MARGIN, null);
		}
		if (map.getSquareType(r,c) == SquareType.HOUSE) {
		    g2d.drawImage(house, c * SQUARE_SIZE + c * MARGIN, r * SQUARE_SIZE + r * MARGIN, null);
		}
		else if (map.getSquareType(r,c) == SquareType.DOOR) {
		    g2d.drawImage(door, c * SQUARE_SIZE + c * MARGIN, r * SQUARE_SIZE + r * MARGIN, null);
		}
	    }
	}

	g2d.drawImage(player, map.getPlayerX() * SQUARE_SIZE + map.getPlayerX() * MARGIN, map.getPlayerY() * SQUARE_SIZE + map.getPlayerY() * MARGIN, null);

	for (Enemy enemy : map.getEnemies()) {
	    if (enemy.toString().equals("Human")) {
		g2d.drawImage(human, enemy.getEnemyX()*SQUARE_SIZE + enemy.getEnemyX()*MARGIN, enemy.getEnemyY()*SQUARE_SIZE + enemy.getEnemyY()*MARGIN, null);
	    }
	    else if (enemy.toString().equals("Vampire")) {
		g2d.drawImage(vampire, enemy.getEnemyX()*SQUARE_SIZE + enemy.getEnemyX()*MARGIN, enemy.getEnemyY()*SQUARE_SIZE + enemy.getEnemyY()*MARGIN, null);
	    }
	    else if (enemy.toString().equals("Werewolf")) {
		g2d.drawImage(werewolf, enemy.getEnemyX()*SQUARE_SIZE + enemy.getEnemyX()*MARGIN, enemy.getEnemyY()*SQUARE_SIZE + enemy.getEnemyY()*MARGIN, null);
	    }
	    else if (enemy.toString().equals("Mermaid")) {
		g2d.drawImage(mermaid, enemy.getEnemyX()*SQUARE_SIZE + enemy.getEnemyX()*MARGIN, enemy.getEnemyY()*SQUARE_SIZE + enemy.getEnemyY()*MARGIN, null);
	    }
	    else if (enemy.toString().equals("Alien")) {
		g2d.drawImage(alien, enemy.getEnemyX()*SQUARE_SIZE + enemy.getEnemyX()*MARGIN, enemy.getEnemyY()*SQUARE_SIZE + enemy.getEnemyY()*MARGIN, null);
	    }
	}
	g2d.drawImage(mysteryBox, map.getGearX()*SQUARE_SIZE + map.getGearX()*MARGIN, map.getGearY()*SQUARE_SIZE + map.getGearY()*MARGIN, null);
    }

    public Dimension getPreferredSize() {
	Dimension size = new Dimension(SQUARE_SIZE * map.getWidth() + (map.getWidth() - 1) * MARGIN,
				       SQUARE_SIZE * map.getHeight() + (map.getHeight() - 1) * MARGIN);
	return size;
    }

    public void changed() {
	repaint();
    }
}
