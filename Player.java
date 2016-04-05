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

    public Player() {
        state = "map";
	health = 10;
	defence = 10;
	speed = 10;
	attack = 10;
	level = 1;
	xp = 0;
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

    public int getXp() {
	return xp;
    }

    public void setXp(final int xp) {
	this.xp = xp;
    }
}
