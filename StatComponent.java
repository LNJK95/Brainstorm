package brainstorm;

import javax.swing.*;
import java.awt.*;

/** Shows the stats in map.*/

public class StatComponent extends JComponent implements GameListener
{
    //Graphic representation for the stats.
    private static final int ROW_SIZE = 25;
    private static final int ROWS = 6;
    private static final int ROW_X = 10;

    private Player player;

    public StatComponent(Player player) {
	this.player = player;
    }

    @Override protected void paintComponent(Graphics g) {
	super.paintComponent(g);

	this.setLayout(new FlowLayout());

	String level = "Level: " + player.getLevel();
	String health = "Health: " + player.getHealth();
	String attack = "Attack: " + player.getAttack();
	String defence = "Defence: " + player.getDefence();
	String speed = "Speed: " + player.getSpeed();
	String ally = "Ally: " + player.getAlly();

	g.drawString(level, ROW_X, ROW_SIZE);
	g.drawString(health, ROW_X, ROW_SIZE * 2);
	g.drawString(attack, ROW_X, ROW_SIZE * 3);
	g.drawString(defence, ROW_X, ROW_SIZE * 4);
	g.drawString(speed, ROW_X, ROW_SIZE * 5);
	g.drawString(ally, ROW_X, ROW_SIZE * 6);

	this.setSize(new Dimension(120, ROW_SIZE*ROWS+ROW_SIZE));
    }

    public void changed() {
	repaint();
    }
}
