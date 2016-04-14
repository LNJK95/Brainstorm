package brainstorm;

public class Vampire extends Enemy
{
   public Vampire(int level) {
       super(level);
       maxHealth = 20 + level;
       defence = 8 + level;
       attack = 12 + level;
       speed = 8 + level;
       cHealth = maxHealth;
    }

    public String toString() {
	return "Vampire" + "\n" + "Health: " + cHealth + "\n";
    }
}
