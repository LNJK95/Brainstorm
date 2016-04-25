package brainstorm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ArenaFrame extends JFrame
{
    private final ArenaComponent arenaComponent;
    private Backpack backpack;
    private Player player;

    public ArenaFrame(final Arena arena, final Backpack backpack, final Player player) {
	this.backpack = backpack;
	this.player = player;

	JPanel contentPane = new JPanel(new BorderLayout());
	this.add(contentPane);
	arenaComponent = new ArenaComponent(arena);
	contentPane.add(arenaComponent, BorderLayout.CENTER);


	arena.addListener(arenaComponent);
	BackpackComponent backpackComponent = new BackpackComponent(backpack);
	contentPane.add(backpackComponent, BorderLayout.LINE_END);
	backpack.addListener(backpackComponent);

	backpackComponent.addMouseListener(new BackpackClicker());


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

	class FlirtAction extends AbstractAction {
	    @Override public void actionPerformed(final ActionEvent e) {
		arena.flirt();
	    }
	}

	JButton flirt = new JButton(new FlirtAction());
	flirt.setText("Flirt ;)");
	contentPane.add(flirt, BorderLayout.PAGE_END);

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

    private class BackpackClicker implements MouseListener
	{
	public void mouseClicked (MouseEvent e) {
	    JFrame ayy = new BackpackFrame(backpack, player);
	    ayy.setVisible(true);
	    ayy.toFront();
	    ayy.setLocationRelativeTo(null);
    	}
    	public void mousePressed(MouseEvent e) {}
    	public void mouseEntered(MouseEvent e){}
    	public void mouseReleased(MouseEvent e) {}
    	public void mouseExited(MouseEvent e) {}
    }

}
