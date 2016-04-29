package brainstorm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/** BackpackFrame lets the Player equip gear.
 * Opening a BackpackFrame will deEquip all currently
 * equipped gear.
 * Regular gear is equipped with RadioButtons
 * and one-use "heal" items like Brain is used with
 * checkboxes.*/

public class BackpackFrame extends JFrame
{

    private Player player;
    private Backpack backpack;

    //This constructor needs to be long because of graphics
    @SuppressWarnings("OverlyLongMethod") public BackpackFrame(Backpack backpack, final Player player) {
	super("Choose your gear");
	this.player = player;
	this.backpack = backpack;

	this.setLayout(new BorderLayout());

	JPanel left = new JPanel();
	JPanel right = new JPanel();
	JPanel brains = new JPanel();
	JPanel gear = new JPanel();
	this.add(brains, BorderLayout.LINE_START);
	this.add(gear, BorderLayout.LINE_END);
	left.setLayout(new BorderLayout());
	right.setLayout(new BorderLayout());
	gear.add(left, BorderLayout.PAGE_START);
	gear.add(right, BorderLayout.PAGE_END);

	JPanel headgear = new JPanel(new FlowLayout());
	JPanel footgear = new JPanel(new FlowLayout());
	JPanel armour = new JPanel(new FlowLayout());
	JPanel weapons = new JPanel(new FlowLayout());

	headgear.setPreferredSize(new Dimension(150,200));
	footgear.setPreferredSize(new Dimension(150,200));
	armour.setPreferredSize(new Dimension(150,200));
	weapons.setPreferredSize(new Dimension(150,200));
	brains.setPreferredSize(new Dimension(100,100));
	gear.setPreferredSize(new Dimension(320, 420));
	this.setSize(new Dimension(420, 420));
	this.setResizable(false);

	left.add(headgear, BorderLayout.PAGE_START);
	left.add(footgear, BorderLayout.PAGE_END);
	right.add(armour, BorderLayout.PAGE_START);
	right.add(weapons, BorderLayout.PAGE_END);

	headgear.setBorder(BorderFactory.createLineBorder(Color.black));
	footgear.setBorder(BorderFactory.createLineBorder(Color.black));
	armour.setBorder(BorderFactory.createLineBorder(Color.black));
	weapons.setBorder(BorderFactory.createLineBorder(Color.black));
	brains.setBorder(BorderFactory.createLineBorder(Color.black));

	ButtonGroup head = new ButtonGroup();
	ButtonGroup foot = new ButtonGroup();
	ButtonGroup armoury = new ButtonGroup();
	ButtonGroup weaponry = new ButtonGroup();

	for (Gear g : player.getEquippedGear()) {
	    player.deEquip(g);
	}
	player.updateEquip();

	for (int r = 0; r < backpack.getBackpackSize(); r++) {
	    for (int c = 0; c < backpack.getBackpackSize(); c++) {
		if (backpack.getGear(r, c) != Gear.NOTHING) {
		    if (backpack.getGear(r, c).getType().equals("Headgear")) {
			JRadioButton gearButton = new JRadioButton(backpack.getGear(r, c).toString());
			head.add(gearButton);
			headgear.add(gearButton);
			gearButton.addActionListener(new Equipper(backpack.getGear(r, c)));
		    } else if (backpack.getGear(r, c).getType().equals("Footgear")) {
			JRadioButton gearButton = new JRadioButton(backpack.getGear(r, c).toString());
			foot.add(gearButton);
			footgear.add(gearButton);
			gearButton.addActionListener(new Equipper(backpack.getGear(r, c)));
		    } else if (backpack.getGear(r, c).getType().equals("Armour")) {
			JRadioButton gearButton = new JRadioButton(backpack.getGear(r, c).toString());
			armoury.add(gearButton);
			armour.add(gearButton);
			gearButton.addActionListener(new Equipper(backpack.getGear(r, c)));
		    } else if (backpack.getGear(r, c).getType().equals("Weapon")) {
			JRadioButton gearButton = new JRadioButton(backpack.getGear(r, c).toString());
			weaponry.add(gearButton);
			weapons.add(gearButton);
			gearButton.addActionListener(new Equipper(backpack.getGear(r, c)));
		    } else if (backpack.getGear(r, c).getType().equals("Heal")) {
			JCheckBoxMenuItem gearButton = new JCheckBoxMenuItem(backpack.getGear(r, c).toString());
			brains.add(gearButton);
			gearButton.addItemListener(new User(backpack.getGear(r, c), r, c));
		    }
		}
	    }
	}
	this.pack();
    }

    private final class Equipper implements ActionListener
    	{
    	    private Gear gear;

    	    private Equipper(Gear gear) {
    		this.gear = gear;
    	    }

    	    public void actionPerformed(ActionEvent e) {
    		player.equip(gear);
    	    }
    	}

    private final class User implements ItemListener
    {
	private Gear gear;
	private int y;
	private int x;

	private User(Gear gear, int y, int x) {
	    this.gear = gear;
	    this.y = y;
	    this.x = x;
	}

	public void itemStateChanged(ItemEvent e) {

	    if (e.getStateChange() == ItemEvent.SELECTED) {
		if (backpack.getGear(y, x) != Gear.NOTHING) {
		    backpack.remove(y, x);
		    if (gear.getType().equals("Heal")) {
			player.heal(gear.getHealth());
		    }
		}
	    }
	}
    }
}
