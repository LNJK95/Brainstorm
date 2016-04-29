package brainstorm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/** ArenaComponent draws the arena. It draws
 * the player, the current ally and the current enemy.*/

public class ArenaComponent extends JComponent implements GameListener
{
    /* These are the X-coordinates for the graphic representation
     * in the arena. The player is in the middle, the ally is to the left
     * and the enemy is to the right.*/
    private static final int PLAYER_X_LOCATION = 100;
    private static final int ALLY_X_LOCATION = 0;
    private static final int ENEMY_X_LOCATION = 200;

    private Arena arena;

    private BufferedImage vampire = null;
    private BufferedImage mermaid = null;
    private BufferedImage human = null;
    private BufferedImage alien = null;
    private BufferedImage werewolf = null;
    private BufferedImage player = null;

    public ArenaComponent(final Arena arena) {
	this.arena = arena;
	this.setPreferredSize(getPreferredSize());

	try {
	    player = ImageIO.read(new File(getClass().getResource("resources/playerArena.png").getPath()));
	    vampire = ImageIO.read(new File(getClass().getResource("resources/vampire.png").getPath()));
	    mermaid = ImageIO.read(new File(getClass().getResource("resources/mermaid.png").getPath()));
	    human = ImageIO.read(new File(getClass().getResource("resources/human.png").getPath()));
	    alien = ImageIO.read(new File(getClass().getResource("resources/alien.png").getPath()));
	    werewolf = ImageIO.read(new File(getClass().getResource("resources/werewolf.png").getPath()));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @Override protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;

	g2d.drawImage(player, PLAYER_X_LOCATION, 0, null);

	if (arena.getPlayer().getAlly().toString().equals("Vampire")) {
	    g2d.drawImage(vampire, ALLY_X_LOCATION, 0, null);
	}
	else if (arena.getPlayer().getAlly().toString().equals("Mermaid")) {
	    g2d.drawImage(mermaid, ALLY_X_LOCATION, 0, null);
	}
	else if (arena.getPlayer().getAlly().toString().equals("Human")) {
	    g2d.drawImage(human, ALLY_X_LOCATION, 0, null);
	}
	else if (arena.getPlayer().getAlly().toString().equals("Alien")) {
	    g2d.drawImage(alien, ALLY_X_LOCATION, 0, null);
	}
	else if (arena.getPlayer().getAlly().toString().equals("Werewolf")) {
	    g2d.drawImage(werewolf, ALLY_X_LOCATION, 0, null);
	}

	if (arena.getEnemy().toString().equals("Vampire")) {
	    //g2d.drawImage(vampire, 0, 0, null);
	    g2d.drawImage(vampire, ENEMY_X_LOCATION, 0, null);
	}
	else if (arena.getEnemy().toString().equals("Mermaid")) {
	    g2d.drawImage(mermaid, ENEMY_X_LOCATION, 0, null);
	}
	else if (arena.getEnemy().toString().equals("Human")) {
	    g2d.drawImage(human, ENEMY_X_LOCATION, 0, null);
	}
	else if (arena.getEnemy().toString().equals("Alien")) {
	    g2d.drawImage(alien, ENEMY_X_LOCATION, 0, null);
	}
	else if (arena.getEnemy().toString().equals("Werewolf")) {
	    g2d.drawImage(werewolf, ENEMY_X_LOCATION, 0, null);
	}
    }

    public Dimension getPreferredSize() {
	Dimension size = new Dimension(300, 200);
	return size;
    }

    public void changed() {
	repaint();
    }
}
