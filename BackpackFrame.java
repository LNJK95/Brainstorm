package brainstorm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class BackpackFrame extends JFrame
{

    private Player player;
    private Backpack backpack;

    public BackpackFrame(Backpack backpack, final Player player) {
	super("Choose your gear");
	this.player = player;
	this.backpack = backpack;

	this.setLayout(new BorderLayout());

	JPanel left = new JPanel();
	JPanel right = new JPanel();
	JPanel brainz = new JPanel();
	JPanel gear = new JPanel();
	this.add(brainz, BorderLayout.LINE_START);
	left.setLayout(new BorderLayout());
	this.add(gear, BorderLayout.LINE_END);
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
	brainz.setPreferredSize(new Dimension(100,100));
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
	brainz.setBorder(BorderFactory.createLineBorder(Color.black));


	ButtonGroup head = new ButtonGroup();
	ButtonGroup foot = new ButtonGroup();
	ButtonGroup arm = new ButtonGroup();
	ButtonGroup wep = new ButtonGroup();

	for (int r = 0; r < backpack.getSize(); r++) {
	    for (int c = 0; c < backpack.getSize(); c++) {
		if (backpack.getGear(r, c) != Gear.NOTHING) {
		    if (backpack.getGear(r, c).getType() == "Headgear") {
			JRadioButton abc = new JRadioButton(backpack.getGear(r, c).toString());
			head.add(abc);
			headgear.add(abc);
			abc.addActionListener(new Equipper(backpack.getGear(r, c)));
		    } else if (backpack.getGear(r, c).getType() == "Footgear") {
			JRadioButton abc = new JRadioButton(backpack.getGear(r, c).toString());
			foot.add(abc);
			footgear.add(abc);
			abc.addActionListener(new Equipper(backpack.getGear(r, c)));
		    } else if (backpack.getGear(r, c).getType() == "Armour") {
			JRadioButton abc = new JRadioButton(backpack.getGear(r, c).toString());
			arm.add(abc);
			armour.add(abc);
			abc.addActionListener(new Equipper(backpack.getGear(r, c)));
		    } else if (backpack.getGear(r, c).getType() == "Weapon") {
			JRadioButton abc = new JRadioButton(backpack.getGear(r, c).toString());
			wep.add(abc);
			weapons.add(abc);
			abc.addActionListener(new Equipper(backpack.getGear(r, c)));
		    } else if (backpack.getGear(r, c).getType() == "Heal" || backpack.getGear(r, c).getType() == "Attack") {
			JCheckBoxMenuItem abc = new JCheckBoxMenuItem(backpack.getGear(r, c).toString());
			brainz.add(abc);
			abc.addItemListener(new User(backpack.getGear(r, c), r, c));
		    }
		}
	    }
	}
	this.pack();
    }

    class Equipper implements ActionListener
    	{
    	    private Gear gear;

    	    public Equipper(Gear gear) {
    		this.gear = gear;
    	    }

    	    public void actionPerformed(ActionEvent e) {
    		player.equip(gear);
    	    }
    	}

    class User implements ItemListener
    {
	private Gear gear;
	private int y;
	private int x;

	public User(Gear gear, int y, int x) {
	    this.gear = gear;
	    this.y = y;
	    this.x = x;
	}

	public void itemStateChanged(ItemEvent e) {

	    if (e.getStateChange() == ItemEvent.SELECTED) {
		if (backpack.getGear(y, x) != Gear.NOTHING) {
		    backpack.remove(y, x);
		    if (gear.getType() == "Heal") {
			player.heal(gear.getHealth());
			System.out.println(player.getHealth());
		    }
		}
	    }
	}
    }
}
