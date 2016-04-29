package brainstorm;

/** The Gear enum contains all the possible
 * types of different gear availible to win
 * or pick up in the game.*/

public enum Gear
{
    //ATTACK, DEFENCE, SPEED, HEALTH, GEAR TYPE, WHO DOES IT HELP TO FLIRT?
    /** Pancakes are a type of gear.*/
    PANCAKES(10, 10, -5, 0, "Headgear", null),
    /** Kitty-slippers are a type of gear. It helps you flirt with Werewolves.*/
    KITTY_SLIPPERS(0, 0, 10, 0, "Footgear", "Werewolf"),
    /** Lipstick is a type of gear. It helps you flirt with Mermaids.*/
    LIPSTICK(2, 0, 0, 0, "Weapon", "Mermaid"),
    /** Oven mitt is a type of gear. It helps you flirt with Aliens.*/
    OVEN_MITT(0, 3, 0, 0, "Weapon", "Alien"),
    /** Helmet is a type of gear. It helps you flirt with Humans.*/
    HELMET(0, 5, 0, 0, "Headgear", "Human"),
    /** Glitter lotion is a type of gear. It helps you flirt with Vampires.*/
    GLITTER_LOTION(0, 1, 0, 0, "Armour", "Vampire"),
    /** Lost arm is a type of gear.*/
    LOST_ARM(15, 0, -2, 0, "Weapon", null),
    /** Roller blades are a type of gear.*/
    ROLLER_BLADES(0, 0, 100, 0, "Footgear", null),
    /** Pizza-slicer is a type of gear.*/
    PIZZA_SLICER(20, 0, 0, 0, "Weapon", null),
    /** Skateboard is a type of gear.*/
    SKATEBOARD(0, 0, 90, 0, "Footgear", null),
    /** Gun is a type of gear.*/
    GUN(10000, 0, 0, 0, "Weapon", null),
    /** Chainsaw is a type of gear.*/
    CHAINSAW(2, 0, 0, 0, "Weapon", null),
    /** Egg carton is a type of gear.*/
    EGG_CARTON(15, 0, 0, 0, "Weapon", null),
    /** Uggs are a type of gear.*/
    UGGS(0, 30, 4, 0, "Footgear", null),
    /** Leather jacket is a type of gear.*/
    LEATHER_JACKET(0, 8, 0, 0, "Armour", null),
    /** Headphones are a type of gear.*/
    HEADPHONES(0, 3, 10, 0, "Headgear", null),
    /** Brains are a type of one-use item.*/
    BRAINS(0, 0, 0, 20, "Heal", null),
    /** Nothing is a gear placeholder.*/
    NOTHING(0,0,0,0,null, null)
    ;

    private final int attack;
    private final int defence;
    private final int speed;
    private final int health;
    private final String type;
    private final String flirt;

    private Gear(final int attack, final int defence, final int speed, final int health, final String type, final String flirt) {
	this.attack = attack;
	this.defence = defence;
	this.speed = speed;
	this.health = health;
	this.type = type;
	this.flirt = flirt;
    }

    public int getAttack() {
	return attack;
    }

    public int getDefence() {
	return defence;
    }

    public int getSpeed() {
	return speed;
    }

    public int getHealth() {
	return health;
    }

    public String getType() {
	return type;
    }

    public String getFlirt() {
	return flirt;
    }
}
