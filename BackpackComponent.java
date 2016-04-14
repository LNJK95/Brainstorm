package brainstorm;

import javax.swing.*;
import java.awt.*;

public class BackpackComponent extends JComponent
{
    public BackpackComponent() {
	this.setPreferredSize(new Dimension(100, 100));

    }

    @Override protected void paintComponent(Graphics g) {
   	super.paintComponent(g);
    }
}
