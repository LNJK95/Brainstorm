package brainstorm.enemies;

import brainstorm.Enemy;

/** Alien is a type of enemy.*/

public class Alien extends Enemy
{
    private final static int BASE_HEALTH = 20;
    private final static int BASE_DEFENCE = 8;
    private final static int BASE_ATTACK = 12;
    private final static int BASE_SPEED = 8;

    public Alien(int level) {
	super(level);
	setStats();
    }

    public void setStats() {
	maxHealth = BASE_HEALTH + level;
	defence = BASE_DEFENCE + level;
	attack = BASE_ATTACK * level;
	speed = BASE_SPEED + level;
	health = maxHealth;
    }

    @Override public String toString() {
	return "Alien";
    }
}
