package brainstorm;

public abstract class Enemy
{
    private int health;
    private int speed;
    private int level;
    private int defence;
    private int attack;
    private int enemyY;
    private int enemyX;
    private int givesXp;

    public Enemy(int level) {
	this.level = level;
	health = 10;
	speed = 10;
	defence = 10;
	attack = 10;
	givesXp = level;
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
}
