package brainstorm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MapFrame extends JFrame
{
    public MapFrame(final Map map) {
	super("Brainstorm");

	final MapComponent mapComponent = new MapComponent(map);

	this.setLayout(new BorderLayout());
	this.add(mapComponent, BorderLayout.CENTER);

	map.addListener(mapComponent);

	//this.setVisible(true);
	this.pack();
	this.setLocationRelativeTo(null);

	class DownAction extends AbstractAction {
	    @Override public void actionPerformed(final ActionEvent e) {
		map.moveDown();
	    }
	}

	class UpAction extends AbstractAction {
	    @Override public void actionPerformed(final ActionEvent e) {
		map.moveUp();
	    }
	}

	class RightAction extends AbstractAction {
	    @Override public void actionPerformed(final ActionEvent e) {
		map.moveRight();
	    }
	}

	class LeftAction extends AbstractAction {
	    @Override public void actionPerformed(final ActionEvent e) {
		map.moveLeft();
	    }
	}

	class QuitAction extends AbstractAction {
	    @Override public void actionPerformed(final ActionEvent e) {
		System.exit(0);
	    }
	}

	final InputMap in = mapComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	in.put(KeyStroke.getKeyStroke("UP"), "moveUp");
	in.put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
	in.put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
	in.put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
	in.put(KeyStroke.getKeyStroke("ESCAPE"), "quit");
	final ActionMap act = mapComponent.getActionMap();
	act.put("moveUp", new UpAction());
	act.put("moveDown", new DownAction());
	act.put("moveRight", new RightAction());
	act.put("moveLeft", new LeftAction());
	act.put("quit", new QuitAction());
    }
}
