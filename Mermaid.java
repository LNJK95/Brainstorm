package brainstorm;

public class Mermaid extends Enemy
{
   public Mermaid(int level) {
       super(level);
       maxHealth = 20 + level;
       defence = 8 + level;
       attack = 12 + level;
       speed = 8 + level;
       cHealth = maxHealth;
    }

    public String toString() {
	return "Mermaid" + "\n" + "Health: " + cHealth + "\n";
    }
}
