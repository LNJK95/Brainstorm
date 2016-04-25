package brainstorm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackpackComponent extends JComponent implements GameListener
{
    private static final int SQUARE_SIZE = 30;
    private static final int MARGIN = 2;

    private Backpack backpack;

    private BufferedImage lostArm;
    private BufferedImage kittySlippers;
    private BufferedImage lipStick;
    private BufferedImage pancakes;
    private BufferedImage ovenMitt;
    private BufferedImage helmet;
    private BufferedImage glitterLotion;
    private BufferedImage pizzaSlicer;
    private BufferedImage rollerBlades;
    private BufferedImage skateBoard;
    private BufferedImage uggs;
    private BufferedImage leatherJacket;
    private BufferedImage headphones;
    private BufferedImage gun;
    private BufferedImage eggCarton;
    private BufferedImage chainsaw;
    private BufferedImage brains;

    public BackpackComponent(Backpack backpack) {
	this.backpack = backpack;
	this.setPreferredSize(getPreferredSize());

	try {
	    lostArm = ImageIO.read(new File("/home/ellka591/TDDD78/Brainstorm/src/brainstorm/lostArm.png"));
	    kittySlippers = ImageIO.read(new File("/home/ellka591/TDDD78/Brainstorm/src/brainstorm/kitty_slippers.png"));
	    lipStick = ImageIO.read(new File("/home/ellka591/TDDD78/Brainstorm/src/brainstorm/lipstick.png"));
	    pancakes = ImageIO.read(new File("/home/ellka591/TDDD78/Brainstorm/src/brainstorm/pancakes.png"));
	    ovenMitt = ImageIO.read(new File("/home/ellka591/TDDD78/Brainstorm/src/brainstorm/oven_mitt.png"));
	    helmet = ImageIO.read(new File("/home/ellka591/TDDD78/Brainstorm/src/brainstorm/helmet.png"));
	    glitterLotion = ImageIO.read(new File("/home/ellka591/TDDD78/Brainstorm/src/brainstorm/glitter lotion.png"));
	    pizzaSlicer = ImageIO.read(new File("/home/ellka591/TDDD78/Brainstorm/src/brainstorm/pizza_slicer.png"));
	    rollerBlades = ImageIO.read(new File("/home/ellka591/TDDD78/Brainstorm/src/brainstorm/roller blades.png"));
	    skateBoard = ImageIO.read(new File("/home/ellka591/TDDD78/Brainstorm/src/brainstorm/skateboard.png"));
	    uggs = ImageIO.read(new File("/home/ellka591/TDDD78/Brainstorm/src/brainstorm/uggs.png"));
	    leatherJacket = ImageIO.read(new File("/home/ellka591/TDDD78/Brainstorm/src/brainstorm/leather_jacket.png"));
	    headphones = ImageIO.read(new File("/home/ellka591/TDDD78/Brainstorm/src/brainstorm/headphones.png"));
	    gun = ImageIO.read(new File("/home/ellka591/TDDD78/Brainstorm/src/brainstorm/gun.png"));
	    eggCarton = ImageIO.read(new File("/home/ellka591/TDDD78/Brainstorm/src/brainstorm/egg_carton.png"));
	    chainsaw = ImageIO.read(new File("/home/ellka591/TDDD78/Brainstorm/src/brainstorm/chainsaw.png"));
	    brains = ImageIO.read(new File("/home/ellka591/TDDD78/Brainstorm/src/brainstorm/brains.png"));
	} catch (IOException e) {
	    System.out.println("hittar ej bild ju");
	}
    }

    public Dimension getPreferredSize() {
	Dimension dim = new Dimension(SQUARE_SIZE * backpack.getSize() + MARGIN * backpack.getSize(),
				      SQUARE_SIZE * backpack.getSize() + MARGIN * backpack.getSize());
	return dim;
    }

    @Override protected void paintComponent(Graphics g) {
   	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;

	for (int r = 0; r<backpack.getSize(); r++) {
	    for (int c = 0; c<backpack.getSize(); c++) {
		Shape rectangle = new Rectangle(c * SQUARE_SIZE + c * MARGIN,
						r * SQUARE_SIZE + r * MARGIN,
						SQUARE_SIZE, SQUARE_SIZE);
		g2d.setPaint(palette(backpack.getGear(r, c)));
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

    public Paint palette(Gear gear) {

	switch(gear) {
	    case BRAINS:
		return Color.PINK;
	    case HEADPHONES:
	    case PANCAKES:
	    case LOST_ARM:
	    case ROLLER_BLADES:
	    case PIZZA_SLICER:
	    case SKATEBOARD:
	    case GUN:
	    case CHAINSAW:
	    case EGG_CARTON:
	    case UGGS:
	    case LEATHER_JACKET:
		return Color.BLUE;
	    case KITTY_SLIPPERS:
	    case LIPSTICK:
	    case OVEN_MITT:
	    case HELMET:
	    case GLITTER_LOTION:
		return Color.YELLOW;
	    case NOTHING:
		return Color.GREEN;
	    default:
		return Color.BLACK;
	}
    }

    public void hasChanged() {
	repaint();
    }

    private class ImageComponent extends JComponent {
       	BufferedImage img;

       	public ImageComponent(BufferedImage img) {
       	    this.img = img;
       	}

       	@Override public void paintComponent(Graphics g) {
       	    super.paintComponent(g);
       	    final Graphics2D g2d = (Graphics2D) g;

       	    g2d.drawImage(img, 0, 0, null);
       	}
    }
}