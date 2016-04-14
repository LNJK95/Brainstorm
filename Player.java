package brainstorm;

public class Player
{
    private String state;
    private int health;
    private int speed;
    private int level;
    private int defence;
    private int attack;
    private int xp;
    private int maxHealth;

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
	return speed;
    }

    public int getLevel() {
	return level;
    }

    public int getDefence() {
	return defence;
    }

    public int getAttack() {
	return attack;
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
