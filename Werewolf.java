package brainstorm;

public class Werewolf extends Enemy
{
   public Werewolf(int level) {
       super(level);
       maxHealth = 20 + level;
       defence = 8 + level;
       attack = 12 + level;
       speed = 8 + level;
       cHealth = maxHealth;
    }

    public String toString() {
	return "Werewolf";
    }
}
