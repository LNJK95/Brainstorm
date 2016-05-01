package brainstorm;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/** MapFrame contains a MapComponent and a BackpackComponent.
 * Clicking the BackpackComponent will open a BackpackFrame.*/

public class MapFrame extends JFrame
{
    private Backpack backpack;
    private Player player;

    public MapFrame(final Map map, final Backpack backpack, final Player player) {
	super("Brainstorm: Map");
	this.backpack = backpack;
	this.player = player;

	final MapComponent mapComponent = new MapComponent(map);
	StatComponent statComponent = new StatComponent(player);

	JPanel contentPane = new JPanel(new BorderLayout());
	JPanel backpackPane = new JPanel(new BorderLayout());
	JPanel statPane = new JPanel(new BorderLayout());

	JSplitPane slicePane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, backpackPane, statPane);
	JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, contentPane, slicePane);
	this.add(splitPane);

	BackpackComponent backpackComponent = new BackpackComponent(backpack);

	contentPane.add(mapComponent, BorderLayout.LINE_START);
	backpackPane.add(backpackComponent, BorderLayout.PAGE_START);

	contentPane.setMinimumSize(contentPane.getPreferredSize());
	contentPane.setMaximumSize(contentPane.getPreferredSize());

	backpackPane.setMinimumSize(backpackPane.getPreferredSize());
	backpackPane.setMaximumSize(backpackPane.getPreferredSize());

	statPane.setMinimumSize(new Dimension(backpackPane.getWidth(), 200));

	backpackComponent.addMouseListener(new BackpackClicker());

	statPane.add(statComponent, BorderLayout.PAGE_START);


	map.addListener(mapComponent);
	backpack.addListener(backpackComponent);
	player.addListener(statComponent);

	this.pack();
	this.setResizable(false);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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

    private class BackpackClicker extends MouseAdapter
    {
	public void mouseClicked (MouseEvent e) {
	    BackpackFrame backpackFrame = new BackpackFrame(backpack, player);
	    backpackFrame.setVisible(true);
	    backpackFrame.toFront();
	    backpackFrame.setLocationRelativeTo(null);
    	}
    }


}
