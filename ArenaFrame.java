package brainstorm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/** ArenaFrame is the frame used to contain ArenaComponent
 *  and a BackpackComponent. It also contains three buttons
 *  (fight, flight, flirt) that calls methods in Arena.
 *  Clicking the BackpackComponent will open up a BackpackFrame.*/

public class ArenaFrame extends JFrame
{
    private Backpack backpack;
    private Player player;

    public ArenaFrame(final Arena arena, final Backpack backpack, final Player player) {
	super("Brainstorm: Arena");
	this.backpack = backpack;
	this.player = player;

	JPanel contentPane = new JPanel(new BorderLayout());
	final ArenaComponent arenaComponent = new ArenaComponent(arena);
	BackpackComponent backpackComponent = new BackpackComponent(backpack);

	this.add(contentPane);
	contentPane.add(arenaComponent, BorderLayout.CENTER);
	contentPane.add(backpackComponent, BorderLayout.LINE_END);

	arena.addListener(arenaComponent);
	backpack.addListener(backpackComponent);
	backpackComponent.addMouseListener(new BackpackClicker());

	class FleeAction extends AbstractAction {
	    @Override public void actionPerformed(final ActionEvent e) {
		arena.flee();
	    }
	}
	class FightAction extends AbstractAction {
	    @Override public void actionPerformed(final ActionEvent e) {
		arena.fight();
	    }
	}
	class FlirtAction extends AbstractAction {
	    @Override public void actionPerformed(final ActionEvent e) {
		arena.flirt();
	    }
	}
	class QuitAction extends AbstractAction {
	    @Override public void actionPerformed(final ActionEvent e) {
		System.exit(0);
	    }
	}

	JButton flee = new JButton(new FleeAction());
	flee.setText("FLIGHT!");
	this.add(flee, BorderLayout.LINE_START);

	JButton fight = new JButton(new FightAction());
	fight.setText("FIGHT!");
	this.add(fight, BorderLayout.LINE_END);

	JButton flirt = new JButton(new FlirtAction());
	flirt.setText("Flirt ;)");
	contentPane.add(flirt, BorderLayout.PAGE_END);

	final InputMap in = arenaComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	in.put(KeyStroke.getKeyStroke("ESCAPE"), "quit");
	final ActionMap act = arenaComponent.getActionMap();
	act.put("quit", new QuitAction());

	this.pack();
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private class BackpackClicker extends MouseAdapter
    {
	public void mouseClicked (MouseEvent e) {
	    JFrame backpackFrame = new BackpackFrame(backpack, player);
	    backpackFrame.setVisible(true);
	    backpackFrame.toFront();
	    backpackFrame.setLocationRelativeTo(null);
    	}
    }
}
