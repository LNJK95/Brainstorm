package brainstorm.enemies;

import brainstorm.Enemy;

/** Nobody is an Enemy placeholder.*/

public class Nobody extends Enemy
{
    public Nobody(int level) {
        super(level);
        setStats();
    }

    public void setStats() {
        maxHealth = 0;
        defence = 0;
        attack = 0;
        speed = 0;
        health = maxHealth;
    }

    @Override public String toString() {
    	return "Nobody";
    }
}
