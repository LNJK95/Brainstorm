package brainstorm;

import javax.swing.*;
import java.awt.*;

public class MapFrame extends JFrame
{
    public MapFrame(final Map map) {
	super("Brainstorm");

	final MapComponent mapComponent = new MapComponent(map);

	this.setLayout(new BorderLayout());
	this.add(mapComponent, BorderLayout.CENTER);

	this.setVisible(true);
	this.pack();
    }
}
