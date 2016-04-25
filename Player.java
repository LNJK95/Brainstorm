package brainstorm;

import java.util.ArrayList;
import java.util.List;

public class Player
{
    private static final int START_SPEED = 10;
    private static final int START_ATTACK = 10;
    private static final int START_DEFENSE = 10;

    private String state;
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

    private Enemy ally = new Dumbfuck(0);

    private List<Gear> equippedGear = new ArrayList<Gear>();

    public Player() {
	level = 1;
        state = "map";
	maxHealth = 10+level;
	health = maxHealth;
	defence = 10+level;
	speed = 15+level;
	attack = 10+level;
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

    public List<Gear> getEquippedGear() {
	return equippedGear;
    }

    public void gearUpdate() {
	int gearSpeed = headgear.getSpeed() + footgear.getSpeed() + armour.getSpeed() + weapon.getSpeed();
	int gearAttack = headgear.getAttack() + footgear.getAttack() + armour.getAttack() + weapon.getAttack() + ally.getAttack();
	int gearDefence = headgear.getDefence() + footgear.getDefence() + armour.getDefence() + weapon.getDefence() + ally.getDefence();

	this.gearSpeed = gearSpeed;
	this.gearAttack = gearAttack;
	this.gearDefence = gearDefence;
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

	System.out.println(headgear + " " + footgear + " " + armour + " " + weapon + " ;)");
    }

    public void deEquip(Gear gear) {
	if (gear.getType().equals("Headgear")) {
	    headgear = null;
	} else if (gear.getType().equals("Footgear")) {
	    footgear = null;
	} else if (gear.getType().equals("Armour")) {
	    armour = null;
	} else if (gear.getType().equals("Weapon")) {
	    weapon = null;
	}
	gearUpdate();
	updateEquip();
    }

    public int getMaxHealth() {
	return maxHealth;
    }

    public void setHealth(final int health) {
	this.health = health;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
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
	System.out.println("adds xp");
	if (xp >= 10*level) {
	    xp -= 10*level;
	    level++;
	    System.out.println("level up");
	}
    }

    public void heal(int heal) {
	health += heal;
	if (health > maxHealth) {
	    health = maxHealth;
	}
    }

    public int getXp() {
	return xp;
    }

    public void setXp(final int xp) {
	this.xp = xp;
    }

    public Enemy getAlly() {
	return ally;
    }

    public void setAlly(final Enemy ally) {
	this.ally = ally;
	gearUpdate();
    }

    public String toString() {
	return "Health: " + getHealth() + "\n"
		+ "Attack: " + getAttack() + "\n"
		+ "Defence: " + getDefence() + "\n"
		+ "Speed: " + getSpeed() + "\n";
    }
}
