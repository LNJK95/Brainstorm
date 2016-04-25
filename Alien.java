package brainstorm;

public class Alien extends Enemy
{
   public Alien(int level) {
       super(level);
       maxHealth = 20 + level;
       defence = 8 + level;
       attack = 12 + level;
       speed = 8 + level;
       cHealth = maxHealth;
    }

    public String toString() {
	return "Alien";
    }
}
