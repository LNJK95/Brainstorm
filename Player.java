package brainstorm;

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

    private Gear headgear;
    private Gear footgear;
    private Gear armour;
    private Gear hand_1;
    private Gear hand_2;

    public Player() {
	level = 1;
        state = "map";
	maxHealth = 10+level;
	health = maxHealth;
	defence = 10+level;
	speed = 15+level;
	attack = 10+level;
	xp = 0;
    }

    public void gearUpdate() {
	int gearSpeed = headgear.getSpeed() + footgear.getSpeed() + armour.getSpeed() + hand_1.getSpeed() + hand_2.getSpeed();
	int gearAttack = headgear.getAttack() + footgear.getAttack() + armour.getAttack() + hand_1.getAttack() + hand_2.getAttack();
	int gearDefence = headgear.getDefence() + footgear.getDefence() + armour.getDefence() + hand_1.getDefence() + hand_2.getDefence();

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
	} else if (gear.getType().equals("1-hand")) {
	    if (hand_1 == null) {
		hand_1 = gear;
	    } else if (hand_2 == null) {
		hand_2 = gear;
	    } else {
		hand_1 = gear;
	    }
	} else if (gear.getType().equals("2-hand")) {
	    hand_1 = gear;
	    hand_2 = Gear.HAND;
	}
	gearUpdate();
    }

    public void deEquip(Gear gear) {
	if (gear.getType().equals("Headgear")) {
	    headgear = null;
	} else if (gear.getType().equals("Footgear")) {
	    footgear = null;
	} else if (gear.getType().equals("Armour")) {
	    armour = null;
	} else if (gear.getType().equals("1-hand")) {
	    if (hand_1 == gear) {
		hand_1 = null;
	    } else if (hand_2 == gear) {
		hand_2 = null;
	    }
	} else if (gear.getType().equals("2-hand")) {
	    hand_1 = null;
	    hand_2 = null;
	}
	gearUpdate();
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

    public int getXp() {
	return xp;
    }

    public void setXp(final int xp) {
	this.xp = xp;
    }

    public String toString() {
	return "Health: " + health + "\n"
		+ "Attack: " + attack + "\n"
		+ "Defence: " + defence + "\n"
		+ "Speed: " + speed + "\n";
    }
}
