package brainstorm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/** BackpackComponent paints the Player's
 * Backpack in both MapFrame and ArenaFrame.*/

public class BackpackComponent extends JComponent implements GameListener
{
    //The graphical representation constants.
    private static final int SQUARE_SIZE = 30;
    private static final int MARGIN = 2;

    private Backpack backpack;

    private BufferedImage lostArm = null;
    private BufferedImage kittySlippers = null;
    private BufferedImage lipStick = null;
    private BufferedImage pancakes = null;
    private BufferedImage ovenMitt = null;
    private BufferedImage helmet = null;
    private BufferedImage glitterLotion = null;
    private BufferedImage pizzaSlicer = null;
    private BufferedImage rollerBlades = null;
    private BufferedImage skateBoard = null;
    private BufferedImage uggs = null;
    private BufferedImage leatherJacket = null;
    private BufferedImage headphones = null;
    private BufferedImage gun = null;
    private BufferedImage eggCarton = null;
    private BufferedImage chainsaw = null;
    private BufferedImage brains = null;

    public BackpackComponent(Backpack backpack) {
	this.backpack = backpack;
	this.setPreferredSize(getPreferredSize());

	try {
	    lostArm = ImageIO.read(new File(getClass().getResource("resources/lostArm.png").getPath()));
	    kittySlippers = ImageIO.read(new File(getClass().getResource("resources/kitty_slippers.png").getPath()));
	    lipStick = ImageIO.read(new File(getClass().getResource("resources/lipstick.png").getPath()));
	    pancakes = ImageIO.read(new File(getClass().getResource("resources/pancakes.png").getPath()));
	    ovenMitt = ImageIO.read(new File(getClass().getResource("resources/oven_mitt.png").getPath()));
	    helmet = ImageIO.read(new File(getClass().getResource("resources/helmet.png").getPath()));
	    glitterLotion = ImageIO.read(new File(getClass().getResource("resources/glitter_lotion.png").getPath()));
	    pizzaSlicer = ImageIO.read(new File(getClass().getResource("resources/pizza_slicer.png").getPath()));
	    rollerBlades = ImageIO.read(new File(getClass().getResource("resources/roller_blades.png").getPath()));
	    skateBoard = ImageIO.read(new File(getClass().getResource("resources/skateboard.png").getPath()));
	    uggs = ImageIO.read(new File(getClass().getResource("resources/uggs.png").getPath()));
	    leatherJacket = ImageIO.read(new File(getClass().getResource("resources/leather_jacket.png").getPath()));
	    headphones = ImageIO.read(new File(getClass().getResource("resources/headphones.png").getPath()));
	    gun = ImageIO.read(new File(getClass().getResource("resources/gun.png").getPath()));
	    eggCarton = ImageIO.read(new File(getClass().getResource("resources/egg_carton.png").getPath()));
	    chainsaw = ImageIO.read(new File(getClass().getResource("resources/chainsaw.png").getPath()));
	    brains = ImageIO.read(new File(getClass().getResource("resources/brains.png").getPath()));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public Dimension getPreferredSize() {
	Dimension dim = new Dimension(SQUARE_SIZE * backpack.getBackpackSize() + MARGIN * backpack.getBackpackSize(),
				      SQUARE_SIZE * backpack.getBackpackSize() + MARGIN * backpack.getBackpackSize());
	return dim;
    }

    @Override protected void paintComponent(Graphics g) {
   	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;

	for (int r = 0; r<backpack.getBackpackSize(); r++) {
	    for (int c = 0; c<backpack.getBackpackSize(); c++) {
		Shape rectangle = new Rectangle(c * SQUARE_SIZE + c * MARGIN,
						r * SQUARE_SIZE + r * MARGIN,
						SQUARE_SIZE, SQUARE_SIZE);
		g2d.setPaint(Color.CYAN);
		g2d.fill(rectangle);

		if (backpack.getGear(r,c) == Gear.LOST_ARM) {
		    g2d.drawImage(lostArm, c * SQUARE_SIZE + c * MARGIN, r * SQUARE_SIZE + r * MARGIN, null);
		}
		else if (backpack.getGear(r,c) == Gear.KITTY_SLIPPERS) {
		    g2d.drawImage(kittySlippers, c * SQUARE_SIZE + c * MARGIN, r * SQUARE_SIZE + r * MARGIN, null);
		}
		else if (backpack.getGear(r,c) == Gear.PANCAKES) {
		    g2d.drawImage(pancakes, c * SQUARE_SIZE + c * MARGIN, r * SQUARE_SIZE + r * MARGIN, null);
		}
		else if (backpack.getGear(r,c) == Gear.LIPSTICK) {
		    g2d.drawImage(lipStick, c * SQUARE_SIZE + c * MARGIN, r * SQUARE_SIZE + r * MARGIN, null);
		}
		else if (backpack.getGear(r,c) == Gear.OVEN_MITT) {
		    g2d.drawImage(ovenMitt, c * SQUARE_SIZE + c * MARGIN, r * SQUARE_SIZE + r * MARGIN, null);
		}
		else if (backpack.getGear(r,c) == Gear.HELMET) {
		    g2d.drawImage(helmet, c * SQUARE_SIZE + c * MARGIN, r * SQUARE_SIZE + r * MARGIN, null);
		}
		else if (backpack.getGear(r,c) == Gear.GLITTER_LOTION) {
		    g2d.drawImage(glitterLotion, c * SQUARE_SIZE + c * MARGIN, r * SQUARE_SIZE + r * MARGIN, null);
		}
		else if (backpack.getGear(r,c) == Gear.PIZZA_SLICER) {
		    g2d.drawImage(pizzaSlicer, c * SQUARE_SIZE + c * MARGIN, r * SQUARE_SIZE + r * MARGIN, null);
		}
		else if (backpack.getGear(r,c) == Gear.ROLLER_BLADES) {
		    g2d.drawImage(rollerBlades, c * SQUARE_SIZE + c * MARGIN, r * SQUARE_SIZE + r * MARGIN, null);
		}
		else if (backpack.getGear(r,c) == Gear.SKATEBOARD) {
		    g2d.drawImage(skateBoard, c * SQUARE_SIZE + c * MARGIN, r * SQUARE_SIZE + r * MARGIN, null);
		}
		else if (backpack.getGear(r,c) == Gear.UGGS) {
		    g2d.drawImage(uggs, c * SQUARE_SIZE + c * MARGIN, r * SQUARE_SIZE + r * MARGIN, null);
		}
		else if (backpack.getGear(r,c) == Gear.LEATHER_JACKET) {
		    g2d.drawImage(leatherJacket, c * SQUARE_SIZE + c * MARGIN, r * SQUARE_SIZE + r * MARGIN, null);
		}
		else if (backpack.getGear(r,c) == Gear.HEADPHONES) {
		    g2d.drawImage(headphones, c * SQUARE_SIZE + c * MARGIN, r * SQUARE_SIZE + r * MARGIN, null);
		}
		else if (backpack.getGear(r,c) == Gear.GUN) {
		    g2d.drawImage(gun, c * SQUARE_SIZE + c * MARGIN, r * SQUARE_SIZE + r * MARGIN, null);
		}
		else if (backpack.getGear(r,c) == Gear.EGG_CARTON) {
		    g2d.drawImage(eggCarton, c * SQUARE_SIZE + c * MARGIN, r * SQUARE_SIZE + r * MARGIN, null);
		}
		else if (backpack.getGear(r,c) == Gear.CHAINSAW) {
		    g2d.drawImage(chainsaw, c * SQUARE_SIZE + c * MARGIN, r * SQUARE_SIZE + r * MARGIN, null);
		}
		else if (backpack.getGear(r,c) == Gear.BRAINS) {
		    g2d.drawImage(brains, c * SQUARE_SIZE + c * MARGIN, r * SQUARE_SIZE + r * MARGIN, null);
		}
	    }
	}
    }

    public void changed() {
	repaint();
    }
}