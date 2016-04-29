package brainstorm;

import java.util.ArrayList;
import java.util.Collection;

/** Player is the playable character that you control. */

public final class Player extends ListenedTo
{
    //Stats for the start of the game.
    private static final int START_SPEED = 10;
    private static final int START_ATTACK = 10;
    private static final int START_DEFENSE = 10;
    private static final int START_HEALTH = 10;

    private FrameState state;
    private int health;
    private int speed;
    private int level;
    private int defence;
    private int attack;
    private int xp;
    private int maxHealth;
    private int gearSpeed;
    private int gearAttack;
    private int gearDefence;

    private Gear headgear = Gear.NOTHING;
    private Gear footgear = Gear.NOTHING;
    private Gear armour = Gear.NOTHING;
    private Gear weapon = Gear.NOTHING;

    private Enemy ally = new Nobody(0);

    private Collection<Gear> equippedGear = new ArrayList<Gear>();

    public Player() {
	level = 1;
        state = FrameState.MAP;
	maxHealth = START_HEALTH+level;
	health = maxHealth;
	defence = START_DEFENSE+level;
	speed = START_SPEED+level;
	attack = START_ATTACK+level;
	xp = 0;
	addEquip();
    }

    public void addEquip() {
	equippedGear.add(headgear);
	equippedGear.add(footgear);
	equippedGear.add(armour);
	equippedGear.add(weapon);
    }

    public void updateEquip() {
	equippedGear.clear();
	addEquip();
    }

    public Iterable<Gear> getEquippedGear() {
	return equippedGear;
    }

    public void gearUpdate() {
	int gearSpeed = headgear.getSpeed() + footgear.getSpeed() + armour.getSpeed() + weapon.getSpeed();
	int gearAttack = headgear.getAttack() + footgear.getAttack() + armour.getAttack() + weapon.getAttack() + ally.getAttack();
	int gearDefence = headgear.getDefence() + footgear.getDefence() + armour.getDefence() + weapon.getDefence() + ally.getDefence();

	this.gearSpeed = gearSpeed;
	this.gearAttack = gearAttack;
	this.gearDefence = gearDefence;

	notifyListeners();
    }

    public void equip(Gear gear) {
	if (gear.getType().equals("Headgear")) {
	    headgear = gear;
	} else if (gear.getType().equals("Footgear")) {
	    footgear = gear;
	} else if (gear.getType().equals("Armour")) {
	    armour = gear;
	} else if (gear.getType().equals("Weapon")) {
	    weapon = gear;
	}
	gearUpdate();
	updateEquip();
    }

    public void deEquip(Gear gear) {
	if (gear.getType() != null) {
	    if (gear.getType().equals("Headgear")) {
		headgear = Gear.NOTHING;
	    } else if (gear.getType().equals("Footgear")) {
		footgear = Gear.NOTHING;
	    } else if (gear.getType().equals("Armour")) {
		armour = Gear.NOTHING;
	    } else if (gear.getType().equals("Weapon")) {
		weapon = Gear.NOTHING;
	    }
	}
	gearUpdate();
    }

    public int getMaxHealth() {
	return maxHealth;
    }

    public void setHealth(final int health) {
	this.health = health;
    }

    public FrameState getState() {
        return state;
    }

    public void setState(final FrameState state) {
	this.state = state;
    }

    public int getHealth() {
	return health;
    }

    public int getSpeed() {
	return speed + gearSpeed;
    }

    public int getLevel() {
	return level;
    }

    public int getDefence() {
	return defence + gearDefence;
    }

    public int getAttack() {
	return attack + gearAttack;
    }

    public void addExperience(Enemy enemy) {
	xp += enemy.getGivesXp();
	if (xp >= 10*level) {
	    xp -= 10*level;
	    level++;
	}
	notifyListeners();
    }

    public void heal(int heal) {
	health += heal;
	if (health > maxHealth) {
	    health = maxHealth;
	}
    }

    public Enemy getAlly() {
	return ally;
    }

    public void setAlly(final Enemy ally) {
	this.ally = ally;
	gearUpdate();
    }

}
