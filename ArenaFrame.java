package brainstorm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ArenaFrame extends JFrame
{
    private final ArenaComponent arenaComponent;

    public ArenaFrame(final Arena arena, final Backpack backpack) {

	JPanel contentPane = new JPanel(new BorderLayout());
	this.add(contentPane);
	arenaComponent = new ArenaComponent(arena);
	contentPane.add(arenaComponent, BorderLayout.CENTER);


	arena.addListener(arenaComponent);
	BackpackComponent backpackComponent = new BackpackComponent(backpack);
	contentPane.add(backpackComponent, BorderLayout.LINE_END);
	backpack.addListener(backpackComponent);



	class FleeAction extends AbstractAction {
	    @Override public void actionPerformed(final ActionEvent e) {
		arena.flee();
	    }
	}

	JButton flee = new JButton(new FleeAction());
	flee.setText("FLIGHT!");
	this.add(flee, BorderLayout.LINE_START);


	class FightAction extends AbstractAction {
	    @Override public void actionPerformed(final ActionEvent e) {
		arena.fight();
	    }
	}

	JButton fight = new JButton(new FightAction());
	fight.setText("FIGHT!");
	this.add(fight, BorderLayout.LINE_END);

	this.pack();
	this.setLocationRelativeTo(null);

	class QuitAction extends AbstractAction {
	    @Override public void actionPerformed(final ActionEvent e) {
			System.exit(0);
		    }
	}


	final InputMap in = arenaComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	in.put(KeyStroke.getKeyStroke("ESCAPE"), "quit");
	final ActionMap act = arenaComponent.getActionMap();
	act.put("quit", new QuitAction());
    }

    public ArenaComponent getArenaComponent() {
	return arenaComponent;
    }
}
