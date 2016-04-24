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

	JPanel up = new JPanel();
	JPanel down = new JPanel();
	JPanel brainz = new JPanel();
	JPanel gear = new JPanel();
	this.add(brainz, BorderLayout.LINE_START);
	up.setLayout(new BorderLayout());
	this.add(gear, BorderLayout.LINE_END);
	down.setLayout(new BorderLayout());
	gear.add(up, BorderLayout.PAGE_START);
	gear.add(down, BorderLayout.PAGE_END);

	JPanel headgear = new JPanel();
	JPanel footgear = new JPanel();
	JPanel armour = new JPanel();
	JPanel weapons = new JPanel();

	up.add(headgear, BorderLayout.LINE_START);
	up.add(footgear, BorderLayout.LINE_END);
	down.add(armour, BorderLayout.LINE_START);
	down.add(weapons, BorderLayout.LINE_END);

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
			abc.addItemListener(new User(backpack.getGear(r, c), r, c, this, abc));
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
	private BackpackFrame bpf;
	private JCheckBoxMenuItem cbmi;

	public User(Gear gear, int y, int x, BackpackFrame bpf, JCheckBoxMenuItem cbmi) {
	    this.gear = gear;
	    this.y = y;
	    this.x = x;
	    this.bpf = bpf;
	    this.cbmi = cbmi;
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
	    if (e.getStateChange() == ItemEvent.DESELECTED) {
		bpf.remove(cbmi);
	    }
	}
    }
}
