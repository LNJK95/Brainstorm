package brainstorm;

public class Human extends Enemy
{

    public Human(int level) {
	super(level);
	int humanHealth = getHealth() + level;
	int humanDefence = getDefence() + level;
	int humanAttack = getAttack() + level;
	int humanSpeed = getSpeed() + level;
    }
}
