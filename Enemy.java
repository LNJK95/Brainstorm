package brainstorm;

/** Enemy is the class all different types of
 * enemies are based on. It contains basic methods
 * that all different enemies have use for.*/

public abstract class Enemy
{
    protected int maxHealth;
    protected int health;
    protected int speed;
    protected int level;
    protected int defence;
    protected int attack;
    protected int enemyY;
    protected int enemyX;
    protected int givesXp;

    protected Enemy(int level) {
	this.level = level;
	givesXp = level*level;
    }

    protected abstract void setStats();

    public abstract String toString();

    public void setHealth(final int health) {
	this.health = health;
    }

    public int getHealth() {
	return health;
    }

    public int getSpeed() {
	return speed;
    }

    public int getDefence() {
	return defence;
    }

    public int getAttack() {
	return attack;
    }

    public int getEnemyY() {
	return enemyY;
    }

    public int getEnemyX() {
	return enemyX;
    }

    public void setCoords(int y, int x) {
	this.enemyY = y;
	this.enemyX = x;
    }

    public int getGivesXp() {
	return givesXp;
    }
}
