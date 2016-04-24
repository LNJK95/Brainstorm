package brainstorm;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MapFrame extends JFrame
{

    private static final int SQUARE_SIZE = 20;
    private static final int MARGIN = 2;
    private Backpack backpack;
    private Player player;

    public MapFrame(final Map map, final Backpack backpack, final Player player) {
	super("Brainstorm");
	this.backpack = backpack;
	this.player = player;

	final MapComponent mapComponent = new MapComponent(map);
	JPanel contentPane = new JPanel(new BorderLayout());
	JPanel backpackPane = new JPanel(new BorderLayout());

	JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, contentPane, backpackPane);
	this.add(splitPane);

	//this.setLayout(new BorderLayout());
	BackpackComponent backpackComponent = new BackpackComponent(backpack);
	contentPane.add(mapComponent, BorderLayout.LINE_START);
	backpackPane.add(backpackComponent, BorderLayout.PAGE_START);

	contentPane.setMinimumSize(contentPane.getPreferredSize());
	contentPane.setMaximumSize(contentPane.getPreferredSize());

	backpackPane.setMinimumSize(backpackPane.getPreferredSize());
	backpackPane.setMaximumSize(backpackPane.getPreferredSize());


	backpackComponent.addMouseListener(new BackpackClicker());

	map.addListener(mapComponent);
	backpack.addListener(backpackComponent);

	//this.setVisible(true);
	this.pack();
	this.setResizable(false);
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

    private class BackpackClicker implements MouseListener {
	public void mouseClicked (MouseEvent e) {
	    JFrame ayy = new BackpackFrame(backpack, player);
	    ayy.setSize(new Dimension(200, 200));
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
