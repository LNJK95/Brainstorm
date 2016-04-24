package brainstorm;

public enum Gear
{
    //ATTACK, DEFENCE, SPEED, HEALTH, GEAR TYPE, DOES IT HELP W/ FLIRT?
    PANCAKES(10, 10, -5, 0, "Headgear", null),
    KITTY_SLIPPERS(0, 0, 10, 0, "Footgear", "Werewolf"),
    LIPSTICK(2, 0, 0, 0, "Weapon", "Mermaid"),
    OVEN_MITT(0, 3, 0, 0, "Weapon", "Alien"),
    HELMET(0, 5, 0, 0, "Headgear", "Human"),
    GLITTER_LOTION(0, 1, 0, 0, "Armour", "Vampire"),
    LOST_ARM(15, 0, -2, 0, "Weapon", null),
    ROLLER_BLADES(0, 0, 100, 0, "Footgear", null),
    PIZZA_SLICER(20, 0, 0, 0, "Weapon", null),
    SKATEBOARD(0, 0, 90, 0, "Footgear", null),
    GUN(10000, 0, 0, 0, "Weapon", null),
    CHAINSAW(2, 0, 0, 0, "Weapon", null),
    EGG_CARTON(15, 0, 0, 0, "Weapon", null),
    UGGS(0, 30, 4, 0, "Footgear", null),
    LEATHER_JACKET(0, 8, 0, 0, "Armour", null),
    HEADPHONES(0, 3, 10, 0, "Headgear", null),
    BRAINS(0, 0, 0, 20, "Heal", null),
    NOTHING(0,0,0,0,null, null)
    ;

    private int attack;
    private int defence;
    private int speed;
    private int health;
    private String type;
    private String flirt;

    Gear(final int attack, final int defence, final int speed, final int health, final String type, final String flirt) {
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
