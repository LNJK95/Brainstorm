package brainstorm;

import javax.swing.*;
import java.awt.*;

public class ArenaFrame extends JFrame
{
    public ArenaFrame(final Arena aa) {

	final ArenaComponent arena = new ArenaComponent(aa);
	this.add(arena, BorderLayout.CENTER);

	JButton fight = new JButton("FIGHT!");
	this.add(fight, BorderLayout.LINE_END);

	JButton flee = new JButton("FLIGHT!");
	this.add(flee, BorderLayout.LINE_START);

	this.pack();
	this.setLocationRelativeTo(null);
    }
}
