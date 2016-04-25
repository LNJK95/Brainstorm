package brainstorm;

public class Human extends Enemy
{
   public Human(int level) {
       super(level);
       maxHealth = 10 + level;
       defence = 8 + level;
       attack = 12 + level;
       speed = 12 + level;
       cHealth = maxHealth;
    }

    public String toString() {
	return "Human";
    }
}
