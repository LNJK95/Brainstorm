package brainstorm;

public abstract class Enemy
{
    protected int maxHealth;
    protected int cHealth;
    protected int speed;
    protected int level;
    protected int defence;
    protected int attack;
    protected int enemyY;
    protected int enemyX;
    protected int givesXp;

    public Enemy(int level) {
	this.level = level;
	givesXp = level*level;
    }


    public void setHealth(final int cHealth) {
	this.cHealth = cHealth;
    }

    public int getHealth() {
	return cHealth;
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

    public void setEnemyY(final int enemyY) {
	this.enemyY = enemyY;
    }

    public void setEnemyX(final int enemyX) {
	this.enemyX = enemyX;
    }

    public void setCoords(int y, int x) {
	this.enemyY = y;
	this.enemyX = x;
    }

    public int getGivesXp() {
	return givesXp;
    }
}
